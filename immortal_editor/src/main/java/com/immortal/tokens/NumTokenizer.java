package com.immortal.tokens;

public class NumTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final boolean isFloat = lexeme.contains(".");
        
        try
        {
            final Token token;
            
            if (isFloat)
            {
                final float literal = Float.parseFloat(lexeme);
                token = new FloatToken(lexeme, literal, line);
            }
            else
            {
                final int literal = Integer.parseInt(lexeme);
                token = new IntToken(lexeme, literal, line);
            }
            
            return token;
        }
        catch (final NumberFormatException e)
        {
            throw new IllegalArgumentException(
                    "Invalid lexeme for Float or Integer", e);
        }
    }
}
