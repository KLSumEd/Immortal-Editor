package com.immortal.lex;

import com.immortal.lex.checker.LexChecker;
import com.immortal.lex.checker.SingleCharLexChecker;
import com.immortal.scan.Scanner;

public class SingleCharLexer extends Lexer
{
    public SingleCharLexer(Scanner scanner, SingleCharLexChecker checker) 
    {
        super(scanner, checker);
    }

    @Override public String generateLexeme() 
    {
        Scanner scanner = this.getScanner();
        LexChecker checker = this.getLexChecker();

        String lexeme = "" + scanner.peek();
        
        for (int i = 0; i < 2; i++)
        {
            char c = scanner.peek();
            if (c == '\0')
            {
                break;
            }
            else
            {
                lexeme += c;
                if (checker.checkLex(lexeme))
                {
                    
                }
            }
        }
        
        return lexeme;
    }
}
