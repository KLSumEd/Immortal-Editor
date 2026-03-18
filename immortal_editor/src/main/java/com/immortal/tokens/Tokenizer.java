package com.immortal.tokens;

import java.util.List;

public class Tokenizer
{
    private final List<Token> tokenList;

    public Tokenizer(List<Token> newTokenList) 
    {
        this.tokenList = newTokenList;
    }

    public void tokenize() {}

    protected List<Token> getTokenList() { return this.tokenList; }
}