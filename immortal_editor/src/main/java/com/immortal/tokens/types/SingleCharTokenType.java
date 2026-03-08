package com.immortal.tokens.types;

import java.util.HashSet;
import java.util.Set;

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

    private static final Set<Character> CHAR_SET;

    static {
        final Set<Character> charSetBuilder = new HashSet<>();
        for (SingleCharTokenType type : SingleCharTokenType.values())
        {
            charSetBuilder.add(type.getLexeme().charAt(0));
        }
        CHAR_SET = Set.copyOf(charSetBuilder);
    }

    private SingleCharTokenType(char lexeme) { this.lexeme = String.valueOf(lexeme); }

    @Override public String getLexeme() { return this.lexeme; }
    public static Set<Character> getCharSet() { return SingleCharTokenType.CHAR_SET; }
}
