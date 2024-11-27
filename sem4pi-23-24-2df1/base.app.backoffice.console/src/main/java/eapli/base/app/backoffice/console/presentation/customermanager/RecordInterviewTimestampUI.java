package eapli.base.app.backoffice.console.presentation.customermanager;

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
