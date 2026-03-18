package com.immortal.lex.checker;

import java.util.Set;

import com.immortal.tokens.types.SingleDoubleCharTokenType;

public class SingleDoubleCharLexChecker implements LexChecker
{
    @Override public boolean checkLex(String lexeme) 
    {
        Set<String> validLexSet = SingleDoubleCharTokenType.getLexSet();
        
        boolean valid = validLexSet.contains(lexeme);

        return valid;
    }
    
}
