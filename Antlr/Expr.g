grammar Expr.grammar
// Reglas sintacticas

expr   : term ( (MAS | MENOS) term)*
        { System.out.println("Análisis terminado.");
        };

term   : factor ( (MULT | DIV) factor)*;

factor : ENTERO;

//Tokens
MAS   : '+';
MENOS : '-';
MULT  : '*';
DIV   : '/';

//Reglas lexicas
ENTERO  :('0'..'9')+;

ESPACIO : (' '
         | '\t'
         | '\r'
         | '\n'

         ) + -> channel (HIDDEN)
         ;

         