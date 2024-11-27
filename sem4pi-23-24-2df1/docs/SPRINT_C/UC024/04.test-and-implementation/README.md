# UC024 - As Operator, I want to enable candidates

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
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

}
```

**Test 1:** Check only inactive candidates are listed.

```java
class CandidateManagementServiceTest {

    @Test
    void testListInactiveCandidates() {
        List<Candidate> inactiveCandidates = (List<Candidate>) service.inactiveCandidates();

        assertEquals(2, inactiveCandidates.size());
        assertTrue(inactiveCandidates.contains(candidate1));
        assertFalse(inactiveCandidates.contains(candidate2));
        assertTrue(inactiveCandidates.contains(candidate3));
    }
  
}
```

**Test 2:** Check no data is listed when there are no inactive users.

```java
class CandidateManagementServiceTest {

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
  
}
```

**Test 3:** Check enable candidate success.

```java
class CandidateManagementServiceTest {

    @Test
    void enableInactiveCandidateSuccessTest() {
        Candidate result = service.enableCandidate(candidate1);
        assertTrue(result.isActive());
    }
  
}
```

**Test 4:** Check activate already active user insuccess.

```java
class CandidateManagementServiceTest {

    @Test
    void enableActiveCandidateUserInsuccessTest() {
        assertThrows(IntegrityViolationException.class, () -> {
            service.enableCandidate(candidate2);
        });
    }
  
}
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class EnableCandidateAction

```java
@Override
public boolean execute () {
  return new EnableCandidateUI().show();
}
```

## Class EnableCandidateUI

```java
@Override
protected boolean doShow() {
  final Iterable<Candidate> iterable = this.theController.inactiveCandidates();
  final List<Candidate> inactiveCandidates = new ArrayList<>();
  iterable.forEach(inactiveCandidates::add);
  if (inactiveCandidates.isEmpty()) {
    System.out.println("There are no registered inactive candidates.");
  } else {
    SelectWidget<Candidate> selector = new SelectWidget<>("Select Candidate to Enable", inactiveCandidates, new CandidatePrinter());
    selector.show();
    int option = selector.selectedOption();
    if (option == 0) {
      System.out.println("No candidate selected");
    } else {
      try {
        this.theController.enableCandidate(selector.selectedElement());
      } catch (IntegrityViolationException | ConcurrencyException ex) {
        LOGGER.error("Error performing the operation", ex);
        System.out.println(
                "Unfortunatelly there was an unexpected error in the application. Please try again and if the problem persists, contact your system admnistrator.");
      }
    }
  }
  return true;
}
```

## Class EnableCandidateController

```java
public Candidate enableCandidate(final Candidate candidate) {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

  return candidateSvc.enableCandidate(candidate);
}
```

## Class CandidateManagementService

```java
@Transactional
public Candidate enableCandidate(final Candidate candidate) {
    if (candidate == null) {
        throw new IllegalArgumentException("Candidate doesn't exist.");
    }

    if (candidate.isActive()) {
        throw new IntegrityViolationException("Candidate is already 3active.");
    } else {
        candidate.activate();
    }

    return candidateRepository.save(candidate);
}
```

# 6. Integration and Demo

* A new option in the operator users menu was added. However, to enable _inactive candidates_, the authenticated user must have operator permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.