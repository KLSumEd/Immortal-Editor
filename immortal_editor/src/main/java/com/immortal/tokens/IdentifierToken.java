package com.immortal.tokens;

import static com.immortal.tokens.IdentifierLiteralTokenType.IDENTIFIER;

public class IdentifierToken extends Token
{
    private final String lexeme;
    
    public IdentifierToken(String lexeme, int line)
    {
        super(line);
        this.lexeme = lexeme;
    }
    
    @Override public String getLexeme()
    { return this.lexeme; }
    
    @Override public TokenType getType()
    { return IDENTIFIER; }
}