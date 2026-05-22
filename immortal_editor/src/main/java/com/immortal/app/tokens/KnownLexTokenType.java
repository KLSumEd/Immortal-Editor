package com.immortal.app.tokens;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum KnownLexTokenType implements TokenType
{
    // Single-Character Tokens
    LEFT_BRACE("{"),
    RIGHT_BRACE("}"),
    LEFT_PAREN("("),
    RIGHT_PAREN(")"),
    LEFT_SQUARE("["),
    RIGHT_SQUARE("]"),
    COMMA(","),
    DOT("."),
    MINUS("-"),
    PLUS("+"),
    SEMICOLON(";"),
    SLASH("/"),
    BACKSLASH("\\"),
    STAR("*"),
    EOF("\0"),
    
    // One-or-Two-Character Tokens
    EXCL_EQUAL("!="),
    EQUAL("="),
    EQUAL_EQUAL("=="),
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),
    
    // Keyword Tokens
    STR("str", "string"),         // <--- For the following entries
    INT("int", "integer"),        // <--- ensure the given TokenType
    BOOL("bool", "boolean"),      // <--- refers to the
    FLOAT("float", "real"),       // <--- TYPE KEYWORD TOKENS,
    CHAR("char", "character"),    // <--- NOT the LITERAL TOKENS
    FUNC("function", "func"),
    AND("and", "&&"),
    OR("or", "||"),
    NOT("not", "!"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),
    CLASS("class"),
    SUPER("super"),
    THIS("this"),
    FOR("for"),
    WHILE("while"),
    
    // Secret Literal Keywords - shh!
    TRUE("true"),
    FALSE("false");
    
    ///// STATIC /////
    
    private static final Map<String, KnownLexTokenType> LEX_TOKEN_MAP;
    private static final int MAX_LEN;
    
    static
    {
        final Map<String, KnownLexTokenType> lexTokenMapBuilder = new HashMap<>();
        int lenChecker = 0;
        
        for (final KnownLexTokenType tokenType : KnownLexTokenType.values())
        {
            
            for (final String lexeme : tokenType.getLexemes())
            {
                lexTokenMapBuilder.put(lexeme, tokenType);
                lenChecker = Math.max(lenChecker, lexeme.length());
            }
        }
        
        LEX_TOKEN_MAP = Map.copyOf(lexTokenMapBuilder);
        MAX_LEN = lenChecker;
    }
    
    public static KnownLexTokenType getTokenType(String lexeme)
    { return LEX_TOKEN_MAP.get(lexeme); }
    
    public static int getMaxTokenLen()
    { return MAX_LEN; }
    
    ///// NON-STATIC /////
    
    private final String[] lexemes;
    
    private KnownLexTokenType(String... lexemes) throws IllegalArgumentException
    {
        if (lexemes.length == 0) throw new IllegalArgumentException(
                "Token must have at least one lexeme");
        
        for (final String lexeme : lexemes)
        {
            if (lexeme.isBlank()) throw new IllegalArgumentException(
                    "Known Lexeme cannot be blank");
            else if (lexeme.contains(" ")) throw new IllegalArgumentException(
                    "Known Lexeme cannot contain whitespace");
        }
        this.lexemes = Arrays.copyOf(lexemes, lexemes.length);
    }
    
    public String[] getLexemes()
    { return Arrays.copyOf(this.lexemes, this.lexemes.length); }
    
    @Override public String toString()
    {
        return switch (this)
        {
            case STR -> "KW_STR";
            case CHAR -> "KW_CHAR";
            case INT -> "KW_INT";
            case FLOAT -> "KW_FLOAT";
            case BOOL -> "KW_BOOL";
            case TRUE -> "LIT_TRUE";
            case FALSE -> "LIT_FALSE";
            default -> name();
        };
    }
}
