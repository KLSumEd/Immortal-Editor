package com.immortal.tokens.types;

public abstract class FloatLexChecker
{
    private static final char DECIMAL_POINT = '.';

    public static final PossibleIdentifierLexChecker POS_CHECKER = (String lexeme) -> {
        return posChecker(lexeme);
    };
    
    public static final CorrectIdentifierLexChecker COR_CHECKER = (String lexeme) -> {
        return corChecker(lexeme);
    };

    public static final LiteralCaster CASTER = (String lexeme) -> {
        return Float.valueOf(lexeme);
    };

    private static boolean posChecker(String lexeme)
    {
        boolean possible = true;
        int numPoints = 0;

        for (char c : lexeme.toCharArray())
        {
            if (c == DECIMAL_POINT)
            {
                numPoints++;
            }

            if (!CharRange.DIGIT.checkChar(c) && c != DECIMAL_POINT)
            {
                possible = false;
                break;
            }
        }

        possible &= numPoints < 1;

        return possible;
    }
    
    private static boolean corChecker(String lexeme)
    {
        boolean valid = true;
        boolean hasOneDecPoint = false;

        for (char c : lexeme.toCharArray())
        {
            if (!CharRange.DIGIT.checkChar(c) && c != DECIMAL_POINT)
            {
                valid = false;
                break;
            }

            if (c == DECIMAL_POINT)
            {
                if (hasOneDecPoint)
                {
                    valid = false;
                    break;
                }
                else
                {
                    hasOneDecPoint = true;
                }
            }
        }

        valid &= hasOneDecPoint;

        return valid;
    }
}