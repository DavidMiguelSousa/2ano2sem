package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class EditJobOpeningAction implements Action {

    @Override
    public boolean execute() {
        return new EditJobOpeningUI().show();
    }
}
