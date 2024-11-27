package eapli.base.app.other.console.presentation;

import eapli.framework.actions.Action;

public class ListCustomerJobOpeningAction implements Action {
    @Override
    public boolean execute() {
        return new ListCustomerJobOpeningUI().show();
    }
}

