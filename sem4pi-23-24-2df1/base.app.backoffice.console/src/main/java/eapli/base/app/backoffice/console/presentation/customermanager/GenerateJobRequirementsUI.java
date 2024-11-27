package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.usermanagement.application.GenerateJobRequirementsController;

import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import requirements.RequirementType;

import java.util.List;

public class GenerateJobRequirementsUI extends AbstractUI {

    private final GenerateJobRequirementsController controller = new GenerateJobRequirementsController();

    public boolean doShow() {
        String filePath = "LPROG/jobRequirements/";
        final String nameOfModel = Console.readLine("Enter the title of the job requirements: ");
        final String fileName = nameOfModel.replaceAll(" ", "-").toLowerCase();
        try {
            if (controller.createFile(filePath, fileName)) {
                System.out.println("File created: " + fileName + ".txt");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("File not created.");
            throw new RuntimeException(e);
        }

        controller.addTitle(nameOfModel);


        List<RequirementType> outputTypes = List.of(RequirementType.values());
        SelectWidget<RequirementType> selectWidget = new SelectWidget<>("Select a question type", outputTypes);
        RequirementType type;
        String question;
        String answer;
        boolean canContinue;

        do {
            selectWidget.show();
            type = selectWidget.selectedElement();
            question = Console.readLine("Enter question: ");
            controller.addQuestion(type, question);

            answer = Console.readLine("Enter solution: ");
            controller.addRequirement(answer);

            canContinue = Console.readLine("Do you want to add another question? (y/n)").trim().equalsIgnoreCase("y");
        } while (canContinue);


        if (controller.writeInFile()) {
            System.out.println("File content written successfully.");
        } else {
            System.out.println("File content not written. Please try again later.");
        }

        System.out.println(filePath + fileName + ".txt");
        return true;
    }


    @Override
    public String headline() {
        return "Generate Job Requirements";
    }
}

