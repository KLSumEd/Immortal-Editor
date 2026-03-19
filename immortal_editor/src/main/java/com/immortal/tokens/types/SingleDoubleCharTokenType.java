package com.immortal.tokens.types;

public enum SingleDoubleCharTokenType implements KnownLexTokenType
{
    // One-or-Two-Character Tokens
    EXCL("!"), 
    EXCL_EQUAL("!="),
    EQUAL("="), 
    EQUAL_EQUAL("=="),
    GREATER(">"), 
    GREATER_EQUAL(">="),
    LESS("<"), 
    LESS_EQUAL("<=");

    private final String lexeme; 

    private SingleDoubleCharTokenType(String lexeme) 
    {
        this.lexeme = lexeme;
    }

    @Override public String getLexeme() { return this.lexeme; }
}
