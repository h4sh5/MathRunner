import java.util.ArrayList;
import java.util.Set;

/**
 * a stream of tokens for parsing and state-keeping
 */
public class TokenStream {

    int pos = 0;
    ArrayList<Token> tokens;
    int numTokens;

    public TokenStream(ArrayList<Token> tokens) {
        this.tokens = tokens;
        numTokens = tokens.size();
    }

    public boolean hasNext() {
        return pos < numTokens;
    }

    public Token getCurrentToken() {
        System.out.println("getCurrentToken:");
        MathRunner.printToken(tokens.get(pos));
        return tokens.get(pos);
    }

    /**
     * match type of token, and only type of token (not value)
     * @param expected
     */
    public void match(TokenType expected) {
        System.out.print(" match: ");
        MathRunner.printToken(tokens.get(pos));
        assert isMatch(expected);
        pos ++;
    }

    /**
     * @param t
     * @return if two tokens match by type
     */
    public boolean isMatch(TokenType t) {
        if (!hasNext()) {
            return false;
        }
        if (t == tokens.get(pos).getType()) {
            return true;
        }
        return false;
    }

    public boolean isMatch(Set<TokenType> tset) {
        if (!hasNext()) {
            return false;
        }
        if (tset.contains(tokens.get(pos).getType())) {
            return true;
        }
        return false;
    }




}
