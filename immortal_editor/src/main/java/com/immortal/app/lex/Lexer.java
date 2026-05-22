package com.immortal.app.lex;

import java.util.Collection;

import com.immortal.app.tokens.Token;

public interface Lexer
{
    public Collection<Token> lex();
    public boolean hasErrorOccurred();
    public String getErrorMessage();
    public int getLastLine();
}
