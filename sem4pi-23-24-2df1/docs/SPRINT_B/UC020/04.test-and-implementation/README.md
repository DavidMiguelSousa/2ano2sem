# UC020 - As Customer Manager, I want to generate and export a template text file with candidate answers during an interview.

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
import java.io.PrintWriter;

class InterviewModelManagementServiceTest {

  private InterviewModelManagementService service;
  private File file;
  private PrintWriter writer;

  @BeforeEach

  void setUp() {
    service = new InterviewModelManagementService();
    file = new File("interviews.txt");
    writer = new PrintWriter(file);
  }

}
```

**Test 1:** Check file is created if it doesn't exist

```java
@Test
void testCreateFileIfDoesntExist() {
  service.createFile("interviews.txt");
  assertTrue(file.exists());
}
```

//TODO: Add more tests

---

# 5. Construction(Implementation)

To help achieve this Use Case's requirements, some EAPLI framework classes were used.

## Class GenerateInterviewModelAction

```java
@Override
public boolean execute () {
  return new GenerateInterviewModelUI().doShow();
}
```

## Class GenerateInterviewModelUI

```java
@Override
protected boolean doShow() {

  final String nameOfModel = Console.readLine("Name of the interview model:");
  final String fileName = nameOfModel.trim();

  try {
    if (controller.createFile(fileName)) {
      System.out.println("File created: " + fileName + ".txt");
    } else {
      System.out.println("File already exists.");
    }
  } catch (Exception e) {
    throw new RuntimeException(e);
  }

  boolean newQuestion;
  int totalGrade = 0;
  do {
    Set<QuestionType> questionTypes = EnumSet.allOf(QuestionType.class);
    QuestionType chosen = selectQuestionType(questionTypes);

    String question = Console.readLine("Question:");
    Pair<Integer, Integer> pair = readQuestionValue(totalGrade);
    int grade = pair.getFirst(); //to be used in the next sprint
    totalGrade = pair.getSecond();

    List<String> toAppend = buildQuestion(chosen, question);
    try {
      controller.appendQuestion(fileName, toAppend);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    newQuestion = Console.readBoolean("Do you want to add another question? (y/n)");
  } while (newQuestion);

  return true;
}
```
        
## Class GenerateInterviewModelController
```java
public void appendQuestion(String fileName, List<String> wholeQuestion) throws FileNotFoundException {
  authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
  intmodService.appendQuestion(fileName, wholeQuestion);
}
```

## Class InterviewModelManagementService
```java
public void appendQuestion(String fileName, List<String> wholeQuestion) throws FileNotFoundException {
  PrintWriter pw = new PrintWriter(filePath + fileName);
  for (String question : wholeQuestion) {
    pw.println(question);
    pw.println();
  }
  pw.close();
}
```

//TODO: Move all business logic to the service layer

---

# 6. Integration and Demo

* A new option in the backoffice users menu was added.
  However, to generate a template interview model .txt file, the authenticated
  user must have customer manager or admin permissions.

# 7. Observations

* To run this Use Case, check the Integration and Demonstration section in this Use Case [Read Me](../README.md) file.