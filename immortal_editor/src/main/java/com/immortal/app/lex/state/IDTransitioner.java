package com.immortal.app.lex.state;

import static com.immortal.app.lex.ImmortalCharGroup.ID_HEAD;
import static com.immortal.app.lex.ImmortalCharGroup.ID_PART;
import static com.immortal.app.lex.state.LexState.ID;
import static com.immortal.app.lex.state.LexState.TERMINATED;

public class IDTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        final LexState result;
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        if ((lexeme.length() > 1 && ID_PART.checkChar(last))
                || (ID_HEAD.checkChar(last)))
            result = ID;
        else result = TERMINATED;
        
        return result;
    }
}
