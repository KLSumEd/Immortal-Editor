package com.immortal.lex;

import com.immortal.lex.checker.KnownLexChecker;
import com.immortal.scan.Scanner;

public class KnownLexer extends Lexer
{
    protected final KnownLexChecker checker;

    public KnownLexer(Scanner scanner, KnownLexChecker checker) 
    {
        super(scanner);
        this.checker = checker;
    }

    @Override public String lex() 
    {
        throw new UnsupportedOperationException("Unimplemented method 'lex'");
    }
}