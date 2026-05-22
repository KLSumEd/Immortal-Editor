package com.immortal.app.tokens;

public class KnownLexToken extends Token
{
    private final KnownLexTokenType type;
    
    public KnownLexToken(KnownLexTokenType type, int line)
    {
        super(line);
        this.type = type;
    }
    
    @Override public String getLexeme()
    { return this.type.getLexemes()[0]; }
    
    @Override public TokenType getType()
    { return this.type; }
}
