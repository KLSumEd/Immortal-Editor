package com.immortal.lex;

import com.immortal.scan.Scanner;

public abstract class Lexer 
{
    private final Scanner scanner;
    
    public Lexer(Scanner scanner) { this.scanner = scanner; }
    
    protected abstract String generateLexeme();

    protected Scanner getScanner() { return this.scanner; }
}
