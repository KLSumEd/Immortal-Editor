package com.immortal.app.tokens;

import static com.immortal.app.tokens.IdentifierLiteralTokenType.CHAR;

public class CharToken extends LiteralToken
{
    private static final TokenType TYPE = CHAR;
    
    public CharToken(String lexeme, Object literal, int line)
    { super(TYPE, lexeme, literal, line); }
    
    @Override public Character getLiteral()
    {
        if (this.literal instanceof final Character ch) return ch;
        else throw new LiteralRetrievalCastException();
    }
}