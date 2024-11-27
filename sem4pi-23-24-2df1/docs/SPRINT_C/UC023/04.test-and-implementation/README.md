# UC023 — As Costumer Manager, I want to edit a job opening.

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

**Test 1:** Ensure that the user can edit the job opening title.

```java

@Test
public void testChangeJobTitleTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    Designation newDesignation = Designation.valueOf("New Title");

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeJobTitleTo(newDesignation, mockJobOpening);

    Mockito.verify(mockJobOpening).updateJobTitle(newDesignation);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```

**Test 2:** Ensure that the user can edit the job opening contract type.

```java
    @Test
public void testChangeContractTypeTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    ContractType newContractType = ContractType.FULL_TIME;

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeContractTypeTo(newContractType, mockJobOpening);

    Mockito.verify(mockJobOpening).updateContractType(newContractType);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```

**Test 3:** Ensure that the user can edit the job opening job mode.

```java
    @Test
public void testChangeJobModeTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    JobMode newJobMode = JobMode.REMOTE;

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeJobModeTo(newJobMode, mockJobOpening);

    Mockito.verify(mockJobOpening).updateJobMode(newJobMode);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```

**Test 4:** Ensure that the user can edit the job opening job description.

```java
    @Test
public void testChangeJobDescriptionTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    Description newDescription = Description.valueOf("New Job Description");

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeJobDescriptionTo(newDescription, mockJobOpening);

    Mockito.verify(mockJobOpening).updateJobDescription(newDescription);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```

**Test 5:** Ensure that the user can edit the job opening address.

```java
    @Test
public void testChangeAddressTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    Address newAddress = Mockito.mock(Address.class);

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeAddressTo(newAddress, mockJobOpening);

    Mockito.verify(mockJobOpening).updateAddress(newAddress);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```

**Test 6:** Ensure that the user can edit the job opening number of vacancies.

```java
    @Test
public void testChangeNumberOfVacanciesTo() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    NumberOfVacancies newNumberOfVacancies = NumberOfVacancies.valueOf(5);

    Mockito.doNothing().when(mockAuthz).ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    Mockito.when(mockService.saveToRepository(mockJobOpening)).thenReturn(mockJobOpening);

    controller.changeNumberOfVacanciesTo(newNumberOfVacancies, mockJobOpening);

    Mockito.verify(mockJobOpening).updateNumberOfVacancies(newNumberOfVacancies);
    Mockito.verify(mockService).saveToRepository(mockJobOpening);
}
```


# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class EditJobOpeningAction

```java
import eapli.framework.actions.Action;

public class EditJobOpeningAction implements Action {

    @Override
    public boolean execute() {
        return new EditJobOpeningUI().show();
    }
}
```

## Class EditJobOpeningUI

```java
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.address.*;
import eapli.base.clientusermanagement.domain.jobopening.ContractType;
import eapli.base.clientusermanagement.domain.jobopening.JobMode;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.usermanagement.application.EditJobOpeningController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.List;

public class EditJobOpeningUI extends AbstractUI {

    EditJobOpeningController controller = new EditJobOpeningController();
    final Iterable<JobOpening> jobOpenings = controller.activeJobOpenings();

    final Iterable<JobMode> jobModes = List.of(JobMode.values());
    final Iterable<ContractType> contractTypes = List.of(ContractType.values());
    final Iterable<ApprovalStatus> approvalStatuses = List.of(ApprovalStatus.values());
    final Iterable<District> districts = List.of(District.values());
    final Iterable<String> sections = List.of("Edit Job Title",
            "Edit Contract Type",
            "Edit Job Mode",
            "Edit Job Description",
            "Edit Address",
            "Edit Number of Vacancies"
            );

    SelectWidget<JobOpening> jobOpeningSelectWidget;
    SelectWidget<ContractType> contractTypeSelectWidget;
    SelectWidget<JobMode> jobModeSelectWidget;
    SelectWidget<ApprovalStatus> approvalStatusSelectWidget;
    SelectWidget<String> optionSelector;
    SelectWidget<District> districtSelectWidget;

    @Override
    protected boolean doShow() {
        buildSelectors();
        System.out.printf("# %-15s%-30s%-15s%-12s%-45s%-20s%-15s%-30s%n", "Job Reference", "Title", "Contract Type", "Job Mode", "Address", "Customer", "Vacancies", "Description");

        jobOpeningSelectWidget.show();
        final JobOpening theJobOpening = jobOpeningSelectWidget.selectedElement();

        optionSelector.show();
        int option = optionSelector.selectedOption();

        switch (option) {
            case (1):
                Designation jobTitle = Designation.valueOf(Console.readLine("Input new job title:"));

                controller.changeJobTitleTo(jobTitle, theJobOpening);
            case (2):
                contractTypeSelectWidget.show();
                ContractType contractType = contractTypeSelectWidget.selectedElement();

                controller.changeContractTypeTo(contractType, theJobOpening);
            case (3):
                jobModeSelectWidget.show();
                JobMode jobMode = jobModeSelectWidget.selectedElement();

                controller.changeJobModeTo(jobMode, theJobOpening);
            case (4):
                Description jobDescription = Description.valueOf(Console.readLine("Input a new job description:"));

                controller.changeJobDescriptionTo(jobDescription, theJobOpening);
            case (5):
                districtSelectWidget.show();
                District district = districtSelectWidget.selectedElement();
                County county = County.valueOf(Console.readLine("Input the District name"));
                Parish parish = Parish.valueOf(Console.readLine("Input the Parish name"));
                Street street = Street.valueOf(Console.readLine("Input Street name"));
                System.out.println("Choose a Door Number");
                DoorNumber doorNumber = DoorNumber.valueOf(Console.readOption(0, Integer.MAX_VALUE, -1));
                PostalCode postalCode = PostalCode.valueOf(Console.readLine("Input the Postal Code (XXXX-XXX)"));

                Address address = new Address(district, county, parish, street, doorNumber, postalCode);

                controller.changeAddressTo(address, theJobOpening);
            case (6):
                NumberOfVacancies numberOfVacancies = NumberOfVacancies.valueOf(Console.readInteger("Input a new number of vacancies"));

                controller.changeNumberOfVacanciesTo(numberOfVacancies, theJobOpening);
            
        }

        return true;
    }

    private void buildSelectors() {
        optionSelector = new SelectWidget<>("Choose a section to edit", sections);
        jobOpeningSelectWidget = new SelectWidget<>("Choose a job opening", jobOpenings, new JobOpeningPrinter());
        contractTypeSelectWidget = new SelectWidget<>("Choose a new contract type", contractTypes);
        jobModeSelectWidget = new SelectWidget<>("Choose a new job mode", jobModes);
        approvalStatusSelectWidget = new SelectWidget<>("Choose a new approval status", approvalStatuses);
        districtSelectWidget = new SelectWidget<>("Choose a district", districts);
    }

    @Override
    public String headline() {
        return "Edit Job Opening";
    }
}
```

## Class EditJobOpeningController

```java
import eapli.base.clientusermanagement.domain.ApprovalStatus;
import eapli.base.clientusermanagement.domain.address.Address;
import eapli.base.clientusermanagement.domain.jobopening.ContractType;
import eapli.base.clientusermanagement.domain.jobopening.JobMode;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobOpeningRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.domain.NumberOfVacancies;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.general.domain.model.Description;
import eapli.framework.general.domain.model.Designation;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class EditJobOpeningController {
    private final JobOpeningRepository jobOpeningRepo = PersistenceContext.repositories().jobOpenings();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final JobOpeningManagementService service = new JobOpeningManagementService(jobOpeningRepo);

    public Iterable<JobOpening> activeJobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return service.findAllAvailable();
    }

    public JobOpening changeJobTitleTo(Designation designation, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobTitle(designation);

        return service.saveToRepository(jobOpening);
    }

    public JobOpening changeContractTypeTo(ContractType contractType, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateContractType(contractType);

        return service.saveToRepository(jobOpening);
    }

    public JobOpening changeJobModeTo(JobMode jobMode, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobMode(jobMode);

        return service.saveToRepository(jobOpening);
    }

    public JobOpening changeJobDescriptionTo(Description description, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateJobDescription(description);

        return service.saveToRepository(jobOpening);
    }

    public JobOpening changeAddressTo(Address address, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateAddress(address);

        return service.saveToRepository(jobOpening);
    }

    public JobOpening changeNumberOfVacanciesTo(NumberOfVacancies numberOfVacancies, JobOpening jobOpening) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        jobOpening.updateNumberOfVacancies(numberOfVacancies);

        return service.saveToRepository(jobOpening);
    }
    
}
```

## Class JobOpeningManagementService

```java
public class JobOpeningManagementService {

    public JobOpening saveToRepository(JobOpening jobOpening) {
        if (jobOpeningRepo.containsOfIdentity(jobOpening.identity())) {
            jobOpeningRepo.removeOfIdentity(jobOpening.identity());
        }
        return jobOpeningRepo.save(jobOpening);
    }

}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.