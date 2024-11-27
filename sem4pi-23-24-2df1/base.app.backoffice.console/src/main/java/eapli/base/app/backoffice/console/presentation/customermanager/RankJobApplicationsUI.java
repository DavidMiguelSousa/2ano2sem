package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.rank.RankStatus;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.rank.Rank;
import eapli.base.usermanagement.application.RankJobApplicationsController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankJobApplicationsUI extends AbstractUI {
    private final RankJobApplicationsController controller = new RankJobApplicationsController();

    @Override
    public String headline() {
        return "Rank Job Opening's Applications";
    }

    protected boolean doShow() {
        Iterable<JobOpening> jobOpenings = controller.jobOpeningsWithAnalysisPhaseOn();
        List<JobOpening> jobOpeningsList = new ArrayList<>();

        jobOpenings.forEach(jobOpeningsList::add);

        if (jobOpeningsList.isEmpty()) {
            System.out.print("No job openings with analysis phase on available.\n");
            return false;
        }

        JobOpening jobOpening;

        SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpeningsList, new JobOpeningPrinter());
        selector.show();
        jobOpening = selector.selectedElement();

        if (selector.selectedOption() == 0) return false;

        jobOpening.updateRanks(new ArrayList<>());

        int numberOfVacancies = jobOpening.numberOfVacancies().value();

        Iterable<JobApplication> jobApplications = controller.jobApplicationsOf(jobOpening);
        List<JobApplication> jobApplicationsList = new ArrayList<>();

        jobApplications.forEach(jobApplicationsList::add);

        Map<Rank, JobApplication> ranks = rankJobApplications(jobApplicationsList, numberOfVacancies);

        saveJobApplicationRanks(jobOpening, ranks, jobApplicationsList);

        for (Map.Entry<Rank, JobApplication> entry : ranks.entrySet()) {
            if(entry.getKey().status().equals(RankStatus.RANKED)){
                controller.sendEmailCandidateAccepted(entry.getKey(),entry.getValue(), jobOpening);
            }else{
                controller.sendEmailCandidateNotAccepted(entry.getValue(), jobOpening);
            }
        }

        controller.sendEmailCustomer(ranks, jobOpening);

        return false;
    }

    private Map<Rank, JobApplication> rankJobApplications(List<JobApplication> jobApplications, int numberOfVacancies) {
        Map<Rank, JobApplication> ranks = new HashMap<>();
        int position;
        Rank rank;

        do {
            SelectWidget<JobApplication> selector = new SelectWidget<>("Select Job Application", jobApplications, new JobApplicationPrinter());
            selector.show();
            JobApplication jobApplication = selector.selectedElement();

            position = Console.readInteger("Rank the candidate [0, 1, 2, ...]: ");

            rank = new Rank(position, RankStatus.RANKED);

            while (position < -1 || ranks.containsKey(rank)) {
                if (position < -1) System.out.println("\nInvalid rank. Please try again.\n");
                if (ranks.containsKey(rank)) System.out.println("\nRank already in use. Please try again.\n");
                position = Console.readInteger("Rank the candidate [0, 1, 2, ...]: ");
                rank = new Rank(position, RankStatus.RANKED);
            }

            ranks.put(rank, jobApplication);

            System.out.printf("Candidate %s ranked...\n", jobApplication.candidate().toString());

            jobApplications.remove(jobApplication);

        } while (ranks.size() < numberOfVacancies && !jobApplications.isEmpty());

        if (jobApplications.isEmpty()) {
            System.out.println("All candidates were ranked.\n");
        } else {
            System.out.println("Number of vacancies filled.\n");
        }

        return ranks;
    }

    private void saveJobApplicationRanks(JobOpening jobOpening, Map<Rank, JobApplication> ranks, List<JobApplication> jobApplications) {
        Rank notRanked = new Rank();

        for (JobApplication jobApplication : jobApplications) {
            if (!ranks.containsValue(jobApplication)) {
                controller.saveJobApplicationRank(jobApplication, notRanked);
                System.out.printf("Candidate %s rank saved...\n", jobApplication.candidate().toString());
            } else {
                for (Map.Entry<Rank, JobApplication> entry : ranks.entrySet()) {
                    if (entry.getValue().equals(jobApplication)) {
                        controller.updateJobOpeningRankList(jobApplication, jobOpening);
                        controller.saveJobApplicationRank(jobApplication, entry.getKey());
                        System.out.printf("Candidate %s rank saved...\n", jobApplication.candidate().toString());
                    }
                }
            }
        }
    }
}