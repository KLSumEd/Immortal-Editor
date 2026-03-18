package com.immortal.lex.checker;

public class IntLiteralLexChecker extends IdentifierLiteralLexChecker
{
    
    public static final String REGEX = "^\\d+$";

    public IntLiteralLexChecker() 
    {
        super(REGEX);
    }
}
