package com.immortal.lex;

import com.immortal.scan.Scanner;

public class SingleCharLexer extends Lexer
{

    public SingleCharLexer(Scanner scanner, LexChecker checker) 
    {
        super(scanner, checker);
    }

    @Override public String generateLexeme() 
    {
        String lexeme = String.valueOf(getScanner().advance());
        lexeme = this.getLexChecker().checkLex(lexeme) ? lexeme : "";
        return lexeme;
    }

    // TODO: 06/03/26 16:10 - Move into new class to follow SRP
    // private boolean checkLexeme(String lexeme, List<Token> tokenList)
    // {
    //     boolean isValid = false;
    //     for (Token token : tokenList)
    //     {
    //         if (lexeme.equals(token.getLexeme())) { isValid = true; }
    //     }

    //     return isValid;
    // }
}
