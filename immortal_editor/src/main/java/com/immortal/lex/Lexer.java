package com.immortal.lex;

import java.util.Collection;

import com.immortal.tokens.Token;

public interface Lexer
{
    public Collection<Token> lex();
    public boolean hasErrorOccurred();
    public String getErrorMessage();
    public int getLastLine();
}
