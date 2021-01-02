// Mostly done with ANTLR4 and Java

// Execute this groovy script, followed by:
// groovy day19-init.groovy
// java -jar antlr-4.9-complete.jar Day19.g4
// javac -classpath antlr-4.9-complete.jar;. *.java
// groovy -cp antlr-4.9-complete.jar day19.groovy

def rslt = 0L
new File('day19-input.txt').each { line ->
  Day19Parser parser = new Day19Parser(new org.antlr.v4.runtime.CommonTokenStream(new Day19Lexer(new org.antlr.v4.runtime.ANTLRInputStream(line))));
  parser.setErrorHandler(new org.antlr.v4.runtime.BailErrorStrategy());
  try {
    org.antlr.v4.runtime.tree.ParseTree ctx = parser.rule0();
    rslt++;
  } catch (org.antlr.v4.runtime.misc.ParseCancellationException uncaught) {
    // Nothing
  }
}
println "Valid lines: $rslt"
