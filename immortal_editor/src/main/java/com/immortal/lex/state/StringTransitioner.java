package com.immortal.lex.state;

import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.STRING;
import static com.immortal.lex.state.LexState.TERMINATED;

public class StringTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException, IllegalArgumentException
    {
        final char last = lexeme.charAt(lexeme.length() - 1);
        
        final LexState result;
        
        if (lexeme.length() < 3 || lexeme.equals("\"\"\"")) result = STRING;
        else
        {
            final StringType type = StringType.getStringType(lexeme);
            if (type.checkTerminated(lexeme)) result = TERMINATED;
            else if (type == StringType.SINGLE && last == '\n') result = ERROR;
            else result = STRING;
        }
        
        return result;
    }
    
    private static enum StringType
    {
        SINGLE
        {
            @Override public boolean _checkTerminated(String lexeme)
            {
                // Terminated lexeme must match regex: \"[!\n]*(?<!\\)\".
                // Assumes lexeme already matches: \".*
                return (lexeme.charAt(lexeme.length() - 3) != '\\'
                        && lexeme.charAt(lexeme.length() - 2) == '"');
            }
        },
        MULTI
        {
            @Override public boolean _checkTerminated(String lexeme)
            {
                // Terminated lexeme must match regex: \"{3}\n.*(?<!\\)\"{3}.
                // Assumes lexeme already matches: \"{3}\n.*
                final String ending = lexeme.substring(lexeme.length() - 4,
                        lexeme.length() - 1);
                
                return (lexeme.charAt(lexeme.length() - 5) != '\\'
                        && ending.equals("\"\"\""));
            }
        };
        
        public static final StringType getStringType(String lexeme)
                throws IllegalArgumentException
        {
            final StringType result;
            
            try
            {
                if (lexeme.startsWith("\"\"\"\n")) result = MULTI;
                else if (lexeme.startsWith("\"")) result = SINGLE;
                else throw new IllegalArgumentException(
                        "lexeme start invalid for type String");
            }
            catch (final IllegalArgumentException | IndexOutOfBoundsException e)
            {
                throw new IllegalArgumentException(
                        "Could not find pattern for type String", e);
            }
            
            return result;
        }
        
        public abstract boolean _checkTerminated(String lexeme);
        
        public final boolean checkTerminated(String lexeme)
                throws IllegalArgumentException
        {
            final boolean result;
            
            try
            {
                if (lexeme.length() > 2) result = _checkTerminated(lexeme);
                else throw new IllegalArgumentException(
                        "lexeme must be of at least length 3 to be terminated");
            }
            catch (final IllegalArgumentException | IndexOutOfBoundsException e)
            {
                throw new IllegalArgumentException(e);
            }
            
            return result;
        }
    }
    
}
