import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Test {
    public static void main(String[] args) throws Exception {
        try {
            HelloLexer lexer;
            if (args.length > 0) {
                lexer = new HelloLexer(CharStreams.fromFileName(args[0]));
            } else {
                lexer = new HelloLexer(CharStreams.fromStream(System.in));
            }

            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HelloParser parser = new HelloParser(tokens);
            ParseTree tree = parser.r(); // regla inicial en Hello.g4

            System.out.println(tree.toStringTree(parser));
        } catch (Exception e) {
            System.err.println("Error (Test): " + e);
        }
    }
}
