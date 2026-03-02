package com.example;

public enum TokenType 
{
    // Single-Character Tokens
    LEFT_BRACE, RIGHT_BRACE, LEFT_PAREN, RIGHT_PAREN,
    LEFT_SQUARE, RIGHT_SQUARE, COMMA, DOT, MINUS, PLUS,
    SEMICOLON, SLASH, BACKSLASH, STAR, 

    // One-or-Two-Character Tokens
    EXCL, EXCL_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, STRING, NUMBER,

    // Keywords
    AND, OR, IF, ELSE, TRUE, FALSE,
    RETURN, CLASS, SUPER, THIS, 
    FOR, WHILE, ENUM,

    // Type Keywords
    STR, INT, FLOAT, BOOL,
    
    EOF
}
