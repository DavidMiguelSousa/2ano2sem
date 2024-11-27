package eapli.base.app.backoffice.console.presentation.printer;

import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.framework.visitor.Visitor;

public class JobRequirementsPrinter implements Visitor<JobRequirements> {
    @Override
    public void visit(final JobRequirements jobRequirements) {
        System.out.printf("%-30s", jobRequirements.identity());
    }
}
