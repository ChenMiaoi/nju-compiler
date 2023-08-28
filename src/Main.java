import java.io.IOException;
import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;

public class Main
{    
    private static String[] tokenSymbols = {
        null, "CONST", "INT", "VOID", "IF", "ELSE", "WHILE", "BREAK", "CONTINUE", 
        "RETURN", "PLUS", "MINUS", "MUL", "DIV", "MOD", "ASSIGN", "EQ", "NEQ", 
        "LT", "GT", "LE", "GE", "NOT", "AND", "OR", "L_PAREN", "R_PAREN", "L_BRACE", 
        "R_BRACE", "L_BRACKT", "R_BRACKT", "COMMA", "SEMICOLON", "IDENT", "INTEGER_CONST", 
        "WS", "LINE_COMMENT", "MULTILINE_COMMENT"
	};

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("input path is required!");
        }
        String source_file = args[0];
        
        // 获取词法分析器
        CharStream inputStream = CharStreams.fromFileName(source_file);
        SysYLexer sysYLexer = new SysYLexer(inputStream);

        // 自定义BaseErrorListen
        sysYLexer.removeErrorListeners();
        SysYErrorListener sysYErrorListener = new SysYErrorListener();
        sysYLexer.addErrorListener(sysYErrorListener);

        // 获取自定义后的token输出
        List<? extends Token> tokens = sysYLexer.getAllTokens();
        for (Token token : tokens) {
            System.out.println(tokenSymbols[token.getType()] + " " + token.getText() + " at Line " + token.getLine());
        }
    }
}