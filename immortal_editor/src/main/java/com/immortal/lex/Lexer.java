package com.immortal.lex;

import com.immortal.scan.Scanner;

public abstract class Lexer 
{
    private final Scanner scanner;
    private final LexChecker checker;
    
    public Lexer(Scanner scanner, LexChecker checker) { this.scanner = scanner; this.checker = checker; }
    
    public abstract String generateLexeme();

    protected Scanner getScanner() { return this.scanner; }
    protected LexChecker getLexChecker() { return this.checker; }
}
