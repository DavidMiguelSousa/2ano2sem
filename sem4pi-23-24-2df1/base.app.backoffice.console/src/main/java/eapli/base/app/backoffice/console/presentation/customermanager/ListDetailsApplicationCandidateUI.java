package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.CandidatePrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ListDetailsApplicationCandidateUI extends AbstractUI {
    private final ListUsersController theController = new ListUsersController();

    @Override
    protected boolean doShow() {
        SelectWidget<Candidate> selectWidget = new SelectWidget<>("Select Candidate", theController.findCandidates(), new CandidatePrinter());
        selectWidget.show();
        final Candidate user = selectWidget.selectedElement();
        final Iterable<JobApplication> applications = theController.applicationByCandidate(user);
        new CandidatePrinter().visit(user);
        System.out.printf("%n%n%-30s %-30s %-30s %-30s\n", "APPLICATION REFERENCE", "STATUS", "DATE OF SUBMISSION", "JOB OPENING REFERENCE");
        for (JobApplication application : applications) {
            new JobApplicationPrinter().visit(application);
        }
        return true;
    }

    @Override
    public String headline() {
        return "List Personal Data + Applications Candidates";
    }

}