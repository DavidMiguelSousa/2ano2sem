package eapli.base.app.backoffice.console.presentation.customermanager;

import eapli.base.app.backoffice.console.presentation.printer.JobApplicationPrinter;
import eapli.base.app.backoffice.console.presentation.printer.JobOpeningPrinter;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.usermanagement.application.UploadTextInterviewModelController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.SelectWidget;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class UploadTextInterviewModelUI {

    private final UploadTextInterviewModelController theController = new UploadTextInterviewModelController();
    protected boolean show() throws IOException {


        final Iterable<JobOpening> jobOpenings = theController.allJobOpeningWithInterviewModel();


        if (jobOpenings == null) {
            System.out.println("No job openings available.");
            return false;
        }

        SelectWidget<JobOpening> selectorJobOpening = new SelectWidget<>("Select job Opening", jobOpenings, new JobOpeningPrinter());
        selectorJobOpening.show();
        JobOpening selectedJobOpening = selectorJobOpening.selectedElement();


        if (selectedJobOpening == null) {
            System.out.println("No job opening selected.");
            return false;
        }

        Iterable<JobApplication> jobApplications = theController.allJobApplicationsByJobOpening(selectedJobOpening);

        if (jobApplications == null) {
            System.out.println("No job applications available.");
            return false;
        }

        SelectWidget<JobApplication> selectorJobApplication = new SelectWidget<>("Select Job Applications", jobApplications, new JobApplicationPrinter());
        selectorJobApplication.show();
        JobApplication selectedJobApplications = selectorJobApplication.selectedElement();


        final String nameOfModel = String.valueOf(selectedJobOpening.interviewModel().identity());
        final String fileName = nameOfModel.replaceAll(" ", "");
        final char temp = fileName.charAt(0);
        fileName.replace(fileName.charAt(0), Character.toLowerCase(temp));

        String filePath = theController.searchInterviewModels(fileName + ".txt");


        if (filePath.equals(null)) {
            System.out.println(filePath);
            return false;
        }


        // String exportFilePath = "src/test/interviewModel/interviewsmodel_template/" + selectedJobOpening.jobReference() + ".txt";

        boolean choise = theController.exportTextInterview(filePath, fileName + ".txt");

        if (choise) {
            String answer = Console.readLine("File created! You want to import the file? (Yes/No): ");
            if (answer.equalsIgnoreCase("Yes")) {
                String path = Console.readLine("Enter the file path to import: ");

                String email = selectedJobApplications.candidate().email().toString();

                String destinationPath = "\\SCOMP\\files\\fileBotFiles\\" + selectedJobOpening.jobReference() + "\\" + email;

                boolean grammar = theController.validateGrammarInterview(path);

                if(grammar){
                    System.out.println("Grammar valid.");

                    boolean imported = theController.importTextInterview(path, destinationPath, fileName+".txt");

                    if (imported) {
                        System.out.println("File imported.");
                        //TODO: Guardar na Bd
                    } else {
                        System.out.println("File not imported.");
                        return false;
                    }
                }else{
                    System.out.println("Grammar not valid.");
                    return false;
                }
            } else {
                System.out.println("File not imported.");
                return false;
            }
        } else {
            System.out.println("Error creating file.");
        }
        return true;
    }

}
