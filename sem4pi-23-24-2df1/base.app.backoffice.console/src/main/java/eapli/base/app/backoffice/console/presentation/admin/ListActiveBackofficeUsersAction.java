package eapli.base.app.backoffice.console.presentation.admin;

import eapli.framework.actions.Action;

public class ListActiveBackofficeUsersAction implements Action {
    @Override
    public boolean execute() {
        return new ListActiveBackofficeUsersUI().show();
    }
}
