package com.bhima.jsonnparser;

import com.bhima.jsonnparser.Parser.Parser;
import com.bhima.jsonnparser.Tokenizer.Tokenizer;

public class JsonnParser {
    public Tokenizer tokenizer;
    public Parser parser;

    public JsonnParser() {
        this.tokenizer = new Tokenizer();
    }
}
