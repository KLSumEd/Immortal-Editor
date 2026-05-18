package com.immortal.lex.state;

import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.STRING;
import static com.immortal.lex.state.LexState.TERMINATED;

public class StringTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        LexState result;
        StringType type = StringType.getStringType(lexeme);
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        if ((last == '\0') || (type == StringType.SINGLE && last == '\n'))
            result = ERROR;
        else if (type.checkTerminated(lexeme)) result = TERMINATED;
        else result = STRING;
        
        return result;
    }
    
    private static enum StringType
    {
        SINGLE
        {
            @Override public boolean checkTerminated(String lexeme)
            {
                boolean result;
                result = lexeme.length() >= 2
                        && lexeme.charAt(lexeme.length() - 1) == '"'
                        && lexeme.charAt(lexeme.length() - 2) != '\\';
                return result;
            }
        },
        MULTI
        {
            @Override public boolean checkTerminated(String lexeme)
            {
                boolean result;
                result = lexeme.length() >= 6 && lexeme.endsWith("\"\"\"")
                        && lexeme.charAt(lexeme.length() - 4) != '\\';
                return result;
            }
        },
        NONE
        {
            @Override public boolean checkTerminated(String lexeme)
            { return false; }
        };
        
        public static StringType getStringType(String lexeme)
        {
            final StringType type;
            
            type = (lexeme.startsWith("\"\"\"")) ? MULTI
                    : (lexeme.startsWith("\"")) ? SINGLE : NONE;
            
            return type;
        }
        
        public abstract boolean checkTerminated(String lexeme);
    }
}
