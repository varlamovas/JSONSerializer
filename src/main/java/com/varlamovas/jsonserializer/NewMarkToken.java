package com.varlamovas.jsonserializer;

public class NewMarkToken extends NewToken {

    public static final NewMarkToken LEFT_CURLY_BRACE = new NewMarkToken("{"){};
    public static final NewMarkToken RIGHT_CURLY_BRACE = new NewMarkToken("}"){};
    public static final NewMarkToken LEFT_SQUARE_BRACKET = new NewMarkToken("["){};
    public static final NewMarkToken RIGHT_SQUARE_BRACKET = new NewMarkToken("]"){};
    public static final NewMarkToken COLON = new NewMarkToken(":"){};
    public static final NewMarkToken COMMA = new NewMarkToken(","){};

    public NewMarkToken(String value) {
        super(value);
    }
}
