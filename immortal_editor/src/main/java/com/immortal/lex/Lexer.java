package com.immortal.lex;

import com.immortal.scan.Scanner;

public abstract class Lexer 
{
    private final Scanner scanner;
    private static boolean hadLexError = false;
    
    public Lexer(Scanner scanner) { this.scanner = scanner; }
    
    public abstract String generateLexeme();

    protected String lex()
    {
        boolean isLexing = true;
        String result = "";

        while (isLexing)
        {
            if (this.scanner.isAtEnd())
            {
                isLexing = false;
            }
            else
            {
                char c = this.scanner.peek();
                result += c;
            }
        }

        return result;
    }

    protected static void error() { hadLexError = true; }
    public static boolean hasErrorOccurred() { return hadLexError; }
}
