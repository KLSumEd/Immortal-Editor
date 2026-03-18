package com.immortal.tokens.types;

import java.util.HashSet;
import java.util.Set;

public enum SingleDoubleCharTokenType implements KnownTokenLexTokenType
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

    private static final int MAX_LENGTH = 2;
    private static final Set<String> LEXEME_SET;
    private static final Set<Character> FIRST_CHAR_SET;

    static 
    {
        // Here we build the static sets for each Enum instance
        // We use the below builders rather than adding directly to LEXEME_SET & FIRST_CHAR_SET
        final Set<String> lexSetBuilder = new HashSet<>();
        final Set<Character> charSetBuilder = new HashSet<>();

        for (SingleDoubleCharTokenType tokenType : values()) // For each Enum Instance of SingleDoubleCharTokenType...
        {
            lexSetBuilder.add(tokenType.getLexeme());
            charSetBuilder.add(tokenType.getFirstChar()); // add() only adds value if not already in set
        }

        // Use Set.copyOf rather than simply assigning the Sets to force them to be unmodifiable
        LEXEME_SET = Set.copyOf(lexSetBuilder);
        FIRST_CHAR_SET = Set.copyOf(charSetBuilder);
    }

    private SingleDoubleCharTokenType(String lexeme) 
    {
        this.lexeme = lexeme;
    }

    @Override public String getLexeme() { return this.lexeme; }
    public static int getMaxLength() { return MAX_LENGTH; }
    public static Set<String> getLexSet() { return LEXEME_SET; }
    public static Set<Character> getFirstCharSet() { return FIRST_CHAR_SET; }

    @Override public char getFirstChar() { return this.lexeme.charAt(0); }
}
