package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.INT;

public class IntToken extends LiteralToken
{
    private static final TokenType TYPE = INT;
    
    public IntToken(String lexeme, Object literal, int line)
    { super(TYPE, lexeme, literal, line); }
    
    @Override public Integer getLiteral()
    {
        if (this.literal instanceof final Integer i) return i;
        else throw new LiteralRetrievalCastException();
    }
}