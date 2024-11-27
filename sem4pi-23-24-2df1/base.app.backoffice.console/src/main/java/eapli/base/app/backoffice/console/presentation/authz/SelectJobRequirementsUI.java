package eapli.base.app.backoffice.console.presentation.authz;

import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobRequirementsPrinter;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.usermanagement.application.SelectJobRequirementsController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class SelectJobRequirementsUI extends AbstractUI {

    private final SelectJobRequirementsController selectJobRequirementsController = new SelectJobRequirementsController();

    @Override
    public String headline() {
        return "Select Job Requirements";
    }

    @Override
    protected boolean doShow() {
        try {
            final Iterable<JobOpening> jobOpenings = selectJobRequirementsController.pendingJobOpenings();
            SelectWidget<JobOpening> jobOpeningSelector = new SelectWidget<>("Select Job Opening", jobOpenings, new JobOpeningPrinter());
            jobOpeningSelector.show();
            final JobOpening jobOpening = jobOpeningSelector.selectedElement();

            final Iterable<JobRequirements> jobRequirementsSpecifications = selectJobRequirementsController.findJobRequirements();
            SelectWidget<JobRequirements> jobRequirementsSelector = new SelectWidget<>("Select Job Requirements", jobRequirementsSpecifications, new JobRequirementsPrinter());
            jobRequirementsSelector.show();
            final JobRequirements jobRequirements = jobRequirementsSelector.selectedElement();

            selectJobRequirementsController.assignJobRequirements(jobOpening, jobRequirements);

            System.out.println("Job requirements assigned successfully.");
        } catch (Exception e) {
            System.out.printf("Error: %s\n", e.getMessage());
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
