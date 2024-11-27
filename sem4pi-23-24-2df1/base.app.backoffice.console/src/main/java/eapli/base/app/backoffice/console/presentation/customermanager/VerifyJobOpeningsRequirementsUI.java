package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.EvaluateJobOpeningsInterviewsController;
import eapli.base.usermanagement.application.VerifyJobOpeningsRequirementsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class VerifyJobOpeningsRequirementsUI extends AbstractUI {

    private final VerifyJobOpeningsRequirementsController controller = new VerifyJobOpeningsRequirementsController();

    public boolean doShow() {
        Iterable<JobOpening> jobOpenings = controller.jobOpeningsWithApplicationPhase();
        List<JobOpening> jobOpeningsList = new ArrayList<>();
        jobOpenings.forEach(jobOpeningsList::add);

        JobOpening jobOpening;

        do {
            SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpeningsList, new JobOpeningPrinter());
            selector.show();
            jobOpening = selector.selectedElement();

            if (selector.selectedOption() == 0) return true;

            if (jobOpening == null) System.out.println("Job Opening invalid! Try again.");
        } while (jobOpening == null);

        Iterable<JobApplication> jobApplicationsIterable = controller.jobApplicationsOf(jobOpening);
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplicationsIterable.forEach(jobApplications::add);

        List<JobApplication> requirements = new ArrayList<>();

        try {
            requirements = controller.verifyJobRequirements(jobApplications, jobOpening);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        if (requirements.isEmpty()) {
            System.out.println("Something went wrong!\n\nThere are no requirements to validate...");
            return false;
        }

        System.out.printf("Job Requirement's Status for Job Opening %s:\n\n", jobOpening.identity());
        for (JobApplication jobApplication : requirements) {
            System.out.println(jobApplication.identity() + " - " + jobApplication.status());
        }

        boolean save = Console.readBoolean("Do you want to save this Status? (Y/N)");

        if (save) controller.saveRequirements(requirements);
        else {
            System.out.println("Job Requirements Status will not be saved.");
            for (JobApplication jobApplication : requirements) {
                jobApplication.isPending();
            }
        }

        return false;
    }

    public String headline() {
        return "Check Requirements Job Opening's";
    }
}