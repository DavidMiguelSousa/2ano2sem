// Generated from C:/Projeto4/sem4pi-23-24-2df1/LPROG/src/main/grammar/JobRequirementsGrammar.g4 by ANTLR 4.13.1
package jobRequirements;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JobRequirementsGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JobRequirementsGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(JobRequirementsGrammarParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#sentences}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentences(JobRequirementsGrammarParser.SentencesContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#sentence}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSentence(JobRequirementsGrammarParser.SentenceContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#quotedTitle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuotedTitle(JobRequirementsGrammarParser.QuotedTitleContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirements(JobRequirementsGrammarParser.RequirementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#question}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuestion(JobRequirementsGrammarParser.QuestionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement(JobRequirementsGrammarParser.RequirementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_min_int(JobRequirementsGrammarParser.Requirement_min_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_max_int(JobRequirementsGrammarParser.Requirement_max_intContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_min_ord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_min_ord(JobRequirementsGrammarParser.Requirement_min_ordContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_max_ord}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_max_ord(JobRequirementsGrammarParser.Requirement_max_ordContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_mulorsing}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_mulorsing(JobRequirementsGrammarParser.Requirement_mulorsingContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#optionz}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOptionz(JobRequirementsGrammarParser.OptionzContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#option}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOption(JobRequirementsGrammarParser.OptionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#answer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnswer(JobRequirementsGrammarParser.AnswerContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requeriment_expected}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequeriment_expected(JobRequirementsGrammarParser.Requeriment_expectedContext ctx);
	/**
	 * Visit a parse tree produced by {@link JobRequirementsGrammarParser#requirement_expected_int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRequirement_expected_int(JobRequirementsGrammarParser.Requirement_expected_intContext ctx);
}