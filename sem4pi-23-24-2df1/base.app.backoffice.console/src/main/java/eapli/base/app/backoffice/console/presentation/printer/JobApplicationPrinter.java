package eapli.base.app.backoffice.console.presentation.printer;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.framework.visitor.Visitor;

public class JobApplicationPrinter implements Visitor<JobApplication>  {
    @Override
    public void visit(final JobApplication ja) {
        System.out.printf("%-30s %-30s %-30s %-30s", ja.identity(), ja.status(), ja.dateOfSubmission(), ja.jobOpening().identity());
    }
}
