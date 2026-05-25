package com.immortal.app.lex;

import java.util.Collection;

import com.immortal.app.tokens.Token;

/**
 * An interface providing structure for a Lexical Analyser implementation.
 * <p>
 * Methods are provided for analysing to a {@link Collection} of {@link Token
 * Tokens}, and error handling.
 * 
 * @author <a href="https://github.com/KLSumEd">KLSumEd</a>
 */
public interface Lexer
{
    /**
     * Scans a source to a {@link Collection} of {@link Token Tokens}.
     * Implementations of this interface must decide the logic of how to scan
     * for lexemes and tokenize them.
     * <p>
     * The {@link Token Token} interface provides a means of storing the lexemes
     * and information about them and where they were found in the source.
     * 
     * @return a {@link Collection} of {@link Token {@code Tokens}} found in the
     *         source
     * @see Token
     */
    public Collection<Token> lex();
    
    /**
     * Checks if an error has occurred during the analysis — primarily in the
     * {@link #lex()} method.
     * 
     * @return {@code true} if an error occurred whilst lexing
     */
    public boolean hasErrorOccurred();
    
    /**
     * Returns any error message generated during lexical analysis.
     * 
     * @return a {@link String} message generated if an error occurred
     * @return an empty {@link String} {@code ""} if no error occurred
     */
    public String getErrorMessage();
    
    /**
     * Returns the last line reached in the last call to the {@link #lex()}
     * method.
     * 
     * @return an {@code int} line number {@code > 0}
     */
    public int getLastLine();
}
