package com.immortal.tokens.types;

public enum CharRange 
{
    UPPER('A', 'Z'),
    LOWER('a', 'z'),
    DIGIT('0', '9');

    private final char begin;
    private final char end;

    private CharRange(char begin, char end) 
    {
        this.begin = begin;
        this.end = end;
    }

    public char getBeginChar() { return this.begin; }
    public char getEndChar() { return this.end; }
}
