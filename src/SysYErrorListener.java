import org.antlr.v4.runtime.*;

public class SysYErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        String simbol = msg.substring(msg.indexOf("'") + 1, msg.lastIndexOf("'"));
        System.err.println("Error type A at Line " + line + ": Mysterious character \"" + simbol + "\"");
    }
}
