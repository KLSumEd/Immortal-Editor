package com.immortal.lex.state;

import static com.immortal.lex.ImmortalCharGroup.DIGIT;
import static com.immortal.lex.ImmortalCharGroup.VALID_SYMS;
import static com.immortal.lex.state.LexState.FLOAT;
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
        
        if ((lexeme.length() == 1 && VALID_SYMS.checkChar(last))
                || KnownLexTokenType.getTokenType(lexeme) != null)
            result = SYMBOL;
        else if (lexeme.length() == 2 && lexeme.charAt(0) == '.'
                && DIGIT.checkChar(last))
            result = FLOAT;
        
        return result;
    }
}