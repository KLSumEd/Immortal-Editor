package com.immortal.lex;

import com.immortal.tokens.Token;
import com.immortal.tokens.Tokenizer;

public final class ImmortalLexer extends AbstractLexer
{
    public ImmortalLexer(String source)
    { super(source); }

    @Override protected Token lexToken()
    {
        final String firstChar = this.scanner.peek(1);
        LexState state = LexState.NULL;
        int index = this.scanner.getIndex();
        String lexeme = firstChar;

        while (!state.equals(LexState.NULL))
        {
            index++;
            char nextChar = this.scanner.peekIndex(index);
            lexeme += nextChar;
            switch (state)
            {
                case INITIAL ->
                    {

                    }
                default ->
                    {}
            }

        }

        // TODO: @KLSumEd — Remove the following — By: When implemented
        throw new UnsupportedOperationException("Unimplemented method 'lexTokenLoop'");
    }

    private static enum LexState
    {
        INITIAL(null), WORD(null), NUM(null), ID(null), STRING(null), FLOAT(null), SYMBOL(null),
        NULL(null);

        private final Tokenizer tokenizer;

        private LexState(Tokenizer tokenizer)
        { this.tokenizer = tokenizer; }

        public boolean checkLex(String lexeme)
        { return this.tokenizer.checkLex(lexeme); }

        public Token tokenize(String lexeme, int line)
        { return this.tokenizer.tokenize(lexeme, line); }
    }
}