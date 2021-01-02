public class Day18VisitorImpl extends Day18BaseVisitor<Long>{
  @Override
  public Long visitCompileUnit(Day18Parser.CompileUnitContext ctx) {
    return visit(ctx.expression());
  }
  
  @Override
  public Long visitExpr1(Day18Parser.Expr1Context ctx) {
    return visit(ctx.expression());
  }

  @Override
  public Long visitExpr2(Day18Parser.Expr2Context ctx) {
    if (ctx.PLUS() != null)
      return visit(ctx.expression(0)) + visit(ctx.expression(1));
    else 
      return visit(ctx.expression(0)) - visit(ctx.expression(1));
    /*else if (ctx.TIMES() != null)
      return visit(ctx.expression(0)) * visit(ctx.expression(1));
    else 
      return visit(ctx.expression(0)) / visit(ctx.expression(1));*/
  }

  @Override
  public Long visitExpr3(Day18Parser.Expr3Context ctx) {
    if (ctx.TIMES() != null)
      return visit(ctx.expression(0)) * visit(ctx.expression(1));
    else
      return visit(ctx.expression(0)) / visit(ctx.expression(1));
  }

  @Override
  public Long visitExpr4(Day18Parser.Expr4Context ctx) {
    return Long.parseLong(ctx.NUMBER().getText());
  }
  
}
