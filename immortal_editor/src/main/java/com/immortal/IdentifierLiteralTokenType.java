package com.immortal;

public enum IdentifierLiteralTokenType implements TokenType 
{
    // Literals
    IDENTIFIER, 
    STR,
    INT, 
    FLOAT, 
    TRUE, 
    FALSE;

    @Override public String getLexeme() { return null; }
}
