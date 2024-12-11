package com.bhima.jsonnparser.Tokenizer;

import java.util.ArrayList;


public class Tokenizer {
    private ArrayList<Token> tokens = new ArrayList<>() ;

    public Tokenizer() {
    }

    public ArrayList<Token> Tokenize(String jsonString) {
        for(int i=0; i<jsonString.length(); i++) {
            char s = jsonString.charAt(i);
            if(s == '['){
                tokens.add(new Token(TokenType.OPENSQUAREBRACKET, Character.toString(s)));
            }
            else if(s == ']'){
                tokens.add(new Token(TokenType.CLOSEDSQUAREBRACKET, Character.toString(s)));
            }
            else if(s == '{'){
                tokens.add(new Token(TokenType.OPENFLOWERBRACKET, Character.toString(s)));
            }
            else if(s == '}'){
                tokens.add(new Token(TokenType.CLOSEDFLOWERBRACKET, Character.toString(s)));
            }
            else if(s == ':'){
                tokens.add(new Token(TokenType.COLON, Character.toString(s)));
            }
            else if(s == ','){
                tokens.add(new Token(TokenType.COMA, Character.toString(s)));
            }
            else if(s=='"'){
                i++;
                String value = "";
                for(;jsonString.charAt(i) != '"'; i++){
                    value += jsonString.charAt(i);
                }
                tokens.add(new Token(TokenType.STRING, value));
            }
            else if(Character.isDigit(s)){
                String value = "";
                Boolean flag = false;
                for(;Character.isDigit(jsonString.charAt(i)) || jsonString.charAt(i) == '.'; i++){
                    if(jsonString.charAt(i) == '.') flag = true;
                    value += jsonString.charAt(i);
                }
                if(flag) tokens.add(new Token(TokenType.DOUBLE, value));
                else tokens.add(new Token(TokenType.INTEGER, value));
            }
            else if(Character.isLetter(s)){
                String value = "";
                for(;Character.isLetter(jsonString.charAt(i)); i++){
                    value += jsonString.charAt(i);
                }
                switch(value) {
                    case "True": 
                        tokens.add(new Token(TokenType.TRUE, value));
                    case "False":
                        tokens.add(new Token(TokenType.FALSE, value));
                    case "null":
                        tokens.add(new Token(TokenType.NULL, value));
                    default: {
                    }
                }
            }
        }
        return tokens;
    }

    public ArrayList<Token> getTokens() {
        return this.tokens;
    }
}
