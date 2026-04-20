package com.immortal.tokens.types;

import java.util.List;
import java.util.Set;

public abstract class IdentifierLexChecker
{
    private static final CharRangeGroup ID_HEAD = new CharRangeGroup(
        List.of(CharRange.LOWER, CharRange.UPPER), Set.of('_')
    );

    private static final CharRangeGroup ID_TAIL = new CharRangeGroup(
        List.of(CharRange.DIGIT, CharRange.LOWER, CharRange.UPPER), Set.of('_')
    );

    public static final PossibleIdentifierLexChecker POS_CHECKER = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final CorrectIdentifierLexChecker COR_CHECKER = (String lexeme) -> {
        final boolean result = lexeme.isEmpty() ? false : checker(lexeme);
        return result;
    };

    public static final LiteralCaster CASTER = (String lexeme) -> {
        return String.valueOf(lexeme);
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