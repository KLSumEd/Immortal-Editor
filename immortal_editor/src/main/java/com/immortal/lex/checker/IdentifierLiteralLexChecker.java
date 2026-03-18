package com.immortal.lex.checker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public abstract class IdentifierLiteralLexChecker implements LexChecker 
{
    private final Pattern lexPattern;

    protected IdentifierLiteralLexChecker(String regex, int flags) 
    {
        if (!checkRegex(regex)) 
        {
            throw new PatternSyntaxException("Invalid Pattern for regex. Must begin with '^' and end with '$'", regex, 0);
        }

        this.lexPattern = Pattern.compile(regex, flags);
    }

    protected IdentifierLiteralLexChecker(String regex) 
    {
        if (!checkRegex(regex)) 
        {
            throw new PatternSyntaxException("Invalid Pattern for regex. Must begin with '^' and end with '$'", regex, 0);
        }

        this.lexPattern = Pattern.compile(regex);
    }

    private boolean checkRegex(String regex)
    {
        int length = regex.length();
        boolean valid = true;
        if (regex.charAt(0) != '^') { valid = false; }
        else if (regex.charAt(length-1) != '$') { valid = false; }

        return valid;
    }

    @Override public boolean checkLex(String lexeme) 
    {
        Matcher matcher = this.lexPattern.matcher(lexeme);
        boolean valid = matcher.matches();

        return valid;
    }
}