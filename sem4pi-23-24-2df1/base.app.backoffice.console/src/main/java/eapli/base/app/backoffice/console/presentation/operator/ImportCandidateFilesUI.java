package eapli.base.app.backoffice.console.presentation.operator;

import eapli.base.app.backoffice.console.presentation.printer.CandidatePrinter;
import eapli.base.app.backoffice.console.presentation.printer.FilePrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.services.ConsoleUtils;
import eapli.base.usermanagement.application.ImportCandidateFilesController;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ImportCandidateFilesUI {

    private final ImportCandidateFilesController theController = new ImportCandidateFilesController();
    private static final Path SHARED_FOLDER = Path.of("SCOMP/files/fileBotFiles");

    public boolean show() {
        System.out.println("Import Candidate Files");

        SelectWidget<JobOpening> jobOpeningWidget = new SelectWidget<>("Select the job opening", theController.jobOpeningsInApplicationPhase(), new JobOpeningPrinter());
        jobOpeningWidget.show();
        JobOpening jobOpening = jobOpeningWidget.selectedElement();

        if (jobOpeningWidget.selectedOption() == 0) return false;

        Candidate candidate = null;
        if (Console.readBoolean("Do you want to select a candidate from the registered candidates in the system? (Y/N)")) {
            SelectWidget<Candidate> candidateWidget = new SelectWidget<>("Select the candidate", theController.allCandidates(), new CandidatePrinter());
            candidateWidget.show();
            candidate = candidateWidget.selectedElement();
        }

        if (candidate == null) {
            Username username = Username.valueOf(ConsoleUtils.readEmail("Insert the candidate's email", "Invalid email").toString());
            if (!theController.existsCandidateFiles(SHARED_FOLDER, username, jobOpening.jobReference())) {
                System.out.println("There are no files related to that job application. Please try again later.\n");
                return false;
            } else {
                try {
                    candidate = theController.createCandidate(SHARED_FOLDER, username, jobOpening.jobReference());
                } catch (Exception e) {
                    System.out.println("Error creating candidate: " + e.getMessage());
                    e.printStackTrace();
                    return false;
                }
                System.out.printf("Candidate %s created successfully.\nLogin credentials were sent to the candidate's email (%s).\n", candidate.name(), candidate.email());
            }
        }

        JobApplication jobApplication;
        try {
            jobApplication = theController.createNewApplication(SHARED_FOLDER, candidate, jobOpening);
        } catch (Exception e) {
            System.out.println("Error importing files: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        System.out.println("Candidate's application files imported successfully.");

        return seeApplicationFiles(jobApplication);
    }

    private boolean seeApplicationFiles(JobApplication jobApplication) {
        if (Console.readBoolean("Do you want to see the candidate's application files? (Y/N)")) {
            File[] files = theController.allApplicationFiles(SHARED_FOLDER, jobApplication);

            if (files.length == 0) {
                System.out.println("There are no files related to that job application.");
            } else {
                System.out.println("Files related to the job application:");
                for (File file : files) {
                    System.out.println("\t" + file.getName());
                }
            }

            List<File> filesList = new java.util.ArrayList<>(Arrays.stream(files).toList());
            do {
                SelectWidget<File> fileWidget = new SelectWidget<>("Select a file to see its content", filesList, new FilePrinter());
                fileWidget.show();
                File file = fileWidget.selectedElement();
                if (file != null) {
                    try {
                        System.out.println("File content:\n\t" + Files.readString(file.toPath()));
                    } catch (Exception e) {
                        System.out.println("Error reading file: " + e.getMessage());
                        e.printStackTrace();
                        return false;
                    }
                }
                filesList.remove(file);
            } while (files.length > 0 && Console.readBoolean("Do you want to see another file? (Y/N)"));
            if (files.length == 0) {
                System.out.println("There are no more files to show.");
            }
        }
        return true;
    }
}
