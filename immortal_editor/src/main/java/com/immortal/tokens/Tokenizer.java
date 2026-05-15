package com.immortal.tokens;

public interface Tokenizer
{
    public Token tokenize(String lexeme, int line);

    public boolean checkLex(String lexeme);
}