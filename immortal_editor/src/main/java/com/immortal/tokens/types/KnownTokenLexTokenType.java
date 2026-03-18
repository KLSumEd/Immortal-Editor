package com.immortal.tokens.types;

public interface KnownTokenLexTokenType extends TokenType
{
    public char getFirstChar();
    @Override public String getLexeme();
}
