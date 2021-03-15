import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lexer {

    public static Token scanNext(char c) {

//        Token t = new Token(TokenType.INVALID);

        // skip whitespaes
        if (Character.isWhitespace(c)) {
            return new Token(TokenType.WHITESPACE);
        }

        else if (Character.isDigit(c)) {
            return new Token(TokenType.NUMBER, Integer.parseInt(String.valueOf(c)));
        }

        else if (c == '+') {
            return new Token(TokenType.PLUS);
        }

        else if (c == '-') {
            return new Token(TokenType.MINUS);
        }

        else if (c == '*') {
            return new Token(TokenType.TIMES);
        }

        else if (c == '/') {
            return new Token(TokenType.DIVIDE);
        }

        else if (c == '(') {
            return new Token(TokenType.LPAREN);
        }

        else if (c == ')') {
            return new Token(TokenType.RPAREN);
        }

        else { // not implemented, error
            Error.Fatal(c + " is not a valid token.");
        }

        return new Token(TokenType.INVALID);

    }

    public static ArrayList<Token> scan(String fileName) {

        ArrayList tokens = new ArrayList();

        File srcFile = new File(fileName);

        try {
            Scanner reader = new Scanner(srcFile);

            while (reader.hasNext()) {
                // byte is type casted to char and if it fails it should crash
                String s = reader.next();

                for (char c : s.toCharArray()) {
                    Token t = scanNext(c);

                    if (t.getType() == TokenType.INVALID) {
                        Error.Fatal("invalid token " + c);
                    }

                    if (t.getType() != TokenType.WHITESPACE) { // skip whitespace
                        tokens.add(t);
                    }
                }
            }


        } catch (FileNotFoundException e) {
            System.err.println("Source file not found.");
            e.printStackTrace();
        }

        return tokens;


    }

}
