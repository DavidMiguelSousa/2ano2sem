package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.authz.ListUsersUI;
import eapli.base.app.backoffice.console.presentation.printer.CandidatePrinter;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.counter.domain.TopWords;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;

import java.util.ArrayList;
import java.util.List;

public class ListPersonalDataCandidateUI extends ListUsersUI {

    private final ListUsersController theController = new ListUsersController();
    private final List<Candidate> candidates = new ArrayList<>();

    @Override
    public boolean doShow() {
        super.doShow();
//        displayTopWords(candidates);
        return true;
    }

    @Override
    public String headline() {
        return "List Candidate's Personal Data";
    }

    @Override
    protected Iterable<SystemUser> elements() {
        Iterable<Candidate> allCandidates = theController.findCandidates();
        List<Candidate> allCandidatesAsList = new ArrayList<>();
        allCandidates.forEach(allCandidatesAsList::add);
        boolean stop = false;
        while (!stop) {
            SelectWidget<Candidate> selector = new SelectWidget<>("Select a Candidate", allCandidatesAsList, new CandidatePrinter());
            selector.show();
            Candidate candidate = selector.selectedElement();
            candidates.add(candidate);
            allCandidatesAsList.remove(candidate);
            if (allCandidatesAsList.isEmpty() || !Console.readBoolean("Do you want to select another candidate? (Y/N)")) {
                stop = true;
            }
        }
        List<SystemUser> users = new ArrayList<>();
        for (Candidate c : candidates) {
            users.add(c.user());
        }

        return users;
    }

//    private void displayTopWords(List<Candidate> candidates) {
//        System.out.println("Displaying top 20 words for each job application of each candidate selected.");
//        for (Candidate candidate : candidates) {
//            System.out.println("\nCandidate: " + candidate.name());
//            for (JobApplication jobApplication : theController.applicationByCandidate(candidate)) {
//                TopWords topWords = theController.countTopWords(jobApplication);
//                if (topWords.wordCounters().isEmpty()) {
//                    System.out.println("\tNo words found for " + candidate.name() + "'s files in his/her job application to job opening " + jobApplication.jobOpening().identity() + ".");
//                    continue;
//                }
//                System.out.println("\tTop " + topWords.wordCounters().size() + " words for " + candidate.name() + "'s files in his/her job application to job opening " + jobApplication.jobOpening().identity() + ":");
//                for (int i = 0; i < topWords.wordCounters().size(); i++) {
//                    WordAggregator wordAggregator = theController.filesWhereWordIsPresent(topWords.wordCounters().get(i).word());
//                    System.out.printf("\t\t%dº \"%s\": %dx -> %s\n", i+1, wordAggregator.word(), wordAggregator.totalOccurrences(), wordAggregator.filesToString());
//                }
//            }
//        }
//    }

}