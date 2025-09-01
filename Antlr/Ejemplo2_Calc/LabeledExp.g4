stat :  expr NEWLINE          #ImprimeExpr
     |  ID '=' expr NEWLINE   #Asignación 
     |  NEWLINE               #Blank
     ;

expr :  expr op=('*'|'/') expr  #Mult y Div
     |  expr op=('+'|'-') expr  #Suma y resta
     |  INT                     #Entero
     |  ID                      #Identificador
     |  '(' expr ')'            #Parentesis
     ;

MUL : '*';  #Asignación del token de multiplicacion con el simbolo * en la gramatica
DIV : '/';
ADD : '+';
SUB : '-';
