package eapli.base.app.backoffice.console.presentation.printer;

import eapli.base.candidatemanagement.domain.Candidate;
import eapli.framework.visitor.Visitor;

public class CandidatePrinter implements Visitor<Candidate> {
    @Override
    public void visit(Candidate visitee) {
        System.out.printf("%-30s%-30s", visitee.name(), visitee.email());
    }
}
