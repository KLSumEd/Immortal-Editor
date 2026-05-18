package com.immortal.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.immortal.scan.Scanner;
import com.immortal.tokens.Token;

public abstract class AbstractLexer implements Lexer
{
    protected final Scanner scanner;
    private final String comInitiatorSL;
    private final EnclosedPattern comPatternML;
    private boolean hadLexError;
    
    public AbstractLexer(
            String source, String comInitiatorSL, EnclosedPattern comPatternML
    )
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = EnclosedPattern.copyOf(comPatternML);
        this.hadLexError = false;
    }
    
    public AbstractLexer(String source, String comInitiatorSL)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = new EnclosedPattern("/*", "*/");
        this.hadLexError = false;
    }
    
    public AbstractLexer(String source)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = "//";
        this.comPatternML = new EnclosedPattern("/*", "*/");
        this.hadLexError = false;
    }
    
    @Override public Collection<Token> lex()
    {
        List<Token> tokens = new ArrayList<>();
        
        while (!this.scanner.isAtEnd() && !this.hadLexError)
        { if (!skip()) tokens.add(lexToken()); }
        
        return tokens;
    }
    
    protected abstract Token lexToken();
    
    private boolean skip()
    { return skipWhiteSpace() || skipCommentSL() || skipCommentML(); }
    
    private boolean skipWhiteSpace()
    {
        boolean result = false;
        boolean isWhiteSpace = false;
        
        do
        {
            char c = this.scanner.peek();
            
            switch (c)
            {
                case '\n':
                    this.scanner.incrementLine();
                case '\t':
                case '\r':
                case '\f':
                case ' ':
                case '\b':
                    this.scanner.incrementIndex();
                    isWhiteSpace = true;
                    break;
                
                default:
                    result = isWhiteSpace;
                    isWhiteSpace = false;
                    break;
            }
            
        }
        while (isWhiteSpace);
        
        return result;
    }
    
    protected final boolean skipCommentSL()
    {
        boolean result = false;
        
        if (this.scanner.peek(2).equals(this.comInitiatorSL))
        {
            int commentLen = this.scanner.peekUntil('\n', true).length();
            this.scanner.incrementIndex(2 + commentLen);
            this.scanner.incrementLine();
            result = true;
        }
        
        return result;
    }
    
    protected final boolean skipCommentML()
    {
        boolean result = false;
        final int lenInitiator = this.comPatternML.getInitiator().length();
        final String beginning = this.scanner.peek(lenInitiator);
        
        if (this.comPatternML.matchBeginning(beginning))
        {
            this.scanner.advance(lenInitiator);
            String s = this.scanner.peekUntil(this.comPatternML.getTerminator(),
                    true);
            int dist = s.length();
            this.scanner.advance(dist);
            result = true;
        }
        
        return result;
    }
    
    protected final void error()
    { this.hadLexError = true; }
    
    public final boolean hasErrorOccurred()
    { return this.hadLexError; }
}