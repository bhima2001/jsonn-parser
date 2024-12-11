package com.bhima.jsonnparser;

import java.util.ArrayList;

import com.bhima.jsonnparser.Exception.UnknownTokenExeception;
import com.bhima.jsonnparser.Parser.AstNode;
import  com.bhima.jsonnparser.Parser.Parser;
import com.bhima.jsonnparser.Tokenizer.Token;
import com.bhima.jsonnparser.Tokenizer.Tokenizer;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws UnknownTokenExeception {
        JsonnParser jsonnParser = new JsonnParser();
        Tokenizer tokenizer = jsonnParser.tokenizer;
        // String jsonString = "{"+ "\"name\": 25.0,"+ "\"age\": 25.56,"+ "\"isStudent\": true,"+ "\"address\": {"+ "    \"street\": \"456 Elm St\","+ "    \"city\": \"Wonderland\""+ "},"+ "\"courses\": [\"Math\", \"Science\", \"Art\"]"+ "}";
        String json1 = "{ \"name\": \"Alice\", \"age\": 30, \"isStudent\": false }";

        ArrayList<Token> tokens = tokenizer.Tokenize(json1);

        Parser parser = new Parser(tokens);
        AstNode<?> root = parser.parse();
        System.out.println(parser.inOrder(root));
    }
}