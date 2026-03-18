package com.immortal.lex.checker;

import java.util.regex.Pattern;

public class MultiLineStringLexChecker extends IdentifierLiteralLexChecker
{
    // A Multi-line String Literal Pattern should be as follows:
    // - The beginning of the lexeme string
    // - 3 '"' characters
    // - Zero or more internal characters (Match Lazily i.e. as few as possible; Including newline characters)
    // - 3 '"' characters not preceded by a '\'
    // - The end of the lexeme string
    private final static String REGEX = "^\\\"{3}.*?(?<!\\\\)\\\"{3}$"; // MUST be compiled with DOTALL Pattern flag

    protected MultiLineStringLexChecker() 
    {
        super(REGEX, Pattern.DOTALL);
    }   
}
