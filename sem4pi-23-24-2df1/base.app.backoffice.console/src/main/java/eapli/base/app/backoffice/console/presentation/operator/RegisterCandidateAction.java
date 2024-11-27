package eapli.base.app.backoffice.console.presentation.operator;

import eapli.framework.actions.Action;

public class RegisterCandidateAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterCandidateUI().show();
    }
}
