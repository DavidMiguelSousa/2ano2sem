# U037 - As Operator, I want to upload a text file with the data fields (requirements ) of a candidate for its verification.

# 4. Tests 

```java
 void setUp() {
    //builder
    StringBuilder builder = new StringBuilder();

    //files
    file_template_1 = Paths.get("src/test/jobRequirements/Requirements/example-1.txt");

    //lists
    questionSolutionList = new ArrayList<>();
    questionAnswersList = new ArrayList<>();

    //needed parameters
    List<Solution> solutionsList = new ArrayList<>();

    //template_1
    builder.append("9");
    Solution solution = new Solution(builder.toString(), 1.0);
    solutionsList.add(solution);
    Requirement question = new Requirement("Enter the number of years of experience (integer).", RequirementType.MININT);
    RequirementSolution questionSolution = new RequirementSolution(question, solutionsList);
    questionSolutionList.add(questionSolution);

    builder = new StringBuilder();
    solutionsList.clear();

    builder.append("8");
    solution = new Solution(builder.toString(), 1.0);
    solutionsList.add(solution);
    question = new Requirement("Enter the number of years of experience (integer).", RequirementType.MAXINT);
    questionSolution = new RequirementSolution(question, solutionsList);
    questionSolutionList.add(questionSolution);

    builder = new StringBuilder();
    solutionsList.clear();

    builder.append("Master");
    solution = new Solution(builder.toString(), 1.0);
    solutionsList.add(solution);
    question = new Requirement("Select one degree (None; Bachelor; Master; PhD)",RequirementType.MINORD);
    questionSolution = new RequirementSolution(question, solutionsList);
    questionSolutionList.add(questionSolution);

    builder = new StringBuilder();
    solutionsList.clear();

    builder.append("Bachelor");
    solution = new Solution(builder.toString(), 1.0);
    solutionsList.add(solution);
    question = new Requirement("Select one degree (None; Bachelor; Master; PhD)",RequirementType.MAXORD);
    questionSolution = new RequirementSolution(question, solutionsList);
    questionSolutionList.add(questionSolution);

    builder = new StringBuilder();
    solutionsList.clear();

    builder.append("java,javascript");
    solution = new Solution(builder.toString(), 1.0);
    solutionsList.add(solution);
    question = new Requirement("Select one or more programming languages you're proficient in (java; javascript; python)",RequirementType.MULORSING);
    questionSolution = new RequirementSolution(question, solutionsList);
    questionSolutionList.add(questionSolution);

}

```

**Test**

```java
void requirementsJobOpening() throws Exception {
    JobRequirementsParser jobRequirementsParser = new JobRequirementsParser();

    List<RequirementSolution> expected = jobRequirementsParser.jobRequirementsJobOpening(file_template_1);

    for (RequirementSolution q1 : expected) {
        for (RequirementSolution q2 : questionSolutionList) {
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
```

# 7. Observations
* n\a