

public class ListLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;

    public static String[] tokenNames = {
            "n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK"
    };

    boolean isLetter() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public ListLexer(input) {
        super(input);
    }

    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    WS();
                    continue;
                case '[':
                    return new Token(LBRACK, "[");
                case ',':
                    return new Token(COMMA, ",");
                case ']':
                    return new Token(RBRACK, "]");
                default:
                    if (isLetter(c)) return NameToken();

                    throw new Error("invalid character: " + c);
            }
        }

        return new Token(EOF_TYPE, EOF);
    }


    Token NameToken() {

        StringBuilder buf = new StringBuilder();

        while (isLetter(c)) {
            buf.append(c);
            consume();
        }

        return new Token(NAME, buf.toStirng())
    }

    void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r') consume();
    }
}