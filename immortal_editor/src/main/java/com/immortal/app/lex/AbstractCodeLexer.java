package com.immortal.app.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.immortal.app.scan.Scanner;
import com.immortal.app.tokens.Token;
import com.immortal.app.util.EnclosedPattern;

/**
 * <p>
 * Provides an abstract implementation of the {@link Lexer} interface. The
 * source reading functionality is provided by the
 * {@link com.immortal.app.scan.Scanner Scanner} package. The implentation
 * contains methods for performing lexical analysis on a
 * <code>String source</code> and skipping both single- and multi-line comments.
 * </p>
 * 
 * <p>
 * To create a simple Lexer <em>(skipping Java-style comments:
 * <code>// ... \n</code> & <code>/* ... {@literal *}/</code>)</em> the
 * programmer need only implement the {@link #lexToken()} method and call the
 * {@link #AbstractLexer(String) Super Constructor}
 * </p>
 * 
 * @author {@link https://github.com/KLSumEd KLSumEd}
 * @see
 */
public abstract class AbstractCodeLexer implements Lexer
{
    /// ATTRIBUTES ///
    protected final Scanner scanner;
    private final String comInitiatorSL;
    private final EnclosedPattern comPatternML;
    private boolean hadLexError = false;
    
    /// CONSTRUCTORS ///
    
    public AbstractCodeLexer(
            String source, String comInitiatorSL, EnclosedPattern comPatternML
    )
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = EnclosedPattern.copyOf(comPatternML);
    }
    
    public AbstractCodeLexer(String source, String comInitiatorSL)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = new EnclosedPattern("/*", "*/");
    }
    
    public AbstractCodeLexer(String source)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = "//";
        this.comPatternML = new EnclosedPattern("/*", "*/");
    }
    
    /// METHODS ///
    
    @Override public Collection<Token> lex()
    {
        final List<Token> tokens = new ArrayList<>();
        
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
    
    @Override public final boolean hasErrorOccurred()
    { return this.hadLexError; }
    
    @Override public final int getLastLine()
    { return this.scanner.getLine(); }
}