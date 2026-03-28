package com.immortal.tokens.types;

public enum ReservedWordsTokenType implements KnownLexTokenType
{
    TRUE("true"),
    FALSE("false"),
    STR("str"),           // For the following entries
    INT("int"),           // ensure the given TokenType
    BOOL("bool"),         // refers to the TYPE IDENTIFIER TOKENS
    FLOAT("float"),       // NOT the LITERAL TOKENS   
    FUNCTION("function"),   
    AND("and"),
    OR("or"),
    NOT("not"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),
    CLASS("class"),
    SUPER("super"),
    THIS("this"),
    FOR("for"),
    WHILE("while");

    private final String lexeme;

    private ReservedWordsTokenType(String lexeme) { this.lexeme = lexeme; }

    @Override public String getLexeme() { return this.lexeme; }
}
