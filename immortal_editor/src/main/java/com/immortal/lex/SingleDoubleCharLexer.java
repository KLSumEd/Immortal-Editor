package com.immortal.lex;

import com.immortal.scan.Scanner;

public class SingleDoubleCharLexer extends Lexer {

    public SingleDoubleCharLexer(Scanner scanner, LexChecker checker)
    {
        super(scanner, checker);
    }

    @Override public String generateLexeme() 
    {
        Scanner scanner = this.getScanner();
        LexChecker checker = this.getLexChecker();
        
        String lexeme;
        
        lexeme = scanner.peek(2);
        lexeme = checker.checkLex(lexeme) ? lexeme : "";

        return lexeme;
    }
    
}
