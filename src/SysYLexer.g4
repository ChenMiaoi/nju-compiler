grammar SysYLexer ;

program : 'program' ;

CONST : 'const' ;
INT : 'int' ;
VOID : 'void' ;
IF : 'if' ;
ELSE : 'else' ;
WHILE : 'while' ;
BREAK : 'break';
CONTINUE : 'continue' ;
RETURN : 'return' ;
PLUS : '+' ;
MINUS : '-' ;
MUL : '*' ;
DIV : '/' ;
MOD : '%' ;
ASSIGN : '=' ;
EQ : '==' ;
NEQ : '!=' ;
LT : '<' ;
GT :'>' ;
LE : '<=' ;
GE : '>=' ;
NOT : '!' ;
AND : '&&' ;
OR : '||' ;
L_PAREN : '(' ;
R_PAREN : ')' ;
L_BRACE : '{' ;
R_BRACE : '}' ;
L_BRACKT : '[' ;
R_BRACKT : ']' ;
COMMA : ',' ;
SEMICOLON : ';' ;

IDENT : (LETTER | DIGIT)('_' | LETTER | DIGIT)* ;
INTEGER_CONST : ('0' | [1-9]DIGIT*)
              | ('0' | [1-7]DIGIT*)
              | ('0x' | [1-9][a-fA-F]*)
              | ('0X' | [1-9][a-fA-F]*)
              ;

WS : [ \t\r\n] -> skip ;
LINE_COMMENT : '//' .*? '\n' -> skip ;
MULTILINE_COMMENT : '/*' .*? '*/' -> skip ;

fragment DIGIT : [0-9] ;
fragment LETTER : [a-zA-Z] ;