package interviewModel;
// Generated from C:/sem4pi-23-24-2df1/LPROG/src/main/grammar/InterviewModelGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link InterviewModelGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface InterviewModelGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#sentences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentences(InterviewModelGrammarParser.SentencesContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(InterviewModelGrammarParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuotedTitle(InterviewModelGrammarParser.QuotedTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(InterviewModelGrammarParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#grade}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGrade(InterviewModelGrammarParser.GradeContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#question_title}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion_title(InterviewModelGrammarParser.Question_titleContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(InterviewModelGrammarParser.AnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#truefalse_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruefalse_question(InterviewModelGrammarParser.Truefalse_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#truefalse_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTruefalse_answer(InterviewModelGrammarParser.Truefalse_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortanswer_question(InterviewModelGrammarParser.Shortanswer_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitShortanswer_answer(InterviewModelGrammarParser.Shortanswer_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinglechoice_question(InterviewModelGrammarParser.Singlechoice_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplechoice_question(InterviewModelGrammarParser.Multiplechoice_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#optionz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionz(InterviewModelGrammarParser.OptionzContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(InterviewModelGrammarParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSinglechoice_answer(InterviewModelGrammarParser.Singlechoice_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplechoice_answer(InterviewModelGrammarParser.Multiplechoice_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#integer_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_question(InterviewModelGrammarParser.Integer_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#integer_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger_answer(InterviewModelGrammarParser.Integer_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#decimal_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_question(InterviewModelGrammarParser.Decimal_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#decimal_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecimal_answer(InterviewModelGrammarParser.Decimal_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#date_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_question(InterviewModelGrammarParser.Date_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#date_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDate_answer(InterviewModelGrammarParser.Date_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#time_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_question(InterviewModelGrammarParser.Time_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#time_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTime_answer(InterviewModelGrammarParser.Time_answerContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#numericscale_question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericscale_question(InterviewModelGrammarParser.Numericscale_questionContext ctx);
	/**
	 * Visit a parse tree produced by {@link InterviewModelGrammarParser#numericscale_answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumericscale_answer(InterviewModelGrammarParser.Numericscale_answerContext ctx);
}