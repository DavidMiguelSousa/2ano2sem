package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.usermanagement.application.ListJobOpeningsInterviewGradesController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class ListJobOpeningsInterviewGradesUI extends AbstractUI {
    private final ListJobOpeningsInterviewGradesController controller = new ListJobOpeningsInterviewGradesController();

    public boolean doShow() {
        Iterable<JobOpening> allJobOpenings = controller.jobOpeningsWithPhaseInterviewCompleted();
        List<JobOpening> jobOpenings = new ArrayList<>();
        allJobOpenings.forEach(jobOpenings::add);

        if (jobOpenings.isEmpty()) {
            System.out.println("No job openings with interview phase completed.\n");
            return false;
        }

        SelectWidget<JobOpening> selector = new SelectWidget<>("Select a Job Opening", jobOpenings, new JobOpeningPrinter());
        selector.show();
        JobOpening jobOpening = selector.selectedElement();

        Iterable<JobApplication> jobApplicationsOf = controller.jobApplicationsOf(jobOpening);
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplicationsOf.forEach(jobApplications::add);

        if (jobApplications.isEmpty()) {
            System.out.println("No job applications for that job opening were found.\nTry again later!\n");
            return false;
        }

        List<JobApplication> jobApplicationsFiltered = controller.filterJobApplicationsWithInterview(jobApplications);

        if (jobApplicationsFiltered.isEmpty()) {
            System.out.println("No job applications with interviews for that job opening were found.\nTry again later!\n");
            return false;
        }

        System.out.println("Job Opening's [" + jobOpening.jobReference() + "] Interview Grades:\n");
        for (JobApplication jobApplication : jobApplicationsFiltered) {
            System.out.println(jobApplication.candidate().identity() + " - " + jobApplication.interview().grade().grade());
        }

        Console.readLine("Press any key to exit...");

        return false;
    }

    @Override
    public String headline() {
        return "List Job Opening's Interview Grades";
    }
}
