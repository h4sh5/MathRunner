import java.util.ArrayList;


public class MathRunner {

    static void usage(String[] args) {
        System.err.println("usage: MathRunner <file to run>");
    }

    static void printToken(Token t) {
        if (t.hasValue()) {
            System.out.println(t.getType() + ":" + t.getValue());
        } else {
            System.out.println(t.getType());
        }

    }


    public static void main(String[] args) {
        if (args.length < 1) {
            usage(args);
            return;
        }
        ArrayList<Token> tokens =  Lexer.scan(args[0]);

        for (Token t : tokens) {
            printToken(t);
        }

        // convert into a tokenStream
        TokenStream ts = new TokenStream(tokens);
        // parse it
        int res = Parser.parseExp(ts);
        System.out.println("result: " + String.valueOf(res));
    }

}
