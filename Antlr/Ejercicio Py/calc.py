import sys
from antlr4 import *
from ExprLexer import ExprLexer
from ExprParser import ExprParser
from EvalVisitor import EvalVisitor

def main():
    input_stream = FileStream(sys.argv[1]) if len(sys.argv) > 1 else InputStream(sys.stdin.read())
    lexer = ExprLexer(input_stream)
    tokens = CommonTokenStream(lexer)
    parser = ExprParser(tokens)
    tree = parser.prog()

    visitor = EvalVisitor()
    visitor.visit(tree)

if __name__ == '__main__':
    main()
