package com.immortal.lex;

import com.immortal.lex.checker.LexChecker;
import com.immortal.parse.Parser;
import com.immortal.scan.Scanner;

public class SingleDoubleCharLexer extends Lexer 
{
    public SingleDoubleCharLexer(Scanner scanner, LexChecker checker)
    {
        super(scanner, checker);
    }

    @Override public String generateLexeme() 
    {
        Scanner scanner = this.getScanner();
        LexChecker checker = this.getLexChecker();
        
        String lexeme = "";
        
        for (int i = 0; i < 2; i++)
        {
            char c = scanner.peek();
            if (c != '\0')
            {
                lexeme += c;
                if (!checker.checkLex(lexeme)) { break; }
            }
        }

        if (!checker.checkLex(lexeme))
        {
            Parser.error(scanner.getLine(), "Unrecognised symbol: \"" + lexeme + "\"");
            lexeme = "";
        } else
        {

        }

        return lexeme;
    } 
}
