import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

public class Main
{    
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("input path is required!");
        }
        String source_file = args[0];
        
        CharStream inputStream = CharStreams.fromFileName(source_file);
        SysYLexer sysYLexer = new SysYLexer(inputStream);

        sysYLexer.removeErrorListeners();
        List<? extends Token> tokens = sysYLexer.getAllTokens();

        for (Token token : tokens) {
            System.out.println(token);
        }
    }
}