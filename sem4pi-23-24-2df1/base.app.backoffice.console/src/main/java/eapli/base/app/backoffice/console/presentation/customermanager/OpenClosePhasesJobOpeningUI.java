package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.app.common.console.presentation.authz.NotifyCustomerAction;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.PhaseDetails;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.usermanagement.application.OpenClosePhasesJobOpeningController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;

import java.util.*;

public class OpenClosePhasesJobOpeningUI {
    private final OpenClosePhasesJobOpeningController theController = new OpenClosePhasesJobOpeningController();

    public boolean show() {

        System.out.println("Setup Job Opening Phase");

        System.out.println("\nObtaining job openings available...\n");
        List<JobOpening> jobOpeningsAvailable = theController.filterJobOpenings(theController.obtainJobOpeningsAvailable());

        if (jobOpeningsAvailable.isEmpty()) {
            System.out.println("There are no job openings available.");
            return false;
        }


        final Iterable<JobOpening> jobOpenings = theController.allJobOpening();


        if (jobOpenings == null) {
            System.out.println("No job openings available.");
            return false;
        }

        SelectWidget<JobOpening> selectorJobOpening = new SelectWidget<>("Select job Opening", jobOpenings, new JobOpeningPrinter());
        selectorJobOpening.show();
        JobOpening jobOpeningSelected = selectorJobOpening.selectedElement();


        if (jobOpeningSelected == null) {
            System.out.println("No job opening selected.");
            return false;
        }else if (jobOpeningSelected.phases().isEmpty()) {
            System.out.println("The phases for this job opening have not been defined yet.");
            return false;
        }else if (theController.areAllPhasesComplete(jobOpeningSelected.phases())) {
            theController.updateStatus(jobOpeningSelected, Status.COMPLETED);
            System.out.println("All phases are completed. The job opening status has been updated to completed.");
            return true;
        }

        boolean validOption = true;

        while (validOption) {

        displayPhasesAndStatus(jobOpeningSelected);

            int option = Console.readInteger("Do you want to:\n" +
                    "1. Close the current phase\n" +
                    "2. Go back to the previous phase\n" +
                    "3. Open the phase\n" +
                    "4. Save and Exit\n" +
                    "5. Cancel\n" +
                    "Choose an option: ");

            switch (option) {
                case 1:
                    boolean success = theController.managePhases(jobOpeningSelected);
                    if (success) {
                            System.out.println("Closed the current phase and opened the next phase successfully.");
                        } else {
                            System.out.println("Opened the next phase successfully, but no more phases to open.");
                            break;
                        }
                  //  new NotifyCustomerAction(jobOpeningSelected).execute();

                    break;
                case 2:
                    success = theController.goBackToPreviousPhase(jobOpeningSelected);
                    if (success) {
                        System.out.println("Opened the phase.");
                    } else {
                        System.out.println("No previous phase available.");
                    }
                   // new NotifyCustomerAction(jobOpeningSelected).execute();

                    break;
                case 3:
                    success = theController.openPhase(jobOpeningSelected);
                    if
                    (success) {
                        System.out.println("Opened the phase.");
                    } else {
                        System.out.println("Failed to open the phase. Either there are no phases available or the phases are not all pending.");
                    }
                    //new NotifyCustomerAction(jobOpeningSelected).execute();

                    break;
                case 4:
                    theController.saveToRepository(jobOpeningSelected);
                    if (jobOpeningSelected.phases().get(Phase.INTERVIEW).status() == Status.COMPLETED){
                        theController.sendEmail(Phase.INTERVIEW, jobOpeningSelected, Status.COMPLETED);
                    }
                    System.out.println("Job opening saved. Exiting...");
                    validOption = false;
                   // new NotifyCustomerAction(jobOpeningSelected).execute();

                    break;
                case 5:
                    validOption = false;
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
                    break;
            }
        }
        return true;
    }


    public void displayPhasesAndStatus(JobOpening jobOpening) {
        Map<Phase, PhaseDetails> phasesMap = jobOpening.phases();
        System.out.println("\nPhases and Their Current Status:");

        Phase currentPhase = null;
        Phase previousPhase = null;
        for (Phase phase : Phase.values()) {
            if (phasesMap.containsKey(phase)) {
                Status status = phasesMap.get(phase).status();
                System.out.println(" - " + phase + ": " + status);
                if (status == Status.IN_PROGRESS) {
                    currentPhase = phase;
                    if (phase.ordinal() > 0) {
                        previousPhase = Phase.values()[phase.ordinal() - 1];
                    }
                }
            }
        }

        if (currentPhase != null) {
            if (previousPhase != null) {
                System.out.println("Previous phase: " + previousPhase + ": " + phasesMap.get(previousPhase).status());
            }
            System.out.println("Current phase: " + currentPhase + ": " + phasesMap.get(currentPhase).status());
            if (currentPhase.ordinal() + 1 < Phase.values().length) {
                Phase nextPhase = Phase.values()[currentPhase.ordinal() + 1];
                if (phasesMap.containsKey(nextPhase)) {
                    System.out.println("Next phase: " + nextPhase + ": " + phasesMap.get(nextPhase).status());
                } else {
                    System.out.println("Next phase: " + nextPhase + ": Not started yet");
                }
            }
        } else {
            System.out.println("No active phases currently.");
        }
    }
}
