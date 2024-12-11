package com.bhima.jsonnparser.Parser;

public class AstNode<T> {
    final private String type;
    final private T value;

    public AstNode(String type, T value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return this.type;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString(); 
    }
}

