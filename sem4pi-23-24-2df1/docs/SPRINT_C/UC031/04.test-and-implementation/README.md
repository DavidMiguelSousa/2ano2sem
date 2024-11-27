# UC031 - As Customer Manager, I want to upload a text file with the candidate responses for an interview

# 4. Tests 

In this project, a Test-Driven Development (TDD) approach was used.

```java
public class InterviewManagementServiceTest {
    @InjectMocks
    InterviewManagementService service;

    String testFileString;
    String testFileName;
    Path destinationDownload;
    String destinationPath;

    @BeforeEach
    void setup() {

        InterviewModelRepository repo = mock(InterviewModelRepository.class);
        service = new InterviewManagementService(repo);


        // File paths
        testFileString = service.searchInterviewModels("PythonProgrammer.txt");
        testFileName = "PythonProgrammer.txt";
        destinationDownload = Paths.get(System.getProperty("user.home"), "Downloads");
        destinationPath = "\\SCOMP\\files\\fileBotFiles\\ISEP-001\\candidate@gmail.com";

    }

    @Test
    public void verifyExportTextInterview() {
        boolean expected = true;
        assertEquals(expected, service.exportTextInterview(testFileString, testFileName));
    }

    @Test
    public void verifyImportTextInterview() {
        boolean expected = true;
        assertEquals(expected, service.importTextInterview(String.valueOf(destinationDownload), destinationPath.toString(), testFileName));
    }
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class UploadTextInterviewModelAction

```java
@Override
public boolean execute() {
    return new UploadTextInterviewUI().show();
}
```

## Class UploadTextInterviewModelUI

```java
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
    final Iterable<JobOpening> jobOpenings = theController.allJobOpening();
    protected boolean show() throws IOException {


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
```

## Class UploadTextInterviewModelController

```java
public Iterable<JobOpening> allJobOpening() {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

    return jobOpeningManagementService.findAllAvailable();
}

public Iterable<JobApplication> allJobApplicationsByJobOpening(JobOpening jobOpening) {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

    return jobApplicationManagementService.jobApplicationsOf(jobOpening);
}

public boolean exportTextInterview(String exportFile, String nameFile){
    return interviewSvc.exportTextInterview(exportFile, nameFile);
}

public boolean importTextInterview(String downloadsPath, String destinationPath , String nameFile) throws IOException {
    return interviewSvc.importTextInterview(downloadsPath, destinationPath, nameFile);
}

public String searchInterviewModels(String s) {
    return interviewSvc.searchInterviewModels(s);
}

public Iterable<JobOpening> allJobOpeningWithInterviewModel(){
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.CUSTOMER_MANAGER, BaseRoles.ADMIN);

    Iterable<JobOpening> allJobOpenings = jobOpeningManagementService.findAllAvailable();

    List<JobOpening> jobOpeningsWithInterviewModel = new ArrayList<>();
    for (JobOpening jobOpening : allJobOpenings) {
        if (jobOpening.interviewModel() != null) {
            jobOpeningsWithInterviewModel.add(jobOpening);
        }
    }

    return jobOpeningsWithInterviewModel;
}

public boolean validateGrammarInterview(String filename) {
    return interviewSvc.validateGrammarInterview(filename);
}
```

## Class InterviewManagementService

```java

InterviewModelRepository interviewRepo;

InterviewModelParser parser = new InterviewModelParser();

public InterviewManagementService(InterviewModelRepository interviewRepo) {
    this.interviewRepo = interviewRepo;
}

public boolean exportTextInterview(String filePath, String fileName) {

    String downloadsPath = System.getProperty("user.home") + "\\Downloads";
    Path file = Paths.get(filePath);
    Path destinationPath = Paths.get(downloadsPath, fileName);

    try {

        InterviewModelParser parser = new InterviewModelParser();

        List<QuestionSolution> questions = parser.interviewJobOpening(file);

        Path newFile = writeQuestionsToFile(questions, downloadsPath, fileName);

        Files.copy(newFile, destinationPath, StandardCopyOption.REPLACE_EXISTING);

        return true;

    } catch (NullPointerException | IOException exception) {
        return false;
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}


private Path writeQuestionsToFile(List<QuestionSolution> questions, String directoryPath, String filename) throws IOException {
    Path filePath = Paths.get(directoryPath, filename);
    String[] newFileName = filename.split("\\.");
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
        writer.write("INTERVIEW \"" + newFileName[0] + "\"\n\n");

        for (QuestionSolution questionSolution : questions) {
            writer.write(questionSolution.question() + "\n");
            writer.write("ANS:\n\n");
        }
    }
    return filePath;
}

public boolean importTextInterview(String path, String destination, String nameFile) {
    try {
        StringBuilder builder = new StringBuilder();
        String projectDirString = Paths.get(System.getProperty("user.dir")).getParent().toString();
        String targetProjectName = "sem4pi-23-24-2df1";

        if (!projectDirString.endsWith(targetProjectName)) {
            projectDirString = projectDirString + "\\" + targetProjectName;
        }

        if (!destination.startsWith("\\")) {
            destination = "\\" + destination;
        }

        String filePathString = builder.append(projectDirString).append(destination).toString();

        copyFile(path, filePathString, nameFile, "interview.txt");

        return true;
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

public void copyFile(String sourceFilePath, String destination, String nameFile, String newName) throws IOException {
    Path sourcePath = Paths.get(sourceFilePath);

    Path destinationPath = Paths.get(destination, newName);

    if (!Files.exists(destinationPath.getParent())) {
        Files.createDirectories(destinationPath.getParent());
    }

    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
}


public String searchInterviewModels(String fileName) {
    StringBuilder builder = new StringBuilder();
    String projectDirString = Paths.get(System.getProperty("user.dir")).getParent().toString();
    String targetProjectName = "sem4pi-23-24-2df1";

    if (!projectDirString.endsWith(targetProjectName)) {
        projectDirString = projectDirString + "\\" + targetProjectName;
    }

    String filePathString = String.valueOf(builder.append(projectDirString).append("\\LPROG\\interviewModels\\").append(fileName));

    Path filePath = Paths.get(filePathString);

    File file = filePath.toFile();

    if (file.exists()) {
        return file.toString();
    } else {
        return "";
    }
}

public boolean validateGrammarInterview(String filename) {
    return parser.validateGrammarInterview(filename);
}
```

## Class InterviewModelParser

```java
public List<QuestionSolution> interviewJobOpening(Path path) throws Exception {

    String content = Files.readString(path);

    CharStream input = CharStreams.fromString(content);

    InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

    ParseTree tree = parser.start();
    InterviewModelVisitor visitor = new InterviewModelVisitor();
    visitor.visit(tree);

    return visitor.interviewJobOpening();
}

public boolean validateGrammarInterview(String filename) {
    try {
        return validateGrammar(filename);
    } catch (IOException e) {
        e.printStackTrace();
        return false;
    }
}

public boolean validateGrammar(String filename) throws IOException {
    String fileContent = readFile(filename);

    CharStream inputCharStream = CharStreams.fromString(fileContent);

    InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(inputCharStream);
    lexer.removeErrorListeners();

    CommonTokenStream tokens = new CommonTokenStream(lexer);

    InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);
    parser.removeErrorListeners();

    parser.setErrorHandler(new BailErrorStrategy());

    try {
        parser.start();
        return true;
    } catch (ParseCancellationException e) {
        return false;
    }
}

public static String readFile(String filePath) throws IOException {
    StringBuilder content = new StringBuilder();
    BufferedReader reader = new BufferedReader(new FileReader(filePath));
    String line;

    boolean firstLine = true;
    while ((line = reader.readLine()) != null) {
        if (firstLine) {
            content.append(line);
            firstLine = false;
        } else {
            content.append("\n").append(line);
        }
    }

    reader.close();
    return content.toString();
}
```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added. This option allows the user to upload a text file with the candidate responses for an interview.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.