package com.example;

public enum ReservedWords {
    TRUE("true", TokenType.TRUE),
    FALSE("false", TokenType.FALSE),
    STR("str", TokenType.KW_STR),           // For the following entries
    INT("int", TokenType.KW_INT),           // ensure the given TokenType
    BOOL("bool", TokenType.KW_BOOL),        // refers to the TYPE IDENTIFIER TOKENS
    FLOAT("float", TokenType.KW_FLOAT),     // NOT the LITERAL TOKENS   
    FUNCTION("function", TokenType.FUNC),   
    AND("and", TokenType.AND),
    OR("or", TokenType.OR),
    NOT("not", TokenType.NOT),
    IF("if", TokenType.IF),
    ELSE("else", TokenType.ELSE),
    RETURN("return", TokenType.TRUE),
    CLASS("class", TokenType.CLASS),
    SUPER("super", TokenType.SUPER),
    THIS("this", TokenType.THIS),
    FOR("for", TokenType.FOR),
    WHILE("while", TokenType.WHILE);

    String lexeme;
    TokenType type;

    private ReservedWords(String lexeme, TokenType type) 
    {
        this.lexeme = lexeme;
        this.type = type;
    }
    public String getLexeme() {return this.lexeme;}
    public TokenType getTokenType() {return this.type;}
}
