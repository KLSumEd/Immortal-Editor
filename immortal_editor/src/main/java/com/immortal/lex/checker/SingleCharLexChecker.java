package com.immortal.lex.checker;

import java.util.Set;

import com.immortal.tokens.types.SingleCharTokenType;

public class SingleCharLexChecker extends KnownCharLexChecker
{
    @Override public boolean checkLex(String lexeme) 
    {
        Set<Character> validChars = SingleCharTokenType.getCharSet();
        return validChars.contains(lexeme.charAt(0));
    }
}
