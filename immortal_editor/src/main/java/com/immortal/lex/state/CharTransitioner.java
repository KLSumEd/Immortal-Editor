package com.immortal.lex.state;

import static com.immortal.lex.state.LexState.CHAR;
import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.TERMINATED;

public class CharTransitioner implements LexState.StateTransitioner
{
    @Override public LexState getNextState(String lexeme)
            throws IndexOutOfBoundsException
    {
        final LexState result;
        final char last = lexeme.charAt(lexeme.length() - 1);
        final char last_1 = lexeme.charAt(lexeme.length() - 2);
        
        result = switch (lexeme.length())
        {
            case 1, 3 -> CHAR;
            case 2 -> last != '\'' ? CHAR : ERROR;
            case 4 ->
                last_1 == '\'' && lexeme.charAt(1) != '\\' ? TERMINATED : CHAR;
            default ->
            {
                if (lexeme.charAt(1) == '\\')
                    yield checkPossibleEscapeSequence(lexeme);
                else if (lexeme.length() == 5 && last_1 == '\'')
                    yield TERMINATED;
                else yield ERROR;
            }
            
        };
        
        return result;
    }
    
    private static LexState checkPossibleEscapeSequence(String lexeme)
            throws IllegalArgumentException
    {
        final LexState result;
        
        try
        {
            if (lexeme.length() < 5)
                throw new IndexOutOfBoundsException(lexeme.length());
            else if (lexeme.charAt(1) != '\\')
                throw new IllegalArgumentException(
                        "Given lexeme does not contain possible escape sequence to check");
            
            final char escapeChar = Character.toLowerCase(lexeme.charAt(2));
            final char last_1 = lexeme.charAt(lexeme.length() - 2);
            result = switch (escapeChar)
            {
                case 'b', 't', 'n', 'f', 'r', 's', '0', '"', '\'', '\\' ->
                    last_1 == '\'' ? TERMINATED : ERROR;
                case 'u' ->
                {
                    final String codePoint = lexeme.substring(3);
                    if (codePoint.matches("^\\+?[0-9a-fA-F]{0,4}$")) yield CHAR;
                    else if (codePoint.matches("^\\+?[0-9a-fA-F]{4}'$"))
                        yield CHAR;
                    else if (codePoint.matches("^\\+?[0-9a-fA-F]{4}'.$"))
                        yield TERMINATED;
                    else yield ERROR;
                }
                default -> ERROR;
            };
        }
        catch (IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException(
                    "Given lexeme must be of at least length 5", e);
        }
        return result;
    }
}
