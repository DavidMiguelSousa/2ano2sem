package eapli.base.services;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.interviewmanagement.domain.interview.InterviewGrade;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import eapli.framework.io.util.Console;
import interviewModel.InterviewModelParser;
import question.QuestionAnswer;
import question.QuestionSolution;
import question.QuestionType;
import question.Solution;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InterviewManagementService {

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
//            Path sourcePath = Paths.get(filePath);
//
//            String downloadsPath = System.getProperty("user.home") + "\\Downloads";
//
//            Path destinationPath = Paths.get(downloadsPath, fileName);
//
//            searchInterviewModels(fileName);
//
//            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

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

//
//    public InterviewModel registerInterviewModel(Description description, Designation name){
//        final var interviewModelBuilder = new InterviewModelBuilder();
//        interviewModelBuilder.withDescription(description).withName(name);
//        final var newInterviewModel = interviewModelBuilder.build();
//        return interviewRepo.save(newInterviewModel);
//    }

    public List<JobApplication> evaluateInterviews(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {
        final String nameOfModel = jobOpening.interviewModel().identity().toString();
        final String fileName = nameOfModel.replaceAll(" ", "");
        final char temp = fileName.charAt(0);
        fileName.replace(fileName.charAt(0), Character.toLowerCase(temp));

        Path pathInterviewModel = Paths.get("LPROG/interviewModels/" + fileName + ".txt");
        List<QuestionSolution> questionWithSolution = parser.interviewJobOpening(pathInterviewModel);

        InterviewGrade totalGrade = obtainInterviewModelTotalGrade(questionWithSolution);

        List<JobApplication> grades = new ArrayList<>();

        for (JobApplication jobApplication : jobApplicationList) {
            Path pathInterview = Paths.get("SCOMP/files/fileBotFiles/" + jobOpening.identity().toString() + "/" + jobApplication.candidate().email().toString() + "/interview.txt");

            List<QuestionAnswer> questionWithAnswer = parser.interviewJobApplication(pathInterview);

            InterviewGrade interviewGrade = new InterviewGrade();

            for (QuestionSolution qs : questionWithSolution) {
                for (QuestionAnswer qa : questionWithAnswer) {
                    if (qs.question().equals(qa.question())) {
                        interviewGrade.incrementGrade(evaluateQuestion(qs.type(), qs, qa));
                    }
                }
            }

            interviewGrade.gradeInPercentage(totalGrade);
            jobApplication.interview().defineGrade(interviewGrade);

            grades.add(jobApplication);
        }

        return grades;
    }

    private InterviewGrade obtainInterviewModelTotalGrade(List<QuestionSolution> questionWithSolution) {
        double totalGrade = 0.0;

        for (QuestionSolution qs : questionWithSolution) {
            totalGrade += qs.value();
        }

        return new InterviewGrade(totalGrade);
    }

    public Double evaluateQuestion(QuestionType type, QuestionSolution qs, QuestionAnswer qa) {
        return switch (type) {
            case TFQUES, SAQUES -> evaluateTextTypeQuestion(qs, qa);
            case SCQUES -> evaluateSingleChoiceQuestion(qs, qa);
            case MCQUES -> evaluateMultipleChoiceQuestion(qs, qa);
            case INTQUES, NSQUES -> evaluateNumberTypeQuestion(qs, qa);
            case DECQUES -> evaluateDecimalQuestion(qs, qa);
            case DATEQUES -> evaluateDateQuestion(qs, qa);
            case TIMEQUES -> evaluateTimeQuestion(qs, qa);
        };
    }

    private Double evaluateTimeQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutionsString = qs.onlySolution().solutions().split(":");
        String[] answersString = qa.answer().split(":");

        if (answersString[0].equals("24")) answersString[0] = "00";

        return evaluateListTypeQuestion(qs, solutionsString, answersString);
    }

    private Double evaluateDateQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutionsString = qs.onlySolution().solutions().split("/");
        String[] answersString = qa.answer().split("/");

        return evaluateListTypeQuestion(qs, solutionsString, answersString);
    }

    private Double evaluateListTypeQuestion(QuestionSolution qs, String[] solutionsString, String[] answersString) {
        int[] solutions = new int[solutionsString.length];
        int[] answers = new int[answersString.length];

        try {
            for (int i = 0; i < solutionsString.length; i++) {
                solutions[i] = Integer.parseInt(solutionsString[i]);
                answers[i] = Integer.parseInt(answersString[i]);
            }

            return Arrays.equals(solutions, answers) ? Double.parseDouble(String.valueOf(qs.value())) : 0.0;

        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private Double evaluateDecimalQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutionsComma = new String[2];
        String[] answersComma = new String[2];
        String[] solutions = new String[2];
        String[] answers = new String[2];

        solutionsComma = qs.onlySolution().solutions().split(",");
        answersComma = qa.answer().split(",");

        if (solutionsComma.length != 2) {
            solutions = qs.onlySolution().solutions().split("\\.");
        }else{
            solutions = solutionsComma;
        }

        if (answersComma.length != 2) {
            answers = qa.answer().split("\\.");
        }else {
            answers = answersComma;
        }

        if (solutions[0].equals(answers[0]) && solutions[1].equals(answers[1])) {
            return Double.parseDouble(String.valueOf(qs.value()));
        } else {
            return 0.0;
        }
    }

    private Double evaluateNumberTypeQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String solution = qs.onlySolution().solutions();
        String answer = qa.answer();

        try {
            if (Integer.parseInt(solution) == Integer.parseInt(answer)) {
                return Double.parseDouble(String.valueOf(qs.value()));
            } else {
                return Double.parseDouble("0");
            }

        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return Double.parseDouble("0");
        }
    }

    private Double evaluateMultipleChoiceQuestion(QuestionSolution qs, QuestionAnswer qa) {
        List<Solution> solutionsList = qs.solutions();
        String[] answer = qa.answer().split(",");

        try {
            for (String ans : answer) {
                Integer.parseInt(ans);
            }

            Solution greater = new Solution("", 0.0);

            for (Solution sol : solutionsList) if (sol.value() == 1.0) greater = sol;

            if (greater.solutions().isEmpty() && greater.value() == 0.0) return 0.0;

            String[] greaterSolutions = greater.solutions().split(",");

            int countCorrectAnswers = 0;

            for (String ans : answer) if (Arrays.asList(greaterSolutions).contains(ans)) countCorrectAnswers++;

            if (countCorrectAnswers == greaterSolutions.length) return Double.parseDouble(String.valueOf(qs.value()));
            else if (countCorrectAnswers == 0) return 0.0;
            else {
                String[] correctAnswers = new String[countCorrectAnswers];
                for (int i = 0; i < answer.length; i++) {
                    for (String greaterSolution : greaterSolutions) {
                        if (answer[i].equals(greaterSolution)) {
                            correctAnswers[i] = answer[i];
                            break;
                        }
                    }
                }

                for (Solution sol : solutionsList) {
                    int counter = 0;
                    String[] s2 = sol.solutions().split(",");
                    for (int i = 0; i < correctAnswers.length; i++) {
                        if (s2.length == correctAnswers.length) {
                            if (s2[i].equals(correctAnswers[i])) counter++;
                        }
                    }

                    if (counter == correctAnswers.length) return sol.value();
                }
            }


        } catch (NumberFormatException e) {
            System.out.println("Solution value is not a number, please check!");
            return 0.0;
        }

        return 0.0;
    }

//    private Double evaluateMultipleChoiceQuestion(QuestionSolution qs, QuestionAnswer qa) {
//        List<Solution> solutionsList = qs.solutions();
//        String[] answer = qa.answer().split(",");
//
//        try {
//            for (String ans : answer) {
//                Integer.parseInt(ans);
//            }
//        } catch (NumberFormatException e) {
//            return 0.0;
//        }
//
//        Solution greater = new Solution("", 0.0);
//
//        for (Solution sol : solutionsList) {
//            String[] s = sol.solutions().split(",");
//            String[] great = greater.solutions().split(",");
//            if (s.length > great.length) {
//                greater = sol;
//            }
//        }
//
//        if (greater.solutions().equals(qa.answer())) {
//            return Double.parseDouble(String.valueOf(qs.value()));
//        } else {
//            String[] s1 = greater.solutions().split(",");
//            int count = 0;
//
//            for (String ans : answer) {
//                if (Arrays.asList(s1).contains(ans)) {
//                    count++;
//                }
//            }
//
//            Iterator<Solution> it = solutionsList.iterator();
//            while (it.hasNext()) {
//                Solution sol = it.next();
//                String[] s2 = sol.solutions().split(",");
//                if (s2.length != count) {
//                    it.remove(); // Remove using iterator to avoid ConcurrentModificationException
//                }
//            }
//
//            for (Solution sol : solutionsList) {
//                if (sol.solutions().equals(qa.answer())) {
//                    return sol.value();
//                }
//            }
//        }
//
//        return 0.0;
//    }

    private Double evaluateSingleChoiceQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String solution = qs.onlySolution().solutions();
        String[] answer = qa.answer().split(",");

        try {
            if (Integer.parseInt(solution) == Integer.parseInt(answer[0])) {
                return Double.parseDouble(String.valueOf(qs.value()));
            } else {
                return 0.0;
            }

        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private Double evaluateTextTypeQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String solution = qs.onlySolution().solutions();
        String answer = qa.answer().toLowerCase();

        if (solution.isEmpty() || answer.equalsIgnoreCase(solution)) {
            return Double.parseDouble(String.valueOf(qs.value()));
        } else {
            return 0.0;
        }
    }
}
