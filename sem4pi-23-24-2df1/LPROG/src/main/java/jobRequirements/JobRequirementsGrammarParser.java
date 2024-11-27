// Generated from C:/Projeto4/sem4pi-23-24-2df1/LPROG/src/main/grammar/JobRequirementsGrammar.g4 by ANTLR 4.13.1
package jobRequirements;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class JobRequirementsGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WORD=5, SPACE=6, EOL=7, INTEGER=8, DECIMAL=9, 
		HASHTAG=10, POINT=11, COMMA=12, TWO_POINTS=13, SEMICOLON=14, LEFT_PARENTHESIS=15, 
		RIGHT_PARENTHESIS=16, QUOTED_MARKS=17, ANSWER=18, REQUIREMENTS=19, MININT=20, 
		MAXINT=21, MINORD=22, MAXORD=23, MULORSING=24, SPECIAL_CHARS=25;
	public static final int
		RULE_start = 0, RULE_sentences = 1, RULE_sentence = 2, RULE_quotedTitle = 3, 
		RULE_requirements = 4, RULE_question = 5, RULE_requirement = 6, RULE_requirement_min_int = 7, 
		RULE_requirement_max_int = 8, RULE_requirement_min_ord = 9, RULE_requirement_max_ord = 10, 
		RULE_requirement_mulorsing = 11, RULE_optionz = 12, RULE_option = 13, 
		RULE_answer = 14, RULE_requeriment_expected = 15, RULE_requirement_expected_int = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "sentences", "sentence", "quotedTitle", "requirements", "question", 
			"requirement", "requirement_min_int", "requirement_max_int", "requirement_min_ord", 
			"requirement_max_ord", "requirement_mulorsing", "optionz", "option", 
			"answer", "requeriment_expected", "requirement_expected_int"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'JOB'", "'REQUIREMENTS'", "'-'", "'''", null, "' '", null, null, 
			null, "'#'", "'.'", "','", "':'", "';'", "'('", "')'", "'\"'", "'Answer:'", 
			"'Requirement:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, "WORD", "SPACE", "EOL", "INTEGER", "DECIMAL", 
			"HASHTAG", "POINT", "COMMA", "TWO_POINTS", "SEMICOLON", "LEFT_PARENTHESIS", 
			"RIGHT_PARENTHESIS", "QUOTED_MARKS", "ANSWER", "REQUIREMENTS", "MININT", 
			"MAXINT", "MINORD", "MAXORD", "MULORSING", "SPECIAL_CHARS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "JobRequirementsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JobRequirementsGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public List<TerminalNode> SPACE() { return getTokens(JobRequirementsGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JobRequirementsGrammarParser.SPACE, i);
		}
		public QuotedTitleContext quotedTitle() {
			return getRuleContext(QuotedTitleContext.class,0);
		}
		public RequirementsContext requirements() {
			return getRuleContext(RequirementsContext.class,0);
		}
		public List<TerminalNode> EOL() { return getTokens(JobRequirementsGrammarParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(JobRequirementsGrammarParser.EOL, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			match(T__0);
			setState(35);
			match(SPACE);
			setState(36);
			match(T__1);
			setState(37);
			match(SPACE);
			setState(38);
			quotedTitle();
			setState(42);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(39);
				match(EOL);
				}
				}
				setState(44);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(45);
			requirements();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SentencesContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> SPACE() { return getTokens(JobRequirementsGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JobRequirementsGrammarParser.SPACE, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JobRequirementsGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JobRequirementsGrammarParser.COMMA, i);
		}
		public List<TerminalNode> POINT() { return getTokens(JobRequirementsGrammarParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(JobRequirementsGrammarParser.POINT, i);
		}
		public SentencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentences; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterSentences(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitSentences(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitSentences(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentencesContext sentences() throws RecognitionException {
		SentencesContext _localctx = new SentencesContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sentences);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			sentence();
			setState(58);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(53);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SPACE:
					case POINT:
					case COMMA:
						{
						setState(49);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==POINT || _la==COMMA) {
							{
							setState(48);
							_la = _input.LA(1);
							if ( !(_la==POINT || _la==COMMA) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
						}

						setState(51);
						match(SPACE);
						}
						break;
					case T__2:
						{
						setState(52);
						match(T__2);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(55);
					sentence();
					}
					} 
				}
				setState(60);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SentenceContext extends ParserRuleContext {
		public List<TerminalNode> WORD() { return getTokens(JobRequirementsGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(JobRequirementsGrammarParser.WORD, i);
		}
		public List<TerminalNode> SPECIAL_CHARS() { return getTokens(JobRequirementsGrammarParser.SPECIAL_CHARS); }
		public TerminalNode SPECIAL_CHARS(int i) {
			return getToken(JobRequirementsGrammarParser.SPECIAL_CHARS, i);
		}
		public TerminalNode INTEGER() { return getToken(JobRequirementsGrammarParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(JobRequirementsGrammarParser.DECIMAL, 0); }
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sentence);
		int _la;
		try {
			int _alt;
			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(61);
				match(WORD);
				setState(63);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
				case 1:
					{
					setState(62);
					match(SPECIAL_CHARS);
					}
					break;
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(65);
					match(T__3);
					setState(66);
					match(WORD);
					setState(68);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
					case 1:
						{
						setState(67);
						match(SPECIAL_CHARS);
						}
						break;
					}
					}
				}

				}
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(72);
				match(INTEGER);
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				match(DECIMAL);
				}
				break;
			case SPECIAL_CHARS:
				enterOuterAlt(_localctx, 4);
				{
				setState(75); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(74);
						match(SPECIAL_CHARS);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(77); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuotedTitleContext extends ParserRuleContext {
		public List<TerminalNode> QUOTED_MARKS() { return getTokens(JobRequirementsGrammarParser.QUOTED_MARKS); }
		public TerminalNode QUOTED_MARKS(int i) {
			return getToken(JobRequirementsGrammarParser.QUOTED_MARKS, i);
		}
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public QuotedTitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_quotedTitle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterQuotedTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitQuotedTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitQuotedTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotedTitleContext quotedTitle() throws RecognitionException {
		QuotedTitleContext _localctx = new QuotedTitleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quotedTitle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			match(QUOTED_MARKS);
			setState(82);
			sentences();
			setState(83);
			match(QUOTED_MARKS);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RequirementsContext extends ParserRuleContext {
		public List<RequirementContext> requirement() {
			return getRuleContexts(RequirementContext.class);
		}
		public RequirementContext requirement(int i) {
			return getRuleContext(RequirementContext.class,i);
		}
		public List<TerminalNode> EOL() { return getTokens(JobRequirementsGrammarParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(JobRequirementsGrammarParser.EOL, i);
		}
		public RequirementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementsContext requirements() throws RecognitionException {
		RequirementsContext _localctx = new RequirementsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_requirements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			requirement();
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==EOL) {
				{
				{
				setState(86);
				match(EOL);
				setState(87);
				requirement();
				}
				}
				setState(92);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QuestionContext extends ParserRuleContext {
		public List<TerminalNode> QUOTED_MARKS() { return getTokens(JobRequirementsGrammarParser.QUOTED_MARKS); }
		public TerminalNode QUOTED_MARKS(int i) {
			return getToken(JobRequirementsGrammarParser.QUOTED_MARKS, i);
		}
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public List<TerminalNode> SPACE() { return getTokens(JobRequirementsGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JobRequirementsGrammarParser.SPACE, i);
		}
		public OptionzContext optionz() {
			return getRuleContext(OptionzContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(JobRequirementsGrammarParser.SEMICOLON, 0); }
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(93);
				match(SPACE);
				}
				}
				setState(98);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(99);
			match(QUOTED_MARKS);
			setState(100);
			sentences();
			setState(101);
			match(SPACE);
			setState(102);
			optionz();
			setState(103);
			match(QUOTED_MARKS);
			setState(104);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RequirementContext extends ParserRuleContext {
		public Requirement_min_intContext requirement_min_int() {
			return getRuleContext(Requirement_min_intContext.class,0);
		}
		public Requirement_max_intContext requirement_max_int() {
			return getRuleContext(Requirement_max_intContext.class,0);
		}
		public Requirement_min_ordContext requirement_min_ord() {
			return getRuleContext(Requirement_min_ordContext.class,0);
		}
		public Requirement_max_ordContext requirement_max_ord() {
			return getRuleContext(Requirement_max_ordContext.class,0);
		}
		public Requirement_mulorsingContext requirement_mulorsing() {
			return getRuleContext(Requirement_mulorsingContext.class,0);
		}
		public RequirementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RequirementContext requirement() throws RecognitionException {
		RequirementContext _localctx = new RequirementContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_requirement);
		try {
			setState(111);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MININT:
				enterOuterAlt(_localctx, 1);
				{
				setState(106);
				requirement_min_int();
				}
				break;
			case MAXINT:
				enterOuterAlt(_localctx, 2);
				{
				setState(107);
				requirement_max_int();
				}
				break;
			case MINORD:
				enterOuterAlt(_localctx, 3);
				{
				setState(108);
				requirement_min_ord();
				}
				break;
			case MAXORD:
				enterOuterAlt(_localctx, 4);
				{
				setState(109);
				requirement_max_ord();
				}
				break;
			case MULORSING:
				enterOuterAlt(_localctx, 5);
				{
				setState(110);
				requirement_mulorsing();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_min_intContext extends ParserRuleContext {
		public TerminalNode MININT() { return getToken(JobRequirementsGrammarParser.MININT, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EOL() { return getToken(JobRequirementsGrammarParser.EOL, 0); }
		public Requirement_expected_intContext requirement_expected_int() {
			return getRuleContext(Requirement_expected_intContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Requirement_min_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_min_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_min_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_min_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_min_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_min_intContext requirement_min_int() throws RecognitionException {
		Requirement_min_intContext _localctx = new Requirement_min_intContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_requirement_min_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(MININT);
			setState(114);
			question();
			setState(115);
			match(EOL);
			setState(118);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIREMENTS:
				{
				setState(116);
				requirement_expected_int();
				}
				break;
			case ANSWER:
				{
				setState(117);
				answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_max_intContext extends ParserRuleContext {
		public TerminalNode MAXINT() { return getToken(JobRequirementsGrammarParser.MAXINT, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EOL() { return getToken(JobRequirementsGrammarParser.EOL, 0); }
		public Requirement_expected_intContext requirement_expected_int() {
			return getRuleContext(Requirement_expected_intContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Requirement_max_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_max_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_max_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_max_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_max_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_max_intContext requirement_max_int() throws RecognitionException {
		Requirement_max_intContext _localctx = new Requirement_max_intContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_requirement_max_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(MAXINT);
			setState(121);
			question();
			setState(122);
			match(EOL);
			setState(125);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIREMENTS:
				{
				setState(123);
				requirement_expected_int();
				}
				break;
			case ANSWER:
				{
				setState(124);
				answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_min_ordContext extends ParserRuleContext {
		public TerminalNode MINORD() { return getToken(JobRequirementsGrammarParser.MINORD, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EOL() { return getToken(JobRequirementsGrammarParser.EOL, 0); }
		public Requeriment_expectedContext requeriment_expected() {
			return getRuleContext(Requeriment_expectedContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Requirement_min_ordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_min_ord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_min_ord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_min_ord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_min_ord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_min_ordContext requirement_min_ord() throws RecognitionException {
		Requirement_min_ordContext _localctx = new Requirement_min_ordContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_requirement_min_ord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(MINORD);
			setState(128);
			question();
			setState(129);
			match(EOL);
			setState(132);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIREMENTS:
				{
				setState(130);
				requeriment_expected();
				}
				break;
			case ANSWER:
				{
				setState(131);
				answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_max_ordContext extends ParserRuleContext {
		public TerminalNode MAXORD() { return getToken(JobRequirementsGrammarParser.MAXORD, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EOL() { return getToken(JobRequirementsGrammarParser.EOL, 0); }
		public Requeriment_expectedContext requeriment_expected() {
			return getRuleContext(Requeriment_expectedContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Requirement_max_ordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_max_ord; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_max_ord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_max_ord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_max_ord(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_max_ordContext requirement_max_ord() throws RecognitionException {
		Requirement_max_ordContext _localctx = new Requirement_max_ordContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_requirement_max_ord);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(MAXORD);
			setState(135);
			question();
			setState(136);
			match(EOL);
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIREMENTS:
				{
				setState(137);
				requeriment_expected();
				}
				break;
			case ANSWER:
				{
				setState(138);
				answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_mulorsingContext extends ParserRuleContext {
		public TerminalNode MULORSING() { return getToken(JobRequirementsGrammarParser.MULORSING, 0); }
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public TerminalNode EOL() { return getToken(JobRequirementsGrammarParser.EOL, 0); }
		public Requeriment_expectedContext requeriment_expected() {
			return getRuleContext(Requeriment_expectedContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Requirement_mulorsingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_mulorsing; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_mulorsing(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_mulorsing(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_mulorsing(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_mulorsingContext requirement_mulorsing() throws RecognitionException {
		Requirement_mulorsingContext _localctx = new Requirement_mulorsingContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_requirement_mulorsing);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(MULORSING);
			setState(142);
			question();
			setState(143);
			match(EOL);
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REQUIREMENTS:
				{
				setState(144);
				requeriment_expected();
				}
				break;
			case ANSWER:
				{
				setState(145);
				answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionzContext extends ParserRuleContext {
		public TerminalNode LEFT_PARENTHESIS() { return getToken(JobRequirementsGrammarParser.LEFT_PARENTHESIS, 0); }
		public TerminalNode RIGHT_PARENTHESIS() { return getToken(JobRequirementsGrammarParser.RIGHT_PARENTHESIS, 0); }
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<TerminalNode> POINT() { return getTokens(JobRequirementsGrammarParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(JobRequirementsGrammarParser.POINT, i);
		}
		public OptionzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterOptionz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitOptionz(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitOptionz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionzContext optionz() throws RecognitionException {
		OptionzContext _localctx = new OptionzContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_optionz);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(LEFT_PARENTHESIS);
			setState(150); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149);
				option();
				}
				}
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 33555232L) != 0) );
			setState(154);
			match(RIGHT_PARENTHESIS);
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==POINT) {
				{
				{
				setState(155);
				match(POINT);
				}
				}
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class OptionContext extends ParserRuleContext {
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(JobRequirementsGrammarParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(JobRequirementsGrammarParser.SEMICOLON, i);
		}
		public List<TerminalNode> SPACE() { return getTokens(JobRequirementsGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JobRequirementsGrammarParser.SPACE, i);
		}
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			sentence();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(162);
				match(SEMICOLON);
				setState(163);
				match(SPACE);
				setState(164);
				sentence();
				}
				}
				setState(169);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AnswerContext extends ParserRuleContext {
		public TerminalNode ANSWER() { return getToken(JobRequirementsGrammarParser.ANSWER, 0); }
		public List<TerminalNode> SPACE() { return getTokens(JobRequirementsGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(JobRequirementsGrammarParser.SPACE, i);
		}
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(JobRequirementsGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JobRequirementsGrammarParser.COMMA, i);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(170);
			match(ANSWER);
			setState(174);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SPACE) {
				{
				{
				setState(171);
				match(SPACE);
				}
				}
				setState(176);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 33555232L) != 0)) {
				{
				{
				setState(177);
				sentence();
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(178);
					match(COMMA);
					setState(179);
					sentence();
					}
					}
					setState(184);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(189);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requeriment_expectedContext extends ParserRuleContext {
		public TerminalNode REQUIREMENTS() { return getToken(JobRequirementsGrammarParser.REQUIREMENTS, 0); }
		public TerminalNode SPACE() { return getToken(JobRequirementsGrammarParser.SPACE, 0); }
		public List<SentenceContext> sentence() {
			return getRuleContexts(SentenceContext.class);
		}
		public SentenceContext sentence(int i) {
			return getRuleContext(SentenceContext.class,i);
		}
		public TerminalNode SEMICOLON() { return getToken(JobRequirementsGrammarParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(JobRequirementsGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(JobRequirementsGrammarParser.COMMA, i);
		}
		public Requeriment_expectedContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requeriment_expected; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequeriment_expected(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequeriment_expected(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequeriment_expected(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requeriment_expectedContext requeriment_expected() throws RecognitionException {
		Requeriment_expectedContext _localctx = new Requeriment_expectedContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_requeriment_expected);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(190);
			match(REQUIREMENTS);
			setState(191);
			match(SPACE);
			setState(192);
			sentence();
			setState(197);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(193);
				match(COMMA);
				setState(194);
				sentence();
				}
				}
				setState(199);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(200);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Requirement_expected_intContext extends ParserRuleContext {
		public TerminalNode REQUIREMENTS() { return getToken(JobRequirementsGrammarParser.REQUIREMENTS, 0); }
		public TerminalNode SPACE() { return getToken(JobRequirementsGrammarParser.SPACE, 0); }
		public TerminalNode INTEGER() { return getToken(JobRequirementsGrammarParser.INTEGER, 0); }
		public TerminalNode SEMICOLON() { return getToken(JobRequirementsGrammarParser.SEMICOLON, 0); }
		public Requirement_expected_intContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_requirement_expected_int; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).enterRequirement_expected_int(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JobRequirementsGrammarListener ) ((JobRequirementsGrammarListener)listener).exitRequirement_expected_int(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JobRequirementsGrammarVisitor ) return ((JobRequirementsGrammarVisitor<? extends T>)visitor).visitRequirement_expected_int(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Requirement_expected_intContext requirement_expected_int() throws RecognitionException {
		Requirement_expected_intContext _localctx = new Requirement_expected_intContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_requirement_expected_int);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(202);
			match(REQUIREMENTS);
			setState(203);
			match(SPACE);
			setState(204);
			match(INTEGER);
			setState(205);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u0019\u00d0\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0005\u0000)\b\u0000\n\u0000\f\u0000,\t"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0003\u00012\b"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u00016\b\u0001\u0001\u0001\u0005"+
		"\u00019\b\u0001\n\u0001\f\u0001<\t\u0001\u0001\u0002\u0001\u0002\u0003"+
		"\u0002@\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002E\b\u0002"+
		"\u0003\u0002G\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002"+
		"L\b\u0002\u000b\u0002\f\u0002M\u0003\u0002P\b\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0005"+
		"\u0004Y\b\u0004\n\u0004\f\u0004\\\t\u0004\u0001\u0005\u0005\u0005_\b\u0005"+
		"\n\u0005\f\u0005b\t\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006p\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007w\b\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b~\b\b\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u0085\b\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003"+
		"\n\u008c\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u0093\b\u000b\u0001\f\u0001\f\u0004\f\u0097\b\f\u000b\f\f"+
		"\f\u0098\u0001\f\u0001\f\u0005\f\u009d\b\f\n\f\f\f\u00a0\t\f\u0001\r\u0001"+
		"\r\u0001\r\u0001\r\u0005\r\u00a6\b\r\n\r\f\r\u00a9\t\r\u0001\u000e\u0001"+
		"\u000e\u0005\u000e\u00ad\b\u000e\n\u000e\f\u000e\u00b0\t\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0005\u000e\u00b5\b\u000e\n\u000e\f\u000e\u00b8"+
		"\t\u000e\u0005\u000e\u00ba\b\u000e\n\u000e\f\u000e\u00bd\t\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0005\u000f\u00c4"+
		"\b\u000f\n\u000f\f\u000f\u00c7\t\u000f\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0000\u0000"+
		"\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \u0000\u0001\u0001\u0000\u000b\f\u00db\u0000\"\u0001"+
		"\u0000\u0000\u0000\u0002/\u0001\u0000\u0000\u0000\u0004O\u0001\u0000\u0000"+
		"\u0000\u0006Q\u0001\u0000\u0000\u0000\bU\u0001\u0000\u0000\u0000\n`\u0001"+
		"\u0000\u0000\u0000\fo\u0001\u0000\u0000\u0000\u000eq\u0001\u0000\u0000"+
		"\u0000\u0010x\u0001\u0000\u0000\u0000\u0012\u007f\u0001\u0000\u0000\u0000"+
		"\u0014\u0086\u0001\u0000\u0000\u0000\u0016\u008d\u0001\u0000\u0000\u0000"+
		"\u0018\u0094\u0001\u0000\u0000\u0000\u001a\u00a1\u0001\u0000\u0000\u0000"+
		"\u001c\u00aa\u0001\u0000\u0000\u0000\u001e\u00be\u0001\u0000\u0000\u0000"+
		" \u00ca\u0001\u0000\u0000\u0000\"#\u0005\u0001\u0000\u0000#$\u0005\u0006"+
		"\u0000\u0000$%\u0005\u0002\u0000\u0000%&\u0005\u0006\u0000\u0000&*\u0003"+
		"\u0006\u0003\u0000\')\u0005\u0007\u0000\u0000(\'\u0001\u0000\u0000\u0000"+
		"),\u0001\u0000\u0000\u0000*(\u0001\u0000\u0000\u0000*+\u0001\u0000\u0000"+
		"\u0000+-\u0001\u0000\u0000\u0000,*\u0001\u0000\u0000\u0000-.\u0003\b\u0004"+
		"\u0000.\u0001\u0001\u0000\u0000\u0000/:\u0003\u0004\u0002\u000002\u0007"+
		"\u0000\u0000\u000010\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u0000"+
		"23\u0001\u0000\u0000\u000036\u0005\u0006\u0000\u000046\u0005\u0003\u0000"+
		"\u000051\u0001\u0000\u0000\u000054\u0001\u0000\u0000\u000067\u0001\u0000"+
		"\u0000\u000079\u0003\u0004\u0002\u000085\u0001\u0000\u0000\u00009<\u0001"+
		"\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001\u0000\u0000\u0000"+
		";\u0003\u0001\u0000\u0000\u0000<:\u0001\u0000\u0000\u0000=?\u0005\u0005"+
		"\u0000\u0000>@\u0005\u0019\u0000\u0000?>\u0001\u0000\u0000\u0000?@\u0001"+
		"\u0000\u0000\u0000@F\u0001\u0000\u0000\u0000AB\u0005\u0004\u0000\u0000"+
		"BD\u0005\u0005\u0000\u0000CE\u0005\u0019\u0000\u0000DC\u0001\u0000\u0000"+
		"\u0000DE\u0001\u0000\u0000\u0000EG\u0001\u0000\u0000\u0000FA\u0001\u0000"+
		"\u0000\u0000FG\u0001\u0000\u0000\u0000GP\u0001\u0000\u0000\u0000HP\u0005"+
		"\b\u0000\u0000IP\u0005\t\u0000\u0000JL\u0005\u0019\u0000\u0000KJ\u0001"+
		"\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MK\u0001\u0000\u0000\u0000"+
		"MN\u0001\u0000\u0000\u0000NP\u0001\u0000\u0000\u0000O=\u0001\u0000\u0000"+
		"\u0000OH\u0001\u0000\u0000\u0000OI\u0001\u0000\u0000\u0000OK\u0001\u0000"+
		"\u0000\u0000P\u0005\u0001\u0000\u0000\u0000QR\u0005\u0011\u0000\u0000"+
		"RS\u0003\u0002\u0001\u0000ST\u0005\u0011\u0000\u0000T\u0007\u0001\u0000"+
		"\u0000\u0000UZ\u0003\f\u0006\u0000VW\u0005\u0007\u0000\u0000WY\u0003\f"+
		"\u0006\u0000XV\u0001\u0000\u0000\u0000Y\\\u0001\u0000\u0000\u0000ZX\u0001"+
		"\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[\t\u0001\u0000\u0000\u0000"+
		"\\Z\u0001\u0000\u0000\u0000]_\u0005\u0006\u0000\u0000^]\u0001\u0000\u0000"+
		"\u0000_b\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000\u0000`a\u0001\u0000"+
		"\u0000\u0000ac\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000cd\u0005"+
		"\u0011\u0000\u0000de\u0003\u0002\u0001\u0000ef\u0005\u0006\u0000\u0000"+
		"fg\u0003\u0018\f\u0000gh\u0005\u0011\u0000\u0000hi\u0005\u000e\u0000\u0000"+
		"i\u000b\u0001\u0000\u0000\u0000jp\u0003\u000e\u0007\u0000kp\u0003\u0010"+
		"\b\u0000lp\u0003\u0012\t\u0000mp\u0003\u0014\n\u0000np\u0003\u0016\u000b"+
		"\u0000oj\u0001\u0000\u0000\u0000ok\u0001\u0000\u0000\u0000ol\u0001\u0000"+
		"\u0000\u0000om\u0001\u0000\u0000\u0000on\u0001\u0000\u0000\u0000p\r\u0001"+
		"\u0000\u0000\u0000qr\u0005\u0014\u0000\u0000rs\u0003\n\u0005\u0000sv\u0005"+
		"\u0007\u0000\u0000tw\u0003 \u0010\u0000uw\u0003\u001c\u000e\u0000vt\u0001"+
		"\u0000\u0000\u0000vu\u0001\u0000\u0000\u0000w\u000f\u0001\u0000\u0000"+
		"\u0000xy\u0005\u0015\u0000\u0000yz\u0003\n\u0005\u0000z}\u0005\u0007\u0000"+
		"\u0000{~\u0003 \u0010\u0000|~\u0003\u001c\u000e\u0000}{\u0001\u0000\u0000"+
		"\u0000}|\u0001\u0000\u0000\u0000~\u0011\u0001\u0000\u0000\u0000\u007f"+
		"\u0080\u0005\u0016\u0000\u0000\u0080\u0081\u0003\n\u0005\u0000\u0081\u0084"+
		"\u0005\u0007\u0000\u0000\u0082\u0085\u0003\u001e\u000f\u0000\u0083\u0085"+
		"\u0003\u001c\u000e\u0000\u0084\u0082\u0001\u0000\u0000\u0000\u0084\u0083"+
		"\u0001\u0000\u0000\u0000\u0085\u0013\u0001\u0000\u0000\u0000\u0086\u0087"+
		"\u0005\u0017\u0000\u0000\u0087\u0088\u0003\n\u0005\u0000\u0088\u008b\u0005"+
		"\u0007\u0000\u0000\u0089\u008c\u0003\u001e\u000f\u0000\u008a\u008c\u0003"+
		"\u001c\u000e\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008b\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u0015\u0001\u0000\u0000\u0000\u008d\u008e\u0005"+
		"\u0018\u0000\u0000\u008e\u008f\u0003\n\u0005\u0000\u008f\u0092\u0005\u0007"+
		"\u0000\u0000\u0090\u0093\u0003\u001e\u000f\u0000\u0091\u0093\u0003\u001c"+
		"\u000e\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0091\u0001\u0000"+
		"\u0000\u0000\u0093\u0017\u0001\u0000\u0000\u0000\u0094\u0096\u0005\u000f"+
		"\u0000\u0000\u0095\u0097\u0003\u001a\r\u0000\u0096\u0095\u0001\u0000\u0000"+
		"\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0096\u0001\u0000\u0000"+
		"\u0000\u0098\u0099\u0001\u0000\u0000\u0000\u0099\u009a\u0001\u0000\u0000"+
		"\u0000\u009a\u009e\u0005\u0010\u0000\u0000\u009b\u009d\u0005\u000b\u0000"+
		"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u00a0\u0001\u0000\u0000"+
		"\u0000\u009e\u009c\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000"+
		"\u0000\u009f\u0019\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a7\u0003\u0004\u0002\u0000\u00a2\u00a3\u0005\u000e\u0000"+
		"\u0000\u00a3\u00a4\u0005\u0006\u0000\u0000\u00a4\u00a6\u0003\u0004\u0002"+
		"\u0000\u00a5\u00a2\u0001\u0000\u0000\u0000\u00a6\u00a9\u0001\u0000\u0000"+
		"\u0000\u00a7\u00a5\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000"+
		"\u0000\u00a8\u001b\u0001\u0000\u0000\u0000\u00a9\u00a7\u0001\u0000\u0000"+
		"\u0000\u00aa\u00ae\u0005\u0012\u0000\u0000\u00ab\u00ad\u0005\u0006\u0000"+
		"\u0000\u00ac\u00ab\u0001\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000"+
		"\u0000\u00ae\u00ac\u0001\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000"+
		"\u0000\u00af\u00bb\u0001\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b6\u0003\u0004\u0002\u0000\u00b2\u00b3\u0005\f\u0000\u0000"+
		"\u00b3\u00b5\u0003\u0004\u0002\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000"+
		"\u00b5\u00b8\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000"+
		"\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000"+
		"\u00b8\u00b6\u0001\u0000\u0000\u0000\u00b9\u00b1\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bd\u0001\u0000\u0000\u0000\u00bb\u00b9\u0001\u0000\u0000\u0000"+
		"\u00bb\u00bc\u0001\u0000\u0000\u0000\u00bc\u001d\u0001\u0000\u0000\u0000"+
		"\u00bd\u00bb\u0001\u0000\u0000\u0000\u00be\u00bf\u0005\u0013\u0000\u0000"+
		"\u00bf\u00c0\u0005\u0006\u0000\u0000\u00c0\u00c5\u0003\u0004\u0002\u0000"+
		"\u00c1\u00c2\u0005\f\u0000\u0000\u00c2\u00c4\u0003\u0004\u0002\u0000\u00c3"+
		"\u00c1\u0001\u0000\u0000\u0000\u00c4\u00c7\u0001\u0000\u0000\u0000\u00c5"+
		"\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001\u0000\u0000\u0000\u00c6"+
		"\u00c8\u0001\u0000\u0000\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8"+
		"\u00c9\u0005\u000e\u0000\u0000\u00c9\u001f\u0001\u0000\u0000\u0000\u00ca"+
		"\u00cb\u0005\u0013\u0000\u0000\u00cb\u00cc\u0005\u0006\u0000\u0000\u00cc"+
		"\u00cd\u0005\b\u0000\u0000\u00cd\u00ce\u0005\u000e\u0000\u0000\u00ce!"+
		"\u0001\u0000\u0000\u0000\u0018*15:?DFMOZ`ov}\u0084\u008b\u0092\u0098\u009e"+
		"\u00a7\u00ae\u00b6\u00bb\u00c5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}