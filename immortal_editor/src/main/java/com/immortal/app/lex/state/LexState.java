package com.immortal.app.lex.state;

import com.immortal.app.tokens.CharTokenizer;
import com.immortal.app.tokens.NullToken;
import com.immortal.app.tokens.NumTokenizer;
import com.immortal.app.tokens.StringTokenizer;
import com.immortal.app.tokens.SymbolTokenizer;
import com.immortal.app.tokens.Token;
import com.immortal.app.tokens.Tokenizer;
import com.immortal.app.tokens.WordTokenizer;

public enum LexState implements Tokenizer
{
    INITIAL(new InitialTransitioner()),
    WORD(new WordTransitioner(), LexStateTokenizer.WORD),
    ID(new IDTransitioner(), LexStateTokenizer.WORD),
    NUM(new NumTransitioner(), LexStateTokenizer.NUM),
    STRING(new StringTransitioner(), LexStateTokenizer.STR),
    CHAR(new CharTransitioner(), LexStateTokenizer.CHAR),
    FLOAT(new FloatTransitioner(), LexStateTokenizer.NUM),
    SYMBOL(new SymbolTransitioner(), LexStateTokenizer.SYM),
    TERMINATED(),
    ERROR();
    
    /// STATIC METHODS & ATTRIBUTES ///
    
    private static final StateTransitioner NULL_TRANSITIONER = (
            lexeme) -> (ERROR);
    
    private static StateTransitioner getNullTransitioner()
    { return NULL_TRANSITIONER; }
    
    @FunctionalInterface
    public static interface StateTransitioner
    {
        public LexState getNextState(String lexeme)
                throws IndexOutOfBoundsException;
    }
    
    /// ATTRIBUTES ///
    
    private final StateTransitioner transitioner;
    private final Tokenizer tokenizer;
    
    /// CONSTRUCTORS ///
    
    private LexState(StateTransitioner transitioner, Tokenizer tokenizer)
    {
        this.tokenizer = tokenizer;
        this.transitioner = transitioner;
    }
    
    private LexState(StateTransitioner transitioner)
    {
        this.transitioner = transitioner;
        this.tokenizer = LexStateTokenizer.NULL;
    }
    
    private LexState()
    {
        this.transitioner = getNullTransitioner();
        this.tokenizer = LexStateTokenizer.NULL;
    }
    
    /// METHODS ///
    
    @Override public Token tokenize(String lexeme, int line)
    { return this.tokenizer.tokenize(lexeme, line); }
    
    public LexState getNextState(String lexeme) throws IllegalArgumentException
    {
        LexState result = ERROR;
        
        try
        {
            result = this.transitioner.getNextState(lexeme);
        }
        catch (final IndexOutOfBoundsException e)
        {
            final String msg = lexeme.length() == 0
                    ? "State invalid for empty lexeme"
                    : "Failed to get last character of lexeme";
            throw new IllegalArgumentException(msg, e);
        }
        
        return result;
    }
}


enum LexStateTokenizer implements Tokenizer
{
    NULL((lexeme, line) -> (new NullToken(line))),
    WORD(new WordTokenizer()),
    NUM(new NumTokenizer()),
    STR(new StringTokenizer()),
    CHAR(new CharTokenizer()),
    SYM(new SymbolTokenizer());
    
    private final Tokenizer tokenizer;
    
    private LexStateTokenizer(Tokenizer tokenizer)
    { this.tokenizer = tokenizer; }
    
    @Override public Token tokenize(String lexeme, int line)
    { return this.tokenizer.tokenize(lexeme, line); }
}