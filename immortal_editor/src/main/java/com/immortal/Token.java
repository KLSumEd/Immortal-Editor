package com.immortal;

public class Token
{
    private final TokenType type;
    private final int line;

    public Token(TokenType type, int line) 
    {
        this.type = type;
        this.line = line;
    }

    @Override public String toString() 
    {
        return this.type.toString() + " " + this.type.getLexeme() + " " + this.line;
    }

    protected String getLexeme() { return this.type.getLexeme(); }
    protected TokenType getType() { return this.type; }
    protected int getLine() { return this.line; }
}
