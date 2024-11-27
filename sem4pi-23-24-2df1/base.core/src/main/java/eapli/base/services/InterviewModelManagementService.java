package eapli.base.services;

import eapli.base.interviewmanagement.domain.InterviewToken;
import question.QuestionType;
import eapli.framework.general.domain.model.Description;

import java.io.File;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

public class InterviewModelManagementService {

    private static final String filePath = "LPROG/interviewModels/";

    private static final String fileExtension = ".txt";

    private static File file;

    private static Description description;

    public boolean createFile(String name) {
        try {
            file = new File(filePath + name + fileExtension);
            return file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void addTitle(String title) {
        description = Description.valueOf(String.format(InterviewToken.INTERVIEW.token() + " \"%s\"", title));
    }

    public void addQuestion(QuestionType questionType, String questionText, Double value) {
        if (!questionText.endsWith("?") && !questionText.endsWith(".")) questionText = questionText.concat(".");
        description = Description.valueOf(description.toString().concat(String.format("%n%n%s \"%s\" [%d];", questionType.token(), questionText, value.intValue())));
    }

    public void addOptions(Set<String> options) {
        int i = 1;
        for (String option : options) {
            description = Description.valueOf(description.toString().concat(String.format("%n%d-" + InterviewToken.OPTION.token() + " %s;", i, option)));
            i++;
        }
    }

    public void addSolutions(Set<Map<String, Double>> solutions) {
        for (Map<String, Double> solution : solutions) {
            for (Map.Entry<String, Double> entry : solution.entrySet()) {
                if (entry.getValue().intValue() == 1) description = Description.valueOf(description.toString().concat(String.format("%n" + InterviewToken.SOLUTION.token() + " <%s|%d>;", entry.getKey(), entry.getValue().intValue())));
                else if (entry.getValue().toString().matches("\\\\d\\\\.\\\\d")) description = Description.valueOf(description.toString().concat(String.format("%n" + InterviewToken.SOLUTION.token() + " <%s|%.1f>;", entry.getKey(), entry.getValue())));
                else description = Description.valueOf(description.toString().concat(String.format("%n" + InterviewToken.SOLUTION.token() + " <%s|%.2f>;", entry.getKey(), entry.getValue())));
            }
        }
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

    public void validateTotalGrade(Map<String, Double> questionsAndGrades, double totalGrade) {
        double temp = 100;
        if (totalGrade != temp) questionsAndGrades.replaceAll((k, v) -> (v * temp / totalGrade));
    }
    
}