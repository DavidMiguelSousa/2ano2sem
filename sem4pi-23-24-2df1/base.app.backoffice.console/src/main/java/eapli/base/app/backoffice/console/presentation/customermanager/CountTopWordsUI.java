package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.base.counter.application.CountTopWordsController;
import eapli.base.counter.domain.WordAggregator;
import eapli.base.counter.domain.WordCounter;

import java.io.File;
import java.util.List;

public class CountTopWordsUI extends AbstractUI {

    private final CountTopWordsController controller = new CountTopWordsController();

    public boolean doShow() {
        String jobOpeningSelectionString = "Select a Job Opening:\n" +
                String.format("%-15s %-30s %-15s %-12s %-45s %-40s %-15s %-30s", "Job Reference", "Job Title", "Contract Type", "Job Mode", "Address", "Customer", "Number of Vacancies", "Job Description");
        SelectWidget<JobOpening> selectorJobOpening = new SelectWidget<>(jobOpeningSelectionString, controller.jobOpenings(), new JobOpeningPrinter());
        selectorJobOpening.show();
        JobOpening jobOpening = selectorJobOpening.selectedElement();

        if (jobOpening == null) {
            System.out.println("Invalid Job Opening.");
            return false;
        }

        String jobApplicationSelectionString = "Select a Job Application:\n" +
                String.format("%-30s %-30s %-30s %-30s", "Job Application Reference", "Application Status", "Date of Submission", "Job Opening Reference");
        SelectWidget<JobApplication> selectorJobApplication = new SelectWidget<>(jobApplicationSelectionString, controller.jobApplicationsOf(jobOpening), new JobApplicationPrinter());
        selectorJobApplication.show();
        JobApplication jobApplication = selectorJobApplication.selectedElement();

        if (jobApplication == null) {
            System.out.println("Invalid Job Application.");
            return false;
        }

        List<File> jobApplicationFiles = controller.files(jobApplication, jobOpening);

        if (jobApplicationFiles.isEmpty()) {
            System.out.println("There are no files in the specified directory related to the selected application.");
            return false;
        }

        displayTopWords(jobApplicationFiles);

        boolean searchAgain = Console.readBoolean("Do you want to check in which files a specific word is present? (Y/N)");
        while (searchAgain) {
            String word = Console.readNonEmptyLine("Enter the word you want to search for:", "Invalid word.");
            WordAggregator wordAggregator = controller.filesWhereWordIsPresent(word);
            System.out.println(wordAggregator.toString());
            searchAgain = Console.readBoolean("Do you want to search for another word? (Y/N)");
        }

        return true;
    }

    private void displayTopWords(List<File> jobApplicationFiles) {
        List<WordCounter> wordCounters = controller.countTopWords(jobApplicationFiles).wordCounters();

        for (int i = 1; i <= wordCounters.size(); i++) {
            WordAggregator wordAggregator = controller.filesWhereWordIsPresent(wordCounters.get(i-1).word());
            System.out.printf("%dº \"%s\": %dx -> %s\n", i, wordAggregator.word(), wordAggregator.totalOccurrences(), wordAggregator.filesToString());
        }
    }

    @Override
    public String headline() {
        return "Top 20 Words from Candidate's Files";
    }
}