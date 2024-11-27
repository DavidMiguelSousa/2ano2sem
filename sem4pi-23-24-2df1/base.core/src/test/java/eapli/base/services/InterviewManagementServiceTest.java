package eapli.base.services;

import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import question.*;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class InterviewManagementServiceTest {
    @InjectMocks
    InterviewManagementService service;

    String testFileString;
    String testFileName;
    Path destinationDownload;
    String destinationPath;

    //questions
    private Question questionTrueFalse;
    private Question questionShortAnswer;
    private Question questionSingleChoice;
    private Question questionMultipleChoice;
    private Question questionInteger;
    private Question questionDecimal;
    private Question questionDate;
    private Question questionTime;
    private Question questionNumericScale;

    //solutions
    private List<Solution> solutions;

    @BeforeEach
    void setup() {

        InterviewModelRepository repo = mock(InterviewModelRepository.class);
        service = new InterviewManagementService(repo);


        // File paths
        testFileString = service.searchInterviewModels("PythonProgrammer.txt");
        testFileName = "PythonProgrammer.txt";
        destinationDownload = Paths.get(System.getProperty("user.home"), "Downloads");
        destinationPath = "\\SCOMP\\files\\fileBotFiles\\ISEP-001\\candidate@gmail.com";

        //questions
        questionTrueFalse = new Question("True/False question?", 1, QuestionType.TFQUES);
        questionShortAnswer = new Question("Short answer question?", 1, QuestionType.SAQUES);
        questionSingleChoice = new Question("Single choice question?", 1, QuestionType.SCQUES);
        questionMultipleChoice = new Question("Multiple choice question?", 1, QuestionType.MCQUES);
        questionInteger = new Question("Integer question?", 1, QuestionType.INTQUES);
        questionDecimal = new Question("Decimal question?", 1, QuestionType.DECQUES);
        questionDate = new Question("Date question?", 1, QuestionType.DATEQUES);
        questionTime = new Question("Time question?", 1, QuestionType.TIMEQUES);
        questionNumericScale = new Question("Numeric scale question?", 1, QuestionType.NSQUES);

        //solutions
        solutions = new ArrayList<>();
    }

//    @Test
//    public void verifyExportTextInterview() {
//        boolean expected = true;
//        assertEquals(expected, service.exportTextInterview(testFileString, testFileName));
//    }
//
//    @Test
//    public void verifyImportTextInterview() {
//        boolean expected = true;
//        assertEquals(expected, service.importTextInterview(String.valueOf(destinationDownload), destinationPath.toString(), testFileName));
//    }

    @Test
    void ensureEvaluateTrueFalseQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("TRUE", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTrueFalse, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTrueFalse, "false");

        Double result = service.evaluateQuestion(questionTrueFalse.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is wrong.");
    }

    @Test
    void ensureEvaluateTrueFalseQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("false", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTrueFalse, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTrueFalse, "FALSE");

        Double result = service.evaluateQuestion(questionTrueFalse.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 1.0, because the answer is correct.");
    }

    @Test
    void ensureEvaluateShortAnswerQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("this answer", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionShortAnswer, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionShortAnswer, "that answer");

        Double result = service.evaluateQuestion(questionShortAnswer.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is wrong.");
    }

    @Test
    void ensureEvaluateShortAnswerQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("this answer", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionShortAnswer, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionShortAnswer, "THIS ANSWER");

        Double result = service.evaluateQuestion(questionShortAnswer.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is wrong.");
    }

    @Test
    void ensureEvaluateSingleChoiceQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionSingleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionSingleChoice, "2");

        Double result = service.evaluateQuestion(questionSingleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is wrong.");
    }

    @Test
    void ensureEvaluateSingleChoiceQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionSingleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionSingleChoice, "1");

        Double result = service.evaluateQuestion(questionSingleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 1.0, because the answer is correct.");
    }

    @Test
    void ensureEvaluateSingleChoiceQuestionReturnsZeroWhenNotNumber() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionSingleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionSingleChoice, "number");

        Double result = service.evaluateQuestion(questionSingleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is not a number.");
    }

    @Test
    void ensureEvaluateMultipleChoiceQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "3");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the option is wrong.");
    }

    @Test
    void ensureEvaluateMultipleChoiceQuestionReturnsOneCorrectOrder() {
        Double expected = 1.0;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "1,2");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateMultipleChoiceQuestionReturnsOneDifferentOrder() {
        Double expected = 1.0;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "2,1");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateMultipleChoiceQuestionReturnsZeroWhenNotNumber() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "answer");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateMultipleChoiceQuestionReturnsDouble() {
        Double expected = 0.8;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "2,3");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateIntegerQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionInteger, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionInteger, "2");

        Double result = service.evaluateQuestion(questionInteger.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateIntegerQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.clear();
        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionInteger, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionInteger, "1");

        Double result = service.evaluateQuestion(questionInteger.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateIntegerQuestionReturnsZeroForString(){
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionInteger, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionInteger, "answer");

        Double result = service.evaluateQuestion(questionInteger.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsZeroWithComma() {
        Double expected = 0.0;

        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "3,4");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsOneWithComma() {
        Double expected = 1.0;

        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "1,2");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsZeroForString(){
        Double expected = 0.0;

        solutions.add(new Solution("1,2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "answer");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsZeroWithDot(){
        Double expected = 0.0;

        solutions.add(new Solution("1.2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "3.4");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsOneWithDot(){
        Double expected = 1.0;

        solutions.add(new Solution("1.2", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "1.2");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDecimalQuestionReturnsOneForDotAndCommaTogether() {
        Double expected = 0.0;

        solutions.add(new Solution("12", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "1,2");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDateQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("01/01/2021", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDate, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDate, "02/01/2021");

        Double result = service.evaluateQuestion(questionDate.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDateQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("01/01/2021", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDate, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDate, "01/01/2021");

        Double result = service.evaluateQuestion(questionDate.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateDateQuestionReturnsOneForSingleNumber(){
        Double expected = 1.0;

        solutions.add(new Solution("01/01/2021", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionDate, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDate, "1/1/2021");

        Double result = service.evaluateQuestion(questionDate.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateTimeQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("09:00", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTime, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTime, "13:00");

        Double result = service.evaluateQuestion(questionTime.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateTimeQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("09:00", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTime, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTime, "09:00");

        Double result = service.evaluateQuestion(questionTime.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateTimeQuestionReturnsOneForSingleNumber(){
        Double expected = 1.0;

        solutions.add(new Solution("09:00", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTime, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTime, "9:00");

        Double result = service.evaluateQuestion(questionTime.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateTimeQuestionRecognizes24as00(){
        Double expected = 1.0;

        solutions.add(new Solution("00:00", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionTime, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTime, "24:00");

        Double result = service.evaluateQuestion(questionTime.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateNumericScaleQuestionReturnsZero() {
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionNumericScale, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionNumericScale, "3");

        Double result = service.evaluateQuestion(questionNumericScale.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateNumericScaleQuestionReturnsOne() {
        Double expected = 1.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionNumericScale, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionNumericScale, "1");

        Double result = service.evaluateQuestion(questionNumericScale.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @Test
    void ensureEvaluateNumericScaleQuestionReturnsZeroForString(){
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionNumericScale, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionNumericScale, "answer");

        Double result = service.evaluateQuestion(questionNumericScale.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }

    @AfterEach
    void tearDown(){
        solutions.clear();
    }
}
