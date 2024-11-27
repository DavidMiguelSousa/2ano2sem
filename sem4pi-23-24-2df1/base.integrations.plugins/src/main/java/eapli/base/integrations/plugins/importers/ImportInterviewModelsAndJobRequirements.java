package eapli.base.integrations.plugins.importers;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.integration.interview_model.import_.application.ImportInterviewModelController;
import eapli.base.integration.job_requirements.import_.application.ImportJobRequirementsController;
import eapli.base.usermanagement.domain.BasePasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ImportInterviewModelsAndJobRequirements {

    private final ImportInterviewModelController controllerIM = new ImportInterviewModelController();
    private final ImportJobRequirementsController controllerJR = new ImportJobRequirementsController();

    private static final String module = "LPROG";
    private static final String folderIM = "interviewModels";
    private static final String folderJR = "jobRequirements";

    public static void main(final String[] args) {

        AuthzRegistry.configure(PersistenceContext.repositories().users(),
                new BasePasswordPolicy(), new PlainTextEncoder());

        new ImportInterviewModelsAndJobRequirements().run();
    }

    protected void run() {
        System.out.println("Import Interview Models and Job Requirements from files\n\n");
        try {
            for (File file : Objects.requireNonNull(new File(module + File.separator + folderIM).listFiles())) {
                if (file.getName().equals("empty")) {
                    System.out.println("No Interview Models to import!");
                    break;
                }
                controllerIM.importInterviewModel(module + File.separator + folderIM + File.separator + file.getName());
            }
            System.out.println("Interview Models imported successfully!");
            for (File file : Objects.requireNonNull(new File(module + File.separator + folderJR).listFiles())) {
                if (file.getName().equals("empty")) {
                    System.out.println("No Job Requirements to import!");
                    break;
                }
                controllerJR.importJobRequirements(module + File.separator + folderJR + File.separator + file.getName());
            }
            System.out.println("Job Requirements imported successfully!");
        } catch (IOException e) {
            System.out.println("Error importing Interview Models or Job Requirements!");
            e.printStackTrace();
        }
    }

}