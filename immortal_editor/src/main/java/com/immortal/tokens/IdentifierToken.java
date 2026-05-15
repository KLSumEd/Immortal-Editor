package com.immortal.tokens;

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
    { return IdentifierLiteralTokenType.IDENTIFIER; }

    @Override public String toString()
    { return this.line + " | " + IdentifierLiteralTokenType.IDENTIFIER.toString() + " " + lexeme; }
}