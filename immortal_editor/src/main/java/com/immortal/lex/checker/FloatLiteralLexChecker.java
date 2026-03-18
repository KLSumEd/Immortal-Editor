package com.immortal.lex.checker;

public class FloatLiteralLexChecker extends IdentifierLiteralLexChecker
{
    private static final String REGEX = "^(?:(\\d+\\.\\d*)|(\\d*\\.\\d+))$";
    
    public FloatLiteralLexChecker() 
    {
        super(REGEX);
    }
}
