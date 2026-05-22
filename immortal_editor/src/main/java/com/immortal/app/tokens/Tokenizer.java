package com.immortal.app.tokens;

@FunctionalInterface
public interface Tokenizer
{
    public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException;
}