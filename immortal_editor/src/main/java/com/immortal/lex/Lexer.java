package com.immortal.lex;

import com.immortal.scan.Scanner;

public abstract class Lexer 
{
    private boolean hadLexError = false;
    protected final Scanner scanner;

    public Lexer(Scanner scanner) 
    {
        this.scanner = scanner;
    }

    public abstract String lex();

    protected void error() { this.hadLexError = true; }
    public boolean hasErrorOccurred() { return this.hadLexError; }
}
