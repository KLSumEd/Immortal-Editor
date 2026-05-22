package com.immortal.app.tokens;

public class WordTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
    {
        final KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        final Token token = type != null ? new KnownLexToken(type, line)
                : new IdentifierToken(lexeme, line);
        return token;
    }
}