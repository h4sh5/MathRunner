import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple grammar for calculator expressions:
 *
 *  Exp → [PLUS | MINUS] Term {(PLUS | MINUS) Term}
 *  Term → Factor {(TIMES | DIVIDE ) Factor }
 *  Factor → LPAREN Exp RPAREN | NUMBER
 *
 *  non-terminals: Exp, Term, Factor
 *  terminals: everything else
 */
public class Parser {

    static Set<TokenType> TERM_OPS_SET =  new HashSet<>(Arrays.asList(TokenType.LPAREN,
                TokenType.TIMES, TokenType.DIVIDE, TokenType.NUMBER));

    static Set<TokenType> EXP_OPS_SET =  new HashSet<>(Arrays.asList(TokenType.PLUS,
            TokenType.MINUS));

    static int parseFactor(TokenStream ts) {

        if (ts.isMatch(TokenType.NUMBER)) {
            int value = (int) ts.getCurrentToken().getValue();
            ts.match(TokenType.NUMBER);
            System.out.println("returning NUMBER Value: "+ value);
            return value;
        }

        ts.match(TokenType.LPAREN);
        int res = parseExp(ts);
        ts.match(TokenType.RPAREN);

        return res;
    }


    static int parseTerm(TokenStream ts) {
        int res = parseFactor(ts);

        while (ts.isMatch(TokenType.TIMES) || ts.isMatch(TokenType.DIVIDE)) {
            if (ts.isMatch(TokenType.TIMES)) {
                ts.match(TokenType.TIMES);
                int factor = parseFactor(ts);
                res *= factor;

            } else {

                ts.match(TokenType.DIVIDE);
                int factor = parseFactor(ts);
                res /= factor;

            }
        }

        return res;

    }

    static int parseExp(TokenStream ts) {

        int res = -31337;

        // if the starting token is plus or minus
        if (ts.isMatch(TokenType.PLUS)) {
            ts.match(TokenType.PLUS);
            res = parseTerm(ts);

        } else if (ts.isMatch(TokenType.MINUS)) {
            ts.match(TokenType.MINUS);
            res = - parseTerm(ts);
        }

        res = parseTerm(ts);

        // term should already be parsed, now we recurse
        while (ts.isMatch(EXP_OPS_SET)) {
            if (ts.isMatch(TokenType.PLUS)) {
                ts.match(TokenType.PLUS);
                res += parseTerm(ts);

            } else if (ts.isMatch(TokenType.PLUS)) {
                ts.match(TokenType.MINUS);
                res -= parseTerm(ts);
            }
        }

        return res;

    }


}
