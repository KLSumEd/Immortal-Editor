package com.immortal.tokens.types;

import java.util.List;
import java.util.Set;

public enum IdentifierLiteralTokenType implements TokenType
{
    IDENTIFIER(IdentifierLexChecker.posChecker, IdentifierLexChecker.corChecker),
    STR(StringLexChecker.posChecker, StringLexChecker.corChecker),
    INT(),
    FLOAT();

    private final PossibleIdentifierLexChecker posChecker;
    private final CorrectIdentifierLexChecker corChecker;

    private IdentifierLiteralTokenType(
        PossibleIdentifierLexChecker posChecker,
        CorrectIdentifierLexChecker corChecker
    ) 
    {
        this.posChecker = posChecker;
        this.corChecker = corChecker;
    }
}

abstract class IdentifierLexChecker
{
    private static final CharRangeGroup ID_HEAD = new CharRangeGroup(
        List.of(CharRange.LOWER, CharRange.UPPER), Set.of('_')
    );

    private static final CharRangeGroup ID_TAIL = new CharRangeGroup(
        List.of(CharRange.DIGIT, CharRange.LOWER, CharRange.UPPER), Set.of('_')
    );

    public static final PossibleIdentifierLexChecker posChecker = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final CorrectIdentifierLexChecker corChecker = (String lexeme) -> {
        final boolean result = lexeme.isEmpty() ? false : checker(lexeme);
        return result;
    };

    private static boolean checker(String lexeme) 
    {
        boolean valid = true;

        if (!lexeme.isEmpty()) valid = ID_HEAD.checkChar(lexeme.charAt(0));

        if (valid)
        {
            for (int i = 1; i < lexeme.length(); i++)
            {
                valid = ID_TAIL.checkChar(lexeme.charAt(i));
                if (!valid) break;
            }
        }

        return valid;
    }
}

abstract class StringLexChecker
{
    public static final PossibleIdentifierLexChecker posChecker = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final CorrectIdentifierLexChecker corChecker = (String lexeme) -> {
        return checker(lexeme) && lexeme.charAt(lexeme.length()-1) == '"';
    };

    private static boolean checker(String lexeme)
    {
        boolean valid = true;

        if (!lexeme.isEmpty()) valid = lexeme.charAt(0) == '"';

        boolean terminated = false;
        for (int i = 1; i < lexeme.length(); i++) 
        {
            char c = lexeme.charAt(i);
            if (terminated) { valid = false; break; }
            else { terminated = c == '"' && lexeme.charAt(i-1) != '\\'; }
        }

        return valid;
    }
}

interface PossibleIdentifierLexChecker
{
    public boolean execute(String lexeme);
}

interface CorrectIdentifierLexChecker
{
    public boolean execute(String lexeme);
}