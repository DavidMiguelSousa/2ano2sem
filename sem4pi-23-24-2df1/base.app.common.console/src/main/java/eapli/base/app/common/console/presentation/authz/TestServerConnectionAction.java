package eapli.base.app.common.console.presentation.authz;

import eapli.framework.actions.Action;

public class TestServerConnectionAction implements Action {
    @Override
    public boolean execute() {
        return new TestServerConnectionUI().doShow();
    }
}
