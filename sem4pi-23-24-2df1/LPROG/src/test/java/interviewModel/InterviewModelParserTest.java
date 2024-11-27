package interviewModel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import question.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class InterviewModelParserTest {
    Path file_template_1;
    List<QuestionSolution> questionSolutionList;
    List<QuestionAnswer> questionAnswersList;

    @BeforeEach
    void setUp() {
        //builder
        StringBuilder builder = new StringBuilder();

        //files
        file_template_1 = Paths.get("src/test/interviewModel/interviews/template-1.txt");

        //lists
        questionSolutionList = new ArrayList<>();
        questionAnswersList = new ArrayList<>();

        //needed parameters
        List<Solution> solutionsList = new ArrayList<>();

        //template_1
        builder.append("31/12/2020");
        Solution solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        Question question = new Question("DATEQUES [DD/MM/YYYY] \"When it's the last day of the year 2020?\" [10];",
                10, QuestionType.DATEQUES);
        QuestionSolution questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("3.14");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("DECQUES [Decimal] \"Write the first 3 digits of pi.\" [10];",
                10, QuestionType.DECQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("32767");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("INTQUES [Integer] \"What is the biggest integer that can be stored in a 16 bit signed integer?\"[10];",
                10, QuestionType.INTQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("4");
        solution = new Solution(builder.toString(), 0.8);
        solutionsList.add(solution);
        builder = new StringBuilder();
        builder.append("5");
        solution = new Solution(builder.toString(), 0.4);
        solutionsList.add(solution);
        builder = new StringBuilder();
        builder.append("C#").append(",").append("Typescript");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("MCQUES [Options] \"What is the best programing language for the system XPTO?\"[10];",
                10, QuestionType.MCQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("5");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("NSQUES [1-5] \"How much do you love ISEP?\"[10];",
                10, QuestionType.NSQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("January");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("SAQUES [Text] \"What's the first month of the year?\"[10];",
                10, QuestionType.SAQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("1");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("SCQUES [Option] \"What does the term informatics primarily refer to?\"[10];",
                10, QuestionType.SCQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("12:00");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("TIMEQUES [HH:MM] \"The middle of the day is at what time.\"[10];",
                10, QuestionType.TIMEQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        builder = new StringBuilder();
        solutionsList.clear();

        builder.append("FALSE");
        solution = new Solution(builder.toString(), 1.0);
        solutionsList.add(solution);
        question = new Question("TFQUES [TRUE|FALSE] \"Informatics engineering exclusively focuses on creating software applications.\"[10];",
                10, QuestionType.TFQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);
    }

    @Test
    void interviewJobOpening() throws Exception {
        InterviewModelParser interviewModelParser = new InterviewModelParser();

        List<QuestionSolution> expected = interviewModelParser.interviewJobOpening(file_template_1);

        for (QuestionSolution q1 : expected) {
            for (QuestionSolution q2 : questionSolutionList) {
                if (q1.equals(q2)) {
                    assertEquals(q1.question(), q2.question());
                    assertEquals(q1.solutions().size(), q2.solutions().size());
                    for (int i = 0; i < q1.solutions().size(); i++) {
                        assertEquals(q1.solutions().get(i).solutions(), q2.solutions().get(i).solutions());
                        assertEquals(q1.solutions().get(i).value(), q2.solutions().get(i).value());
                    }
                }
            }
        }
    }

//    @Test
//    void interviewJobApplication() throws Exception {
//        //TODO: create the file test with answers
//        InterviewModelParser interviewModelParser = new InterviewModelParser();
//
//        List<QuestionAnswer> expected = interviewModelParser.interviewJobApplication(file_template_1);
//
//        for (QuestionAnswer q1 : expected) {
//            for (QuestionAnswer q2 : questionAnswersList) {
//                if (q1.equals(q2)) {
//                    assertEquals(q1.question(), q2.question());
//                    assertEquals(q1.answer(), q2.answer());
//                }
//            }
//        }
//    }
}