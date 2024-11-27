# UC025 - As Operator, I want to disable a candidate

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

**Test 1:** Check only active candidates are listed.

```java
class CandidateManagementServiceTest {

    @Test
    void testListActiveCandidates() {
        List<Candidate> activeCandidates = (List<Candidate>) service.activeCandidates();

        assertEquals(1, activeCandidates.size());
        assertFalse(activeCandidates.contains(candidate1));
        assertTrue(activeCandidates.contains(candidate2));
        assertFalse(activeCandidates.contains(candidate3));
    }
  
}
```

**Test 2:** Check no data is listed when there are no active users.

```java
class CandidateManagementServiceTest {

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
  
}
```

**Test 3:** Check disable candidate success.

```java
class CandidateManagementServiceTest {

    @Test
    void disableActiveCandidateSuccessTest() {
        Candidate result = service.disableCandidate(candidate2);
        assertFalse(result.isActive());
    }
  
}
```

**Test 4:** Check disable already disabled candidate insuccess.

```java
class CandidateManagementServiceTest {

    @Test
    void disableInactiveCandidateInsuccessTest() {
        assertThrows(IntegrityViolationException.class, () -> service.disableCandidate(candidate1));
    }
  
}
```

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class DisableCandidateAction

```java
@Override
public boolean execute () {
  return new DisableCandidateUI().show();
}
```

## Class DisableCandidateUI

```java
@Override
protected boolean doShow() {
  final Iterable<Candidate> iterable = this.theController.activeCandidates();
  final List<Candidate> activeCandidates = new ArrayList<>();
  iterable.forEach(activeCandidates::add);
  if (activeCandidates.isEmpty()) {
    System.out.println("There are no registered active candidates.");
  } else {
    SelectWidget<Candidate> selector = new SelectWidget<>("Select Candidate to Disable", activeCandidates, new CandidatePrinter());
    selector.show();
    int option = selector.selectedOption();
    if (option == 0) {
      System.out.println("No candidate selected");
    } else {
      try {
        this.theController.disableCandidate(selector.selectedElement());
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

## Class DisableCandidateController

```java
public Candidate disableCandidate(final Candidate candidate) {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER, BaseRoles.OPERATOR);

  return candidateSvc.disableCandidate(candidate);
}
```

## Class CandidateManagementService

```java
@Transactional
public Candidate disableCandidate(final Candidate candidate) {
  if (candidate == null) {
    throw new IllegalArgumentException("Candidate doesn't exist.");
  }

  if (!candidate.isActive()) {
    throw new IntegrityViolationException("Candidate is already inactive.");
  } else {
      candidate.deactivate();
  }

  return candidateRepository.save(candidate);
}
```

# 6. Integration and Demo

* A new option in the operator users menu was added. However, to disable _active candidates_, the authenticated user must have operator permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.