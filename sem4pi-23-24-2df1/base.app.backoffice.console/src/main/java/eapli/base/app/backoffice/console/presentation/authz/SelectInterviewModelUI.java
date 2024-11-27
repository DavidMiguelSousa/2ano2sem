package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.app.backoffice.console.presentation.printer.InterviewModelPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.base.usermanagement.application.SelectInterviewModelController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class SelectInterviewModelUI extends AbstractUI {

    private final SelectInterviewModelController selectInterviewModelController = new SelectInterviewModelController();

    @Override
    public String headline() {
        return "Select Interview Model";
    }

    @Override
    protected boolean doShow() {
        try {
            final Iterable<JobOpening> jobOpenings = selectInterviewModelController.findJobOpenings();
            SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Select Job Opening", jobOpenings, new JobOpeningPrinter());
            jobOpeningSelector.show();
            final JobOpening jobOpening = jobOpeningSelector.selectedElement();

            final Iterable<InterviewModel> interviewModels = selectInterviewModelController.findInterviewModels();
            SelectWidget<InterviewModel> interviewModelSelector = new SelectWidget<>("Select Interview Model", interviewModels, new InterviewModelPrinter());
            interviewModelSelector.show();

            final InterviewModel interviewModel = interviewModelSelector.selectedElement();

            selectInterviewModelController.assignInterviewModel(jobOpening, interviewModel);
            System.out.println("Interview model assigned successfully.");

        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
            if (!Console.readLine("Invalid input. Please type 0 to try again or type anything else to exit.").equals("0")) {
                return false;
            }
        }

        return true;
    }
}
