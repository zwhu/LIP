

public class ListLexer extends Lexer {

    public static int NAME = 2;
    public static int COMMA = 3;
    public static int LBRACK = 4;
    public static int RBRACK = 5;

    public static int EQUALS = 6;

    public static String[] tokenNames = {
            "n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK", "EQUALS"
    };

    boolean isLETTER() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }

    public String getTokenName(int x) {
        return tokenNames[x];
    }


    public ListLexer(String input) {
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
                    consume();
                    return new Token(LBRACK, "[");
                case ',':
                    consume();
                    return new Token(COMMA, ",");
                case ']':
                    consume();
                    return new Token(RBRACK, "]");
                case '=':
                    consume();
                    return new Token(EQUALS, "=");
                default:
                    if (isLETTER()) return NameToken();

                    throw new Error("invalid character: " + c);
            }
        }

        return new Token(EOF_TYPE, "<EOF>");
    }


    Token NameToken() {

        StringBuilder buf = new StringBuilder();

        while (isLETTER()) {
            buf.append(c);
            consume();
        }

        return new Token(NAME, buf.toString());
    }

    void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r') consume();
    }
}