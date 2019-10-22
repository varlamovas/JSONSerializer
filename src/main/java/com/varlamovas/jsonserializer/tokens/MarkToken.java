package com.varlamovas.jsonserializer.tokens;

public class MarkToken extends Token {

    public static final MarkToken LEFT_CURLY_BRACE = new MarkToken("{"){};
    public static final MarkToken RIGHT_CURLY_BRACE = new MarkToken("}"){};
    public static final MarkToken LEFT_SQUARE_BRACKET = new MarkToken("["){};
    public static final MarkToken RIGHT_SQUARE_BRACKET = new MarkToken("]"){};
    public static final MarkToken COLON = new MarkToken(":"){};
    public static final MarkToken COMMA = new MarkToken(","){};

    public MarkToken(String value) {
        super(value);
    }
}
