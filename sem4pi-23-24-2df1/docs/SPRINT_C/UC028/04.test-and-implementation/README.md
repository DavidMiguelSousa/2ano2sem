# UC028 — As Customer Manager, I want to record the time and date for an interview with a candidate.

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

**Test 1:** Test job openings.

```java

@Test
public void testJobOpenings() {
    Iterable<JobOpening> mockJobOpenings = List.of(Mockito.mock(JobOpening.class));

    Mockito.when(mockJobOpeningService.findAllAvailable()).thenReturn(mockJobOpenings);

    Iterable<JobOpening> result = controller.jobOpenings();

    Mockito.verify(mockJobOpeningService).findAllAvailable();
}
```

**Test 2:** Test find job applications by job opening

```java

@Test
public void testFindJobApplicationsByJobOpening() {
    JobOpening mockJobOpening = Mockito.mock(JobOpening.class);
    Iterable<JobApplication> mockJobApplications = List.of(Mockito.mock(JobApplication.class));

    Mockito.when(mockJobApplicationService.jobApplicationsOf(mockJobOpening)).thenReturn(mockJobApplications);

    Iterable<JobApplication> result = controller.findJobApplicationsByJobOpening(mockJobOpening);

    Mockito.verify(mockJobApplicationService).jobApplicationsOf(mockJobOpening);
}
```

**Test 3:** Test record interview timestamp.
```java

@Test
public void testRecordInterviewTimestamp() {
    JobApplication mockJobApplication = Mockito.mock(JobApplication.class);
    Calendar mockTimestamp = Calendar.getInstance();

    Mockito.when(mockJobApplicationService.recordInterviewTimeStamp(mockJobApplication, mockTimestamp)).thenReturn(mockJobApplication);

    JobApplication result = controller.recordInterviewTimestamp(mockJobApplication, mockTimestamp);

    Mockito.verify(mockJobApplicationService).recordInterviewTimeStamp(mockJobApplication, mockTimestamp);
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class RecordInterviewTimestampAction

```java
import eapli.framework.actions.Action;

public class RecordInterviewTimestampAction implements Action {

    @Override
    public boolean execute() {
        return new RecordInterviewTimestampUI().doShow();
    }
}
```

## Class RecordInterviewTimestampUI

```java
import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.RecordInterviewTimestampController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.Calendar;

public class RecordInterviewTimestampUI extends AbstractUI {

    RecordInterviewTimestampController controller = new RecordInterviewTimestampController();

    @Override
    public boolean doShow() {

        SelectWidget<JobOpening> jobOpeningSelectWidget = new SelectWidget<>("Select job opening", controller.jobOpenings(), new JobOpeningPrinter());
        jobOpeningSelectWidget.show();

        JobOpening jobOpening = jobOpeningSelectWidget.selectedElement();

        Iterable<JobApplication> jobApplications = controller.findJobApplicationsByJobOpening(jobOpening);

        SelectWidget<JobApplication> jobApplicationSelectWidget = new SelectWidget<>("Select job application", jobApplications, new JobApplicationPrinter());
        jobApplicationSelectWidget.show();

        JobApplication jobApplication = jobApplicationSelectWidget.selectedElement();

        Calendar timestamp = Console.readCalendar("Insert the timestamp of the interview (dd-MM-yyyy HH:mm:ss): ", "dd-MM-yyyy HH:mm:ss");

        controller.recordInterviewTimestamp(jobApplication, timestamp);

        System.out.println("Interview timestamp recorded successfully!");
        return false;
    }

    @Override
    public String headline() {
        return "Record Interview Timestamp";
    }
}
```

## Class RecordInterviewTimestampController

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

import java.util.Calendar;

@UseCaseController
public class RecordInterviewTimestampController {

    AuthorizationService authz = AuthzRegistry.authorizationService();
    JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(PersistenceContext.repositories().jobOpenings());
    JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(PersistenceContext.repositories().jobApplications());

    public Iterable<JobOpening> jobOpenings() {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);

        return jobOpeningManagementService.findAllAvailable();
    }

    public Iterable<JobApplication> findJobApplicationsByJobOpening(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public JobApplication recordInterviewTimestamp(JobApplication jobApplication, Calendar timestamp) {
        return jobApplicationManagementService.recordInterviewTimeStamp(jobApplication, timestamp);
    }
}
```

## Class JobApplicationManagementService

```java
public class JobApplicationManagementService {

    public JobApplication recordInterviewTimeStamp(JobApplication jobApplication, Calendar timestamp) {
        jobApplication.recordInterviewTimestamp(timestamp);
        return jobApplicationRepo.save(jobApplication);
    }

}
```

# 6. Integration and Demo

* A new option in the backoffice users menu was added.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.