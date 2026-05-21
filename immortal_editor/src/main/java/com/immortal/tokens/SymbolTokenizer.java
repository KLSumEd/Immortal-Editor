package com.immortal.tokens;

public class SymbolTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        if (type == null || lexeme.length() > 2)
            throw new IllegalArgumentException(
                    "No known token found for lexeme");
        return new KnownLexToken(type, line);
    }
}
