package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.FLOAT;
import static com.immortal.tokens.IdentifierLiteralTokenType.INT;

public class NumTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final boolean isFloat = lexeme.contains(".");
        
        try
        {
            final Object literal = isFloat ? Float.valueOf(lexeme)
                    : Integer.valueOf(lexeme);
            final TokenType type = isFloat ? FLOAT : INT;
            final Token token = new LiteralToken(type, lexeme, literal, line);
            return token;
        }
        catch (final NumberFormatException e)
        {
            throw new IllegalArgumentException(
                    "Invalid lexeme for Float or Integer", e);
        }
    }
}
