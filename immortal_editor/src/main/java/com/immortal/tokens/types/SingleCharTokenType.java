package com.immortal.tokens.types;

public enum SingleCharTokenType implements TokenType
{
    LEFT_BRACE('{'), 
    RIGHT_BRACE('}'), 
    LEFT_PAREN('('), 
    RIGHT_PAREN(')'),
    LEFT_SQUARE('['), 
    RIGHT_SQUARE(']'), 
    COMMA(','), 
    DOT('.'), 
    MINUS('-'), 
    PLUS('+'),
    SEMICOLON(';'), 
    SLASH('/'), 
    BACKSLASH('\\'), 
    STAR('*'),
    EOF('\0');

    private final String lexeme;

    private SingleCharTokenType(char lexeme) { this.lexeme = String.valueOf(lexeme); }

    @Override public String getLexeme() { return this.lexeme; }
}
