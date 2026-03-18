package com.immortal.lex;

import com.immortal.lex.checker.SingleCharLexChecker;
import com.immortal.lex.checker.SingleDoubleCharLexChecker;
import com.immortal.scan.Scanner;

public abstract class Lexer 
{
    private final Scanner scanner;
    private static boolean hadLexError = false;
    
    public Lexer(Scanner scanner) { this.scanner = scanner; }
    
    public abstract String generateLexeme();

    protected String lex()
    {
        String result = "";


        while (!this.scanner.isAtEnd())
        {
            char c = this.scanner.peek();
            result += c;
            SingleCharLexChecker singleCharLexChecker = new SingleCharLexChecker();
            SingleDoubleCharLexChecker singleDoubleCharLexChecker = new SingleDoubleCharLexChecker();
            if (singleCharLexChecker.checkLex(result))
            {
                
            }
            else if (singleDoubleCharLexChecker.checkLex(result))
            {
                finiteLookahead(c);
            }

        }

        return result;
    }

    private void finiteLookahead(int dist) 
    {
        for (int i = 0; i < dist; i++)
        {

        }
    }

    protected Scanner getScanner() { return this.scanner; }

    protected static void error() { hadLexError = true; }
    public static boolean hasErrorOccurred() { return hadLexError; }
}
