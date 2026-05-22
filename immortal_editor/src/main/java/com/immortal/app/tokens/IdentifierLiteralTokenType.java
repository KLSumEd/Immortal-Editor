package com.immortal.app.tokens;

public enum IdentifierLiteralTokenType implements TokenType
{
    IDENTIFIER, STR, INT, CHAR, FLOAT;
    
    @Override public String toString()
    {
        return switch (this)
        {
            case IDENTIFIER -> "ID";
            case STR -> "STRING";
            case INT -> "INTEGER";
            case CHAR -> "CHARACTER";
            default -> this.name();
        };
    }
}