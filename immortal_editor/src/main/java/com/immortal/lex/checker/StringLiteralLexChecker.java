package com.immortal.lex.checker;

public class StringLiteralLexChecker extends IdentifierLiteralLexChecker
{
    // A Single-line String Literal Pattern should be as follows:
    // - The beginning of the lexeme string 
    // - A '"' character
    // - Zero or more internal characters (MATCH LAZILY, i.e. as few as possible)
    // - A '"' character not preceded by a '\'
    // - The end of the lexeme string
    private static final String REGEX = "^\\\".*?(?<!\\\\)\\\"$";

    protected StringLiteralLexChecker() 
    {
        super(REGEX);
    }
}
