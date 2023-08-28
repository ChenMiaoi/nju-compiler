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

    private static int hex2Dec(Character c) {
        int ret = 0;
        if (Character.isDigit(c)) {
            ret = Character.getNumericValue(c);
        } else if (Character.isLetter(c)) {
            if (Character.isUpperCase(c)) {
                ret = c - 'A' + 10;
            } else {
                ret = c - 'a' + 10;
            }
        }
        return ret;
    }

    private static int oct2Dec(Character c) {
        return Character.getNumericValue(c);
    }

    private static int any2Dec(String any, Boolean flag) {
        int sum = 0;
        if (flag) { // 0x
            for (int i = 0; i < any.length(); i++) {
                sum += hex2Dec(any.charAt(i)) * Math.pow(16, any.length() - i - 1);
            }
        } else { // 0
            for (int i = 0; i < any.length(); i++) {
                sum += oct2Dec(any.charAt(i)) * Math.pow(8, any.length() - i - 1);
            }
        }
        return sum;
    }

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
            if (token.getText().contains("0x") || token.getText().contains("0X")) {
                String hex = token.getText().substring(2);
                System.out.println(tokenSymbols[token.getType()] + " " + any2Dec(hex, true) + " at Line " + token.getLine());
            } else if (token.getText().length() > 1 && token.getText().contains("0")) {
                String oct = token.getText().substring(1);
                System.out.println(tokenSymbols[token.getType()] + " " + any2Dec(oct, false) + " at Line " + token.getLine());
            } else {
                System.out.println(tokenSymbols[token.getType()] + " " + token.getText() + " at Line " + token.getLine());
            }
        }
    }
}