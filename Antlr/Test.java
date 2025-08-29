import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.File;

public class Test {
    public static void main (String[] args) throws Exception {
        try{
            //Creación de un analizador lexico que funciona a partir de consola o archivo externo
            NOMBRE_GRAMATICALexer lexer;
            if (args.length>0) 
                lexer = new NOMBRE_GRAMATICALexer (new ANTLRFileStream(args[0]));    
            else
                lexer = new NOMBRE_GRAMATICALexer (new ANTLRInputStream(System.in));

                //Identificar al analizador lexico como fuente de tokens para el sintactico
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            //Crear el analizador sintactico que se alimenta a partir del buffer de tokens
            NOMBRE_GRAMATICAParser parser = new NOMBRE_GRAMATICAParser(tokens);
            ParseTree tree = parser.reglaInicialGramatica(); // Analiza la la regla inicial
            System.out.println(tree.toStringTree(parser));
        } catch(Exception e){
            System.err.println("Error (Test);" + e);
        }
    }
}