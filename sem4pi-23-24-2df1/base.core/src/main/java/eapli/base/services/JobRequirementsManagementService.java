package eapli.base.services;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.domain.jobopening.JobRequirements;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.interviewmanagement.domain.interview.InterviewGrade;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.framework.general.domain.model.Designation;
import interviewModel.InterviewModelParser;
import jobRequirements.JobRequirementsGrammarLexer;
import jobRequirements.JobRequirementsGrammarParser;
import jobRequirements.JobRequirementsParser;
import jobRequirements.JobRequirementsVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ParseTree;
import question.QuestionAnswer;
import question.QuestionSolution;
import question.QuestionType;
import question.Solution;
import requirements.RequirementAnswer;
import requirements.RequirementSolution;
import requirements.RequirementType;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;
import java.util.stream.Collectors;

public class JobRequirementsManagementService {

    private static final String filePath = "LPROG/jobRequirements/";

    JobRequirementsRepository jobRequirementsRepository;

    JobRequirementsParser parser = new JobRequirementsParser();

    public JobRequirementsManagementService(JobRequirementsRepository jobRequirementsRepository) {
        this.jobRequirementsRepository = jobRequirementsRepository;
    }

    private String fileName;

    public Iterable<JobRequirements> allJobRequirements() {
        return jobRequirementsRepository.findAll();
    }

    public Iterable<JobRequirements> findJobRequirements(Designation id) {
        return jobRequirementsRepository.findByDesignation(id);
    }


//    public boolean createFile(String title) {
//        try {
//            String name = title.replaceAll(" ", "");
//            final char temp = name.charAt(0);
//            name.replace(name.charAt(0), Character.toLowerCase(temp));
//            this.fileName = filePath + name + ".txt";
//            File myFile = new File(fileName);
//            return myFile.createNewFile();
//        } catch (Exception e) {
//            System.out.println("An error occurred.");
//            return false;
//        }
//    }
//
//    public void addTitle(String title) {
//        try {
//            FileWriter writer = new FileWriter(fileName, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write("JOB REQUIREMENTS \"" + title + "\"");
//            bufferedWriter.newLine();
//            bufferedWriter.newLine();
//            bufferedWriter.close();
//            System.out.println("Title added: " + title);
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//
//    public void addQuestion(String question) {
//        try {
//            FileWriter writer = new FileWriter(fileName, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write("# " + question);
//            bufferedWriter.newLine();
//            bufferedWriter.close();
//            System.out.println("Question added: " + question);
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//
//    public void addAnswer(String answer) {
//        try {
//            FileWriter writer = new FileWriter(fileName, true);
//            BufferedWriter bufferedWriter = new BufferedWriter(writer);
//            bufferedWriter.write(answer);
//            bufferedWriter.newLine();
//            bufferedWriter.close();
//            System.out.println("Answer added: " + answer);
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//    }

    public boolean exportJobRequirements(String filePath, String fileName) {

        String downloadsPath = System.getProperty("user.home") + "\\Downloads";
        Path file = Paths.get(filePath);
        Path destinationPath = Paths.get(downloadsPath, fileName);

        try {

            JobRequirementsParser parser = new JobRequirementsParser();

            List<RequirementSolution> questions = parser.jobRequirementsJobOpening(file);

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

    private Path writeQuestionsToFile(List<RequirementSolution> questions, String directoryPath, String filename) throws IOException {
        Path filePath = Paths.get(directoryPath, filename);
        String[] newFileName = filename.split("\\.");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath.toFile()))) {
            writer.write("JOB REQUIREMENTS \"" + newFileName[0] + "\"\n\n");

            for (RequirementSolution questionSolution : questions) {
                writer.write(questionSolution.question() + "\n");
                writer.write("Answer:\n\n");
            }
        }
        return filePath;
    }


    public boolean importTextJobRequirements(String path, String destination, String nameFile) {
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

            copyFile(path, filePathString, nameFile, "requirements.txt");

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


    public String searchJobRequirements(String fileName) {
        StringBuilder builder = new StringBuilder();
        String projectDirString = Paths.get(System.getProperty("user.dir")).getParent().toString();
        String targetProjectName = "sem4pi-23-24-2df1";

        if (!projectDirString.endsWith(targetProjectName)) {
            projectDirString = projectDirString + "\\" + targetProjectName;
        }

        String filePathString = String.valueOf(builder.append(projectDirString).append("\\LPROG\\jobRequirements\\").append(fileName));

        Path filePath = Paths.get(filePathString);

        File file = filePath.toFile();

        if (file.exists()) {
            return file.toString();
        } else {
            return "";
        }
    }

    public boolean validateGrammarJobRequirements(String filename) {
        return parser.validateGrammarJobRequirements(filename);
    }

    public List<JobApplication> verifyJobRequirements(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {

        List<JobApplication> requirements = new ArrayList<>();

        if(jobOpening.jobRequirements()!=null) {
            final String nameOfModel = jobOpening.jobRequirements().identity().toString();
            final String fileName = nameOfModel.replaceAll(" ", "");
            final char temp = fileName.charAt(0);
            fileName.replace(fileName.charAt(0), Character.toLowerCase(temp));

            Path pathJobRequirements = Paths.get("LPROG/jobRequirements/" + fileName + ".txt");
            List<RequirementSolution> questionWithSolution = parser.jobRequirementsJobOpening(pathJobRequirements);

            boolean allAccepted = true;

            for (JobApplication jobApplication : jobApplicationList) {

                Path pathRequirements = Paths.get("SCOMP/files/fileBotFiles/" + jobOpening.identity()+ "/"+ jobApplication.candidate().email() + "/requirements.txt");

                if(Files.exists(pathRequirements)) {
                    List<RequirementAnswer> questionWithAnswer = parser.jobRequirementsJobApplication(pathRequirements);

                    for (RequirementSolution qs : questionWithSolution) {
                        for (RequirementAnswer qa : questionWithAnswer) {
                            if (qs.question().equals(qa.question()) && allAccepted) {
                                allAccepted = evaluateQuestion(qs.type(), qs, qa);
                                if(!allAccepted)
                                System.out.println(qs.type() + " " + qs.question() + " " + qa.answer() + " " + allAccepted);
                            }
                        }
                    }
                    if (allAccepted) {
                        jobApplication.accept();
                    } else {
                        jobApplication.refuse();
                    }
                    allAccepted = true;

                    requirements.add(jobApplication);
                }
            }
        }
        return requirements;
    }

    public boolean evaluateQuestion(RequirementType type, RequirementSolution qs, RequirementAnswer qa) {
        return switch (type) {
            case MININT -> evaluateMinInt(qs, qa);
            case MAXINT -> evaluateMaxInt(qs, qa);
            case MINORD -> evaluateMinOrd(qs, qa);
            case MAXORD -> evaluateMaxOrd(qs, qa);
            case MULORSING -> evaluateMulOrSing(qs, qa);
            default -> false;
        };
    }



    private boolean evaluateMinInt(RequirementSolution qs, RequirementAnswer qa) {
        try {
            int solution = Integer.parseInt(qs.onlySolution().solutions().split(" ")[1].split(";")[0]);
            int answer = Integer.parseInt(qa.answer().split(" ")[1]);

            if(answer >= solution){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean evaluateMaxInt(RequirementSolution qs, RequirementAnswer qa) {

        try {
            int solution = Integer.parseInt(qs.onlySolution().solutions().split(" ")[1].split(";")[0]);
            int answer = Integer.parseInt(qa.answer().split(" ")[1]);

            if(answer <= solution){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    private boolean evaluateMinOrd(RequirementSolution qs, RequirementAnswer qa) {
        for(Solution solution : qs.solutions()){
            if(Objects.equals(qa.answer().split(" ")[1], solution.solutions())){
                return true;
            }
        }
        return false;
    }

    private boolean evaluateMaxOrd(RequirementSolution qs, RequirementAnswer qa) {
        for(Solution solution : qs.solutions()){
            if(Objects.equals(qa.answer().split(" ")[1], solution.solutions())){
                return true;
            }
        }
        return false;
    }

    private boolean evaluateMulOrSing(RequirementSolution qs, RequirementAnswer qa) {
        int count = 0;
        String[] answers = qa.answer().split(" ")[1].split(",\\s*");
        for(Solution solution : qs.solutions()){
            for(String answer : answers){
                if(Objects.equals(answer, solution.solutions())){
                    count++;
                }
            }
        }
        if(count == qs.solutions().size()){
            return true;
        }
        return false;
    }

}
