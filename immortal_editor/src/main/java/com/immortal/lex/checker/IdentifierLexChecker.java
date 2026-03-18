package com.immortal.lex.checker;

public class IdentifierLexChecker extends IdentifierLiteralLexChecker
{
    // An Identifier pattern should be as follows:
    // - The beginning of the string
    // - Any single character between 'A-Z', or 'a-z', or an underscore ('_')
    // - Followed by 0 or more '\w' (or 'word') characters (i.e. alphanumeric/underscore)
    // - The end of the string
    private static final String REGEX = "^[A-Za-z_]\\w*$";  

    public IdentifierLexChecker() 
    {
        super(REGEX);
    }
}
