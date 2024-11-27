package eapli.base.app.backoffice.console.presentation.operator;

import eapli.framework.actions.Action;

public class EnableCandidateAction implements Action {
    public boolean execute() {
        return new EnableCandidateUI().show();
    }
}
