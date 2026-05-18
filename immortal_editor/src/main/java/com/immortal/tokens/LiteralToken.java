package com.immortal.tokens;

public class LiteralToken extends Token
{
    private final TokenType type;
    private final String lexeme;
    private final Object literal;
    
    public LiteralToken(TokenType type, String lexeme, Object literal, int line)
    {
        super(line);
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
    }
    
    @Override public String getLexeme()
    { return this.lexeme; }
    
    @Override public TokenType getType()
    { return this.type; }
    
    @Override public String toString()
    {
        return this.line + " | " + this.type.toString() + this.lexeme
                + this.literal.toString();
    }
    
    public Object getLiteral()
    { return this.literal; }
}