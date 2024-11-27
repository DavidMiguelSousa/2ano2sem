package eapli.base.app.backoffice.console.presentation.operator;

import eapli.framework.actions.Action;

public class DisableCandidateAction implements Action {
    public boolean execute() {
        return new DisableCandidateUI().show();
    }
}
