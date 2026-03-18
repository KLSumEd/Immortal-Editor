package com.immortal.lex;

import com.immortal.lex.checker.LexChecker;
import com.immortal.scan.Scanner;

public class IdentifierLiteralLexer extends Lexer
{
    public IdentifierLiteralLexer(Scanner scanner, LexChecker checker) { super(scanner, checker); }

    @Override public String generateLexeme() 
    {
        Scanner scanner = this.getScanner();
        LexChecker checker = this.getLexChecker();
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
