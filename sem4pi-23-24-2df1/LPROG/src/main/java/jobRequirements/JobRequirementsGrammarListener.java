// Generated from C:/Projeto4/sem4pi-23-24-2df1/LPROG/src/main/grammar/JobRequirementsGrammar.g4 by ANTLR 4.13.1
package jobRequirements;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JobRequirementsGrammarParser}.
 */
public interface JobRequirementsGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(JobRequirementsGrammarParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(JobRequirementsGrammarParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void enterSentences(JobRequirementsGrammarParser.SentencesContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#sentences}.
	 * @param ctx the parse tree
	 */
	void exitSentences(JobRequirementsGrammarParser.SentencesContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void enterSentence(JobRequirementsGrammarParser.SentenceContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#sentence}.
	 * @param ctx the parse tree
	 */
	void exitSentence(JobRequirementsGrammarParser.SentenceContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 */
	void enterQuotedTitle(JobRequirementsGrammarParser.QuotedTitleContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 */
	void exitQuotedTitle(JobRequirementsGrammarParser.QuotedTitleContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 */
	void enterRequirements(JobRequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 */
	void exitRequirements(JobRequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void enterQuestion(JobRequirementsGrammarParser.QuestionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#question}.
	 * @param ctx the parse tree
	 */
	void exitQuestion(JobRequirementsGrammarParser.QuestionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void enterRequirement(JobRequirementsGrammarParser.RequirementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement}.
	 * @param ctx the parse tree
	 */
	void exitRequirement(JobRequirementsGrammarParser.RequirementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_int}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_min_int(JobRequirementsGrammarParser.Requirement_min_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_int}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_min_int(JobRequirementsGrammarParser.Requirement_min_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_int}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_max_int(JobRequirementsGrammarParser.Requirement_max_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_int}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_max_int(JobRequirementsGrammarParser.Requirement_max_intContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_ord}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_min_ord(JobRequirementsGrammarParser.Requirement_min_ordContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_ord}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_min_ord(JobRequirementsGrammarParser.Requirement_min_ordContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_ord}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_max_ord(JobRequirementsGrammarParser.Requirement_max_ordContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_ord}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_max_ord(JobRequirementsGrammarParser.Requirement_max_ordContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_mulorsing}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_mulorsing(JobRequirementsGrammarParser.Requirement_mulorsingContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_mulorsing}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_mulorsing(JobRequirementsGrammarParser.Requirement_mulorsingContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#optionz}.
	 * @param ctx the parse tree
	 */
	void enterOptionz(JobRequirementsGrammarParser.OptionzContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#optionz}.
	 * @param ctx the parse tree
	 */
	void exitOptionz(JobRequirementsGrammarParser.OptionzContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void enterOption(JobRequirementsGrammarParser.OptionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#option}.
	 * @param ctx the parse tree
	 */
	void exitOption(JobRequirementsGrammarParser.OptionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void enterAnswer(JobRequirementsGrammarParser.AnswerContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#answer}.
	 * @param ctx the parse tree
	 */
	void exitAnswer(JobRequirementsGrammarParser.AnswerContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requeriment_expected}.
	 * @param ctx the parse tree
	 */
	void enterRequeriment_expected(JobRequirementsGrammarParser.Requeriment_expectedContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requeriment_expected}.
	 * @param ctx the parse tree
	 */
	void exitRequeriment_expected(JobRequirementsGrammarParser.Requeriment_expectedContext ctx);
	/**
	 * Enter a parse tree produced by {@link JobRequirementsGrammarParser#requirement_expected_int}.
	 * @param ctx the parse tree
	 */
	void enterRequirement_expected_int(JobRequirementsGrammarParser.Requirement_expected_intContext ctx);
	/**
	 * Exit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_expected_int}.
	 * @param ctx the parse tree
	 */
	void exitRequirement_expected_int(JobRequirementsGrammarParser.Requirement_expected_intContext ctx);
}