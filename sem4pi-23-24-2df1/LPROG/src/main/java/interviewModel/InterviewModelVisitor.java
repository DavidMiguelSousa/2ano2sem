package interviewModel;

import question.*;

import java.util.ArrayList;
import java.util.List;

public class InterviewModelVisitor extends InterviewModelGrammarBaseVisitor<Void> {
    private static final List<QuestionSolution> questionsWithSolutions = new ArrayList<>();
    private static final List<QuestionAnswer> questionsWithAnswers = new ArrayList<>();

    @Override
    public Void visitStart(InterviewModelGrammarParser.StartContext ctx) {
        System.out.println("Starting interview processing...");
        questionsWithSolutions.clear();
        questionsWithAnswers.clear();
        return visitChildren(ctx);
    }

    private Question createQuestion(String questionType, InterviewModelGrammarParser.Question_titleContext questionTitleCtx) {
        String text = questionTitleCtx.getText(); // Use appropriate method to get the actual text
        int grade = Integer.parseInt(questionTitleCtx.grade().getText());

        String[] type = questionType.split(" ");

        return new Question(questionType + " " + text, grade, QuestionType.valueOf(type[0]));
    }

    @Override
    public Void visitTruefalse_question(InterviewModelGrammarParser.Truefalse_questionContext ctx) {
        Question question = createQuestion(QuestionType.TFQUES.token(), ctx.question_title());

        if (ctx.truefalse_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.truefalse_answer().getText(),
                    Double.parseDouble(ctx.truefalse_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitShortanswer_question(InterviewModelGrammarParser.Shortanswer_questionContext ctx) {
        Question question = createQuestion(QuestionType.SAQUES.token(), ctx.question_title());

        if (ctx.shortanswer_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.shortanswer_answer().sentence().getText(),
                    Double.parseDouble(ctx.shortanswer_answer().grade().INTEGER().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitSinglechoice_question(InterviewModelGrammarParser.Singlechoice_questionContext ctx) {
        Question question = createQuestion(QuestionType.SAQUES.token(), ctx.question_title());

        if (ctx.singlechoice_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.singlechoice_answer().sentences().getText(),
                    Double.parseDouble(ctx.singlechoice_answer().grade().INTEGER().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitMultiplechoice_question(InterviewModelGrammarParser.Multiplechoice_questionContext ctx) {

        Question question = createQuestion(QuestionType.MCQUES.token(), ctx.question_title());

        for (int k = 0; k < ctx.multiplechoice_answer(k).size(); k++) {
            if (ctx.multiplechoice_answer(k) != null) {
                List<Solution> solutions = new ArrayList<>();

                for (int i = 0; i < ctx.multiplechoice_answer(i).size(); i++) {
                    StringBuilder builder = new StringBuilder();
                    InterviewModelGrammarParser.Multiplechoice_answerContext answerCtx = ctx.multiplechoice_answer(i);

                    for (int j = 0; j < answerCtx.sentences().size(); j++) {
                        builder.append(answerCtx.sentences(j).getText());
                        if (j < answerCtx.sentences().size() - 1) {
                            builder.append(",");
                        }
                    }

                    Solution solution = new Solution(builder.toString(), Double.parseDouble(answerCtx.grade().getText()));
                    solutions.add(solution);
                }

                QuestionSolution questionSolution = new QuestionSolution(question, solutions);
                questionsWithSolutions.add(questionSolution);
            }
        }

        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }

        return visitChildren(ctx);
    }

    @Override
    public Void visitInteger_question(InterviewModelGrammarParser.Integer_questionContext ctx) {
        Question question = createQuestion(QuestionType.INTQUES.token(), ctx.question_title());

        if (ctx.integer_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.integer_answer().INTEGER().getText(),
                    Double.parseDouble(ctx.integer_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDecimal_question(InterviewModelGrammarParser.Decimal_questionContext ctx) {
        Question question = createQuestion(QuestionType.DECQUES.token(), ctx.question_title());

        if (ctx.decimal_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.decimal_answer().DECIMAL().getText(),
                    Double.parseDouble(ctx.decimal_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitDate_question(InterviewModelGrammarParser.Date_questionContext ctx) {
        Question question = createQuestion(QuestionType.DATEQUES.token() + " ", ctx.question_title());

        if (ctx.date_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.date_answer().DATE().getText(),
                    Double.parseDouble(ctx.date_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitTime_question(InterviewModelGrammarParser.Time_questionContext ctx) {
        Question question = createQuestion(QuestionType.TIMEQUES.token(), ctx.question_title());

        if (ctx.time_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.time_answer().TIME().getText(),
                    Double.parseDouble(ctx.time_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    @Override
    public Void visitNumericscale_question(InterviewModelGrammarParser.Numericscale_questionContext ctx) {
        Question question = createQuestion(QuestionType.NSQUES.token(), ctx.question_title());

        if (ctx.numericscale_answer() != null) {
            List<Solution> solutions = new ArrayList<>();
            Solution solution = new Solution(ctx.numericscale_answer().INTEGER().getText(),
                    Double.parseDouble(ctx.numericscale_answer().grade().getText()));
            solutions.add(solution);
            QuestionSolution questionSolution = new QuestionSolution(question, solutions);
            questionsWithSolutions.add(questionSolution);
        }
        if (ctx.answer() != null) {
            QuestionAnswer answer = new QuestionAnswer(question, ctx.answer().sentences().getText());
            questionsWithAnswers.add(answer);
        }
        return visitChildren(ctx);
    }

    public List<QuestionSolution> interviewJobOpening() {
        return new ArrayList<>(questionsWithSolutions); // return a copy of the list
    }

    public List<QuestionAnswer> interviewJobApplication() {
        return new ArrayList<>(questionsWithAnswers); // return a copy of the list
    }
}