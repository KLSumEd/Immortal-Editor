package com.immortal.app.lex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.immortal.app.scan.Scanner;
import com.immortal.app.tokens.Token;
import com.immortal.app.util.EnclosedPattern;

// @formatter:off
/**
 * Provides an abstract implementation of the {@link Lexer} interface for lexing
 * a coding language. The source reading functionality is provided by a
 * {@link Scanner} implementation. Contains methods for performing 
 * lexical analysis on a {@code String} source and skipping both
 * single- and multi-line comments.
 * <p>
 * To create a simple Code Lexer _(skipping Java-style comments:
 * {@code // ... \n} {@literal &} <code>/* ... {@literal *}/</code>)_ 
 * the programmer need only implement the
 * {@link Lexer#lexToken() <code>lexToken()</code>} method and call the
 * {@link #AbstractLexer(String) Super Constructor}.
 * 
 * @author <a href="https://github.com/KLSumEd">KLSumEd</a> 
 * @see Lexer Lexer 
 * @see com.immortal.app.scan.Scanner Scanner
 */
// @formatter:on
public abstract class AbstractCodeLexer implements Lexer
{
    /// ATTRIBUTES ///
    
    /**
     * The {@link Scanner <code>Scanner</code>} implementation used by this
     * class to read {@link Character <code>chars</code>} from the
     * {@code source}.
     */
    protected final Scanner scanner;
    private final String comInitiatorSL;
    private final EnclosedPattern comPatternML;
    private boolean hadLexError = false;
    
    /// CONSTRUCTORS ///
    
    /**
     * Creates a new instance of the {@link AbstractCodeLexer} for a given
     * {@link String} {@code source}. This constructor also allows the
     * programmer to specify the initiator pattern for a singline comment, and
     * the initiator/terminator pattern for a multiline comment.
     * 
     * @param source         the source {@link String} to read from
     * @param comInitiatorSL the initiator pattern for a singleline comment
     *                       _(e.g. {@code //})_
     * @param comPatternML   the initiator/terminator {@link EnclosedPattern
     *                       pattern} for a multiline comment
     * @see EnclosedPattern
     */
    public AbstractCodeLexer(
            String source, String comInitiatorSL, EnclosedPattern comPatternML
    )
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = EnclosedPattern.copyOf(comPatternML);
    }
    
    /**
     * Creates a new instance of the {@link AbstractCodeLexer} for a given
     * {@link String} {@code source}. This constructor also allows the
     * programmer to specify the initiator pattern for a singleline comment.
     * <p>
     * The default Java-style pattern <code>/* ... {@literal *}/</code> is used
     * for a multiline comment.
     * 
     * @param source         the source {@link String} to read from
     * @param comInitiatorSL the initiator pattern for a singleline comment
     *                       _(e.g. {@code //})_
     */
    public AbstractCodeLexer(String source, String comInitiatorSL)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = comInitiatorSL;
        this.comPatternML = new EnclosedPattern("/*", "*/");
    }
    
    /**
     * Creates a new instance of the {@link AbstractCodeLexer} for a given
     * {@link String} {@code source}.
     * <p>
     * The default Java-style pattern {@code //} is used for a singleline
     * comment. The default Java-style pattern <code>/* ... {@literal *}/</code>
     * is used for a multiline comment.
     * 
     * @param source the source {@link String} to read from
     */
    public AbstractCodeLexer(String source)
    {
        this.scanner = new Scanner(source);
        this.comInitiatorSL = "//";
        this.comPatternML = new EnclosedPattern("/*", "*/");
    }
    
    /// METHODS ///
    
    /**
     * {@inheritDoc} This implementation calls the {@link #skip()} method
     * iteratively until it returns false to find the start of the next lexeme.
     * It then calls the {@link #lexToken()} method to scan that lexeme and
     * convert it to a {@link Token}.
     * <p>
     * This process is repeated until the end of the {@code source} is reached.
     * The lexing loop ends early if {@link #error()} is called.
     * 
     * @see #lexToken()
     * @see #skip()
     * @see #error()
     */
    @Override public final Collection<Token> lex()
    {
        final List<Token> tokens = new ArrayList<>();
        
        while (!this.scanner.isAtEnd() && !this.hadLexError)
        {
            
            while (skip())
                ;
            
            final int currentIndex = this.scanner.getIndex();
            final Token token = lexToken();
            final int nextIndex = this.scanner.getIndex();
            final String lexeme = token.getLexeme();
            
            if (currentIndex == nextIndex && !lexeme.isEmpty())
                this.scanner.incrementIndex(lexeme.length());
            
            tokens.add(token);
        }
        
        return tokens;
    }
    
    /**
     * Generates a {@link Token} for the current lexeme pointed to by the
     * current index of the {@link #scanner}.
     * <p>
     * This method is called iteratively by the {@link #lex()} method.
     * 
     * @apiNote The programmer may assume that the {@link #scanner} will be
     *          pointing to a non-comment, non-whitespace character at the
     *          beginning of the next lexeme when this method is called.
     * @apiNote It does not matter if the index of the {@link #scanner} is
     *          updated to the next index, so long as this next index is correct
     *          _(i.e.
     *          {@code nextIndex == scanner.incrementIndex(lexeme.length())})_
     * @apiNote For lexemes spanning multiple lines, it is expected that the
     *          programmer increments the current line of the Scanner
     *          accordingly.
     * 
     * @return a new {@link Token} for the next lexeme in the {@code source}
     * @see #lex()
     */
    protected abstract Token lexToken();
    
    /**
     * Checks if the current index of the {@link #scanner} matches the pattern
     * for whitespace, a singleline comment, or a multiline comment and skips it
     * if so.
     * 
     * @apiNote This method skips only one of the possible patterns. i.e. if
     *          there was a block of multiple consecutive skippable sections,
     *          only the first would be skipped. To skip consecutive blocks, it
     *          is recommended to call this method in a conditional loop until
     *          it returns {@code false}.
     *          
     * @return {@code true} if any of the patterns were skipped
     * @see #skipWhiteSpace()
     * @see #skipCommentSL()
     * @see #skipCommentML()
     */
    protected final boolean skip()
    { return skipWhiteSpace() || skipCommentSL() || skipCommentML(); }
    
    /**
     * Checks if the current index of the {@link #scanner} matches a whitespace
     * pattern and skips it if so.
     * 
     * @return {@code true} if a whitespace pattern was found and skipped
     * @see #skip()
     */
    protected boolean skipWhiteSpace()
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
    
    /**
     * Checks if the current index of the {@link #scanner} matches a singleline
     * comment pattern and skips it if so.
     * 
     * @apiNote This method only skips a single comment. i.e. If there were
     *          multiple commented lines in a row, only the first would be
     *          skipped.
     * 
     * @return {@code true} if a singleline comment was found and skipped
     * @see #skip()
     */
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
    
    /**
     * Checks if the current index of the {@link #scanner} matches a multiline
     * comment pattern and skips it if so.
     * 
     * @return {@code true} if a multiline comment was found and skipped
     * @see #skip()
     */
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
    
    /**
     * Signals that an error has occurred whilst lexing. If so, the lexing
     * process will halt.
     * 
     * @see #lex()
     */
    protected final void error()
    { this.hadLexError = true; }
    
    /**
     * {@inheritDoc}
     */
    @Override public final boolean hasErrorOccurred()
    { return this.hadLexError; }
    
    /**
     * {@inheritDoc}
     */
    @Override public final int getLastLine()
    { return this.scanner.getLine(); }
}