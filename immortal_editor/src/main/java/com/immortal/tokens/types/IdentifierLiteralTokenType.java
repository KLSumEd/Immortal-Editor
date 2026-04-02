package com.immortal.tokens.types;

import java.util.List;
import java.util.Set;

public enum IdentifierLiteralTokenType implements TokenType
{
    IDENTIFIER(new IdentifierPatternChecker()),
    STR(new StringLiteralPatternChecker()),
    INT(new IntLiteralPatternChecker()),
    FLOAT(new FloatLiteralPatternChecker());

    final IdentifierLiteralPatternChecker patternChecker;

    private IdentifierLiteralTokenType(IdentifierLiteralPatternChecker patternChecker) 
    {
        this.patternChecker = patternChecker;
    }

    private CharRangeGroup getCharRangeGroup(String lexeme) 
    { 
        return this.patternChecker.getCharRangeGroup(lexeme); 
    }

    public static IdentifierLiteralTokenType getTokenType(String lexeme) 
    {
        throw new UnsupportedOperationException("Unimplemented method 'getTokenType'");
    }
}

interface IdentifierLiteralPatternChecker
{
    public CharRangeGroup getCharRangeGroup(String lexeme);
}

class IdentifierPatternChecker implements IdentifierLiteralPatternChecker
{
    private static final CharRangeGroup ID_HEAD = new CharRangeGroup(
        List.of(CharRange.UPPER, CharRange.LOWER), Set.of('_')
    );
    private static final CharRangeGroup ID_TAIL = new CharRangeGroup(
        List.of(CharRange.UPPER, CharRange.LOWER, CharRange.DIGIT), Set.of('_')
    );

    @Override public CharRangeGroup getCharRangeGroup(String lexeme) 
    {
        CharRangeGroup validGroup = ID_HEAD;

        if (lexeme.length() > 1) { validGroup = ID_TAIL; }

        return validGroup;
    }
}

class StringLiteralPatternChecker implements IdentifierLiteralPatternChecker
{
    private static final CharRangeGroup STRING_HEAD_TAIL = new CharRangeGroup(
        List.of(), Set.of('"')
    );
    private static final CharRangeGroup STRING_BODY = new CharRangeGroup(
        null, Set.of()
    );

    @Override public CharRangeGroup getCharRangeGroup(String lexeme) 
    {
        CharRangeGroup validGroup = STRING_BODY;

        if (lexeme.length() > 0 && lexeme.charAt(lexeme.length() - 1) == '"')
        { 
            validGroup = STRING_HEAD_TAIL; 
        }

        return validGroup;
    }
}

class IntLiteralPatternChecker implements IdentifierLiteralPatternChecker
{
    private static final CharRangeGroup NUM = new CharRangeGroup(
        List.of(CharRange.DIGIT), Set.of()
    );

    @Override public CharRangeGroup getCharRangeGroup(String lexeme) 
    {
        CharRangeGroup validGroup = NUM;
        return validGroup;
    }
}

class FloatLiteralPatternChecker implements IdentifierLiteralPatternChecker
{
    private static final CharRangeGroup NUM = new CharRangeGroup(
        List.of(CharRange.DIGIT), Set.of()
    );

    private static final CharRangeGroup FLOAT = new CharRangeGroup(
        List.of(CharRange.DIGIT), Set.of('.')
    );

    @Override public CharRangeGroup getCharRangeGroup(String lexeme) 
    {
        CharRangeGroup validGroup = FLOAT;
        for (char c : lexeme.toCharArray())
        {
            if (c == '.')
            {
                validGroup = NUM;
            }
        }
        return validGroup;
    }
}