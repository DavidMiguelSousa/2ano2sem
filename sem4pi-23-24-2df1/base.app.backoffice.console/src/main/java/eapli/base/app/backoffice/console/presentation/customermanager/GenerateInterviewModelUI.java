package eapli.base.app.backoffice.console.presentation.customermanager;

import question.QuestionType;
import eapli.base.services.ConsoleUtils;
import eapli.base.usermanagement.application.GenerateInterviewModelController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.EnumSelectWidget;
import eapli.framework.presentation.console.SelectWidget;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GenerateInterviewModelUI extends AbstractUI {

    private final GenerateInterviewModelController controller = new GenerateInterviewModelController();

    @Override
    protected boolean doShow() {
        final String nameOfModel = Console.readLine("Name of the interview model:");
        final String fileName = nameOfModel.replaceAll(" ", "");
        final char temp = fileName.charAt(0);
        fileName.replace(fileName.charAt(0), Character.toLowerCase(temp));
        try {
            if (controller.createFile(fileName)) {
                System.out.println("File created: " + fileName + ".txt");
            } else {
                System.out.println("File already exists.");
            }
        } catch (Exception e) {
            System.out.println("File not created.");
            throw new RuntimeException(e);
        }

        controller.addTitle(nameOfModel);
        double totalGrade = 0;
        Map<String, Double> questionsAndGrades = new HashMap<>();
        do {
            EnumSelectWidget<QuestionType> questionTypeWidget = new EnumSelectWidget<>("Question Type", QuestionType.class);
            questionTypeWidget.show();
            QuestionType questionType = questionTypeWidget.selectedElement();

            String question = Console.readLine("Question:");
            double grade = ConsoleUtils.readDoubleInScale("Question value:", 1, 100);
            questionsAndGrades.put(question, grade);
            totalGrade += grade;

            controller.addQuestion(questionType, question, grade);

            Set<String> options = new HashSet<>();
            Set<Map<String, Double>> solutions = new HashSet<>();

            readOptionsAndSolutions(questionType, options, solutions);

            controller.addOptions(options);
            controller.addSolutions(solutions);
        } while (totalGrade < 100 && Console.readBoolean("Do you want to add another question? (y/n)"));

        controller.validateTotalGrade(questionsAndGrades, totalGrade);

        if (controller.writeInFile()) System.out.println("File content written successfully.");
        else System.out.println("File content not written. Please try again later.");

        return true;
    }

    private void readOptionsAndSolutions(QuestionType questionType, Set<String> options, Set<Map<String, Double>> solutions) {
        switch (questionType) {
            case DATEQUES:
                solutions.add(Map.of(String.valueOf(Console.readDate("Correct answer (yyyy/MM/dd):")), 1.0));
                break;
            case INTQUES:
                solutions.add(Map.of(String.valueOf(Console.readInteger("Correct answer (Integer):")), 1.0));
                break;
            case TFQUES:
                solutions.add(Map.of(String.valueOf(Console.readBoolean("Correct answer (y/n):")).toUpperCase(), 1.0));
                break;
            case TIMEQUES:
                solutions.add(Map.of(String.valueOf(ConsoleUtils.readTime("Correct answer (HH:mm):")), 1.0));
                break;
            case DECQUES:
                solutions.add(Map.of(String.valueOf(Console.readDouble("Correct answer (Decimal):")), 1.0));
                break;
            case NSQUES:
//                int min = Console.readInteger("Minimum value:");
//                int max = Console.readInteger("Maximum value:");
                int min = 1;
                int max = 5;
                solutions.add(Map.of(String.valueOf(ConsoleUtils.readIntegerInScale("Correct answer (" + min + "-" + max + "):", min, max)), 1.0));
                break;
            case SAQUES:
                solutions.add(Map.of(Console.readLine("Correct answer:"), 1.0));
                break;
            case MCQUES:
                readOptions(options);
                readMCQUESGrades(options, solutions);
                break;
            case SCQUES:
                readOptions(options);
                SelectWidget<String> optionsWidget = new SelectWidget<>("Select a correct answer:", options);
                optionsWidget.show();
                solutions.add(Map.of(optionsWidget.selectedElement(), 1.0));
                break;
            default:
                System.out.println("Invalid question type.");
                break;
        }
    }

    private void readOptions(Set<String> options) {
        int numOptions = Console.readInteger("Number of options:");
        for (int i = 0; i < numOptions; i++) {
            options.add(Console.readLine("Option " + (i + 1) + ":"));
        }
    }

    private void readMCQUESGrades(Set<String> options, Set<Map<String, Double>> solutions) {
        System.out.println("Define the grade system:");
        String correctAnswersString = readCorrectAnswers(options);
        solutions.add(Map.of(correctAnswersString, 1.0));
        System.out.println("Define the grade system. If the candidate selects all options above, he will receive 100% of the grade - 1.\n" +
                "Let's define what he/she should receive for each set of correct answers.");
        do {
            correctAnswersString = readCorrectAnswers(options);
            double grade = ConsoleUtils.readDoubleInScale("Grade (0-1):", 0, 1);
            solutions.add(Map.of(correctAnswersString, grade));
        } while (Console.readBoolean("Do you want to grade another set of answers? (y/n)"));
    }

    private String readCorrectAnswers(Set<String> options) {
        Set<String> optionsCopy = new HashSet<>(Set.copyOf(options));
        Set<String> correctAnswers = new HashSet<>();
        SelectWidget<String> optionsWidget;
        do {
            optionsWidget = new SelectWidget<>("Select a correct answer:", optionsCopy);
            optionsWidget.show();
            correctAnswers.add(optionsWidget.selectedElement());
            optionsCopy.remove(optionsWidget.selectedElement());
        } while (!optionsCopy.isEmpty() && Console.readBoolean("Do you want to add another correct answer? (y/n)"));
        return String.join(", ", correctAnswers);
    }

    @Override
    public String headline() {
        return "Generate Interview Model";
    }
}
