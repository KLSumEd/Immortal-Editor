package com.immortal.app.tokens;

@Deprecated
public enum _TokenType
{
    // Single-Character Tokens
    LEFT_BRACE,
    RIGHT_BRACE,
    LEFT_PAREN,
    RIGHT_PAREN,
    LEFT_SQUARE,
    RIGHT_SQUARE,
    COMMA,
    DOT,
    MINUS,
    PLUS,
    SEMICOLON,
    SLASH,
    BACKSLASH,
    STAR,
    
    // One-or-Two-Character Tokens
    EXCL,
    EXCL_EQUAL,
    EQUAL,
    EQUAL_EQUAL,
    GREATER,
    GREATER_EQUAL,
    LESS,
    LESS_EQUAL,
    
    // Literals
    IDENTIFIER,
    STR,
    INT,
    FLOAT,
    TRUE,
    FALSE,
    
    // Keywords
    AND,
    OR,
    NOT,
    IF,
    ELSE,
    CLASS,
    SUPER,
    THIS,
    FOR,
    WHILE,
    ENUM,
    FUNC,
    RETURN,
    
    // Type Keywords
    KW_STR,
    KW_INT,
    KW_FLOAT,
    KW_BOOL,
    
    EOF
}
