package com.immortal.tokens;

public class KnownLexToken extends Token
{
    private final KnownLexTokenType type;

    public KnownLexToken(KnownLexTokenType type, int line)
    {
        super(line);
        this.type = type;
    }

    @Override public String toString()
    { return this.line + "|" + this.type + " " + this.type.getLexeme(); }

    @Override public String getLexeme()
    { return this.type.getLexeme(); }

    @Override public TokenType getType()
    { return this.type; }
}
