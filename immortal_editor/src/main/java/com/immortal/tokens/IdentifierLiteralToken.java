package com.immortal.tokens;

import com.immortal.tokens.types.IdentifierLiteralTokenType;
import com.immortal.tokens.types.TokenType;

public class IdentifierLiteralToken extends Token
{
    private final IdentifierLiteralTokenType type;
    private final String lexeme;
    private final Object literal;

    public IdentifierLiteralToken(IdentifierLiteralTokenType type, String lexeme, Object literal, int line) 
    {
        super(line);
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }

    @Override public String toString() 
    {
        return this.type + " " + this.lexeme + " | " + this.literal + " " + this.line;
    }
    
    @Override public String getLexeme() { return this.lexeme; }
    public Object getLiteral() { return this.literal; }

    @Override public TokenType getType() { return this.type; }
}
