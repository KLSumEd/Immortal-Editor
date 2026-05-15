package com.immortal.tokens;

public class KnownLexTokenizer implements Tokenizer
{
    @Override public Token tokenize(String lexeme, int line)
    {
        final KnownLexTokenType type = KnownLexTokenType.getTokenType(lexeme);
        final KnownLexToken token = new KnownLexToken(type, line);
        return token;
    }

    @Override public boolean checkLex(String lexeme)
    {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkChar'");
    }
}
