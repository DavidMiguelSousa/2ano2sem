// Generated from C:/Projeto4/sem4pi-23-24-2df1/LPROG/src/main/grammar/JobRequirementsGrammar.g4 by ANTLR 4.13.1
package jobRequirements;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class JobRequirementsGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, WORD=5, SPACE=6, EOL=7, INTEGER=8, DECIMAL=9, 
		HASHTAG=10, POINT=11, COMMA=12, TWO_POINTS=13, SEMICOLON=14, LEFT_PARENTHESIS=15, 
		RIGHT_PARENTHESIS=16, QUOTED_MARKS=17, ANSWER=18, REQUIREMENTS=19, MININT=20, 
		MAXINT=21, MINORD=22, MAXORD=23, MULORSING=24, SPECIAL_CHARS=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "WORD", "SPACE", "EOL", "INTEGER", "DECIMAL", 
			"HASHTAG", "POINT", "COMMA", "TWO_POINTS", "SEMICOLON", "LEFT_PARENTHESIS", 
			"RIGHT_PARENTHESIS", "QUOTED_MARKS", "ANSWER", "REQUIREMENTS", "MININT", 
			"MAXINT", "MINORD", "MAXORD", "MULORSING", "SPECIAL_CHARS"
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


	public JobRequirementsGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "JobRequirementsGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u00c8\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004"+
		"\u0004\u0004J\b\u0004\u000b\u0004\f\u0004K\u0001\u0004\u0001\u0004\u0004"+
		"\u0004P\b\u0004\u000b\u0004\f\u0004Q\u0005\u0004T\b\u0004\n\u0004\f\u0004"+
		"W\t\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0004\u0006\\\b\u0006\u000b"+
		"\u0006\f\u0006]\u0001\u0007\u0004\u0007a\b\u0007\u000b\u0007\f\u0007b"+
		"\u0001\b\u0001\b\u0001\b\u0003\bh\b\b\u0001\b\u0005\bk\b\b\n\b\f\bn\t"+
		"\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001"+
		"\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0015\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0000\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001\u0000\u0006\u0002"+
		"\u0000AZaz\u0002\u0000\'\'--\u0002\u0000\n\n\r\r\u0001\u000009\u0001\u0000"+
		"19\u0006\u0000!&(.:@[[]_{~\u00ce\u0000\u0001\u0001\u0000\u0000\u0000\u0000"+
		"\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000"+
		"\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b"+
		"\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001"+
		"\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001"+
		"\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001"+
		"\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001"+
		"\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001"+
		"\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001\u0000\u0000"+
		"\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000\u0000\u0000"+
		"\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000\u0000-"+
		"\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000\u00001\u0001\u0000"+
		"\u0000\u0000\u00013\u0001\u0000\u0000\u0000\u00037\u0001\u0000\u0000\u0000"+
		"\u0005D\u0001\u0000\u0000\u0000\u0007F\u0001\u0000\u0000\u0000\tI\u0001"+
		"\u0000\u0000\u0000\u000bX\u0001\u0000\u0000\u0000\r[\u0001\u0000\u0000"+
		"\u0000\u000f`\u0001\u0000\u0000\u0000\u0011d\u0001\u0000\u0000\u0000\u0013"+
		"q\u0001\u0000\u0000\u0000\u0015s\u0001\u0000\u0000\u0000\u0017u\u0001"+
		"\u0000\u0000\u0000\u0019w\u0001\u0000\u0000\u0000\u001by\u0001\u0000\u0000"+
		"\u0000\u001d{\u0001\u0000\u0000\u0000\u001f}\u0001\u0000\u0000\u0000!"+
		"\u007f\u0001\u0000\u0000\u0000#\u0081\u0001\u0000\u0000\u0000%\u0089\u0001"+
		"\u0000\u0000\u0000\'\u0096\u0001\u0000\u0000\u0000)\u009f\u0001\u0000"+
		"\u0000\u0000+\u00a8\u0001\u0000\u0000\u0000-\u00b1\u0001\u0000\u0000\u0000"+
		"/\u00ba\u0001\u0000\u0000\u00001\u00c6\u0001\u0000\u0000\u000034\u0005"+
		"J\u0000\u000045\u0005O\u0000\u000056\u0005B\u0000\u00006\u0002\u0001\u0000"+
		"\u0000\u000078\u0005R\u0000\u000089\u0005E\u0000\u00009:\u0005Q\u0000"+
		"\u0000:;\u0005U\u0000\u0000;<\u0005I\u0000\u0000<=\u0005R\u0000\u0000"+
		"=>\u0005E\u0000\u0000>?\u0005M\u0000\u0000?@\u0005E\u0000\u0000@A\u0005"+
		"N\u0000\u0000AB\u0005T\u0000\u0000BC\u0005S\u0000\u0000C\u0004\u0001\u0000"+
		"\u0000\u0000DE\u0005-\u0000\u0000E\u0006\u0001\u0000\u0000\u0000FG\u0005"+
		"\'\u0000\u0000G\b\u0001\u0000\u0000\u0000HJ\u0007\u0000\u0000\u0000IH"+
		"\u0001\u0000\u0000\u0000JK\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000LU\u0001\u0000\u0000\u0000MO\u0007\u0001"+
		"\u0000\u0000NP\u0007\u0000\u0000\u0000ON\u0001\u0000\u0000\u0000PQ\u0001"+
		"\u0000\u0000\u0000QO\u0001\u0000\u0000\u0000QR\u0001\u0000\u0000\u0000"+
		"RT\u0001\u0000\u0000\u0000SM\u0001\u0000\u0000\u0000TW\u0001\u0000\u0000"+
		"\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000V\n\u0001\u0000"+
		"\u0000\u0000WU\u0001\u0000\u0000\u0000XY\u0005 \u0000\u0000Y\f\u0001\u0000"+
		"\u0000\u0000Z\\\u0007\u0002\u0000\u0000[Z\u0001\u0000\u0000\u0000\\]\u0001"+
		"\u0000\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^\u000e\u0001\u0000\u0000\u0000_a\u0007\u0003\u0000\u0000`_\u0001\u0000"+
		"\u0000\u0000ab\u0001\u0000\u0000\u0000b`\u0001\u0000\u0000\u0000bc\u0001"+
		"\u0000\u0000\u0000c\u0010\u0001\u0000\u0000\u0000dg\u0003\u000f\u0007"+
		"\u0000eh\u0003\u0015\n\u0000fh\u0003\u0017\u000b\u0000ge\u0001\u0000\u0000"+
		"\u0000gf\u0001\u0000\u0000\u0000hl\u0001\u0000\u0000\u0000ik\u0003\u000f"+
		"\u0007\u0000ji\u0001\u0000\u0000\u0000kn\u0001\u0000\u0000\u0000lj\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mo\u0001\u0000\u0000\u0000"+
		"nl\u0001\u0000\u0000\u0000op\u0007\u0004\u0000\u0000p\u0012\u0001\u0000"+
		"\u0000\u0000qr\u0005#\u0000\u0000r\u0014\u0001\u0000\u0000\u0000st\u0005"+
		".\u0000\u0000t\u0016\u0001\u0000\u0000\u0000uv\u0005,\u0000\u0000v\u0018"+
		"\u0001\u0000\u0000\u0000wx\u0005:\u0000\u0000x\u001a\u0001\u0000\u0000"+
		"\u0000yz\u0005;\u0000\u0000z\u001c\u0001\u0000\u0000\u0000{|\u0005(\u0000"+
		"\u0000|\u001e\u0001\u0000\u0000\u0000}~\u0005)\u0000\u0000~ \u0001\u0000"+
		"\u0000\u0000\u007f\u0080\u0005\"\u0000\u0000\u0080\"\u0001\u0000\u0000"+
		"\u0000\u0081\u0082\u0005A\u0000\u0000\u0082\u0083\u0005n\u0000\u0000\u0083"+
		"\u0084\u0005s\u0000\u0000\u0084\u0085\u0005w\u0000\u0000\u0085\u0086\u0005"+
		"e\u0000\u0000\u0086\u0087\u0005r\u0000\u0000\u0087\u0088\u0005:\u0000"+
		"\u0000\u0088$\u0001\u0000\u0000\u0000\u0089\u008a\u0005R\u0000\u0000\u008a"+
		"\u008b\u0005e\u0000\u0000\u008b\u008c\u0005q\u0000\u0000\u008c\u008d\u0005"+
		"u\u0000\u0000\u008d\u008e\u0005i\u0000\u0000\u008e\u008f\u0005r\u0000"+
		"\u0000\u008f\u0090\u0005e\u0000\u0000\u0090\u0091\u0005m\u0000\u0000\u0091"+
		"\u0092\u0005e\u0000\u0000\u0092\u0093\u0005n\u0000\u0000\u0093\u0094\u0005"+
		"t\u0000\u0000\u0094\u0095\u0005:\u0000\u0000\u0095&\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0005M\u0000\u0000\u0097\u0098\u0005I\u0000\u0000\u0098"+
		"\u0099\u0005N\u0000\u0000\u0099\u009a\u0005I\u0000\u0000\u009a\u009b\u0005"+
		"N\u0000\u0000\u009b\u009c\u0005T\u0000\u0000\u009c\u009d\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0003\u000b\u0005\u0000\u009e(\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0005M\u0000\u0000\u00a0\u00a1\u0005A\u0000\u0000\u00a1\u00a2"+
		"\u0005X\u0000\u0000\u00a2\u00a3\u0005I\u0000\u0000\u00a3\u00a4\u0005N"+
		"\u0000\u0000\u00a4\u00a5\u0005T\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0003\u000b\u0005\u0000\u00a7*\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0005M\u0000\u0000\u00a9\u00aa\u0005I\u0000\u0000\u00aa\u00ab"+
		"\u0005N\u0000\u0000\u00ab\u00ac\u0005O\u0000\u0000\u00ac\u00ad\u0005R"+
		"\u0000\u0000\u00ad\u00ae\u0005D\u0000\u0000\u00ae\u00af\u0001\u0000\u0000"+
		"\u0000\u00af\u00b0\u0003\u000b\u0005\u0000\u00b0,\u0001\u0000\u0000\u0000"+
		"\u00b1\u00b2\u0005M\u0000\u0000\u00b2\u00b3\u0005A\u0000\u0000\u00b3\u00b4"+
		"\u0005X\u0000\u0000\u00b4\u00b5\u0005O\u0000\u0000\u00b5\u00b6\u0005R"+
		"\u0000\u0000\u00b6\u00b7\u0005D\u0000\u0000\u00b7\u00b8\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\u0003\u000b\u0005\u0000\u00b9.\u0001\u0000\u0000\u0000"+
		"\u00ba\u00bb\u0005M\u0000\u0000\u00bb\u00bc\u0005U\u0000\u0000\u00bc\u00bd"+
		"\u0005L\u0000\u0000\u00bd\u00be\u0005O\u0000\u0000\u00be\u00bf\u0005R"+
		"\u0000\u0000\u00bf\u00c0\u0005S\u0000\u0000\u00c0\u00c1\u0005I\u0000\u0000"+
		"\u00c1\u00c2\u0005N\u0000\u0000\u00c2\u00c3\u0005G\u0000\u0000\u00c3\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c4\u00c5\u0003\u000b\u0005\u0000\u00c50\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0007\u0005\u0000\u0000\u00c72\u0001\u0000"+
		"\u0000\u0000\b\u0000KQU]bgl\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}