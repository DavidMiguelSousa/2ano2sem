package eapli.base.app.backoffice.console.presentation.operator;

import eapli.framework.actions.Action;

public class ImportCandidateFilesAction implements Action {
    public boolean execute() {
        return new ImportCandidateFilesUI().show();
    }
}
