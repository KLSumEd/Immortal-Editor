package com.immortal.tokens;

import java.util.List;

public abstract class Tokenizer
{
    private final List<Token> tokenList;

    public Tokenizer(List<Token> newTokenList) 
    {
        this.tokenList = newTokenList;
    }

    public abstract void tokenize();

    protected List<Token> getTokenList() { return this.tokenList; }
}