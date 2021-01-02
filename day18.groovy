// Mostly done with ANTLR4 and Java
def rslt = 0L
new File('day18.txt').each { line ->
  Day18Parser parser = new Day18Parser(new org.antlr.v4.runtime.CommonTokenStream(new Day18Lexer(new org.antlr.v4.runtime.ANTLRInputStream(line))));
  org.antlr.v4.runtime.tree.ParseTree ctx = parser.compileUnit();
  Long zz = new Day18VisitorImpl().visit(ctx);
  println "$line = $zz"
  rslt += zz;
}
println "Final result: $rslt"
