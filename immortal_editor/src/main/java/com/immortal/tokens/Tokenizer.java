package com.immortal.tokens;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.immortal.tokens.types.KnownLexTokenType;

public class Tokenizer
{
    private final List<Token> tokenList;

    public Tokenizer() { this.tokenList = new ArrayList<>(); }

    public void tokenize(String lexeme, int line) 
    {
        KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        Token token = new KnownLexToken(type, line);

        tokenList.add(token);
    }

    public boolean checkLexeme(String lexeme)
    {
        throw new UnsupportedOperationException();
    }

    protected Collection<Token> getTokens() { return this.tokenList; }
}