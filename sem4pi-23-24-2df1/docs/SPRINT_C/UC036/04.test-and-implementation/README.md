# UC036 — As Customer Manager, I want to display all the data of an application.

# 4. Tests 

In this project, a Test-Driven Development (TDD) approach was used.

```java

```

**Test 1:** Check the validity of the password policy (with a valid password).

```java

@Test
public void testDisplayAllData() {
    JobApplication mockJobApplication = Mockito.mock(JobApplication.class);

    controller.displayAllData(mockJobApplication);

    Mockito.verify(mockJobApplicationService).displayApplicationData(mockJobApplication);
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class DisplayJobApplicationDataAction
```java
import eapli.framework.actions.Action;

public class DisplayJobApplicationDataAction implements Action {

    @Override
    public boolean execute() {
        return new DisplayJobApplicationDataUI().show();
    }
}
```

## Class DisplayJobApplicationDataUI
```java
import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.DisplayJobApplicationDataController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class DisplayJobApplicationDataUI extends AbstractUI {

    DisplayJobApplicationDataController controller = new DisplayJobApplicationDataController();
    final Iterable<JobOpening> jobOpenings = controller.jobOpenings();

    public boolean doShow() {
        SelectWidget<JobOpening> jobOpeningSelectWidget = new SelectWidget<>("Select a job opening: ", jobOpenings, new JobOpeningPrinter());
        jobOpeningSelectWidget.show();

        JobOpening selectedJobOpening = jobOpeningSelectWidget.selectedElement();
        Iterable<JobApplication> jobApplications = controller.jobApplicationsByJobOpening(selectedJobOpening);

        SelectWidget<JobApplication> jobApplicationSelectWidget = new SelectWidget<>("Select a job application: ", jobApplications, new JobApplicationPrinter());
        jobApplicationSelectWidget.show();

        JobApplication jobApplication = jobApplicationSelectWidget.selectedElement();

        controller.displayAllData(jobApplication);

        return true;
    }

    @Override
    public String headline() {
        return "Display Job Application Data";
    }

}
```

## Class DisplayJobApplicationDataController
```java
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.services.JobApplicationManagementService;
import eapli.base.services.JobOpeningManagementService;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

@UseCaseController
public class DisplayJobApplicationDataController {

    AuthorizationService authz = AuthzRegistry.authorizationService();
    JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(PersistenceContext.repositories().jobApplications());
    JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(PersistenceContext.repositories().jobOpenings());

    public Iterable<JobOpening> jobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningManagementService.findAllAvailable();
    }

    public Iterable<JobApplication> jobApplicationsByJobOpening(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public void displayAllData(JobApplication jobApplication) {
        jobApplicationManagementService.displayApplicationData(jobApplication);
    }
}
```

## Class JobApplicationManagementService
```java
public class JobApplicationManagementService {

    public void displayApplicationData(JobApplication jobApplication) {
        String path = "SCOMP/files/fileBotFiles/";
        String jobApplicationID = jobApplication.identity().toString();
        String candidateEmail = jobApplication.candidate().toString();
        String fullPath = path + jobApplicationID + "/" + candidateEmail + "/";

        String curriculumPath = jobApplication.curriculumVitae().cvFileName();
        List<String> candidateData = jobApplication.additionalData().files();

        List<String> allPaths = new ArrayList<>();

        for (String data : candidateData) {
            allPaths.add(fullPath + data);
        }

        allPaths.add(fullPath + curriculumPath);

        System.out.println("Displaying all data related to job application: " + jobApplicationID + "\n\n");

        for (String singlePath : allPaths) {
            System.out.println("----------------------------------------------------\n");
            readFileAndDisplay(singlePath);
            System.out.println("----------------------------------------------------\n");
        }

    }

    public static void readFileAndDisplay(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));

            System.out.println(content);
        } catch (IOException e) {
            // Handle the exception if the file is not found or can't be read
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
}
```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added. 

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.