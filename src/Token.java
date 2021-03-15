public class Token {

    protected TokenType type;
    protected Object value = null;

    public boolean hasValue() {
        return !(value == null);
    }


    public Object getValue() {
        if (this.hasValue()) {
            return value;

        } else {
            return null;
        }
    }

    public TokenType getType() {
        return type;
    }

    /**
     * constructs a token, with type and value
     */
    public Token(TokenType t, Object val) {

        if (t == TokenType.NUMBER) {
            value = val;
        }
        type = t;
    }

    public Token(TokenType t) {
        type = t;
    }


}
