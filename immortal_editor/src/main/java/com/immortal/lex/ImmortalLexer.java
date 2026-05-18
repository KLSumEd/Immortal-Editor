package com.immortal.lex;

import com.immortal.lex.state.LexState;
import static com.immortal.lex.state.LexState.ERROR;
import static com.immortal.lex.state.LexState.INITIAL;
import static com.immortal.lex.state.LexState.TERMINATED;
import com.immortal.tokens.NullToken;
import com.immortal.tokens.Token;

public final class ImmortalLexer extends AbstractLexer
{
    private String errMsg = "";
    
    public ImmortalLexer(String source)
    { super(source); }
    
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
            token = prevState.tokenize(lexeme, this.scanner.getLine());
        }
        else
        {
            error();
            token = new NullToken(this.scanner.getLine());
        }
        
        return token;
    }
    
    @Override public final String getErrorMessage()
    { return this.errMsg; }
}