LabeledExprLexer lexer = new LabeledExprLexer (input);
CommonTokenStream tokens = new CommonTokenStream(lexer);
LabeledExprParser parser = new LabeledExprParser(tokens);
ParseTree tree = parser.prog(); // Analiza la expresión
EvalVisitor eval = new EvalVisitor();
eval.visit(tree);

