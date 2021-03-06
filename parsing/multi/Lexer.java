

public abstract class Lexer {

    public  static final char EOF = (char) -1;
    public  static final char EOF_TYPE = 1;

    public String input;
    char c;
    int p = 0;

    public Lexer(String input) {
        this.input = input;
        c = input.charAt(p);
    }

    void consume() {
        p++;
        if (p < input.length()) c = input.charAt(p);
        else c = EOF;
    }

    public abstract Token nextToken();

    public abstract String getTokenName(int tokenType);


}