grammar InterviewModelGrammar;

// Define tokens
TRUE: 'TRUE';
FALSE: 'FALSE';
WORD : [a-zA-Z]+('-'[a-zA-Z]+)* ;  // Single word
SPACE : ' '; //[ \t]+ ; //|[ \t] -> skip ;  // Define SPACE to include space and tab characters
EOL : '\r'? '\n';  // Define EOL for newline characters (carriage return or newline)
INTEGER : [0-9]+ ;
DECIMAL: INTEGER (POINT|COMMA) (INTEGER*[1-9]);
POINT : '.' ;
COMMA : ',' ;
TWO_POINTS : ':' ;
SEMICOLON : ';' ;
LEFT_PARENTHESIS : '(' ;
RIGHT_PARENTHESIS : ')' ;
LEFT_BRACKET : '[' ;
RIGHT_BRACKET : ']' ;
QUESTION_MARK : '?' ;
QUOTATION_MARKS: '"';
LEFT_SIGNAL: '<';
RIGHT_SIGNAL: '>';
BAR: '|';
SOLUTION: 'SOL' SPACE;
OPTION: 'OPT' SPACE;
TRACO: '-' ;

SPECIAL_CHARS: ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'|'-'|'='|'+'|'{'|'}'|'['|']'|'|'|':'|';'|'"'|'<'|'>'|','|'.'|'?'|'/');

D: [0]?[1-9] | [1-2][0-9] | [3][0|1];
M: [0]?[1-9] | [1][0-2];
Y: [1-2][0-9][0-9][0-9];
DATE: D '/' M '/' Y;

H: [0-1]?[0-9] | [2][0-3];
MIN: [0-5][0-9] ;
TIME: H TWO_POINTS MIN;

TRUE_FALSE: 'TFQUES [TRUE|FALSE]' SPACE;
SHORT_ANSWER: 'SAQUES [Text]' SPACE;
SINGLE_CHOICE : 'SCQUES [Number]' SPACE;
MULTIPLE_CHOICE: 'MCQUES [Numbers]' SPACE;
INTEGER_QUESTION: 'INTQUES [Integer]' SPACE;
DECIMAL_QUESTION: 'DECQUES [Decimal]' SPACE;
DATE_QUESTION: 'DATEQUES [DD/MM/YYYY]' SPACE;
TIME_QUESTION: 'TIMEQUES [HH:MM]' SPACE;
NUMERIC_SCALE: 'NSQUES [1-5]' SPACE;

// Define grammar rules
start: 'INTERVIEW ' quotedTitle EOL+ questions;

sentences: sentence (((COMMA|POINT)? SPACE|'-') sentence)*;

sentence : WORD(SPECIAL_CHARS)?('\'' WORD(SPECIAL_CHARS)?)?|INTEGER|DECIMAL|SPECIAL_CHARS+;

//'-'?(WORD|INTEGER|DECIMAL)'-'?


quotedTitle : QUOTATION_MARKS sentences QUOTATION_MARKS;

questions: question+;

question: truefalse_question
             | shortanswer_question
             | singlechoice_question
             | multiplechoice_question
             | integer_question
             | decimal_question
             | date_question
             | time_question
             | numericscale_question
             ;

grade: DECIMAL | INTEGER;

question_title: QUOTATION_MARKS sentences (QUESTION_MARK|POINT)? QUOTATION_MARKS SPACE LEFT_BRACKET grade RIGHT_BRACKET SEMICOLON;

answer: 'ANS:' SPACE? sentences?;


truefalse_question: TRUE_FALSE question_title EOL (truefalse_answer | answer);

truefalse_answer: SOLUTION LEFT_SIGNAL (TRUE | FALSE) BAR grade RIGHT_SIGNAL SEMICOLON;


shortanswer_question: SHORT_ANSWER question_title EOL (shortanswer_answer | answer);

shortanswer_answer: SOLUTION LEFT_SIGNAL sentence POINT? BAR grade RIGHT_SIGNAL SEMICOLON;


singlechoice_question: SINGLE_CHOICE question_title EOL optionz (singlechoice_answer | answer);

multiplechoice_question: MULTIPLE_CHOICE question_title EOL optionz (multiplechoice_answer | answer);

optionz: option option+;

option: OPTION INTEGER sentences SEMICOLON EOL?;

singlechoice_answer: SOLUTION LEFT_SIGNAL sentences BAR grade RIGHT_SIGNAL SEMICOLON;

multiplechoice_answer: SOLUTION LEFT_SIGNAL sentences (COMMA sentences)* BAR grade RIGHT_SIGNAL SEMICOLON;


integer_question: INTEGER_QUESTION question_title EOL (integer_answer | answer);

integer_answer: SOLUTION LEFT_SIGNAL INTEGER BAR grade RIGHT_SIGNAL SEMICOLON;


decimal_question: DECIMAL_QUESTION question_title EOL (decimal_answer | answer);

decimal_answer: SOLUTION LEFT_SIGNAL DECIMAL BAR grade RIGHT_SIGNAL SEMICOLON;


date_question: DATE_QUESTION question_title EOL (date_answer | answer);

date_answer: SOLUTION LEFT_SIGNAL DATE BAR grade RIGHT_SIGNAL SEMICOLON;


time_question: TIME_QUESTION question_title EOL (time_answer | answer);

time_answer: SOLUTION LEFT_SIGNAL TIME BAR grade RIGHT_SIGNAL SEMICOLON;


numericscale_question: NUMERIC_SCALE question_title EOL (numericscale_answer | answer);

numericscale_answer: SOLUTION LEFT_SIGNAL INTEGER BAR grade RIGHT_SIGNAL SEMICOLON;