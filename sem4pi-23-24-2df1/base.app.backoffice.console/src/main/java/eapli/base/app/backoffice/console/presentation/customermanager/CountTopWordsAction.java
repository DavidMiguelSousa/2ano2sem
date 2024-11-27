package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class CountTopWordsAction implements Action {
    @Override
    public boolean execute() {
        return new CountTopWordsUI().doShow();
    }
}
