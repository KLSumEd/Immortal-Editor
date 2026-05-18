package com.immortal.lex.state;

import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.STRING;
import static com.immortal.lex.state.LexState.TERMINATED;

public class StringTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        final LexState result = switch (lexeme.length())
        {
            case 1, 3 -> last == '"' ? STRING : TERMINATED;
            case 2 -> lexeme.charAt(0) == '"' ? STRING : ERROR;
            default ->
            {
                final StringType type = StringType.getStringType(lexeme);
                if (type == StringType.NONE) yield ERROR;
                else if (type.checkTerminated(lexeme)) yield TERMINATED;
                else if (type == StringType.SINGLE && last == '\n') yield ERROR;
                else yield STRING;
            }
        };
        
        return result;
    }
    
    private static enum StringType
    {
        SINGLE
        {
            @Override public boolean checkTerminated(String lexeme)
            {
                final boolean result;
                result = lexeme.length() >= 3
                        && lexeme.charAt(lexeme.length() - 2) == '"'
                        && lexeme.charAt(lexeme.length() - 3) != '\\';
                return result;
            }
        },
        MULTI
        {
            @Override public boolean checkTerminated(String lexeme)
            {
                final String truncated = lexeme.substring(0,
                        lexeme.length() - 1);
                final boolean result;
                result = truncated.length() >= 7 && lexeme.endsWith("\"\"\"")
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
            
            type = (lexeme.startsWith("\"\"\"\n")) ? MULTI
                    : (lexeme.startsWith("\"")) ? SINGLE : NONE;
            
            return type;
        }
        
        public abstract boolean checkTerminated(String lexeme);
    }
}
