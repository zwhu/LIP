

public class Test {

    public static void main(String[] args) throws RecognitionException {
        BackTrackLexer lexer = new BackTrackLexer(args[0]);

        BackTrackParser parser = new BackTrackParser(lexer);

        parser.stat();
    }

}