grammar Expr;

prog   : stat+ ;

stat   : expr NEWLINE               # printExpr
       | ID '=' expr NEWLINE        # assign
       | NEWLINE                    # blank
       ;

expr
       : expr op=('*'|'/') expr     # MulDiv
       | expr op=('+'|'-') expr     # AddSub
       | expr '!'                   # Factorial
       | func '(' expr ')'          # Function
       | INT                        # Int
       | DOUBLE                     # Double
       | ID                         # Id
       | '(' expr ')'               # Parens
       ;

func   : 'Sin' | 'Cos' | 'Tan'
       | 'Sqrt' | 'Ln' | 'Log' ;

MUL    : '*' ;
DIV    : '/' ;
ADD    : '+' ;
SUB    : '-' ;
FACTORIAL : '!' ;

ID     : [a-zA-Z]+ ;
INT    : [0-9]+ ;
DOUBLE : [0-9]+'.'[0-9]+ ;
NEWLINE: '\r'? '\n' ;
WS     : [ \t]+ -> skip ;
