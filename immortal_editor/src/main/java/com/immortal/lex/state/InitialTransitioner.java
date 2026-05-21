package com.immortal.lex.state;

import static com.immortal.lex.ImmortalCharGroup.ALPHA;
import static com.immortal.lex.ImmortalCharGroup.DIGIT;
import static com.immortal.lex.ImmortalCharGroup.VALID_SYMS;
import static com.immortal.lex.state.LexState.CHAR;
import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.ID;
import static com.immortal.lex.state.LexState.INITIAL;
import static com.immortal.lex.state.LexState.NUM;
import static com.immortal.lex.state.LexState.STRING;
import static com.immortal.lex.state.LexState.SYMBOL;
import static com.immortal.lex.state.LexState.WORD;

public class InitialTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result = lexeme.length() == 0 ? INITIAL : ERROR;
        
        if (lexeme.length() == 1)
        {
            char firstChar = lexeme.charAt(0);
            
            result = switch (firstChar)
            {
                case '_' -> ID;
                case '"' -> STRING;
                case '\'' -> CHAR;
                default ->
                {
                    if (ALPHA.checkChar(firstChar)) yield WORD;
                    else if (VALID_SYMS.checkChar(firstChar)) yield SYMBOL;
                    else if (DIGIT.checkChar(firstChar)) yield NUM;
                    else yield ERROR;
                }
            };
        }
        
        return result;
    };
}
