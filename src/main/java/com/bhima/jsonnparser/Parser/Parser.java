package com.bhima.jsonnparser.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.bhima.jsonnparser.Exception.UnknownTokenExeception;
import com.bhima.jsonnparser.Tokenizer.Token;
import com.bhima.jsonnparser.Tokenizer.TokenType;

public class Parser {
    ArrayList<Token> tokens;    
    int current = 0;
    String jsonObjString = "";

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    public AstNode<?> parse() throws UnknownTokenExeception {
        Token token = tokens.get(current);
        String value = token.getValue();
        System.out.println(token.toString());
        
        switch (token.getTokenType()) {
            case INTEGER:
                return new AstNode<>("Integer", Integer.valueOf(value));
            case DOUBLE:
                return new AstNode<>("Double", Double.valueOf(value));
            case STRING:
                return new AstNode<>("String", value);
            case TRUE:
                return new AstNode<>("Boolean", Boolean.valueOf(value));
            case FALSE:
                return new AstNode<>("Boolean", Boolean.valueOf(value));
            case OPENFLOWERBRACKET:
                return parseObject();
            case OPENSQUAREBRACKET:
                return parseArray();
            default:
                throw new UnknownTokenExeception();
        }
    }

    private AstNode<?> parseObject() throws UnknownTokenExeception{
        current++;
        Map<String, Object> mp = new HashMap<>();
        String key = "";
        for(;tokens.get(current).getTokenType() != TokenType.CLOSEDFLOWERBRACKET;){
            Token temp = tokens.get(current);
            if(temp.getTokenType() != TokenType.COMA && temp.getTokenType() != TokenType.COLON)  {
                if("".equals(key)){
                    key = temp.getValue();
                }else {
                    mp.put(key, parse());
                }
            }
            current++;
        }
        current++;
        
        return new AstNode<>("Object", mp);
    }

    private AstNode<?> parseArray() throws UnknownTokenExeception {
        ArrayList<AstNode<?>> array = new ArrayList<>(); 
        for(;tokens.get(current).getTokenType() != TokenType.CLOSEDSQUAREBRACKET ; current++){
            Token temp = tokens.get(current);
            if(temp.getTokenType() != TokenType.COMA){
                array.add(parse());
                current++;
            }
        }
        current++;
        return new AstNode<>("Array", array);
    }
}
