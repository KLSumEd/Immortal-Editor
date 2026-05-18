package com.immortal.tokens;

public class IdentifierTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    { return new IdentifierToken(lexeme, line); }
}
