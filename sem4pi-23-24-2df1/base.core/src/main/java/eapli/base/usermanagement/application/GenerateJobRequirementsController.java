package eapli.base.usermanagement.application;


import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.general.domain.model.Description;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import requirements.RequirementToken;
import requirements.RequirementType;

import java.io.File;
import java.io.PrintWriter;

public class GenerateJobRequirementsController {

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    private static final String fileExtension = ".txt";

    private static File file;

    private static Description description;


    public boolean createFile(String filepath, String fileName) {
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
        try {
            file = new File(filepath + fileName + fileExtension);
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    public void addTitle(String nameOfModel) {
        description = Description.valueOf(String.format(RequirementToken.JOBREQUIREMENTS.token() + " \"%s\"", nameOfModel));

    }

    public void addQuestion(RequirementType outputType, String question) {
        if (!question.endsWith("?") && !question.endsWith(".")) question = question.concat(".");
        description = Description.valueOf(description.toString().concat(String.format("%n%n%s \"%s\";", outputType.token(), question)));
    }

    public void addRequirement(String option) {
        description = Description.valueOf(description.toString().concat(String.format("%n" + RequirementToken.REQUIREMENT_TOKEN.token() + ": %s;", option)));
    }

    public void addAnswer(String answer) {
        description = Description.valueOf(description.toString().concat(String.format("%n" + RequirementToken.ANSWER.token() + ": %s;", answer)));
    }

    public boolean writeInFile() {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.println(description.toString());
            writer.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
