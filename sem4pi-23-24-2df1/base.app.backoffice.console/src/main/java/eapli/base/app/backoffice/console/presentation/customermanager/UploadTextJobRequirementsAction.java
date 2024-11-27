

package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

import java.io.IOException;

public class UploadTextJobRequirementsAction implements Action {
    @Override
    public boolean execute() {
        try {
            return new UploadTextJobRequirementsUI().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}