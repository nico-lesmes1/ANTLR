grammar Hello;

r  : 'hello' ID ;

ID : [a-z]+ ;
WS : [ \t\r\n]+ -> skip ;  // Aquí le decimos a ANTLR que ignore los espacios y saltos de línea
