package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

public class ExportInterviewModelsAction implements Action {
    @Override
    public boolean execute() {
        return new ExportInterviewModelsUI().show();
    }
}