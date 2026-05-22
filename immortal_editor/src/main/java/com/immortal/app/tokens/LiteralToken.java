package com.immortal.app.tokens;

public abstract class LiteralToken extends Token
{
    private final TokenType type;
    private final String lexeme;
    protected final Object literal;
    
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
        return "%d | %s %s %s".formatted(this.line, this.type, this.lexeme,
                getLiteral());
    }
    
    public abstract Object getLiteral() throws LiteralRetrievalCastException;
    
    protected class LiteralRetrievalCastException
            extends IllegalArgumentException
    {
        private static final String DEFAULT_MSG = "Underlying literal could not be retrieved";
        
        public LiteralRetrievalCastException()
        { super(DEFAULT_MSG); }
        
        public LiteralRetrievalCastException(Throwable cause)
        { super(DEFAULT_MSG, cause); }
        
        public LiteralRetrievalCastException(String msg)
        { super(msg); }
        
        public LiteralRetrievalCastException(String msg, Throwable cause)
        { super(msg, cause); }
    }
}