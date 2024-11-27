package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.PhaseDetails;
import eapli.base.clientusermanagement.domain.jobopening.Phase;
import eapli.base.clientusermanagement.domain.jobopening.Status;
import eapli.base.usermanagement.application.SetupJobOpeningPhasesController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.time.domain.model.DateInterval;

import java.util.*;

public class SetupJobOpeningPhasesUI {

    private final SetupJobOpeningPhasesController theController = new SetupJobOpeningPhasesController();

    public boolean show() {
        System.out.println("Setup Job Opening Phases");

        System.out.println("\nObtaining job openings available...\n");
        List<JobOpening> jobOpeningsAvailable = filterJobOpenings(theController.obtainJobOpeningsAvailable());
        SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Job openings:", jobOpeningsAvailable, new JobOpeningPrinter());
        jobOpeningSelector.show();
        JobOpening jobOpeningSelected = jobOpeningSelector.selectedElement();

        if (jobOpeningSelected == null) {
            System.out.println("Job opening invalid.");
            return true;
        }

        if (!jobOpeningSelected.phases().isEmpty()) {
            System.out.println("The phases for this job opening have already been defined.");
            return true;
        }

        Map<Phase, PhaseDetails> phases = new HashMap<>();

        System.out.println("\nJob Opening Phase:");

        Calendar lastDate = null;

        for (Phase phase : Phase.values()) {
            if (phase.equals(Phase.INTERVIEW)) {
                boolean hasInterview = Console.readBoolean("\nIs there an interview phase? (Y/N)");
                if (!hasInterview) {
                    continue;
                }
            }
            System.out.println(phase.phase() + " phase");

            lastDate = defineDates(phases, phase, lastDate);
        }

        theController.setupPhases(jobOpeningSelected, phases);

        return true;
    }

    private List<JobOpening> filterJobOpenings(List<JobOpening> jobOpenings) {
        List<JobOpening> filtered = new ArrayList<>();

        for (JobOpening jobOpening : jobOpenings) {
            if (jobOpening.phases().isEmpty()) {
                filtered.add(jobOpening);
            }
        }

        return filtered;
    }

    private Calendar defineDates(Map<Phase, PhaseDetails> phases, Phase phase, Calendar lastDate) {
        System.out.println("\nStart Date");
        Calendar startDate = Console.readCalendar("Please, insert date [DD-MM-YYYY]: ");
        lastDate = validateDate(startDate, lastDate);
        startDate = lastDate;

        System.out.println("\nEnd Date");
        Calendar endDate = Console.readCalendar("Please, insert date [DD-MM-YYYY]: ");
        lastDate = validateDate(endDate, lastDate);
        endDate = lastDate;

        phases.put(phase, new PhaseDetails(new DateInterval(startDate, endDate), Status.PENDING));

        return lastDate;
    }

    private Calendar validateDate(Calendar thisDate, Calendar lastDate) {
        if (lastDate != null && thisDate.before(lastDate)) {
            System.out.println("\nThe entered date must be after the last date/the same date as the last date.");
            thisDate = Console.readCalendar("Please insert date [DD-MM-YYYY]: ");
            thisDate = validateDate(thisDate, lastDate);
        }
        return thisDate;
    }
}
