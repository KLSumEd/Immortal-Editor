package com.immortal.lex;

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

        while (checker.checkLex(lexeme))
        {
            lexeme += scanner.peekIndex(scanner.getIndex() + lengthCounter);
            lengthCounter++;
        }

        lexeme = lexeme.substring(0, lengthCounter);
        return lexeme;
    }
    
}
