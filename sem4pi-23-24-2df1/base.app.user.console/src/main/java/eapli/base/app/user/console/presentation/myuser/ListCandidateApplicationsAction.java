package eapli.base.app.user.console.presentation.myuser;

import eapli.framework.actions.Action;

public class ListCandidateApplicationsAction implements Action {
    @Override
    public boolean execute() {
        return new ListCandidateApplicationsUI().doShow();
    }
}
