package com.immortal.lex;

import com.immortal.tokens.types.SingleCharTokenType;

public class SingleCharLexChecker implements LexChecker
{
    @Override public boolean checkLex(String lexeme) 
    {
        boolean valid = SingleCharTokenType.getCharSet().contains(lexeme.charAt(0));
        return valid;
    }
    
}
