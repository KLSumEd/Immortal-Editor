package com.immortal.lex.state;

import static com.immortal.lex.ImmortalCharGroup.VALID_SYMS;
import static com.immortal.lex.state.LexState.SYMBOL;
import static com.immortal.lex.state.LexState.TERMINATED;
import com.immortal.tokens.KnownLexTokenType;

public class SymbolTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result = TERMINATED;
        
        char last = lexeme.charAt(lexeme.length() - 1);
        
        if (VALID_SYMS.checkChar(last)
                && !KnownLexTokenType.getPossibleLexemes(lexeme).isEmpty())
        { result = SYMBOL; }
        
        return result;
    }
}
