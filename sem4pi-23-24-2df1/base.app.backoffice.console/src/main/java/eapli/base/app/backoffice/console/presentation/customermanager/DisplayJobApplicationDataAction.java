package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class DisplayJobApplicationDataAction implements Action {

    @Override
    public boolean execute() {
        return new DisplayJobApplicationDataUI().show();
    }
}
