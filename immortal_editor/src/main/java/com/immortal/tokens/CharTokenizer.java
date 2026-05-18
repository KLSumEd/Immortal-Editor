package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.CHAR;

public class CharTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final char literal;
        
        try
        {
            final String literalStr = lexeme.translateEscapes();
            
            if (literalStr.length() != 1)
                throw new IllegalArgumentException("Unrecognised Character");
            else literal = literalStr.charAt(0);
        }
        catch (final IllegalArgumentException | IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Unrecognized character", e);
        }
        
        final Token token = new LiteralToken(CHAR, lexeme, literal, line);
        
        return token;
    }
}
