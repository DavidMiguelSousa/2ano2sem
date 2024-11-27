package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class ListApplicationJobOpeningAction implements Action {
    @Override
    public boolean execute() {
        return new ListApplicationJobOpeningUI().show();
    }
}
