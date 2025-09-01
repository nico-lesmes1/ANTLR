import java.io.File;

public class MiExpr {
    public static void main(String[] args) throws Exception {
        try {
            //Crear un analizador lexico que se alimenta a partir de la entrada de un archivo o consola
            if (args.length > 0) {
                lexer = new ExprLexer ( new ANTLRFileStream(args[0]) );
            } else {
                lexer = new ExprLexer ( new ANTLRInputStream(System.in) );
            }
            //Identificar al analizador lexico como fuente de tokens para el sintactico
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            //Crear el analizador sintactico que se alimenta a partir del buffer de tokens
            ExprParser parser = new ExprParser(tokens);
            ParseTree tree = parser.expr(); // Comienza el analisis sintactico con la regla inicial
            System.out.println(tree.toStringTree(parser)); //Imprime el arbol en formato de texto
        } catch (Exception e) {
            System.err.println("Error: (Test)" + e);
        }
    }
}