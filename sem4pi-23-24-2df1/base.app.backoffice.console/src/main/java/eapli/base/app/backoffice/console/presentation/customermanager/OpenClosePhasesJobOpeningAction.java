package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class OpenClosePhasesJobOpeningAction implements Action {
    @Override
    public boolean execute() {
        return new OpenClosePhasesJobOpeningUI().show();
    }
}
