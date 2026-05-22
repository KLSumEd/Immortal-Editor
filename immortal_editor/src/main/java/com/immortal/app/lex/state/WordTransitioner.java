package com.immortal.app.lex.state;

import static com.immortal.app.lex.ImmortalCharGroup.ALPHA;
import static com.immortal.app.lex.ImmortalCharGroup.ID_PART;
import static com.immortal.app.lex.state.LexState.ID;
import static com.immortal.app.lex.state.LexState.TERMINATED;
import static com.immortal.app.lex.state.LexState.WORD;
import com.immortal.app.tokens.KnownLexTokenType;

public class WordTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        final LexState result;
        
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        if (ID_PART.checkChar(last) && (!ALPHA.checkChar(last)
                || lexeme.length() > KnownLexTokenType.getMaxTokenLen()))
            result = ID;
        else if (ALPHA.checkChar(last)) result = WORD;
        else result = TERMINATED;
        
        return result;
    }
}
