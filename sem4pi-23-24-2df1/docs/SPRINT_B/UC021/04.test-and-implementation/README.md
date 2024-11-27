# UC021 - As Operator, I want to generate and export a template text file to help collect data fields for candidates of a job opening (so the data is used to verify the requirements of the job opening).

# 4. Tests

In this project, a Test-Driven Development (TDD) approach was used.

```java
import java.io.PrintWriter;

class Test {

  private JobRequirementsManagementService service;
  private File file;
  private PrintWriter writer;

  @BeforeEach

  void setUp() {
    service = new JobRequirementsManagementService();
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

## Class GenerateJobRequirementsAction

```java
@Override
public boolean execute () {
  return new GenerateJobRequirementsUI().doShow();
}
```

## Class GenerateJobRequirementsUI

```java
    public boolean doShow() {
  final String nameOfModel = Console.readLine("Name of the job requirements file: ");
  final String fileName = nameOfModel.replaceAll(" ", "-");

  try {
    if (controller.createFile(fileName, nameOfModel)) {
      System.out.println("File created: " + fileName + ".txt");
    } else {
      System.out.println("File already exists.");
    }
  } catch (Exception e) {
    throw new RuntimeException(e);
  }

  boolean newQuestion;
  do {
    String question = Console.readLine("Enter your question following the specific templates:");
//            if (!validateQuestion(question)) {
//                System.out.println("Invalid question format. Please try again.");
//                continue;
//            }

    List<String> toAppend = new ArrayList<>();
    toAppend.add(question);
    try {
      controller.appendQuestion(fileName, toAppend);
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }

    newQuestion = Console.readLine("Do you want to add another question? (y/n)").trim().equalsIgnoreCase("y");
  } while (newQuestion);

  return true;
}
```
        
## Class GenerateJobRequirementsController
```java
public void appendQuestion(String fileName, List<String> wholeQuestion) throws FileNotFoundException {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    jobReqsMgmtService.appendQuestion(fileName, wholeQuestion);
}

public boolean createFile(String fileName, String headline) {
    authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.ADMIN, BaseRoles.CUSTOMER_MANAGER);
    return jobReqsMgmtService.createFile(fileName, headline);
}
```

## Class JobRequirementsManagementService
```java
    public boolean createFile(String fileName, String headline) {
  File file = new File(filePath + fileName + ".txt");
  try {
    try {
      file.createNewFile();
      PrintWriter pw = new PrintWriter(file);
      pw.println("JOB REQUIREMENTS + \"" + headline + "\"");
      pw.println();
      pw.close();
      return true;
    } catch (FileNotFoundException e) {
      return false;
    }
  } catch (Exception e) {
    throw new RuntimeException(e);
  }
}

public void appendQuestion(String fileName, List<String> wholeQuestion) throws FileNotFoundException {
  PrintWriter pw = new PrintWriter(filePath + fileName + ".txt");
  for (String line : wholeQuestion) {
    pw.println("#" + line);
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