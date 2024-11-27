package eapli.base.app.backoffice.console.presentation.printer;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.interviewmanagement.domain.InterviewModel;
import eapli.framework.visitor.Visitor;

public class InterviewModelPrinter implements Visitor<InterviewModel> {
    @Override
    public void visit(final InterviewModel interviewModel) {
        System.out.printf("%-30s", interviewModel.identity());
    }
}
