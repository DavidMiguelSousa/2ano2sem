grammar JobRequirementsGrammar;

// Define tokens
WORD : [a-zA-Z]+( ('-'|'\'')[a-zA-Z]+)*;   // Single word
SPACE : ' ' ;  // Define SPACE to include space and tab characters
EOL : [\r\n]+ ;  // Define EOL for newline characters (carriage return or newline)
INTEGER : [0-9]+ ;
DECIMAL: INTEGER (POINT|COMMA) (INTEGER*[1-9]);
HASHTAG : '#' ;
POINT : '.' ;
COMMA : ',' ;
TWO_POINTS : ':' ;
SEMICOLON : ';' ;
LEFT_PARENTHESIS : '(' ;
RIGHT_PARENTHESIS : ')' ;
QUOTED_MARKS: '"';
ANSWER: 'Answer:';
REQUIREMENTS: 'Requirement:';


MININT: 'MININT' SPACE; // Minimum integer
MAXINT: 'MAXINT' SPACE; // Maximum integer
MINORD: 'MINORD' SPACE; // Minimum ordinal (Select one)
MAXORD: 'MAXORD' SPACE; // Maximum ordinal
MULORSING: 'MULORSING' SPACE; // Multiple choice (Select one or more)



SPECIAL_CHARS: ('~'|'!'|'@'|'#'|'$'|'%'|'^'|'&'|'*'|'('|')'|'_'|'-'|'='|'+'|'{'|'}'|'['|']'|'|'|':'|';'|'"'|'<'|'>'|','|'.'|'?');

// Define grammar rules
start : 'JOB' SPACE 'REQUIREMENTS' SPACE quotedTitle EOL* requirements;

sentences: sentence (((COMMA|POINT)? SPACE|'-') sentence)*;

sentence : (WORD(SPECIAL_CHARS)?('\'' WORD(SPECIAL_CHARS)?)?)|INTEGER|DECIMAL|SPECIAL_CHARS+;

quotedTitle : QUOTED_MARKS sentences QUOTED_MARKS ;

requirements : requirement (EOL requirement)*;

question: (SPACE)* QUOTED_MARKS sentences SPACE optionz QUOTED_MARKS SEMICOLON;



requirement: requirement_min_int | requirement_max_int | requirement_min_ord | requirement_max_ord | requirement_mulorsing;

requirement_min_int: MININT question EOL (requirement_expected_int  | answer);

requirement_max_int: MAXINT question EOL (requirement_expected_int | answer);

requirement_min_ord: MINORD question EOL (requeriment_expected | answer);

requirement_max_ord: MAXORD question EOL (requeriment_expected | answer);

requirement_mulorsing: MULORSING question EOL (requeriment_expected | answer);






optionz: LEFT_PARENTHESIS option+ RIGHT_PARENTHESIS(POINT)*;

option: sentence (SEMICOLON SPACE sentence)*;

answer: ANSWER (SPACE)* (sentence (COMMA sentence)* )*;

requeriment_expected: REQUIREMENTS SPACE sentence (COMMA sentence)* SEMICOLON;

requirement_expected_int: REQUIREMENTS SPACE INTEGER SEMICOLON;