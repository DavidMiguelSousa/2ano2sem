package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.EvaluateJobOpeningsInterviewsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class EvaluateJobOpeningsInterviewsUI extends AbstractUI {

    private final EvaluateJobOpeningsInterviewsController controller = new EvaluateJobOpeningsInterviewsController();

    public boolean doShow() {
        List<JobOpening> jobOpenings = controller.jobOpeningsWithInterviewPhaseCompleted();
        JobOpening jobOpening;

        do {
            SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpenings, new JobOpeningPrinter());
            selector.show();
            jobOpening = selector.selectedElement();

            if (selector.selectedOption() == 0) return true;

            if (jobOpening == null) System.out.println("Job Opening invalid! Try again.");
        } while (jobOpening == null);

        Iterable<JobApplication> jobApplicationsIterable = controller.jobApplicationsOf(jobOpening);
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplicationsIterable.forEach(jobApplications::add);

        List<JobApplication> grades = new ArrayList<>();

        try {
            grades = controller.evaluateInterviews(jobApplications, jobOpening);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        if (grades.isEmpty()) {
            System.out.println("Something went wrong!\n\nThere are no interviews to evaluate...");
            return false;
        }

        System.out.printf("Interview's Grades for Job Opening %s:\n\n", jobOpening.identity());
        for (JobApplication jobApplication : grades) {
            System.out.println(jobApplication.identity() + " - " + jobApplication.interview().grade().grade());
        }

        boolean save = Console.readBoolean("Do you want to save this grades? (Y/N)");

        if (save) controller.saveGrades(grades);
        else {
            System.out.println("Grades will not be saved.");
            for (JobApplication jobApplication : grades) {
                jobApplication.interview().grade().reset();
            }
        }

        return false;
    }

    public String headline() {
        return "Evaluate Job Opening's Interviews";
    }
}