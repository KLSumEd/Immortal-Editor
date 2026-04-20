package com.immortal.tokens.types;

public abstract class StringLexChecker
{
    public static final PossibleIdentifierLexChecker POS_CHECKER = (String lexeme) -> {
        return checker(lexeme);
    };

    public static final CorrectIdentifierLexChecker COR_CHECKER = (String lexeme) -> {
        return checker(lexeme) 
          && lexeme.charAt(lexeme.length()-1) == '"' 
          && lexeme.charAt(lexeme.length()-2) != '\\';
    };

    public static final LiteralCaster CASTER = (String lexeme) -> {
        return String.valueOf(lexeme);
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