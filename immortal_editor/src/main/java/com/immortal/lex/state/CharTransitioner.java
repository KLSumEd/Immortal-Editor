package com.immortal.lex.state;

import static com.immortal.lex.state.LexState.CHAR;
import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.TERMINATED;

public class CharTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result = ERROR;
        
        if (((lexeme.length() == 4 && lexeme.charAt(1) == '\\')
                || lexeme.length() == 3)
                && lexeme.charAt(lexeme.length() - 1) == '\'')
            result = TERMINATED;
        else if (lexeme.charAt(0) == '\'') result = CHAR;
        
        return result;
    }
}
