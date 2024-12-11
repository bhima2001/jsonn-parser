package com.bhima.jsonnparser.Tokenizer;

public class Token {
    private final TokenType type;
    private final String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getTokenType() {
        return this.type;
    }

    public String getValue() {
        return this.value; 
    }

    @Override
    public String toString() {
        return "Value: " + this.value + " type: " + this.type.name(); 
    }
}