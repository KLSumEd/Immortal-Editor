package com.immortal.app.lex.state;

import static com.immortal.app.lex.ImmortalCharGroup.DIGIT;
import static com.immortal.app.lex.state.LexState.FLOAT;
import static com.immortal.app.lex.state.LexState.NUM;
import static com.immortal.app.lex.state.LexState.TERMINATED;

public class NumTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result = TERMINATED;
        
        char last = lexeme.charAt(lexeme.length() - 1);
        if (DIGIT.checkChar(last)) result = NUM;
        else if (last == '.') result = FLOAT;
        
        return result;
    }
}
