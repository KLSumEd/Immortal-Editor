package com.immortal.app.tokens;

public class KnownLexTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
    {
        final KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        final KnownLexToken token = new KnownLexToken(type, line);
        return token;
    }
}
