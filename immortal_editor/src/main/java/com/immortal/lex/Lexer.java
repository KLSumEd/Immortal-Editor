package com.immortal.lex;

import java.util.Collection;

import com.immortal.scan.Scanner;
import com.immortal.tokens.Token;
import com.immortal.tokens.Tokenizer;

public class Lexer 
{
    private boolean hadLexError = false;
    private final Scanner scanner;
    private final Tokenizer tokenizer;

    public Lexer(Scanner scanner, Tokenizer tokenizer) 
    {
        this.scanner = scanner;
        this.tokenizer = tokenizer;
    }

    public Token lex() throws IndexOutOfBoundsException
    {
        scanner.advance();
        throw new UnsupportedOperationException();
    }

    public Collection<Token> lexExpression()
    {
        throw new UnsupportedOperationException();
    }

    public Collection<Token> lexAll() 
    {
        throw new UnsupportedOperationException();
    }

    protected void error() { this.hadLexError = true; }
    public boolean hasErrorOccurred() { return this.hadLexError; }
    public boolean hasNext() 
    {
        return false;
    }
}
