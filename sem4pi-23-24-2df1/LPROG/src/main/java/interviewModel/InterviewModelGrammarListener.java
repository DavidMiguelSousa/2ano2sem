package interviewModel;
// Generated from C:/sem4pi-23-24-2df1/LPROG/src/main/grammar/InterviewModelGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link InterviewModelGrammarParser}.
 */
public interface InterviewModelGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(InterviewModelGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void enterSentences(InterviewModelGrammarParser.SentencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void exitSentences(InterviewModelGrammarParser.SentencesContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(InterviewModelGrammarParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(InterviewModelGrammarParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 */
	void enterQuotedTitle(InterviewModelGrammarParser.QuotedTitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 */
	void exitQuotedTitle(InterviewModelGrammarParser.QuotedTitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 */
	void enterQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#questions}.
	 * @param ctx the parse tree
	 */
	void exitQuestions(InterviewModelGrammarParser.QuestionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(InterviewModelGrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(InterviewModelGrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#grade}.
	 * @param ctx the parse tree
	 */
	void enterGrade(InterviewModelGrammarParser.GradeContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#grade}.
	 * @param ctx the parse tree
	 */
	void exitGrade(InterviewModelGrammarParser.GradeContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#question_title}.
	 * @param ctx the parse tree
	 */
	void enterQuestion_title(InterviewModelGrammarParser.Question_titleContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#question_title}.
	 * @param ctx the parse tree
	 */
	void exitQuestion_title(InterviewModelGrammarParser.Question_titleContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(InterviewModelGrammarParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(InterviewModelGrammarParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#truefalse_question}.
	 * @param ctx the parse tree
	 */
	void enterTruefalse_question(InterviewModelGrammarParser.Truefalse_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#truefalse_question}.
	 * @param ctx the parse tree
	 */
	void exitTruefalse_question(InterviewModelGrammarParser.Truefalse_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#truefalse_answer}.
	 * @param ctx the parse tree
	 */
	void enterTruefalse_answer(InterviewModelGrammarParser.Truefalse_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#truefalse_answer}.
	 * @param ctx the parse tree
	 */
	void exitTruefalse_answer(InterviewModelGrammarParser.Truefalse_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_question}.
	 * @param ctx the parse tree
	 */
	void enterShortanswer_question(InterviewModelGrammarParser.Shortanswer_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_question}.
	 * @param ctx the parse tree
	 */
	void exitShortanswer_question(InterviewModelGrammarParser.Shortanswer_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_answer}.
	 * @param ctx the parse tree
	 */
	void enterShortanswer_answer(InterviewModelGrammarParser.Shortanswer_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#shortanswer_answer}.
	 * @param ctx the parse tree
	 */
	void exitShortanswer_answer(InterviewModelGrammarParser.Shortanswer_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_question}.
	 * @param ctx the parse tree
	 */
	void enterSinglechoice_question(InterviewModelGrammarParser.Singlechoice_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_question}.
	 * @param ctx the parse tree
	 */
	void exitSinglechoice_question(InterviewModelGrammarParser.Singlechoice_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_question}.
	 * @param ctx the parse tree
	 */
	void enterMultiplechoice_question(InterviewModelGrammarParser.Multiplechoice_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_question}.
	 * @param ctx the parse tree
	 */
	void exitMultiplechoice_question(InterviewModelGrammarParser.Multiplechoice_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#optionz}.
	 * @param ctx the parse tree
	 */
	void enterOptionz(InterviewModelGrammarParser.OptionzContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#optionz}.
	 * @param ctx the parse tree
	 */
	void exitOptionz(InterviewModelGrammarParser.OptionzContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(InterviewModelGrammarParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(InterviewModelGrammarParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_answer}.
	 * @param ctx the parse tree
	 */
	void enterSinglechoice_answer(InterviewModelGrammarParser.Singlechoice_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#singlechoice_answer}.
	 * @param ctx the parse tree
	 */
	void exitSinglechoice_answer(InterviewModelGrammarParser.Singlechoice_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_answer}.
	 * @param ctx the parse tree
	 */
	void enterMultiplechoice_answer(InterviewModelGrammarParser.Multiplechoice_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#multiplechoice_answer}.
	 * @param ctx the parse tree
	 */
	void exitMultiplechoice_answer(InterviewModelGrammarParser.Multiplechoice_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#integer_question}.
	 * @param ctx the parse tree
	 */
	void enterInteger_question(InterviewModelGrammarParser.Integer_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#integer_question}.
	 * @param ctx the parse tree
	 */
	void exitInteger_question(InterviewModelGrammarParser.Integer_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#integer_answer}.
	 * @param ctx the parse tree
	 */
	void enterInteger_answer(InterviewModelGrammarParser.Integer_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#integer_answer}.
	 * @param ctx the parse tree
	 */
	void exitInteger_answer(InterviewModelGrammarParser.Integer_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#decimal_question}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_question(InterviewModelGrammarParser.Decimal_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#decimal_question}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_question(InterviewModelGrammarParser.Decimal_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#decimal_answer}.
	 * @param ctx the parse tree
	 */
	void enterDecimal_answer(InterviewModelGrammarParser.Decimal_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#decimal_answer}.
	 * @param ctx the parse tree
	 */
	void exitDecimal_answer(InterviewModelGrammarParser.Decimal_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#date_question}.
	 * @param ctx the parse tree
	 */
	void enterDate_question(InterviewModelGrammarParser.Date_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#date_question}.
	 * @param ctx the parse tree
	 */
	void exitDate_question(InterviewModelGrammarParser.Date_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#date_answer}.
	 * @param ctx the parse tree
	 */
	void enterDate_answer(InterviewModelGrammarParser.Date_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#date_answer}.
	 * @param ctx the parse tree
	 */
	void exitDate_answer(InterviewModelGrammarParser.Date_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#time_question}.
	 * @param ctx the parse tree
	 */
	void enterTime_question(InterviewModelGrammarParser.Time_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#time_question}.
	 * @param ctx the parse tree
	 */
	void exitTime_question(InterviewModelGrammarParser.Time_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#time_answer}.
	 * @param ctx the parse tree
	 */
	void enterTime_answer(InterviewModelGrammarParser.Time_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#time_answer}.
	 * @param ctx the parse tree
	 */
	void exitTime_answer(InterviewModelGrammarParser.Time_answerContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#numericscale_question}.
	 * @param ctx the parse tree
	 */
	void enterNumericscale_question(InterviewModelGrammarParser.Numericscale_questionContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#numericscale_question}.
	 * @param ctx the parse tree
	 */
	void exitNumericscale_question(InterviewModelGrammarParser.Numericscale_questionContext ctx);
	/**
	 * Enter a parse tree produced by {@link InterviewModelGrammarParser#numericscale_answer}.
	 * @param ctx the parse tree
	 */
	void enterNumericscale_answer(InterviewModelGrammarParser.Numericscale_answerContext ctx);
	/**
	 * Exit a parse tree produced by {@link InterviewModelGrammarParser#numericscale_answer}.
	 * @param ctx the parse tree
	 */
	void exitNumericscale_answer(InterviewModelGrammarParser.Numericscale_answerContext ctx);
}