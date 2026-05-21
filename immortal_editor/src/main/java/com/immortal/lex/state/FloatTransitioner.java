package com.immortal.lex.state;

import static com.immortal.lex.ImmortalCharGroup.DIGIT;
import static com.immortal.lex.state.LexState.FLOAT;
import static com.immortal.lex.state.LexState.TERMINATED;

public class FloatTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result = TERMINATED;
        
        char last = lexeme.charAt(lexeme.length() - 1);
        if (DIGIT.checkChar(last)) result = FLOAT;
        
        return result;
    }
}
