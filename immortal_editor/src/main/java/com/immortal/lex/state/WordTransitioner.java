package com.immortal.lex.state;

import static com.immortal.lex.ImmortalCharGroup.ALPHA;
import static com.immortal.lex.ImmortalCharGroup.DIGIT;
import static com.immortal.lex.state.LexState.ID;
import static com.immortal.lex.state.LexState.TERMINATED;
import static com.immortal.lex.state.LexState.WORD;
import com.immortal.tokens.KnownLexTokenType;

public class WordTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        final LexState result;
        
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        if (DIGIT.checkChar(last)
                || lexeme.length() > KnownLexTokenType.getMaxTokenLen())
            result = ID;
        else if (ALPHA.checkChar(last)) result = WORD;
        else result = TERMINATED;
        
        return result;
    }
}
