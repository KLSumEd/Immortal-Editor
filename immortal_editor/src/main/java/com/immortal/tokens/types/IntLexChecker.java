package com.immortal.tokens.types;

public abstract class IntLexChecker
{
    public static final PossibleIdentifierLexChecker POS_CHECKER = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final CorrectIdentifierLexChecker COR_CHECKER = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final LiteralCaster CASTER = (String lexeme) -> {
        return Integer.valueOf(lexeme);
    };

    private static boolean checker(String lexeme)
    {
        boolean valid = true;
        
        for (char c : lexeme.toCharArray())
        {
            if (!CharRange.DIGIT.checkChar(c))
            {
                valid = false;
                break;
            }
        }

        return valid;
    }
}
