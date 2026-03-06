package com.immortal;

public class IdentifierLiteralToken extends Token implements TokenType
{
    private final String lexeme;
    private final Object literal;

    public IdentifierLiteralToken(IdentifierLiteralTokenType type, String lexeme, Object literal, int line) 
    {
        super(type, line);
        this.lexeme = lexeme;
        this.literal = literal;
    }

    @Override public String toString() 
    {
        return this.getType().toString() + " " + this.getLexeme() + " | " + this.getLiteral() + " " + this.getLine();
    }
    
    @Override public String getLexeme() { return this.lexeme; }
    @Override public TokenType getType() { return this; }
    public Object getLiteral() { return this.literal; }
}
