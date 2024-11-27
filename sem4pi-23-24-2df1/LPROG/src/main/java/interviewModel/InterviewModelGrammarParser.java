package interviewModel;
// Generated from C:/sem4pi-23-24-2df1/LPROG/src/main/grammar/InterviewModelGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class InterviewModelGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, TRUE=4, FALSE=5, WORD=6, SPACE=7, EOL=8, INTEGER=9, 
		DECIMAL=10, POINT=11, COMMA=12, TWO_POINTS=13, SEMICOLON=14, LEFT_PARENTHESIS=15, 
		RIGHT_PARENTHESIS=16, LEFT_BRACKET=17, RIGHT_BRACKET=18, QUESTION_MARK=19, 
		QUOTATION_MARKS=20, LEFT_SIGNAL=21, RIGHT_SIGNAL=22, BAR=23, SOLUTION=24, 
		OPTION=25, TRACO=26, SPECIAL_CHARS=27, D=28, M=29, Y=30, DATE=31, H=32, 
		MIN=33, TIME=34, TRUE_FALSE=35, SHORT_ANSWER=36, SINGLE_CHOICE=37, MULTIPLE_CHOICE=38, 
		INTEGER_QUESTION=39, DECIMAL_QUESTION=40, DATE_QUESTION=41, TIME_QUESTION=42, 
		NUMERIC_SCALE=43;
	public static final int
		RULE_start = 0, RULE_sentences = 1, RULE_sentence = 2, RULE_quotedTitle = 3, 
		RULE_questions = 4, RULE_question = 5, RULE_grade = 6, RULE_question_title = 7, 
		RULE_answer = 8, RULE_truefalse_question = 9, RULE_truefalse_answer = 10, 
		RULE_shortanswer_question = 11, RULE_shortanswer_answer = 12, RULE_singlechoice_question = 13, 
		RULE_multiplechoice_question = 14, RULE_optionz = 15, RULE_option = 16, 
		RULE_singlechoice_answer = 17, RULE_multiplechoice_answer = 18, RULE_integer_question = 19, 
		RULE_integer_answer = 20, RULE_decimal_question = 21, RULE_decimal_answer = 22, 
		RULE_date_question = 23, RULE_date_answer = 24, RULE_time_question = 25, 
		RULE_time_answer = 26, RULE_numericscale_question = 27, RULE_numericscale_answer = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "sentences", "sentence", "quotedTitle", "questions", "question", 
			"grade", "question_title", "answer", "truefalse_question", "truefalse_answer", 
			"shortanswer_question", "shortanswer_answer", "singlechoice_question", 
			"multiplechoice_question", "optionz", "option", "singlechoice_answer", 
			"multiplechoice_answer", "integer_question", "integer_answer", "decimal_question", 
			"decimal_answer", "date_question", "date_answer", "time_question", "time_answer", 
			"numericscale_question", "numericscale_answer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'INTERVIEW '", "'''", "'ANS:'", "'TRUE'", "'FALSE'", null, "' '", 
			null, null, null, "'.'", "','", "':'", "';'", "'('", "')'", "'['", "']'", 
			"'?'", "'\"'", "'<'", "'>'", "'|'", null, null, "'-'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "TRUE", "FALSE", "WORD", "SPACE", "EOL", "INTEGER", 
			"DECIMAL", "POINT", "COMMA", "TWO_POINTS", "SEMICOLON", "LEFT_PARENTHESIS", 
			"RIGHT_PARENTHESIS", "LEFT_BRACKET", "RIGHT_BRACKET", "QUESTION_MARK", 
			"QUOTATION_MARKS", "LEFT_SIGNAL", "RIGHT_SIGNAL", "BAR", "SOLUTION", 
			"OPTION", "TRACO", "SPECIAL_CHARS", "D", "M", "Y", "DATE", "H", "MIN", 
			"TIME", "TRUE_FALSE", "SHORT_ANSWER", "SINGLE_CHOICE", "MULTIPLE_CHOICE", 
			"INTEGER_QUESTION", "DECIMAL_QUESTION", "DATE_QUESTION", "TIME_QUESTION", 
			"NUMERIC_SCALE"
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
	public String getGrammarFileName() { return "InterviewModelGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public InterviewModelGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StartContext extends ParserRuleContext {
		public QuotedTitleContext quotedTitle() {
			return getRuleContext(QuotedTitleContext.class,0);
		}
		public QuestionsContext questions() {
			return getRuleContext(QuestionsContext.class,0);
		}
		public List<TerminalNode> EOL() { return getTokens(InterviewModelGrammarParser.EOL); }
		public TerminalNode EOL(int i) {
			return getToken(InterviewModelGrammarParser.EOL, i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitStart(this);
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
			setState(58);
			match(T__0);
			setState(59);
			quotedTitle();
			setState(61); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(60);
				match(EOL);
				}
				}
				setState(63); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==EOL );
			setState(65);
			questions();
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
		public List<TerminalNode> SPACE() { return getTokens(InterviewModelGrammarParser.SPACE); }
		public TerminalNode SPACE(int i) {
			return getToken(InterviewModelGrammarParser.SPACE, i);
		}
		public List<TerminalNode> TRACO() { return getTokens(InterviewModelGrammarParser.TRACO); }
		public TerminalNode TRACO(int i) {
			return getToken(InterviewModelGrammarParser.TRACO, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(InterviewModelGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(InterviewModelGrammarParser.COMMA, i);
		}
		public List<TerminalNode> POINT() { return getTokens(InterviewModelGrammarParser.POINT); }
		public TerminalNode POINT(int i) {
			return getToken(InterviewModelGrammarParser.POINT, i);
		}
		public SentencesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentences; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterSentences(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitSentences(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitSentences(this);
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
			setState(67);
			sentence();
			setState(78);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(73);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case SPACE:
					case POINT:
					case COMMA:
						{
						setState(69);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==POINT || _la==COMMA) {
							{
							setState(68);
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

						setState(71);
						match(SPACE);
						}
						break;
					case TRACO:
						{
						setState(72);
						match(TRACO);
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(75);
					sentence();
					}
					} 
				}
				setState(80);
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
		public List<TerminalNode> WORD() { return getTokens(InterviewModelGrammarParser.WORD); }
		public TerminalNode WORD(int i) {
			return getToken(InterviewModelGrammarParser.WORD, i);
		}
		public List<TerminalNode> SPECIAL_CHARS() { return getTokens(InterviewModelGrammarParser.SPECIAL_CHARS); }
		public TerminalNode SPECIAL_CHARS(int i) {
			return getToken(InterviewModelGrammarParser.SPECIAL_CHARS, i);
		}
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public TerminalNode DECIMAL() { return getToken(InterviewModelGrammarParser.DECIMAL, 0); }
		public SentenceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sentence; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterSentence(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitSentence(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitSentence(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SentenceContext sentence() throws RecognitionException {
		SentenceContext _localctx = new SentenceContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sentence);
		int _la;
		try {
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WORD:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				match(WORD);
				setState(83);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SPECIAL_CHARS) {
					{
					setState(82);
					match(SPECIAL_CHARS);
					}
				}

				setState(90);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__1) {
					{
					setState(85);
					match(T__1);
					setState(86);
					match(WORD);
					setState(88);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==SPECIAL_CHARS) {
						{
						setState(87);
						match(SPECIAL_CHARS);
						}
					}

					}
				}

				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				match(INTEGER);
				}
				break;
			case DECIMAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				match(DECIMAL);
				}
				break;
			case SPECIAL_CHARS:
				enterOuterAlt(_localctx, 4);
				{
				setState(95); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(94);
					match(SPECIAL_CHARS);
					}
					}
					setState(97); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==SPECIAL_CHARS );
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
		public List<TerminalNode> QUOTATION_MARKS() { return getTokens(InterviewModelGrammarParser.QUOTATION_MARKS); }
		public TerminalNode QUOTATION_MARKS(int i) {
			return getToken(InterviewModelGrammarParser.QUOTATION_MARKS, i);
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
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuotedTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuotedTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuotedTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuotedTitleContext quotedTitle() throws RecognitionException {
		QuotedTitleContext _localctx = new QuotedTitleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_quotedTitle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(QUOTATION_MARKS);
			setState(102);
			sentences();
			setState(103);
			match(QUOTATION_MARKS);
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
	public static class QuestionsContext extends ParserRuleContext {
		public QuestionContext question() {
			return getRuleContext(QuestionContext.class,0);
		}
		public QuestionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionsContext questions() throws RecognitionException {
		QuestionsContext _localctx = new QuestionsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_questions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			question();
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
		public Truefalse_questionContext truefalse_question() {
			return getRuleContext(Truefalse_questionContext.class,0);
		}
		public Shortanswer_questionContext shortanswer_question() {
			return getRuleContext(Shortanswer_questionContext.class,0);
		}
		public Singlechoice_questionContext singlechoice_question() {
			return getRuleContext(Singlechoice_questionContext.class,0);
		}
		public Multiplechoice_questionContext multiplechoice_question() {
			return getRuleContext(Multiplechoice_questionContext.class,0);
		}
		public Integer_questionContext integer_question() {
			return getRuleContext(Integer_questionContext.class,0);
		}
		public Decimal_questionContext decimal_question() {
			return getRuleContext(Decimal_questionContext.class,0);
		}
		public Date_questionContext date_question() {
			return getRuleContext(Date_questionContext.class,0);
		}
		public Time_questionContext time_question() {
			return getRuleContext(Time_questionContext.class,0);
		}
		public Numericscale_questionContext numericscale_question() {
			return getRuleContext(Numericscale_questionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_question);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TRUE_FALSE:
				enterOuterAlt(_localctx, 1);
				{
				setState(107);
				truefalse_question();
				}
				break;
			case SHORT_ANSWER:
				enterOuterAlt(_localctx, 2);
				{
				setState(108);
				shortanswer_question();
				}
				break;
			case SINGLE_CHOICE:
				enterOuterAlt(_localctx, 3);
				{
				setState(109);
				singlechoice_question();
				}
				break;
			case MULTIPLE_CHOICE:
				enterOuterAlt(_localctx, 4);
				{
				setState(110);
				multiplechoice_question();
				}
				break;
			case INTEGER_QUESTION:
				enterOuterAlt(_localctx, 5);
				{
				setState(111);
				integer_question();
				}
				break;
			case DECIMAL_QUESTION:
				enterOuterAlt(_localctx, 6);
				{
				setState(112);
				decimal_question();
				}
				break;
			case DATE_QUESTION:
				enterOuterAlt(_localctx, 7);
				{
				setState(113);
				date_question();
				}
				break;
			case TIME_QUESTION:
				enterOuterAlt(_localctx, 8);
				{
				setState(114);
				time_question();
				}
				break;
			case NUMERIC_SCALE:
				enterOuterAlt(_localctx, 9);
				{
				setState(115);
				numericscale_question();
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
	public static class GradeContext extends ParserRuleContext {
		public TerminalNode DECIMAL() { return getToken(InterviewModelGrammarParser.DECIMAL, 0); }
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public GradeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterGrade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitGrade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitGrade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeContext grade() throws RecognitionException {
		GradeContext _localctx = new GradeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grade);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==DECIMAL) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
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
	public static class Question_titleContext extends ParserRuleContext {
		public List<TerminalNode> QUOTATION_MARKS() { return getTokens(InterviewModelGrammarParser.QUOTATION_MARKS); }
		public TerminalNode QUOTATION_MARKS(int i) {
			return getToken(InterviewModelGrammarParser.QUOTATION_MARKS, i);
		}
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public TerminalNode SPACE() { return getToken(InterviewModelGrammarParser.SPACE, 0); }
		public TerminalNode LEFT_BRACKET() { return getToken(InterviewModelGrammarParser.LEFT_BRACKET, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_BRACKET() { return getToken(InterviewModelGrammarParser.RIGHT_BRACKET, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public TerminalNode QUESTION_MARK() { return getToken(InterviewModelGrammarParser.QUESTION_MARK, 0); }
		public TerminalNode POINT() { return getToken(InterviewModelGrammarParser.POINT, 0); }
		public Question_titleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterQuestion_title(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitQuestion_title(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitQuestion_title(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Question_titleContext question_title() throws RecognitionException {
		Question_titleContext _localctx = new Question_titleContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_question_title);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(QUOTATION_MARKS);
			setState(121);
			sentences();
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POINT || _la==QUESTION_MARK) {
				{
				setState(122);
				_la = _input.LA(1);
				if ( !(_la==POINT || _la==QUESTION_MARK) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(125);
			match(QUOTATION_MARKS);
			setState(126);
			match(SPACE);
			setState(127);
			match(LEFT_BRACKET);
			setState(128);
			grade();
			setState(129);
			match(RIGHT_BRACKET);
			setState(130);
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
	public static class AnswerContext extends ParserRuleContext {
		public TerminalNode SPACE() { return getToken(InterviewModelGrammarParser.SPACE, 0); }
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public AnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AnswerContext answer() throws RecognitionException {
		AnswerContext _localctx = new AnswerContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(132);
			match(T__2);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SPACE) {
				{
				setState(133);
				match(SPACE);
				}
			}

			setState(137);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 134219328L) != 0)) {
				{
				setState(136);
				sentences();
				}
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
	public static class Truefalse_questionContext extends ParserRuleContext {
		public TerminalNode TRUE_FALSE() { return getToken(InterviewModelGrammarParser.TRUE_FALSE, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Truefalse_answerContext truefalse_answer() {
			return getRuleContext(Truefalse_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Truefalse_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truefalse_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterTruefalse_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitTruefalse_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitTruefalse_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Truefalse_questionContext truefalse_question() throws RecognitionException {
		Truefalse_questionContext _localctx = new Truefalse_questionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_truefalse_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(TRUE_FALSE);
			setState(140);
			question_title();
			setState(141);
			match(EOL);
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(142);
				truefalse_answer();
				}
				break;
			case T__2:
				{
				setState(143);
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
	public static class Truefalse_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public TerminalNode TRUE() { return getToken(InterviewModelGrammarParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(InterviewModelGrammarParser.FALSE, 0); }
		public Truefalse_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_truefalse_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterTruefalse_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitTruefalse_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitTruefalse_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Truefalse_answerContext truefalse_answer() throws RecognitionException {
		Truefalse_answerContext _localctx = new Truefalse_answerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_truefalse_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(SOLUTION);
			setState(147);
			match(LEFT_SIGNAL);
			setState(148);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(149);
			match(BAR);
			setState(150);
			grade();
			setState(151);
			match(RIGHT_SIGNAL);
			setState(152);
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
	public static class Shortanswer_questionContext extends ParserRuleContext {
		public TerminalNode SHORT_ANSWER() { return getToken(InterviewModelGrammarParser.SHORT_ANSWER, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Shortanswer_answerContext shortanswer_answer() {
			return getRuleContext(Shortanswer_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Shortanswer_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortanswer_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterShortanswer_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitShortanswer_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitShortanswer_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shortanswer_questionContext shortanswer_question() throws RecognitionException {
		Shortanswer_questionContext _localctx = new Shortanswer_questionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_shortanswer_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(SHORT_ANSWER);
			setState(155);
			question_title();
			setState(156);
			match(EOL);
			setState(159);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(157);
				shortanswer_answer();
				}
				break;
			case T__2:
				{
				setState(158);
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
	public static class Shortanswer_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public SentenceContext sentence() {
			return getRuleContext(SentenceContext.class,0);
		}
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public TerminalNode POINT() { return getToken(InterviewModelGrammarParser.POINT, 0); }
		public Shortanswer_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortanswer_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterShortanswer_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitShortanswer_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitShortanswer_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Shortanswer_answerContext shortanswer_answer() throws RecognitionException {
		Shortanswer_answerContext _localctx = new Shortanswer_answerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_shortanswer_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(SOLUTION);
			setState(162);
			match(LEFT_SIGNAL);
			setState(163);
			sentence();
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==POINT) {
				{
				setState(164);
				match(POINT);
				}
			}

			setState(167);
			match(BAR);
			setState(168);
			grade();
			setState(169);
			match(RIGHT_SIGNAL);
			setState(170);
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
	public static class Singlechoice_questionContext extends ParserRuleContext {
		public TerminalNode SINGLE_CHOICE() { return getToken(InterviewModelGrammarParser.SINGLE_CHOICE, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public OptionzContext optionz() {
			return getRuleContext(OptionzContext.class,0);
		}
		public Singlechoice_answerContext singlechoice_answer() {
			return getRuleContext(Singlechoice_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Singlechoice_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singlechoice_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterSinglechoice_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitSinglechoice_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitSinglechoice_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Singlechoice_questionContext singlechoice_question() throws RecognitionException {
		Singlechoice_questionContext _localctx = new Singlechoice_questionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_singlechoice_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(SINGLE_CHOICE);
			setState(173);
			question_title();
			setState(174);
			match(EOL);
			setState(175);
			optionz();
			setState(178);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(176);
				singlechoice_answer();
				}
				break;
			case T__2:
				{
				setState(177);
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
	public static class Multiplechoice_questionContext extends ParserRuleContext {
		public TerminalNode MULTIPLE_CHOICE() { return getToken(InterviewModelGrammarParser.MULTIPLE_CHOICE, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public OptionzContext optionz() {
			return getRuleContext(OptionzContext.class,0);
		}
		public Multiplechoice_answerContext multiplechoice_answer(int i) {
			return getRuleContext(Multiplechoice_answerContext.class,i);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Multiplechoice_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplechoice_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterMultiplechoice_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitMultiplechoice_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitMultiplechoice_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiplechoice_questionContext multiplechoice_question() throws RecognitionException {
		Multiplechoice_questionContext _localctx = new Multiplechoice_questionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_multiplechoice_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(MULTIPLE_CHOICE);
			setState(181);
			question_title();
			setState(182);
			match(EOL);
			setState(183);
			optionz();
			setState(186);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(184);
				multiplechoice_answer();
				}
				break;
			case T__2:
				{
				setState(185);
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
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public OptionzContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_optionz; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterOptionz(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitOptionz(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitOptionz(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionzContext optionz() throws RecognitionException {
		OptionzContext _localctx = new OptionzContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_optionz);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			option();
			setState(190); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(189);
				option();
				}
				}
				setState(192); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
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
		public TerminalNode OPTION() { return getToken(InterviewModelGrammarParser.OPTION, 0); }
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(194);
			match(OPTION);
			setState(195);
			match(INTEGER);
			setState(196);
			sentences();
			setState(197);
			match(SEMICOLON);
			setState(199);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==EOL) {
				{
				setState(198);
				match(EOL);
				}
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
	public static class Singlechoice_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public SentencesContext sentences() {
			return getRuleContext(SentencesContext.class,0);
		}
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Singlechoice_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_singlechoice_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterSinglechoice_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitSinglechoice_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitSinglechoice_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Singlechoice_answerContext singlechoice_answer() throws RecognitionException {
		Singlechoice_answerContext _localctx = new Singlechoice_answerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_singlechoice_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(SOLUTION);
			setState(202);
			match(LEFT_SIGNAL);
			setState(203);
			sentences();
			setState(204);
			match(BAR);
			setState(205);
			grade();
			setState(206);
			match(RIGHT_SIGNAL);
			setState(207);
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
	public static class Multiplechoice_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public List<SentencesContext> sentences() {
			return getRuleContexts(SentencesContext.class);
		}
		public SentencesContext sentences(int i) {
			return getRuleContext(SentencesContext.class,i);
		}
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public List<TerminalNode> COMMA() { return getTokens(InterviewModelGrammarParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(InterviewModelGrammarParser.COMMA, i);
		}
		public Multiplechoice_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplechoice_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterMultiplechoice_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitMultiplechoice_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitMultiplechoice_answer(this);
			else return visitor.visitChildren(this);
		}

		public int size(){
			return sentences().size();
		}
	}

	public final Multiplechoice_answerContext multiplechoice_answer() throws RecognitionException {
		Multiplechoice_answerContext _localctx = new Multiplechoice_answerContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_multiplechoice_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(SOLUTION);
			setState(210);
			match(LEFT_SIGNAL);
			setState(211);
			sentences();
			setState(216);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(212);
				match(COMMA);
				setState(213);
				sentences();
				}
				}
				setState(218);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(219);
			match(BAR);
			setState(220);
			grade();
			setState(221);
			match(RIGHT_SIGNAL);
			setState(222);
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
	public static class Integer_questionContext extends ParserRuleContext {
		public TerminalNode INTEGER_QUESTION() { return getToken(InterviewModelGrammarParser.INTEGER_QUESTION, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Integer_answerContext integer_answer() {
			return getRuleContext(Integer_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Integer_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterInteger_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitInteger_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitInteger_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_questionContext integer_question() throws RecognitionException {
		Integer_questionContext _localctx = new Integer_questionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_integer_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			match(INTEGER_QUESTION);
			setState(225);
			question_title();
			setState(226);
			match(EOL);
			setState(229);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(227);
				integer_answer();
				}
				break;
			case T__2:
				{
				setState(228);
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
	public static class Integer_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Integer_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterInteger_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitInteger_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitInteger_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Integer_answerContext integer_answer() throws RecognitionException {
		Integer_answerContext _localctx = new Integer_answerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_integer_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(SOLUTION);
			setState(232);
			match(LEFT_SIGNAL);
			setState(233);
			match(INTEGER);
			setState(234);
			match(BAR);
			setState(235);
			grade();
			setState(236);
			match(RIGHT_SIGNAL);
			setState(237);
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
	public static class Decimal_questionContext extends ParserRuleContext {
		public TerminalNode DECIMAL_QUESTION() { return getToken(InterviewModelGrammarParser.DECIMAL_QUESTION, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Decimal_answerContext decimal_answer() {
			return getRuleContext(Decimal_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Decimal_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterDecimal_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitDecimal_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitDecimal_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_questionContext decimal_question() throws RecognitionException {
		Decimal_questionContext _localctx = new Decimal_questionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_decimal_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(DECIMAL_QUESTION);
			setState(240);
			question_title();
			setState(241);
			match(EOL);
			setState(244);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(242);
				decimal_answer();
				}
				break;
			case T__2:
				{
				setState(243);
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
	public static class Decimal_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode DECIMAL() { return getToken(InterviewModelGrammarParser.DECIMAL, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Decimal_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decimal_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterDecimal_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitDecimal_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitDecimal_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decimal_answerContext decimal_answer() throws RecognitionException {
		Decimal_answerContext _localctx = new Decimal_answerContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_decimal_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(SOLUTION);
			setState(247);
			match(LEFT_SIGNAL);
			setState(248);
			match(DECIMAL);
			setState(249);
			match(BAR);
			setState(250);
			grade();
			setState(251);
			match(RIGHT_SIGNAL);
			setState(252);
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
	public static class Date_questionContext extends ParserRuleContext {
		public TerminalNode DATE_QUESTION() { return getToken(InterviewModelGrammarParser.DATE_QUESTION, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Date_answerContext date_answer() {
			return getRuleContext(Date_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Date_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterDate_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitDate_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitDate_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_questionContext date_question() throws RecognitionException {
		Date_questionContext _localctx = new Date_questionContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_date_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			match(DATE_QUESTION);
			setState(255);
			question_title();
			setState(256);
			match(EOL);
			setState(259);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(257);
				date_answer();
				}
				break;
			case T__2:
				{
				setState(258);
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
	public static class Date_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode DATE() { return getToken(InterviewModelGrammarParser.DATE, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Date_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_date_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterDate_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitDate_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitDate_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Date_answerContext date_answer() throws RecognitionException {
		Date_answerContext _localctx = new Date_answerContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_date_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(SOLUTION);
			setState(262);
			match(LEFT_SIGNAL);
			setState(263);
			match(DATE);
			setState(264);
			match(BAR);
			setState(265);
			grade();
			setState(266);
			match(RIGHT_SIGNAL);
			setState(267);
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
	public static class Time_questionContext extends ParserRuleContext {
		public TerminalNode TIME_QUESTION() { return getToken(InterviewModelGrammarParser.TIME_QUESTION, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Time_answerContext time_answer() {
			return getRuleContext(Time_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Time_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterTime_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitTime_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitTime_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Time_questionContext time_question() throws RecognitionException {
		Time_questionContext _localctx = new Time_questionContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_time_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(269);
			match(TIME_QUESTION);
			setState(270);
			question_title();
			setState(271);
			match(EOL);
			setState(274);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(272);
				time_answer();
				}
				break;
			case T__2:
				{
				setState(273);
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
	public static class Time_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode TIME() { return getToken(InterviewModelGrammarParser.TIME, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Time_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_time_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterTime_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitTime_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitTime_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Time_answerContext time_answer() throws RecognitionException {
		Time_answerContext _localctx = new Time_answerContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_time_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(276);
			match(SOLUTION);
			setState(277);
			match(LEFT_SIGNAL);
			setState(278);
			match(TIME);
			setState(279);
			match(BAR);
			setState(280);
			grade();
			setState(281);
			match(RIGHT_SIGNAL);
			setState(282);
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
	public static class Numericscale_questionContext extends ParserRuleContext {
		public TerminalNode NUMERIC_SCALE() { return getToken(InterviewModelGrammarParser.NUMERIC_SCALE, 0); }
		public Question_titleContext question_title() {
			return getRuleContext(Question_titleContext.class,0);
		}
		public TerminalNode EOL() { return getToken(InterviewModelGrammarParser.EOL, 0); }
		public Numericscale_answerContext numericscale_answer() {
			return getRuleContext(Numericscale_answerContext.class,0);
		}
		public AnswerContext answer() {
			return getRuleContext(AnswerContext.class,0);
		}
		public Numericscale_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericscale_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterNumericscale_question(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitNumericscale_question(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitNumericscale_question(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numericscale_questionContext numericscale_question() throws RecognitionException {
		Numericscale_questionContext _localctx = new Numericscale_questionContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_numericscale_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(NUMERIC_SCALE);
			setState(285);
			question_title();
			setState(286);
			match(EOL);
			setState(289);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SOLUTION:
				{
				setState(287);
				numericscale_answer();
				}
				break;
			case T__2:
				{
				setState(288);
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
	public static class Numericscale_answerContext extends ParserRuleContext {
		public TerminalNode SOLUTION() { return getToken(InterviewModelGrammarParser.SOLUTION, 0); }
		public TerminalNode LEFT_SIGNAL() { return getToken(InterviewModelGrammarParser.LEFT_SIGNAL, 0); }
		public TerminalNode INTEGER() { return getToken(InterviewModelGrammarParser.INTEGER, 0); }
		public TerminalNode BAR() { return getToken(InterviewModelGrammarParser.BAR, 0); }
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public TerminalNode RIGHT_SIGNAL() { return getToken(InterviewModelGrammarParser.RIGHT_SIGNAL, 0); }
		public TerminalNode SEMICOLON() { return getToken(InterviewModelGrammarParser.SEMICOLON, 0); }
		public Numericscale_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericscale_answer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).enterNumericscale_answer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof InterviewModelGrammarListener ) ((InterviewModelGrammarListener)listener).exitNumericscale_answer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof InterviewModelGrammarVisitor ) return ((InterviewModelGrammarVisitor<? extends T>)visitor).visitNumericscale_answer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Numericscale_answerContext numericscale_answer() throws RecognitionException {
		Numericscale_answerContext _localctx = new Numericscale_answerContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_numericscale_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			match(SOLUTION);
			setState(292);
			match(LEFT_SIGNAL);
			setState(293);
			match(INTEGER);
			setState(294);
			match(BAR);
			setState(295);
			grade();
			setState(296);
			match(RIGHT_SIGNAL);
			setState(297);
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
		"\u0004\u0001+\u012c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000\u0001\u0000\u0004\u0000"+
		">\b\u0000\u000b\u0000\f\u0000?\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0003\u0001F\b\u0001\u0001\u0001\u0001\u0001\u0003\u0001J\b\u0001"+
		"\u0001\u0001\u0005\u0001M\b\u0001\n\u0001\f\u0001P\t\u0001\u0001\u0002"+
		"\u0001\u0002\u0003\u0002T\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002Y\b\u0002\u0003\u0002[\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0004\u0002`\b\u0002\u000b\u0002\f\u0002a\u0003\u0002d\b\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005u\b\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007|\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\b\u0001\b\u0003\b\u0087\b\b\u0001\b\u0003\b\u008a\b"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u0091\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00a0\b\u000b\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0003\f\u00a6\b\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u00b3"+
		"\b\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000e\u0003\u000e\u00bb\b\u000e\u0001\u000f\u0001\u000f\u0004\u000f\u00bf"+
		"\b\u000f\u000b\u000f\f\u000f\u00c0\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0003\u0010\u00c8\b\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0005\u0012"+
		"\u00d7\b\u0012\n\u0012\f\u0012\u00da\t\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u00e6\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015\u00f5"+
		"\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0003\u0017\u0104\b\u0017\u0001\u0018\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001\u0018\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0001\u0019\u0003\u0019\u0113"+
		"\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001b\u0003\u001b\u0122\b\u001b\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0000\u0000\u001d\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468\u0000\u0004\u0001\u0000"+
		"\u000b\f\u0001\u0000\t\n\u0002\u0000\u000b\u000b\u0013\u0013\u0001\u0000"+
		"\u0004\u0005\u0131\u0000:\u0001\u0000\u0000\u0000\u0002C\u0001\u0000\u0000"+
		"\u0000\u0004c\u0001\u0000\u0000\u0000\u0006e\u0001\u0000\u0000\u0000\b"+
		"i\u0001\u0000\u0000\u0000\nt\u0001\u0000\u0000\u0000\fv\u0001\u0000\u0000"+
		"\u0000\u000ex\u0001\u0000\u0000\u0000\u0010\u0084\u0001\u0000\u0000\u0000"+
		"\u0012\u008b\u0001\u0000\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000"+
		"\u0016\u009a\u0001\u0000\u0000\u0000\u0018\u00a1\u0001\u0000\u0000\u0000"+
		"\u001a\u00ac\u0001\u0000\u0000\u0000\u001c\u00b4\u0001\u0000\u0000\u0000"+
		"\u001e\u00bc\u0001\u0000\u0000\u0000 \u00c2\u0001\u0000\u0000\u0000\""+
		"\u00c9\u0001\u0000\u0000\u0000$\u00d1\u0001\u0000\u0000\u0000&\u00e0\u0001"+
		"\u0000\u0000\u0000(\u00e7\u0001\u0000\u0000\u0000*\u00ef\u0001\u0000\u0000"+
		"\u0000,\u00f6\u0001\u0000\u0000\u0000.\u00fe\u0001\u0000\u0000\u00000"+
		"\u0105\u0001\u0000\u0000\u00002\u010d\u0001\u0000\u0000\u00004\u0114\u0001"+
		"\u0000\u0000\u00006\u011c\u0001\u0000\u0000\u00008\u0123\u0001\u0000\u0000"+
		"\u0000:;\u0005\u0001\u0000\u0000;=\u0003\u0006\u0003\u0000<>\u0005\b\u0000"+
		"\u0000=<\u0001\u0000\u0000\u0000>?\u0001\u0000\u0000\u0000?=\u0001\u0000"+
		"\u0000\u0000?@\u0001\u0000\u0000\u0000@A\u0001\u0000\u0000\u0000AB\u0003"+
		"\b\u0004\u0000B\u0001\u0001\u0000\u0000\u0000CN\u0003\u0004\u0002\u0000"+
		"DF\u0007\u0000\u0000\u0000ED\u0001\u0000\u0000\u0000EF\u0001\u0000\u0000"+
		"\u0000FG\u0001\u0000\u0000\u0000GJ\u0005\u0007\u0000\u0000HJ\u0005\u001a"+
		"\u0000\u0000IE\u0001\u0000\u0000\u0000IH\u0001\u0000\u0000\u0000JK\u0001"+
		"\u0000\u0000\u0000KM\u0003\u0004\u0002\u0000LI\u0001\u0000\u0000\u0000"+
		"MP\u0001\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000"+
		"\u0000O\u0003\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000\u0000QS\u0005"+
		"\u0006\u0000\u0000RT\u0005\u001b\u0000\u0000SR\u0001\u0000\u0000\u0000"+
		"ST\u0001\u0000\u0000\u0000TZ\u0001\u0000\u0000\u0000UV\u0005\u0002\u0000"+
		"\u0000VX\u0005\u0006\u0000\u0000WY\u0005\u001b\u0000\u0000XW\u0001\u0000"+
		"\u0000\u0000XY\u0001\u0000\u0000\u0000Y[\u0001\u0000\u0000\u0000ZU\u0001"+
		"\u0000\u0000\u0000Z[\u0001\u0000\u0000\u0000[d\u0001\u0000\u0000\u0000"+
		"\\d\u0005\t\u0000\u0000]d\u0005\n\u0000\u0000^`\u0005\u001b\u0000\u0000"+
		"_^\u0001\u0000\u0000\u0000`a\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000"+
		"\u0000ab\u0001\u0000\u0000\u0000bd\u0001\u0000\u0000\u0000cQ\u0001\u0000"+
		"\u0000\u0000c\\\u0001\u0000\u0000\u0000c]\u0001\u0000\u0000\u0000c_\u0001"+
		"\u0000\u0000\u0000d\u0005\u0001\u0000\u0000\u0000ef\u0005\u0014\u0000"+
		"\u0000fg\u0003\u0002\u0001\u0000gh\u0005\u0014\u0000\u0000h\u0007\u0001"+
		"\u0000\u0000\u0000ij\u0003\n\u0005\u0000j\t\u0001\u0000\u0000\u0000ku"+
		"\u0003\u0012\t\u0000lu\u0003\u0016\u000b\u0000mu\u0003\u001a\r\u0000n"+
		"u\u0003\u001c\u000e\u0000ou\u0003&\u0013\u0000pu\u0003*\u0015\u0000qu"+
		"\u0003.\u0017\u0000ru\u00032\u0019\u0000su\u00036\u001b\u0000tk\u0001"+
		"\u0000\u0000\u0000tl\u0001\u0000\u0000\u0000tm\u0001\u0000\u0000\u0000"+
		"tn\u0001\u0000\u0000\u0000to\u0001\u0000\u0000\u0000tp\u0001\u0000\u0000"+
		"\u0000tq\u0001\u0000\u0000\u0000tr\u0001\u0000\u0000\u0000ts\u0001\u0000"+
		"\u0000\u0000u\u000b\u0001\u0000\u0000\u0000vw\u0007\u0001\u0000\u0000"+
		"w\r\u0001\u0000\u0000\u0000xy\u0005\u0014\u0000\u0000y{\u0003\u0002\u0001"+
		"\u0000z|\u0007\u0002\u0000\u0000{z\u0001\u0000\u0000\u0000{|\u0001\u0000"+
		"\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0005\u0014\u0000\u0000~\u007f"+
		"\u0005\u0007\u0000\u0000\u007f\u0080\u0005\u0011\u0000\u0000\u0080\u0081"+
		"\u0003\f\u0006\u0000\u0081\u0082\u0005\u0012\u0000\u0000\u0082\u0083\u0005"+
		"\u000e\u0000\u0000\u0083\u000f\u0001\u0000\u0000\u0000\u0084\u0086\u0005"+
		"\u0003\u0000\u0000\u0085\u0087\u0005\u0007\u0000\u0000\u0086\u0085\u0001"+
		"\u0000\u0000\u0000\u0086\u0087\u0001\u0000\u0000\u0000\u0087\u0089\u0001"+
		"\u0000\u0000\u0000\u0088\u008a\u0003\u0002\u0001\u0000\u0089\u0088\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u0011\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005#\u0000\u0000\u008c\u008d\u0003\u000e"+
		"\u0007\u0000\u008d\u0090\u0005\b\u0000\u0000\u008e\u0091\u0003\u0014\n"+
		"\u0000\u008f\u0091\u0003\u0010\b\u0000\u0090\u008e\u0001\u0000\u0000\u0000"+
		"\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0013\u0001\u0000\u0000\u0000"+
		"\u0092\u0093\u0005\u0018\u0000\u0000\u0093\u0094\u0005\u0015\u0000\u0000"+
		"\u0094\u0095\u0007\u0003\u0000\u0000\u0095\u0096\u0005\u0017\u0000\u0000"+
		"\u0096\u0097\u0003\f\u0006\u0000\u0097\u0098\u0005\u0016\u0000\u0000\u0098"+
		"\u0099\u0005\u000e\u0000\u0000\u0099\u0015\u0001\u0000\u0000\u0000\u009a"+
		"\u009b\u0005$\u0000\u0000\u009b\u009c\u0003\u000e\u0007\u0000\u009c\u009f"+
		"\u0005\b\u0000\u0000\u009d\u00a0\u0003\u0018\f\u0000\u009e\u00a0\u0003"+
		"\u0010\b\u0000\u009f\u009d\u0001\u0000\u0000\u0000\u009f\u009e\u0001\u0000"+
		"\u0000\u0000\u00a0\u0017\u0001\u0000\u0000\u0000\u00a1\u00a2\u0005\u0018"+
		"\u0000\u0000\u00a2\u00a3\u0005\u0015\u0000\u0000\u00a3\u00a5\u0003\u0004"+
		"\u0002\u0000\u00a4\u00a6\u0005\u000b\u0000\u0000\u00a5\u00a4\u0001\u0000"+
		"\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000\u00a6\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a7\u00a8\u0005\u0017\u0000\u0000\u00a8\u00a9\u0003\f\u0006"+
		"\u0000\u00a9\u00aa\u0005\u0016\u0000\u0000\u00aa\u00ab\u0005\u000e\u0000"+
		"\u0000\u00ab\u0019\u0001\u0000\u0000\u0000\u00ac\u00ad\u0005%\u0000\u0000"+
		"\u00ad\u00ae\u0003\u000e\u0007\u0000\u00ae\u00af\u0005\b\u0000\u0000\u00af"+
		"\u00b2\u0003\u001e\u000f\u0000\u00b0\u00b3\u0003\"\u0011\u0000\u00b1\u00b3"+
		"\u0003\u0010\b\u0000\u00b2\u00b0\u0001\u0000\u0000\u0000\u00b2\u00b1\u0001"+
		"\u0000\u0000\u0000\u00b3\u001b\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005"+
		"&\u0000\u0000\u00b5\u00b6\u0003\u000e\u0007\u0000\u00b6\u00b7\u0005\b"+
		"\u0000\u0000\u00b7\u00ba\u0003\u001e\u000f\u0000\u00b8\u00bb\u0003$\u0012"+
		"\u0000\u00b9\u00bb\u0003\u0010\b\u0000\u00ba\u00b8\u0001\u0000\u0000\u0000"+
		"\u00ba\u00b9\u0001\u0000\u0000\u0000\u00bb\u001d\u0001\u0000\u0000\u0000"+
		"\u00bc\u00be\u0003 \u0010\u0000\u00bd\u00bf\u0003 \u0010\u0000\u00be\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bf\u00c0\u0001\u0000\u0000\u0000\u00c0\u00be"+
		"\u0001\u0000\u0000\u0000\u00c0\u00c1\u0001\u0000\u0000\u0000\u00c1\u001f"+
		"\u0001\u0000\u0000\u0000\u00c2\u00c3\u0005\u0019\u0000\u0000\u00c3\u00c4"+
		"\u0005\t\u0000\u0000\u00c4\u00c5\u0003\u0002\u0001\u0000\u00c5\u00c7\u0005"+
		"\u000e\u0000\u0000\u00c6\u00c8\u0005\b\u0000\u0000\u00c7\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c7\u00c8\u0001\u0000\u0000\u0000\u00c8!\u0001\u0000\u0000"+
		"\u0000\u00c9\u00ca\u0005\u0018\u0000\u0000\u00ca\u00cb\u0005\u0015\u0000"+
		"\u0000\u00cb\u00cc\u0003\u0002\u0001\u0000\u00cc\u00cd\u0005\u0017\u0000"+
		"\u0000\u00cd\u00ce\u0003\f\u0006\u0000\u00ce\u00cf\u0005\u0016\u0000\u0000"+
		"\u00cf\u00d0\u0005\u000e\u0000\u0000\u00d0#\u0001\u0000\u0000\u0000\u00d1"+
		"\u00d2\u0005\u0018\u0000\u0000\u00d2\u00d3\u0005\u0015\u0000\u0000\u00d3"+
		"\u00d8\u0003\u0002\u0001\u0000\u00d4\u00d5\u0005\f\u0000\u0000\u00d5\u00d7"+
		"\u0003\u0002\u0001\u0000\u00d6\u00d4\u0001\u0000\u0000\u0000\u00d7\u00da"+
		"\u0001\u0000\u0000\u0000\u00d8\u00d6\u0001\u0000\u0000\u0000\u00d8\u00d9"+
		"\u0001\u0000\u0000\u0000\u00d9\u00db\u0001\u0000\u0000\u0000\u00da\u00d8"+
		"\u0001\u0000\u0000\u0000\u00db\u00dc\u0005\u0017\u0000\u0000\u00dc\u00dd"+
		"\u0003\f\u0006\u0000\u00dd\u00de\u0005\u0016\u0000\u0000\u00de\u00df\u0005"+
		"\u000e\u0000\u0000\u00df%\u0001\u0000\u0000\u0000\u00e0\u00e1\u0005\'"+
		"\u0000\u0000\u00e1\u00e2\u0003\u000e\u0007\u0000\u00e2\u00e5\u0005\b\u0000"+
		"\u0000\u00e3\u00e6\u0003(\u0014\u0000\u00e4\u00e6\u0003\u0010\b\u0000"+
		"\u00e5\u00e3\u0001\u0000\u0000\u0000\u00e5\u00e4\u0001\u0000\u0000\u0000"+
		"\u00e6\'\u0001\u0000\u0000\u0000\u00e7\u00e8\u0005\u0018\u0000\u0000\u00e8"+
		"\u00e9\u0005\u0015\u0000\u0000\u00e9\u00ea\u0005\t\u0000\u0000\u00ea\u00eb"+
		"\u0005\u0017\u0000\u0000\u00eb\u00ec\u0003\f\u0006\u0000\u00ec\u00ed\u0005"+
		"\u0016\u0000\u0000\u00ed\u00ee\u0005\u000e\u0000\u0000\u00ee)\u0001\u0000"+
		"\u0000\u0000\u00ef\u00f0\u0005(\u0000\u0000\u00f0\u00f1\u0003\u000e\u0007"+
		"\u0000\u00f1\u00f4\u0005\b\u0000\u0000\u00f2\u00f5\u0003,\u0016\u0000"+
		"\u00f3\u00f5\u0003\u0010\b\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f3\u0001\u0000\u0000\u0000\u00f5+\u0001\u0000\u0000\u0000\u00f6\u00f7"+
		"\u0005\u0018\u0000\u0000\u00f7\u00f8\u0005\u0015\u0000\u0000\u00f8\u00f9"+
		"\u0005\n\u0000\u0000\u00f9\u00fa\u0005\u0017\u0000\u0000\u00fa\u00fb\u0003"+
		"\f\u0006\u0000\u00fb\u00fc\u0005\u0016\u0000\u0000\u00fc\u00fd\u0005\u000e"+
		"\u0000\u0000\u00fd-\u0001\u0000\u0000\u0000\u00fe\u00ff\u0005)\u0000\u0000"+
		"\u00ff\u0100\u0003\u000e\u0007\u0000\u0100\u0103\u0005\b\u0000\u0000\u0101"+
		"\u0104\u00030\u0018\u0000\u0102\u0104\u0003\u0010\b\u0000\u0103\u0101"+
		"\u0001\u0000\u0000\u0000\u0103\u0102\u0001\u0000\u0000\u0000\u0104/\u0001"+
		"\u0000\u0000\u0000\u0105\u0106\u0005\u0018\u0000\u0000\u0106\u0107\u0005"+
		"\u0015\u0000\u0000\u0107\u0108\u0005\u001f\u0000\u0000\u0108\u0109\u0005"+
		"\u0017\u0000\u0000\u0109\u010a\u0003\f\u0006\u0000\u010a\u010b\u0005\u0016"+
		"\u0000\u0000\u010b\u010c\u0005\u000e\u0000\u0000\u010c1\u0001\u0000\u0000"+
		"\u0000\u010d\u010e\u0005*\u0000\u0000\u010e\u010f\u0003\u000e\u0007\u0000"+
		"\u010f\u0112\u0005\b\u0000\u0000\u0110\u0113\u00034\u001a\u0000\u0111"+
		"\u0113\u0003\u0010\b\u0000\u0112\u0110\u0001\u0000\u0000\u0000\u0112\u0111"+
		"\u0001\u0000\u0000\u0000\u01133\u0001\u0000\u0000\u0000\u0114\u0115\u0005"+
		"\u0018\u0000\u0000\u0115\u0116\u0005\u0015\u0000\u0000\u0116\u0117\u0005"+
		"\"\u0000\u0000\u0117\u0118\u0005\u0017\u0000\u0000\u0118\u0119\u0003\f"+
		"\u0006\u0000\u0119\u011a\u0005\u0016\u0000\u0000\u011a\u011b\u0005\u000e"+
		"\u0000\u0000\u011b5\u0001\u0000\u0000\u0000\u011c\u011d\u0005+\u0000\u0000"+
		"\u011d\u011e\u0003\u000e\u0007\u0000\u011e\u0121\u0005\b\u0000\u0000\u011f"+
		"\u0122\u00038\u001c\u0000\u0120\u0122\u0003\u0010\b\u0000\u0121\u011f"+
		"\u0001\u0000\u0000\u0000\u0121\u0120\u0001\u0000\u0000\u0000\u01227\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0005\u0018\u0000\u0000\u0124\u0125\u0005"+
		"\u0015\u0000\u0000\u0125\u0126\u0005\t\u0000\u0000\u0126\u0127\u0005\u0017"+
		"\u0000\u0000\u0127\u0128\u0003\f\u0006\u0000\u0128\u0129\u0005\u0016\u0000"+
		"\u0000\u0129\u012a\u0005\u000e\u0000\u0000\u012a9\u0001\u0000\u0000\u0000"+
		"\u001a?EINSXZact{\u0086\u0089\u0090\u009f\u00a5\u00b2\u00ba\u00c0\u00c7"+
		"\u00d8\u00e5\u00f4\u0103\u0112\u0121";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}