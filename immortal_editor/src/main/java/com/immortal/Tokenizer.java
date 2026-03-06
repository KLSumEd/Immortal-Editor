package com.immortal;

import java.util.List;

public abstract class Tokenizer
{
    private final List<Token> tokenList;

    public Tokenizer(List<Token> newTokenList) 
    {
        this.tokenList = newTokenList;
    }

    public List<Token> getTokenList() { return this.tokenList; }
}
