# UC029 - As CM I want to execute the process of verification of requirements of applications for a job opening

# 4. Tests 


**Test 1:**
```java
public void verifyExportTextInterview() {
boolean expected = true;
assertEquals(expected, service.exportJobRequirements(testFileString, testFileName));
}
```

**Test 2:**
```java
public void verifyImportTextInterview() {
boolean expected = true;
assertEquals(expected, service.importTextJobRequirements(String.valueOf(destinationDownload), destinationPath.toString(), testFileName));
}
```

**Test 3:**
```java
void ensureVerifyMinIntAccepted() {
boolean expected = true;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 6");

        boolean result = service.evaluateQuestion(RequirementType.MININT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

```
**Test 4:**
```java
void ensureVerifyMinIntDeclined() {
boolean expected = false;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 4");

        boolean result = service.evaluateQuestion(RequirementType.MININT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
```


**Test 5:**
```java
void ensureVerifyMaxIntAccepted() {
boolean expected = true;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 4");

        boolean result = service.evaluateQuestion(RequirementType.MAXINT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }

```

**Test 6:**
```java
void ensureVerifyMaxIntDeclined() {
boolean expected = false;

        solutions.add(new Solution("Requirement: 5", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: 6");

        boolean result = service.evaluateQuestion(RequirementType.MAXINT, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
```

**Test 7:**
```java
void ensureVerifyMinOrdAccepted() {
boolean expected = true;

        solutions.add(new Solution("Master", 1.0));
        solutions.add(new Solution("PhD", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Master");

        boolean result = service.evaluateQuestion(RequirementType.MINORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }
```

**Test 8:**
```java
void ensureVerifyMinOrdDeclined() {
boolean expected = false;

        solutions.add(new Solution("Master", 1.0));
        solutions.add(new Solution("PhD", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Bachelor");

        boolean result = service.evaluateQuestion(RequirementType.MINORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
```
**Test 9:**
```java
void ensureVerifyMaxOrdAccepted() {
boolean expected = true;

        solutions.add(new Solution("None", 1.0));
        solutions.add(new Solution("Bachelor", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Bachelor");

        boolean result = service.evaluateQuestion(RequirementType.MAXORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }
```
**Test 10:**
```java

void ensureVerifyMaxOrdDeclined() {
boolean expected = false;

        solutions.add(new Solution("None", 1.0));
        solutions.add(new Solution("Bachelor", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: Master");

        boolean result = service.evaluateQuestion(RequirementType.MAXORD, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
```
**Test 11:**
```java
void ensureVerifyMulOrSingAccepted() {
boolean expected = true;

        solutions.add(new Solution("java", 1.0));
        solutions.add(new Solution("python", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: python,java");

        boolean result = service.evaluateQuestion(RequirementType.MULORSING, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Accepted");
    }
```
**Test 12:**
```java

void ensureVerifyMulOrSingDeclined() {
boolean expected = false;

        solutions.add(new Solution("java", 1.0));
        solutions.add(new Solution("python", 1.0));

        RequirementSolution questionSolution = new RequirementSolution(questionMinInt, solutions);
        RequirementAnswer questionAnswer = new RequirementAnswer(questionMinInt, "Answer: python,javascript");

        boolean result = service.evaluateQuestion(RequirementType.MULORSING, questionSolution, questionAnswer);

        assertEquals(expected, result, "Requirements Declined");
    }
```

**Test 13:**
```java
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

```
