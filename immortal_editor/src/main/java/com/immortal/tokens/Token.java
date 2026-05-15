package com.immortal.tokens;

public abstract class Token
{
    protected final int line;

    public Token(int line)
    { this.line = line; }

    public abstract String getLexeme();

    public abstract TokenType getType();

    public final int getLine()
    { return this.line; }

    @Override public String toString()
    { return this.line + " | " + getType() + " " + getLexeme(); }
}
