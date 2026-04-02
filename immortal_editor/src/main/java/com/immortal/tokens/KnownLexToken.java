package com.immortal.tokens;

import com.immortal.tokens.types.KnownLexTokenType;
import com.immortal.tokens.types.TokenType;

public class KnownLexToken extends Token
{
    private final KnownLexTokenType type;
    
    public KnownLexToken(KnownLexTokenType type, int line) 
    {
        super(line);
        this.type = type;
    }

    @Override public String toString()
    {
        return this.type + this.type.getLexeme() + this.line;
    }

    @Override public String getLexeme() { return this.type.getLexeme(); }

    @Override public TokenType getType() { return this.type; }
}
