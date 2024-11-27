# UC032 - As Customer Manager, I want to execute the process that evaluates (grades) the interviews for a job opening.

# 4. Tests 
In this project, a Test-Driven Development (TDD) approach was used.

## 4.1 InterviewManagementServiceTest

```java
class InterviewManagementServiceTest {

    private InterviewManagementService service;
    
    private Question questionTrueFalse;
    private Question questionShortAnswer;
    private Question questionSingleChoice;
    private Question questionMultipleChoice;
    private Question questionDecimal;
    private Question questionDate;
    private Question questionTime;
    private Question questionNumericScale;

    private List<Solution> solutions;

    @BeforeEach
    void setUp() {
        service = new InterviewManagementService();
        
        questionTrueFalse = new Question("True/False question?", QuestionType.TFQUES, 1);
        questionShortAnswer = new Question("Short answer question?", QuestionType.SAQUES, 1);
        questionSingleChoice = new Question("Single choice question?", QuestionType.SCQUES, 1);
        questionMultipleChoice = new Question("Multiple choice question?", QuestionType.MCQUES, 1);
        questionDecimal = new Question("Decimal question?", QuestionType.DECQUES, 1);
        questionDate = new Question("Date question?", QuestionType.DATEQUES, 1);
        questionTime = new Question("Time question?", QuestionType.TIMEQUES, 1);
        questionNumericScale = new Question("Numeric scale question?", QuestionType.NSQUES, 1);
        
        solutions = new ArrayList<>();
    }
    
    @AfterEach
    void tearDown() {
        solutions.clear();
    }
}
```

**Test 1:** Evaluate question for True/False question type

```java
class InterviewManagementServiceTest {
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
}
```

**Test 2:** Evaluate question for Short Answer question type

```java
class InterviewManagementServiceTest {
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
}
```

**Test 3:** Evaluate question for Single Choice question type

```java
class InterviewManagementServiceTest {
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
        Double expected = 0.0;

        solutions.add(new Solution("1", 1.0));

        QuestionSolution questionSolution = new QuestionSolution(questionSingleChoice, solutions);
        QuestionAnswer questionAnswer = new QuestionAnswer(questionSingleChoice, "2");

        Double result = service.evaluateQuestion(questionSingleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result, "Output should be 0.0, because the answer is wrong.");
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
}
```

**Test 4:** Evaluate question for Multiple Choice question type

```java
class InterviewManagementServiceTest {
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
        Double expected = 1.0;

        solutions.add(new Solution("1", 0.4));
        solutions.add(new Solution("2", 0.8));
        solutions.add(new Solution("1,2", 1.0));
        
        QuestionSolution questionSolution = new QuestionSolution(questionMultipleChoice, "1,2", "1");
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
        QuestionAnswer questionAnswer = new QuestionAnswer(questionMultipleChoice, "2");

        Double result = service.evaluateQuestion(questionMultipleChoice.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }
}
```

**Test 5:** Evaluate question for Integer question type

```java
class InterviewManagementServiceTest {
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
}
```

**Test 6:** Evaluate question for Decimal question type

```java
class InterviewManagementServiceTest {
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
        Double expected = 1.0;
        
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

        QuestionSolution questionSolution = new QuestionSolution(questionDecimal, "1.2", "1");
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDecimal, "1,2");

        Double result = service.evaluateQuestion(questionDecimal.type(), questionSolution, questionAnswer);

        assertEquals(expected, result);
    }
}
```

**Test 7:** Evaluate question for Date question type

```java
class InterviewManagementServiceTest {
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
        
        QuestionSolution questionSolution = new QuestionSolution(questionDate, "01/01/2021", "1");
        QuestionAnswer questionAnswer = new QuestionAnswer(questionDate, "1/1/2021");
        
        Double result = service.evaluateQuestion(questionDate.type(), questionSolution, questionAnswer);
        
        assertEquals(expected, result);
    }
}
```

**Test 8:** Evaluate question for Time question type

```java
class InterviewManagementServiceTest {
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
        
        QuestionSolution questionSolution = new QuestionSolution(questionTime, "00:00", "1");
        QuestionAnswer questionAnswer = new QuestionAnswer(questionTime, "24:00");
        
        Double result = service.evaluateQuestion(questionTime.type(), questionSolution, questionAnswer);
        
        assertEquals(expected, result);
    }
}
```

**Test 9:** Check evaluateNumericScaleQuestion() works

```java
class InterviewManagementServiceTest {
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
}
```

## 4.2 InterviewModelParserTest
```java
class InterviewModelParserTest {
    Path file_template_1;
    List<QuestionSolution> questionSolutionList;
    List<QuestionAnswer> questionAnswersList;

    @BeforeEach
    void setUp() {
        //files
        file_template_1 = Paths.get("src/test/interviewModel/interviews/template-1.txt");

        //lists
        questionSolutionList = new ArrayList<>();
        questionAnswersList = new ArrayList<>();

        //needed parameters
        List<Solution> solutionsList = new ArrayList<>();
        Set<String> solutionsSet = new HashSet<>();

        //template_1
        solutionsSet.add("31/12/2020");
        Solution solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        Question question = new Question("DATEQUES [DD/MM/YYYY] \"When it's the last day of the year 2020?\" [10];",
                10, QuestionType.DATEQUES);
        QuestionSolution questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("3.14");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("DECQUES [Decimal] \"Write the first 3 digits of pi.\" [10];",
                10, QuestionType.DECQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("32767");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("INTQUES[Integer] \"What is the biggest integer that can be stored in a 16 bit signed integer?\"[10];",
                10, QuestionType.INTQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("4");
        solution = new Solution(solutionsSet, 0.8);
        solutionsList.add(solution);
        solutionsSet.clear();
        solutionsSet.add("5");
        solution = new Solution(solutionsSet, 0.4);
        solutionsList.add(solution);
        solutionsSet.clear();
        solutionsSet.add("C#");
        solutionsSet.add("Typescript");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("MCQUES[Options] \"What is the best programing language for the system XPTO?\"[10];",
                10, QuestionType.MCQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("5");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("NSQUES[1 - 5] \"How much do you love ISEP?\"[10];",
                10, QuestionType.NSQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("January");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("SAQUES[Text] \"What's the first month of the year?\"[10];",
                10, QuestionType.SAQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("1");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("SCQUES[Option] \"What does the term informatics primarily refer to?\"[10];",
                10, QuestionType.SCQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("12:00");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("TIMEQUES[HH:MM] \"The middle of the day is at what time.\"[10];",
                10, QuestionType.TIMEQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);

        solutionsSet.clear();
        solutionsList.clear();

        solutionsSet.add("FALSE");
        solution = new Solution(solutionsSet, 1.0);
        solutionsList.add(solution);
        question = new Question("TFQUES[TRUE | FALSE] \"Informatics engineering exclusively focuses on creating software applications.\"[10];",
                10, QuestionType.TFQUES);
        questionSolution = new QuestionSolution(question, solutionsList);
        questionSolutionList.add(questionSolution);
    }
}
```

**Test 1:** Check visit() for Job Opening works

```java
public class InterviewModelParserTest {    
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
                        assertEquals(q1.solutions().get(i).solution(), q2.solutions().get(i).solution());
                        assertEquals(q1.solutions().get(i).value(), q2.solutions().get(i).value());
                    }
                }
            }
        }
    }
}
```

**Test 2:** Check visit() for Job Application works

```java
public class InterviewModelParserTest {    
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
                        assertEquals(q1.solutions().get(i).solution(), q2.solutions().get(i).solution());
                        assertEquals(q1.solutions().get(i).value(), q2.solutions().get(i).value());
                    }
                }
            }
        }
    }
}
```

# 5. Construction (Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class EvaluateJobOpeningsInterviewsUI

```java
public class EvaluateJobOpeningsInterviewsUI extends AbstractUI {

    private final EvaluateJobOpeningsInterviewsController controller = new EvaluateJobOpeningsInterviewsController();

    public boolean doShow() {
        List<JobOpening> jobOpenings = controller.jobOpeningsWithInterviewPhaseCompleted();
        JobOpening jobOpening;

        do {
            SelectWidget<JobOpening> selector = new SelectWidget<>("Select Job Opening", jobOpenings, new JobOpeningPrinter());
            selector.show();
            jobOpening = selector.selectedElement();

            if (jobOpening == null) System.out.println("Job Opening invalid! Try again.");
        } while (jobOpening == null);

        Iterable<JobApplication> jobApplicationsIterable = controller.jobApplicationsOf(jobOpening);
        List<JobApplication> jobApplications = new ArrayList<>();
        jobApplicationsIterable.forEach(jobApplications::add);

        List<JobApplication> grades = new ArrayList<>();

        try {
            grades = controller.evaluateInterviews(jobApplications, jobOpening);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

        if (grades.isEmpty()) {
            System.out.println("Something went wrong!\n\nThere are no interviews to evaluate...");
            return false;
        }

        System.out.printf("Interview's Grades for Job Opening %s:\n\n", jobOpening.identity());
        for (JobApplication jobApplication : grades) {
            System.out.println(jobApplication.identity() + " - " + jobApplication.interview().grade().grade());
        }

        boolean save = Console.readBoolean("Do you want to save this grades? (Y/N)");

        if (save) controller.saveGrades(grades);
        else {
            System.out.println("Grades will not be saved.");
            for (JobApplication jobApplication : grades) {
                jobApplication.interview().grade().reset();
            }
        }

        return false;
    }

    public String headline() {
        return "Evaluate Job Opening's Interviews";
    }
}
```

## Class EvaluateJobOpeningsInterviewsController

```java
public class EvaluateJobOpeningsInterviewsController {
    private final JobApplicationRepository jobApplicationRepository = PersistenceContext.repositories().jobApplications();
    private final JobOpeningRepository jobOpeningRepository = PersistenceContext.repositories().jobOpenings();
    private final InterviewModelRepository interviewModelRepository = PersistenceContext.repositories().interviewModels();

    private final JobApplicationManagementService jobApplicationManagementService = new JobApplicationManagementService(jobApplicationRepository);
    private final JobOpeningManagementService jobOpeningManagementService = new JobOpeningManagementService(jobOpeningRepository);
    private final InterviewManagementService interviewManagementService = new InterviewManagementService(interviewModelRepository);

    public List<JobOpening> jobOpeningsWithInterviewPhaseCompleted() {
        return jobOpeningManagementService.jobOpeningsWithPhaseInterviewCompleted();
    }

    public Iterable<JobApplication> jobApplicationsOf(JobOpening jobOpening) {
        return jobApplicationManagementService.jobApplicationsOf(jobOpening);
    }

    public List<JobApplication> evaluateInterviews(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {
        return interviewManagementService.evaluateInterviews(jobApplicationList, jobOpening);
    }

    public void saveGrades(List<JobApplication> grades) {
        jobApplicationManagementService.saveGrades(grades);
    }
}
```

## Class JobApplicationManagementService

```java
public class JobApplicationManagementService {
    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationsManagementService(JobApplicationRepository jobApplicationRepository) {
        this.jobApplicationRepository = jobApplicationRepository;
    }

    public void saveGrades(List<JobApplication> grades) {
        for (JobApplication jobApplication : grades) {
            jobApplicationRepo.save(jobApplication);
        }
    }
}
```

## Class JobOpeningManagementService

```java
public List<JobOpening> jobOpeningsWithPhaseInterviewCompleted() {
    Iterable<JobOpening> allJobOpenings = jobOpeningsWithPhaseOn(Phase.ANALYSIS);
    List<JobOpening> jobOpenings = new ArrayList<>();
    allJobOpenings.forEach(jobOpenings::add);

    jobOpenings.removeIf(jobOpening -> !jobOpening.phases().containsKey(Phase.INTERVIEW));

    return jobOpenings;

}
```

## Class InterviewManagementService

```java
public class InterviewManagementService {
    public final Path pathInterviewModel;
    public final Path pathInterview;
    public final InterviewModelParser interviewModelParser = new InterviewModelParser();
    public final InterviewModelRepository interviewModelRepository;
    
    public InterviewManagementService(InterviewModelRepository interviewModelRepository) {
        this.interviewModelRepository = interviewModelRepository;
    }


    public List<JobApplication> evaluateInterviews(List<JobApplication> jobApplicationList, JobOpening jobOpening) throws Exception {
        Path pathInterviewModel = Paths.get("LPROG/interviewModels/" + jobOpening.interviewModel().identity() + ".txt");
        List<QuestionSolution> questionWithSolution = parser.interviewJobOpening(pathInterviewModel);

        InterviewGrade totalGrade = obtainInterviewModelTotalGrade(questionWithSolution);

        List<JobApplication> grades = new ArrayList<>();

        for (JobApplication jobApplication : jobApplicationList) {
            Path pathInterview = Paths.get("SCOMP/fileBotFiles/" + jobApplication.identity() + "/" +
                    jobApplication.candidate().identity() + ".txt");

            List<QuestionAnswer> questionWithAnswer = parser.interviewJobApplication(pathInterview);

            InterviewGrade interviewGrade = new InterviewGrade(0.0);

            for (QuestionSolution qs : questionWithSolution) {
                for (QuestionAnswer qa : questionWithAnswer) {
                    if (qs.question().equals(qa.question())) {
                        switch (qs.type()) {
                            case TFQUES, SAQUES:
                                interviewGrade.incrementGrade(evaluateTextTypeQuestion(qs, qa));
                                break;
                            case SCQUES:
                                interviewGrade.incrementGrade(evaluateSingleChoiceQuestion(qs, qa));
                                break;
                            case MCQUES:
                                interviewGrade.incrementGrade(evaluateMultipleChoiceQuestion(qs, qa));
                                break;
                            case INTQUES, NSQUES:
                                interviewGrade.incrementGrade(evaluateNumberTypeQuestion(qs, qa));
                                break;
                            case DECQUES:
                                interviewGrade.incrementGrade(evaluateDecimalQuestion(qs, qa));
                                break;
                            case DATEQUES:
                                interviewGrade.incrementGrade(evaluateDateQuestion(qs, qa));
                                break;
                            case TIMEQUES:
                                interviewGrade.incrementGrade(evaluateTimeQuestion(qs, qa));
                                break;
                            default:
                                interviewGrade.incrementGrade(0.0);
                                break;
                        }
                    }
                }
            }

            InterviewGrade finalGrade = interviewGrade.gradeInPercentage(totalGrade);

            jobApplication.interview().defineGrade(finalGrade);
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


    private Double evaluateTimeQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutions = qs.onlySolution().solutions().split(":");
        String[] answers = qa.answer().split(":");

        if (answers[0].equals("24")) {
            answers[0] = "00";
        }

        return solutions[0].equals(answers[0]) && solutions[1].equals(answers[1]) ? Double.parseDouble(String.valueOf(qs.value())) : 0.0;
    }

    private Double evaluateDateQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutions = qs.onlySolution().solutions().split("/");
        String[] answers = qa.answer().split("/");

        return solutions[0].equals(answers[0]) &&
                solutions[1].equals(answers[1]) && solutions[2].equals(answers[2]) ? Double.parseDouble(String.valueOf(qs.value())) : 0.0;
    }

    private Double evaluateDecimalQuestion(QuestionSolution qs, QuestionAnswer qa) {
        String[] solutions = qs.onlySolution().solutions().split(",");
        String[] answers = qa.answer().split(",");

        if (solutions.length != 2) {
            solutions = qs.onlySolution().solutions().split("/.");
        }

        if (answers.length != 2) {
            answers = qa.answer().split("/.");
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
            return Double.parseDouble("0");
        }
    }

    private Double evaluateMultipleChoiceQuestion(QuestionSolution qs, QuestionAnswer qa) {
        List<Solution> solutions = qs.solutions();
        String[] answer = qa.answer().split(",");

        try {
            for (String ans : answer) {
                Integer.parseInt(ans);
            }
        } catch (NumberFormatException e) {
            return 0.0;
        }

        Solution greater = new Solution("", 0.0);

        for (Solution sol : solutions) {
            String[] s = sol.solutions().split(",");
            String[] great = greater.solutions().split(",");
            if (s.length > great.length) {
                greater = sol;
            }
        }

        if (greater.solutions().equals(qa.answer())) {
            return Double.parseDouble(String.valueOf(qs.value()));
        } else {
            String[] s1 = greater.solutions().split(",");
            int count = 0;

            for (String ans : answer) {
                if (List.of(s1).contains(ans)) {
                    count++;
                }
            }

            for (Solution sol : solutions){
                String[] s2 = sol.solutions().split(",");
                if (s2.length != count){
                    solutions.remove(sol);
                }
            }

            for (Solution sol : solutions){
                if (sol.solutions().equals(qa.answer())){
                    return Double.parseDouble(String.valueOf(qs.value()));
                }
            }
        }

        return 0.0;
    }

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

```

## Class InterviewModelParser

```java
public class InterviewModelParser {
    public List<QuestionSolution> interviewJobOpening(Path path) throws Exception {
        CharStream input = CharStreams.fromPath(path);
        InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

        ParseTree tree = parser.start(); // Start parsing at the root of the grammar
        InterviewModelVisitor visitor = new InterviewModelVisitor();
        visitor.visit(tree); // Visit the parse tree with the visitor

        return visitor.interviewJobOpening();
    }

    public List<QuestionAnswer> interviewJobApplication(Path path) throws Exception {
        CharStream input = CharStreams.fromPath(path);
        InterviewModelGrammarLexer lexer = new InterviewModelGrammarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        InterviewModelGrammarParser parser = new InterviewModelGrammarParser(tokens);

        ParseTree tree = parser.start(); // Start parsing at the root of the grammar
        InterviewModelVisitor visitor = new InterviewModelVisitor();
        visitor.visit(tree); // Visit the parse tree with the visitor

        return visitor.interviewJobApplication();
    }
}
```

# 6. Integration and Demo 

* A new option in the backoffice users menu was added. However, to register _backoffice users_, the authenticated user must have admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.