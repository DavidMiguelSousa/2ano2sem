package eapli.base.app.backoffice.console.presentation.operator;

import eapli.base.app.backoffice.console.presentation.authz.ListUsersUI;
import eapli.base.candidatemanagement.domain.Candidate;
import eapli.base.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class ListCandidatesUI extends ListUsersUI {

    private final ListUsersController theController = new ListUsersController();

    @Override
    public String headline() {
        return "List Candidates";
    }

    @Override
    protected Iterable<SystemUser> elements() {
        Iterable<Candidate> candidates = theController.findCandidates();
        List<SystemUser> users = new ArrayList<>();
        for (Candidate c : candidates) {
            users.add(c.user());
        }
        return users;
    }

    @Override
    protected String elementName() {
        return "Candidate";
    }

}