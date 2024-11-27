package eapli.base.services;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.candidatemanagement.domain.CandidateBuilder;
import eapli.base.candidatemanagement.repositories.CandidateRepository;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CandidateManagementServiceTest {

    @Mock
    private CandidateRepository candidateRepo;

    @InjectMocks
    private CandidateManagementService service;

    private Candidate candidate1;
    private Candidate candidate2;
    private Candidate candidate3;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        CandidateBuilder candidateBuilder = new CandidateBuilder();

        SystemUser user1 = userBuilder.withUsername("test1@domain.com")
                .withPassword("Password123!")
                .withName("Test", "Test")
                .withEmail("test1@domain.com")
                .build();

        SystemUser user2 = userBuilder.withUsername("test2@domain.com")
                .withPassword("Password123!")
                .withName("Name", "Name")
                .withEmail("test2@domain.com")
                .build();

        SystemUser user3 = userBuilder.withUsername("test3@domain.com")
                .withPassword("Password123!")
                .withName("Testname", "Testname")
                .withEmail("test3@domain.com")
                .build();

        candidate1 = candidateBuilder.with(user1).build();
        candidate2 = candidateBuilder.with(user2).build();
        candidate3 = candidateBuilder.with(user3).build();

        candidate1.deactivate();
        candidate3.deactivate();

        when(candidateRepo.save(candidate1)).thenReturn(candidate1);
        when(candidateRepo.save(candidate2)).thenReturn(candidate2);
        when(candidateRepo.save(candidate3)).thenReturn(candidate3);

        when(candidateRepo.findAll()).thenAnswer(invocation -> Arrays.asList(candidate1, candidate2, candidate3));

        when(candidateRepo.activeCandidates()).thenAnswer(invocation ->
                Stream.of(candidate1, candidate2, candidate3)
                        .filter(Candidate::isActive)
                        .collect(Collectors.toList())
        );

        when(candidateRepo.inactiveCandidates()).thenAnswer(invocation ->
                Stream.of(candidate1, candidate2, candidate3)
                        .filter(candidate -> !candidate.isActive())
                        .collect(Collectors.toList())
        );

        when(candidateRepo.ofIdentity(candidate1.identity())).thenReturn(Optional.of(candidate1));
        when(candidateRepo.ofIdentity(candidate2.identity())).thenReturn(Optional.of(candidate2));
        when(candidateRepo.ofIdentity(candidate3.identity())).thenReturn(Optional.of(candidate3));

        service = new CandidateManagementService(candidateRepo);
    }

    @Test
    void testListInactiveCandidates() {
        List<Candidate> inactiveCandidates = (List<Candidate>) service.inactiveCandidates();

        assertEquals(2, inactiveCandidates.size());
        assertTrue(inactiveCandidates.contains(candidate1));
        assertFalse(inactiveCandidates.contains(candidate2));
        assertTrue(inactiveCandidates.contains(candidate3));
    }

    @Test
    void noInactivesTest() {
        candidate1.activate();
        candidate3.activate();

        when(candidateRepo.inactiveCandidates()).thenAnswer(invocation ->
                Stream.of(candidate1, candidate2, candidate3)
                        .filter(candidate -> !candidate.isActive())
                        .collect(Collectors.toList())
        );

        List<Candidate> inactiveCandidates = (List<Candidate>) service.inactiveCandidates();

        assertTrue(inactiveCandidates.isEmpty());
    }

    @Test
    void enableInactiveCandidateSuccessTest() {
        Candidate result = service.enableCandidate(candidate1);
        assertTrue(result.isActive());
    }

    @Test
    void enableActiveCandidateInsuccessTest() {
        assertThrows(IntegrityViolationException.class, () -> {
            service.enableCandidate(candidate2);
        });
    }

    @Test
    void testListActiveCandidates() {
        List<Candidate> activeCandidates = (List<Candidate>) service.activeCandidates();

        assertEquals(1, activeCandidates.size());
        assertFalse(activeCandidates.contains(candidate1));
        assertTrue(activeCandidates.contains(candidate2));
        assertFalse(activeCandidates.contains(candidate3));
    }

    @Test
    void noActivesTest() {
        candidate2.deactivate();

        when(candidateRepo.activeCandidates()).thenAnswer(invocation ->
                Stream.of(candidate1, candidate2, candidate3)
                        .filter(Candidate::isActive)
                        .collect(Collectors.toList())
        );

        List<Candidate> activeCandidates = (List<Candidate>) service.activeCandidates();

        assertTrue(activeCandidates.isEmpty());
    }

    @Test
    void disableActiveCandidateSuccessTest() {
        Candidate result = service.disableCandidate(candidate2);
        assertFalse(result.isActive());
    }

    @Test
    void disableInactiveCandidateInsuccessTest() {
        assertThrows(IntegrityViolationException.class, () -> service.disableCandidate(candidate1));
    }

}