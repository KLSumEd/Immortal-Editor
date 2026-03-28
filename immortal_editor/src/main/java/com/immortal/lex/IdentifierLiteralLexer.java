package com.immortal.lex;

import com.immortal.lex.checker.LexChecker;
import com.immortal.scan.Scanner;

public class IdentifierLiteralLexer extends Lexer
{
    private final LexChecker checker;

    public IdentifierLiteralLexer(Scanner scanner, LexChecker checker) 
    { 
        super(scanner); 
        this.checker = checker;
    }

    @Override public String lex() 
    {
        String lexeme = "";
        int lengthCounter = 0;
        int startingIndex = scanner.getIndex();

        while (checker.checkLex(lexeme))
        {
            lexeme += scanner.peekIndex(startingIndex + lengthCounter);
            lengthCounter++;
        }

        lexeme = lexeme.substring(0, lengthCounter - 1);
        scanner.incrementIndex(lengthCounter);
        return lexeme;
    }
    
}
