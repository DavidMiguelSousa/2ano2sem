package jobRequirements;

import interviewModel.InterviewModelGrammarParser;
import question.*;
import requirements.Requirement;
import requirements.RequirementAnswer;
import requirements.RequirementSolution;
import requirements.RequirementType;

import java.util.*;
import java.util.stream.Collectors;

public class JobRequirementsVisitor extends JobRequirementsGrammarBaseVisitor<Void> {

    private static final List<RequirementSolution> requirementSolutions = new ArrayList<>();
    private static final List<RequirementAnswer> requirementAnswers = new ArrayList<>();

    private boolean allAccepted = true;

    public boolean isAllAccepted() {
        return allAccepted;
    }

    @Override
    public Void visitStart(JobRequirementsGrammarParser.StartContext ctx) {
        System.out.println("Starting job requirements processing...");
        requirementSolutions.clear();
        requirementAnswers.clear();
        return visitChildren(ctx);
    }

    private Requirement createRequirement(String questionType, JobRequirementsGrammarParser.QuestionContext questionTitleCtx) {
        String text = questionTitleCtx.getText(); // Use appropriate method to get the actual text
        String[] type = questionType.split(" ");

        return new Requirement(questionType + " " + text, RequirementType.valueOf(type[0]));
    }

    // Implement the visit methods for each specific requirement type:
    @Override
    public Void visitRequirement_min_int(JobRequirementsGrammarParser.Requirement_min_intContext ctx) {
        Requirement question = createRequirement(RequirementType.MININT.token(), ctx.question());

        if (ctx.requirement_expected_int() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.requirement_expected_int().getText(), 0.0);
            solutions.add(solution);
            RequirementSolution requirementSolution = new RequirementSolution(question, solutions);
            requirementSolutions.add(requirementSolution);
        }
        if (ctx.answer() != null) {
            RequirementAnswer answer = new RequirementAnswer(question, ctx.answer().getText());
            requirementAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitRequirement_max_int(JobRequirementsGrammarParser.Requirement_max_intContext ctx) {
        Requirement question = createRequirement(RequirementType.MAXINT.token(), ctx.question());

        if (ctx.requirement_expected_int() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.requirement_expected_int().getText(), 0.0);
            solutions.add(solution);
            RequirementSolution requirementSolution = new RequirementSolution(question, solutions);
            requirementSolutions.add(requirementSolution);
        }
        if (ctx.answer() != null) {
            RequirementAnswer answer = new RequirementAnswer(question, ctx.answer().getText());
            requirementAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitRequirement_min_ord(JobRequirementsGrammarParser.Requirement_min_ordContext ctx) {
        Requirement question = createRequirement(RequirementType.MINORD.token(), ctx.question());

        if (ctx.requeriment_expected() != null && ctx.question().optionz().option() != null) {
            List<Solution> solutions = new LinkedList<>();
            List<JobRequirementsGrammarParser.OptionContext> lista = ctx.question().optionz().option();
            String combinedSolutions =  lista.get(0).getText().trim();

            String[] solutionParts = combinedSolutions.split(";\\s*"); // Split on semicolon and trim whitespace
            for (String part : solutionParts) {
                solutions.add(new Solution(part.trim(), 0.0));
            }

            String expectedWord = ctx.requeriment_expected().getText().trim().split(" ")[1].split(";")[0];

            int count = 0;
            int expectedIndex = -1;
            for(Solution solution : solutions) {
                if (Objects.equals(solution.solutions(), expectedWord)) {
                    expectedIndex = count;
                    break;
                }
                count++;
            }

            List<Solution> filteredSolutions = new ArrayList<>();
            if (expectedIndex != -1) {  // Check if the expected solution is found
                filteredSolutions = solutions.subList(expectedIndex, solutions.size()); // +1 to include the expected solution itself
            }

            RequirementSolution requirementSolution = new RequirementSolution(question, filteredSolutions);
            requirementSolutions.add(requirementSolution);
        }
        if (ctx.answer() != null) {
            RequirementAnswer answer = new RequirementAnswer(question, ctx.answer().getText());
            requirementAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitRequirement_max_ord(JobRequirementsGrammarParser.Requirement_max_ordContext ctx) {
        Requirement question = createRequirement(RequirementType.MAXORD.token(), ctx.question());

        if (ctx.requeriment_expected() != null && ctx.question().optionz().option() != null) {
            List<Solution> solutions = new LinkedList<>();
            List<JobRequirementsGrammarParser.OptionContext> lista = ctx.question().optionz().option();
            String combinedSolutions =  lista.get(0).getText().trim();

            String[] solutionParts = combinedSolutions.split(";\\s*"); // Split on semicolon and trim whitespace
            for (String part : solutionParts) {
                solutions.add(new Solution(part.trim(), 0.0));
            }

            String expectedWord = ctx.requeriment_expected().getText().trim().split(" ")[1].split(";")[0];

            int count = 0;
            int expectedIndex = -1;
            for(Solution solution : solutions) {
                if (Objects.equals(solution.solutions(), expectedWord)) {
                    expectedIndex = count;
                    break;
                }
                count++;
            }

            List<Solution> filteredSolutions = new ArrayList<>();
            if (expectedIndex != -1) {  // Check if the expected solution is found
                filteredSolutions = solutions.subList(0, expectedIndex); // +1 to include the expected solution itself
            }

            RequirementSolution requirementSolution = new RequirementSolution(question, filteredSolutions);
            requirementSolutions.add(requirementSolution);
        }
        if (ctx.answer() != null) {
            RequirementAnswer answer = new RequirementAnswer(question, ctx.answer().getText());
            requirementAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitRequirement_mulorsing(JobRequirementsGrammarParser.Requirement_mulorsingContext ctx) {
        Requirement question = createRequirement(RequirementType.MULORSING.token(), ctx.question());

        if (ctx.requeriment_expected() != null) {
            List<Solution> solutions = new ArrayList<>();
            for (JobRequirementsGrammarParser.SentenceContext sentenceCtx : ctx.requeriment_expected().sentence()) {
                solutions.add(new Solution(sentenceCtx.getText().trim(), 0.0));
            }
            RequirementSolution requirementSolution = new RequirementSolution(question, solutions);
            requirementSolutions.add(requirementSolution);
        }
        if (ctx.answer() != null) {
            RequirementAnswer answer = new RequirementAnswer(question, ctx.answer().getText());
            requirementAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    public List<RequirementSolution> jobRequirementsJobOpening() {
        return new ArrayList<>(requirementSolutions); // return a copy of the list
    }

    public List<RequirementAnswer> jobRequirementsJobApplication() {
        return new ArrayList<>(requirementAnswers); // return a copy of the list
    }
}
