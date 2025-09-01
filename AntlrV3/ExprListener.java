import org.antlr.v4.runtime.tree.ParseTreeListener;


public interface ExprListener extends ParseTreeListener {
	
	 @param ctx 
	
	void enterProg(ExprParser.ProgContext ctx);
	
	 Exit a parse tree produced by {@link ExprParser#prog}.
	 @param ctx the parse tree
	
	void exitProg(ExprParser.ProgContext ctx);
	
	 Enter a parse tree produced by {@link ExprParser#stat}.
	 @param ctx the parse tree
	
	void enterStat(ExprParser.StatContext ctx);
	
	 Exit a parse tree produced by {@link ExprParser#stat}.
	 @param ctx the parse tree
	
	void exitStat(ExprParser.StatContext ctx);
	
	 Enter a parse tree produced by {@link ExprParser#expr}.
	 @param ctx the parse tree
	
	void enterExpr(ExprParser.ExprContext ctx);
	
	 Exit a parse tree produced by {@link ExprParser#expr}.
	 @param ctx the parse tree
	
	void exitExpr(ExprParser.ExprContext ctx);
}