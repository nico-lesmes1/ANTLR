import math
from ExprVisitor import ExprVisitor
from ExprParser import ExprParser

class EvalVisitor(ExprVisitor):
    def __init__(self):
        self.memory = {}
        self.use_degrees = True  # True = grados, False = radianes

    def visitAssign(self, ctx):
        id_name = ctx.ID().getText()
        value = self.visit(ctx.expr())
        self.memory[id_name] = value
        return value

    def visitPrintExpr(self, ctx):
        value = self.visit(ctx.expr())
        print(value)
        return 0

    def visitInt(self, ctx):
        return int(ctx.INT().getText())

    def visitDouble(self, ctx):
        return float(ctx.DOUBLE().getText())

    def visitId(self, ctx):
        id_name = ctx.ID().getText()
        return self.memory.get(id_name, 0)

    def visitParens(self, ctx):
        return self.visit(ctx.expr())

    def visitMulDiv(self, ctx):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == ExprParser.MUL:
            return left * right
        else:
            return left / right

    def visitAddSub(self, ctx):
        left = self.visit(ctx.expr(0))
        right = self.visit(ctx.expr(1))
        if ctx.op.type == ExprParser.ADD:
            return left + right
        else:
            return left - right

    def visitFactorial(self, ctx):
        n = int(self.visit(ctx.expr()))
        if n < 0:
            raise Exception("Factorial no definido para negativos")
        result = 1
        for i in range(2, n+1):
            result *= i
        return result

    def visitFunction(self, ctx):
        func = ctx.func().getText()
        val = self.visit(ctx.expr())

        if self.use_degrees and func in ["Sin", "Cos", "Tan"]:
            val = math.radians(val)

        if func == "Sin":
            return math.sin(val)
        elif func == "Cos":
            return math.cos(val)
        elif func == "Tan":
            return math.tan(val)
        elif func == "Sqrt":
            return math.sqrt(val)
        elif func == "Ln":
            return math.log(val)
        elif func == "Log":
            return math.log10(val)
        else:
            raise Exception(f"Función desconocida: {func}")
