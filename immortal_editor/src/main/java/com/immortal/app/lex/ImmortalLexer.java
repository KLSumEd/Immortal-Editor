package com.immortal.app.lex;

import com.immortal.app.lex.state.LexState;
import static com.immortal.app.lex.state.LexState.ERROR;
import static com.immortal.app.lex.state.LexState.INITIAL;
import static com.immortal.app.lex.state.LexState.TERMINATED;
import com.immortal.app.tokens.NullToken;
import com.immortal.app.tokens.Token;

/**
 * An concrete implementation of the {@link Lexer} interface built on the
 * {@link AbstractCodeLexer} abstract class.
 * <p>
 * This implementation only provides bodies to the abstract methods
 * {@link AbstractCodeLexer#lexToken() lexToken()} and
 * {@link Lexer#getErrorMessage() getErrorMessage()}, relying primarily upon the
 * superclass for core functionality.
 * <p>
 * The {@link #lexToken()} method implementation relies on a Finite State
 * Machine defined in the {@link LexState} class. The Lexer refers to its
 * {@code state} for the logic of deciding which {@link Token} to return.
 * 
 * @author <a href="https://github.com/KLSumEd">KLSumEd</a>
 * @see AbstractCodeLexer
 * @see LexState
 */
public final class ImmortalLexer extends AbstractCodeLexer
{
    private String errMsg = "";
    
    /**
     * Creates a new instance of the {@link ImmortalLexer} for a given
     * {@link String} {@code source}.
     * 
     * @param source the {@link String} source to read from.
     */
    public ImmortalLexer(String source)
    { super(source); }
    
    /**
     * {@inheritDoc}
     * <p>
     * This implementation uses a Finite State Machine based upon the
     * {@link LexState} class. The initial {@code state} of the Lexer is
     * {@link LexState#INITIAL}. The FSM transitions between states using the
     * {@link LexState#getNextState(String lexeme)} method.
     * <p>
     * The {@link LexState} implementation requires that the Lexer reads the
     * source character-by-character and checks its state after each one. The
     * Lexer knows it has reached the end if its {@code state} is
     * {@link LexState#TERMINATED}. It may also end early if its state is
     * {@link LexState#ERROR}.
     * <p>
     * After the {@code state} is {@link LexState#TERMINATED TERMINATED}, the
     * previous {@code state} determines the {@link Token} returned via the
     * {@link LexState#tokenize(String lexeme, int line)}.
     * <p>
     * If an {@link IllegalArgumentException} occurs with the
     * {@link LexState#getNextState(String lexeme)} and
     * {@link LexState#tokenize(String lexeme, int line)} methods, the Lexer
     * fails quietly and calls the {@link AbstractCodeLexer#error() error()}
     * method and returns a {@link NullToken}.
     * 
     * @return a {@link Token} corresponding to the scanned lexeme
     * @see LexState
     */
    @Override protected Token lexToken()
    {
        LexState state = INITIAL;
        LexState prevState = state;
        final int startIndex = this.scanner.getIndex();
        int index = startIndex;
        String lexBuilder = "";
        
        while (state != TERMINATED && state != ERROR)
        {
            prevState = state;
            lexBuilder += this.scanner.peekIndex(index);
            
            try
            {
                state = state.getNextState(lexBuilder);
            }
            catch (IllegalArgumentException e)
            {
                state = ERROR;
                this.errMsg = e.getMessage();
            }
            
            index++;
        }
        
        final Token token;
        
        if (state == TERMINATED)
        {
            final int dist = index - startIndex - 1;
            final String lexeme = this.scanner.advance(dist);
            Token resultToken = new NullToken(this.scanner.getLine());
            
            try
            {
                resultToken = prevState.tokenize(lexeme,
                        this.scanner.getLine());
            }
            catch (final IllegalArgumentException e)
            {
                error();
                this.errMsg = e.getMessage();
            }
            finally
            {
                token = resultToken;
            }
        }
        else
        {
            error();
            token = new NullToken(this.scanner.getLine());
        }
        
        return token;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override public final String getErrorMessage()
    { return this.errMsg; }
}