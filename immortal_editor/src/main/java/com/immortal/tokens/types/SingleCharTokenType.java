package com.immortal.tokens.types;

import java.util.HashSet;
import java.util.Set;

public enum SingleCharTokenType implements KnownTokenLexTokenType
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
    private final char lexChar;

    private static final Set<Character> CHAR_SET;

    static 
    {
        final Set<Character> charSetBuilder = new HashSet<>();

        for (SingleCharTokenType type : SingleCharTokenType.values())
        {
            charSetBuilder.add(type.getLexeme().charAt(0));
        }

        CHAR_SET = Set.copyOf(charSetBuilder);
    }

    private SingleCharTokenType(char lexChar) 
    { 
        this.lexChar = lexChar;
        this.lexeme = String.valueOf(lexChar); 
    }

    @Override public String getLexeme() { return this.lexeme; }
    @Override public char getFirstChar() { return this.lexChar; }
    public static Set<Character> getCharSet() { return SingleCharTokenType.CHAR_SET; }
}
