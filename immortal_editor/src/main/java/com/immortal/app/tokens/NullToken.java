package com.immortal.app.tokens;

public final class NullToken extends Token
{
    public NullToken(int line)
    { super(line); }
    
    @Override public String getLexeme()
    { return ""; }
    
    @Override public TokenType getType()
    { return NULL_TOKEN_TYPE; }
    
    private static final TokenType NULL_TOKEN_TYPE = new TokenType()
    {
        @Override public String toString()
        { return "NullTokenType"; }
    };
}
