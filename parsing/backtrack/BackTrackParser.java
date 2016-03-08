

public class BackTrackParser extends Parser {

    public BackTrackParser(Lexer input) {
        super(input);
    }


    public void stat() {
        if (speclate_stat_alt1()) {
            list();
            match(Lexer.EOF_TYPE);
        } else if (speclate_stat_alt2()) {
            assign();
            match(Lexer.EOF_TYPE);
        } else throw new NoViabelAltExpection("expecting stat found:" + LT(1));


    }

    void assign() {
        list();
        match(BackTrackLexer.EQUALS);
        list();
    }

    void list() {
        match(ListLexer.LBRACK);
        elements();
        match(ListLexer.RBRACK);
    }

    void elements() {
        element();
        while (lookahead.type == ListLexer.COMMA) {
            match(ListLexer.COMMA);
            element();
        }
    }

    void element() {
        if (lookahead.type == ListLexer.NAME) match(ListLexer.NAME);
        else if (lookahead.type == ListLexer.LBRACK) list();
        else throw new Error("expecting name or list; found" + lookahead);
    }


}