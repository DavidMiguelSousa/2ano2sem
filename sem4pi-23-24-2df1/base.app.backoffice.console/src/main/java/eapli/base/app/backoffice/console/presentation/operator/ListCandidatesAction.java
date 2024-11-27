package eapli.base.app.backoffice.console.presentation.operator;

import eapli.framework.actions.Action;

public class ListCandidatesAction implements Action {
    @Override
    public boolean execute() {
        return new ListCandidatesUI().show();
    }
}
