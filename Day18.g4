// Execute those two command lines:
// java -jar antlr-4.9-complete.jar Day18.g4
// javac -classpath antlr-4.9-complete.jar;. *.java
// groovy -cp antlr-4.9-complete.jar day18.groovy

grammar Day18;

compileUnit:
   expression EOF
 ;

// This is the solution of part 2 - For part 1, just use PLUS | MINUS | TIMES | DIV on one line
expression:
    '(' expression ')'                    # expr1
  | expression (PLUS | MINUS ) expression # expr2
  | expression (TIMES| DIV) expression    # expr3
  | NUMBER                                # expr4
  ;

PLUS:
   '+'
  ;


MINUS:
   '-'
  ;


TIMES:
   '*'
  ;


DIV:
   '/'
  ;


NUMBER:
   ('0' .. '9')+
  ;


WS:
   [ \r\n\t] + -> skip
  ;