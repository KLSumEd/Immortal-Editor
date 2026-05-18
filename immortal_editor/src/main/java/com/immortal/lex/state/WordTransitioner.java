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
        LexState result = TERMINATED;
        
        final char last = lexeme.charAt(lexeme.length() - 1);
        if (ALPHA.checkChar(last)) result = WORD;
        else if (DIGIT.checkChar(last)
                || KnownLexTokenType.isPossibleLexeme(lexeme))
            result = ID;
        
        return result;
    }
}
