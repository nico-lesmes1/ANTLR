import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends LabeledExprBaseVisitor<Integer> {
    //Memoria para la calculadora
    Map<String, Integer> memory = new HashMap<String, Integer>();

   
    @Override
    public Integer visitAssign(LabeledExprParser.AssignContext ctx) {
        String id = ctx.ID().getText(); // id is left-hand side of '='
        int value = visit(ctx.expr()); // compute value of expression on right
        memory.put(id, value); // store it in our memory
        return value;
    }

    // Expresion NEWLINE
    @Override
    public Integer visitPrintExpr(LabeledExprParser.PrintExprContext ctx) {
        Integer value = visit(ctx.expr()); // Evaluate the expression
        System.out.println(value);         // Print the result
        return 0;                          // Return dummy value
    }


    //Valor INT
    @Override
    public Integer visitInt(LabeledExprParser.IntContext ctx) {
        return Integer.valueOf(ctx.INT().getText());
    }

    //Valor ID
    @Override
    public Integer visitId(LabeledExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0;
    }

    // Expresiones de multiplicación y división
    @Override
    public Integer visitMulDiv(LabeledExprParser.MulDivContext ctx) {
        int left = visit(ctx.expr(0));  // Get value of left subexpression
        int right = visit(ctx.expr(1)); // Get value of right subexpression
        if (ctx.op.getType() == LabeledExprParser.MUL) return left * right;
        return left / right; // Must be DIV
    }


    // Expresiones de suma y resta
    @Override
    public Integer visitAddSub(LabeledExprParser.AddSubContext ctx) {
        int left = visit(ctx.expr(0));  // Get value of left subexpression
        int right = visit(ctx.expr(1)); // Get value of right subexpression
        if (ctx.op.getType() == LabeledExprParser.ADD) return left + right;
        return left - right; // Must be SUB
    }

    // Expresiones de parentesis
    @Override
    public Integer visitParens(LabeledExprParser.ParensContext ctx) {
        return visit(ctx.expr()); // Evaluate the inner expression
    }