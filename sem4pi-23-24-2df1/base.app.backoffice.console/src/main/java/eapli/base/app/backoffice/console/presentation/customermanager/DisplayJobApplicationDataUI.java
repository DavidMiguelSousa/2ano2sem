package eapli.base.app.backoffice.console.presentation.customermanager;

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
