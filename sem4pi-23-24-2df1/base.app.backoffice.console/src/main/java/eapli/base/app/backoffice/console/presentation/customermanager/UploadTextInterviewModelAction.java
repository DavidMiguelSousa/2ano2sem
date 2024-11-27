
package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.framework.actions.Action;

import javax.swing.*;
import java.io.IOException;

public class UploadTextInterviewModelAction implements Action {
    @Override
    public boolean execute() {
        try {
            return new UploadTextInterviewModelUI().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}