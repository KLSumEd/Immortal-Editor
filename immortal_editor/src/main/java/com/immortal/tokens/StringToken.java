package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.STR;

public class StringToken extends LiteralToken
{
    private static final IdentifierLiteralTokenType TYPE = STR;
    
    public StringToken(String lexeme, Object literal, int line)
    { super(TYPE, lexeme, literal, line); }
    
    @Override public String getLiteral()
    {
        if (this.literal instanceof final String s) return s;
        else throw new LiteralRetrievalCastException();
    }
}
