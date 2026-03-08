package com.immortal.tokens.types;

import java.util.HashSet;
import java.util.Set;

public enum ReservedWordsTokenType implements TokenType
{
    TRUE("true"),
    FALSE("false"),
    STR("str"),           // For the following entries
    INT("int"),           // ensure the given TokenType
    BOOL("bool"),        // refers to the TYPE IDENTIFIER TOKENS
    FLOAT("float"),     // NOT the LITERAL TOKENS   
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

    private static final Set<String> LEXEME_SET;

    static {
        Set<String> lexSetBuilder = new HashSet<>();
        for (ReservedWordsTokenType tokenType : ReservedWordsTokenType.values())
        {
            lexSetBuilder.add(tokenType.getLexeme());
        }
        LEXEME_SET = Set.copyOf(lexSetBuilder);
    }

    private ReservedWordsTokenType(String newLexeme) { this.lexeme = newLexeme; }

    @Override public String getLexeme() { return this.lexeme; }
    public static Set<String> getLexSet() { return LEXEME_SET; }
}
