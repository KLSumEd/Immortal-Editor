package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.FLOAT;

public class FloatToken extends LiteralToken
{
    private static final TokenType TYPE = FLOAT;
    
    public FloatToken(String lexeme, Object literal, int line)
    {
        super(TYPE, lexeme, literal, line);
        // TODO Auto-generated constructor stub
    }
    
    @Override public Float getLiteral() throws LiteralRetrievalCastException
    {
        if (this.literal instanceof final Float f) return f;
        else throw new LiteralRetrievalCastException();
    }
}
