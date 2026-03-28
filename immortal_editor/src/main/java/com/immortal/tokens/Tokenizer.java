package com.immortal.tokens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.immortal.tokens.types.TokenType;

public class Tokenizer
{
    private final List<Token> tokenList;

    public Tokenizer() { this.tokenList = new ArrayList<>(); }

    public void tokenize(String lexeme, TokenType type, int line) 
    {
        Token token = new Token(type, line);
        tokenList.add(token);
    }

    protected Collection<Token> getTokens() { return this.tokenList; }
}