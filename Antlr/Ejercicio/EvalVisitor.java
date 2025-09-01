import java.util.HashMap;
import java.util.Map;

public class EvalVisitor extends ExprBaseVisitor<Double> {
    Map<String, Double> memory = new HashMap<>();
    boolean useDegrees = true; // true = grados, false = radianes

    @Override
    public Double visitAssign(ExprParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        double value = visit(ctx.expr());
        memory.put(id, value);
        return value;
    }

    @Override
    public Double visitPrintExpr(ExprParser.PrintExprContext ctx) {
        Double value = visit(ctx.expr());
        System.out.println(value);
        return 0.0;
    }

    @Override
    public Double visitInt(ExprParser.IntContext ctx) {
        return Double.valueOf(ctx.INT().getText());
    }

    @Override
    public Double visitDouble(ExprParser.DoubleContext ctx) {
        return Double.valueOf(ctx.DOUBLE().getText());
    }

    @Override
    public Double visitId(ExprParser.IdContext ctx) {
        String id = ctx.ID().getText();
        if (memory.containsKey(id)) return memory.get(id);
        return 0.0;
    }

    @Override
    public Double visitParens(ExprParser.ParensContext ctx) {
        return visit(ctx.expr());
    }

    @Override
    public Double visitMulDiv(ExprParser.MulDivContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        return ctx.op.getType() == ExprParser.MUL ? left * right : left / right;
    }

    @Override
    public Double visitAddSub(ExprParser.AddSubContext ctx) {
        double left = visit(ctx.expr(0));
        double right = visit(ctx.expr(1));
        return ctx.op.getType() == ExprParser.ADD ? left + right : left - right;
    }

    @Override
    public Double visitFactorial(ExprParser.FactorialContext ctx) {
        int n = visit(ctx.expr()).intValue();
        return (double) factorial(n);
    }

    @Override
    public Double visitFunction(ExprParser.FunctionContext ctx) {
        String func = ctx.func().getText();
        double val = visit(ctx.expr());

        if (useDegrees && (func.equals("Sin") || func.equals("Cos") || func.equals("Tan"))) {
            val = Math.toRadians(val);
        }

        switch (func) {
            case "Sin": return Math.sin(val);
            case "Cos": return Math.cos(val);
            case "Tan": return Math.tan(val);
            case "Sqrt": return Math.sqrt(val);
            case "Ln": return Math.log(val);
            case "Log": return Math.log10(val);
            default: throw new RuntimeException("Función desconocida: " + func);
        }
    }

    private long factorial(int n) {
        if (n < 0) throw new RuntimeException("Factorial no definido para negativos");
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
}


