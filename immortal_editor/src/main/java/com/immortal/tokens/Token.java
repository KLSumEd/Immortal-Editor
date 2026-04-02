package com.immortal.tokens;

import com.immortal.tokens.types.TokenType;

public abstract class Token
{
    protected final int line;

    public Token(int line) { this.line = line; }

    public abstract String getLexeme();
    public abstract TokenType getType();
    public int getLine() { return this.line; }
}
