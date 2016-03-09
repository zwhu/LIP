

public class BackTrackParser extends Parser {

    public BackTrackParser(Lexer input) {
        super(input);
    }


    public void stat() throws RecognitionException {
        if (speclate_stat_alt1()) {
            list();
            match(Lexer.EOF_TYPE);
        } else if (speclate_stat_alt2()) {
            assign();
            match(Lexer.EOF_TYPE);
        } else throw new NoViableAltException("expecting stat found:" + LT(1));


    }

    public boolean speclate_stat_alt1() {
        boolean success = true;
        mark();
        try {
            list();
            match(Lexer.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        relase();
        return success;
    }

    public boolean speclate_stat_alt2() {
        boolean success = true;
        mark();
        try {
            assign();
            match(Lexer.EOF_TYPE);
        } catch (RecognitionException e) {
            success = false;
        }
        relase();
        return success;
    }

    public void assign() throws RecognitionException {
        list();
        match(BackTrackLexer.EQUALS);
        list();
    }

    public void list() throws RecognitionException {
        match(BackTrackLexer.LBRACK);
        elements();
        match(BackTrackLexer.RBRACK);
    }

    void elements() throws RecognitionException {
        element();
        while (LA(1) == BackTrackLexer.COMMA) {
            match(BackTrackLexer.COMMA);
            element();
        }
    }

    void element() throws RecognitionException {
        if (LA(1) == BackTrackLexer.NAME && LA(2) == BackTrackLexer.EQUALS) {
            match(BackTrackLexer.NAME);
            match(BackTrackLexer.EQUALS);
            match(BackTrackLexer.NAME);
        } else if (LA(1) == BackTrackLexer.NAME) match(BackTrackLexer.NAME);
        else if (LA(1) == BackTrackLexer.LBRACK) list();
        else throw new NoViableAltException("expecting name or list; found" + LT(1));
    }


}