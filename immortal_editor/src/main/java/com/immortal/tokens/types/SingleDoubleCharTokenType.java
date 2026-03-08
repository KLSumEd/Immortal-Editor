package com.immortal.tokens.types;

import java.util.HashSet;
import java.util.Set;

public enum SingleDoubleCharTokenType implements TokenType
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

    private static final Set<String> LEXEME_SET;

    static {
        final Set<String> lexSetBuilder = new HashSet<>();
        for (SingleDoubleCharTokenType tokenType : SingleDoubleCharTokenType.values())
        {
            lexSetBuilder.add(tokenType.getLexeme());
        }
        LEXEME_SET = Set.copyOf(lexSetBuilder);
    }

    private SingleDoubleCharTokenType(String lexeme) 
    {
        this.lexeme = lexeme;
    }

    @Override public String getLexeme() { return this.lexeme; }
    public static Set<String> getLexSet() { return SingleDoubleCharTokenType.LEXEME_SET; }
}
