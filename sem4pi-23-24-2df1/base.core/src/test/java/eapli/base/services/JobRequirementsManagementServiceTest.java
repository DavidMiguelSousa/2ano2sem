package eapli.base.services;

import eapli.base.clientusermanagement.domain.jobapplication.JobApplication;
import eapli.base.clientusermanagement.domain.jobapplication.JobApplicationReference;
import eapli.base.clientusermanagement.domain.jobopening.JobOpening;
import eapli.base.clientusermanagement.repositories.JobRequirementsRepository;
import eapli.base.interviewmanagement.repositories.InterviewModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import question.*;
import requirements.Requirement;
import requirements.RequirementAnswer;
import requirements.RequirementSolution;
import requirements.RequirementType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JobRequirementsManagementServiceTest {
    @InjectMocks
    JobRequirementsManagementService service;

    String testFileString;
    String testFileName;
    Path destinationDownload;
    String destinationPath;

    private Requirement questionMinInt;
    private Requirement questionMaxInt;
    private Requirement questionMinOrd;
    private Requirement questionMaxOrd;
    private Requirement questionMulOrSing;

    private List<Solution> solutions;

    private List<JobApplication> jobApplicationList;


    @BeforeEach
    void setup() {
        JobRequirementsRepository repo = mock(JobRequirementsRepository.class);

        service = new JobRequirementsManagementService(repo);


        // File paths
        testFileString = service.searchJobRequirements("javaProgrammer.txt");
        testFileName = "javaProgrammer.txt";
        destinationDownload = Paths.get(System.getProperty("user.home"), "Downloads");
        destinationPath = "\\SCOMP\\files\\fileBotFiles\\ISEP-001\\candidate@gmail.com";

        questionMinInt = new Requirement("Minimum integer question?",  RequirementType.MININT);
        questionMaxInt = new Requirement("Maximum integer question?",  RequirementType.MAXINT);
        questionMinOrd = new Requirement("Minimum ordinal question?",  RequirementType.MINORD);
        questionMaxOrd = new Requirement("Maximum ordinal question?",  RequirementType.MAXORD);
        questionMulOrSing = new Requirement("Multiple or single choice question?",  RequirementType.MULORSING);

        solutions = new ArrayList<>();

    }

    @Test
    public void verifyExportTextInterview() {
        boolean expected = true;
        assertEquals(expected, service.exportJobRequirements(testFileString, testFileName));
    }

    @Test
    public void verifyImportTextInterview() {
        boolean expected = true;
        assertEquals(expected, service.importTextJobRequirements(String.valueOf(destinationDownload), destinationPath.toString(), testFileName));
    }

    @Test
    void ensureVerifyMinIntAccepted() {
        boolean expected = true;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 6");

        boolean result = service.evaluateQuestion(RequirementType.MININT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

    @Test
    void ensureVerifyMinIntDeclined() {
        boolean expected = false;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 4");

        boolean result = service.evaluateQuestion(RequirementType.MININT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }

    @Test
    void ensureVerifyMaxIntAccepted() {
        boolean expected = true;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 4");

        boolean result = service.evaluateQuestion(RequirementType.MAXINT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

    @Test
    void ensureVerifyMaxIntDeclined() {
        boolean expected = false;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 6");

        boolean result = service.evaluateQuestion(RequirementType.MAXINT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }

    @Test
    void ensureVerifyMinOrdAccepted() {
        boolean expected = true;

        solutions.add(new Solution("Master", 1.0));
        solutions.add(new Solution("PhD", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Master");

        boolean result = service.evaluateQuestion(RequirementType.MINORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

    @Test
    void ensureVerifyMinOrdDeclined() {
        boolean expected = false;

        solutions.add(new Solution("Master", 1.0));
        solutions.add(new Solution("PhD", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Bachelor");

        boolean result = service.evaluateQuestion(RequirementType.MINORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }

    @Test
    void ensureVerifyMaxOrdAccepted() {
        boolean expected = true;

        solutions.add(new Solution("None", 1.0));
        solutions.add(new Solution("Bachelor", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Bachelor");

        boolean result = service.evaluateQuestion(RequirementType.MAXORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

    @Test
    void ensureVerifyMaxOrdDeclined() {
        boolean expected = false;

        solutions.add(new Solution("None", 1.0));
        solutions.add(new Solution("Bachelor", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Master");

        boolean result = service.evaluateQuestion(RequirementType.MAXORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }

    @Test
    void ensureVerifyMulOrSingAccepted() {
        boolean expected = true;

        solutions.add(new Solution("java", 1.0));
        solutions.add(new Solution("python", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: python,java");

        boolean result = service.evaluateQuestion(RequirementType.MULORSING, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

    @Test
    void ensureVerifyMulOrSingDeclined() {
        boolean expected = false;

        solutions.add(new Solution("java", 1.0));
        solutions.add(new Solution("python", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: python,javascript");

        boolean result = service.evaluateQuestion(RequirementType.MULORSING, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
}
