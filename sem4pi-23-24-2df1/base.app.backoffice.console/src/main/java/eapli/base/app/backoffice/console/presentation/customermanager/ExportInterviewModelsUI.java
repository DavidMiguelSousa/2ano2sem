package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.integration.interview_model.export.application.ExportInterviewModelsController;
import eapli.base.integration.interview_model.export.application.FileFormat;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.io.IOException;

public class ExportInterviewModelsUI extends AbstractUI {

    private final ExportInterviewModelsController theController = new ExportInterviewModelsController();

    @Override
    protected boolean doShow() {
        try {
            theController.exportAll(Console.readLine("File name (without extension): "), FileFormat.TXT);
            System.out.println("Interview models exported successfully!");
        } catch (IOException e) {
            System.out.println("Error exporting interview models: " + e.getMessage());
        }
        return true;
    }

    @Override
    public String headline() {
        return "Export Interview Models";
    }
}
