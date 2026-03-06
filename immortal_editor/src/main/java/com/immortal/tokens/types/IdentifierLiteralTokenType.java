package com.immortal.tokens.types;

public enum IdentifierLiteralTokenType implements TokenType 
{
    // Literals
    IDENTIFIER, 
    STR,
    INT, 
    FLOAT, 
    TRUE, 
    wFALSE;

    @Override public String getLexeme() { return null; }
}
