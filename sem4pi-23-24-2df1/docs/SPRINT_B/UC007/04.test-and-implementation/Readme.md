# UC007 - As Customer Manager, I want to list job openings.

# 4. Tests

**Test 1:** Check printer.

```java
class JobOpeningTest {

  @Test
  public void testPrintJobOpening() {
    JobOpening jobOpening = createTestJobOpening();

    JobOpeningPrinter printer = new JobOpeningPrinter();

    printer.visit(jobOpening);
  }

  private JobOpening createTestJobOpening() {
    JobReference jobReference = new JobReference("DEF_456");
    String title = "Software Engineer";
    ContractType contractType = ContractType.FULL_TIME;
    JobMode modeWork = JobMode.REMOTE;
    String address = "123 Main St";
    final Set<Role> roles = new HashSet<>();
    roles.add(BaseRoles.CUSTOMER);

    Customer customer = new Customer();

    int vacancies = 5;
    String description = "Description";
    String requirements = "Requirements";

    Map<Phase, DateInterval> phases = new HashMap<>();
    JobOpening jobOpening = new JobOpening(jobReference, Designation.valueOf(title),contractType,modeWork, new Address(District.ACORES, County.valueOf("aaa"), Parish.valueOf("aaa"), Street.valueOf("aaa"), DoorNumber.valueOf(12), PostalCode.valueOf("4400-146")), customer, NumberOfVacancies.valueOf(vacancies), Description.valueOf(description),phases, interviewModel, jobRequirements);
    return jobOpening;
  }
  
}
```


# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class ListJobOpeningAction

```java
@Override
public boolean execute () {
  return new ListJobOpeningUI().show();
}
```

## Class ListJobOpeningUI

```java
@Override
protected Iterable<JobOpening> elements() {
    return theController.allJobOpening();
}
```

## Class AddJobOpeningController

```java
public Iterable<JobOpening> allJobOpening() {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

  return jobOpeningManagementService.findAllAvailable();
}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added. However, to list _active backoffice users_, the authenticated
  user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.