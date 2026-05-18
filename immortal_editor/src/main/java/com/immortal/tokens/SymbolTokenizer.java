package com.immortal.tokens;

public class SymbolTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        return new KnownLexToken(type, line);
    }
}
