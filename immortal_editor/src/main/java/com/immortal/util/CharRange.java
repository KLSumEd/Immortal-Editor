package com.immortal.util;

public class CharRange implements CharGroup
{
    private final char start;
    private final char end;

    public CharRange(char start, char end)
    {
        if (start <= end)
        {
            this.start = start;
            this.end = end;
        }
        else
        {
            this.start = end;
            this.end = start;
        }
    }

    @Override public final boolean checkChar(char c)
    { return this.start < c && c < this.end; }

    public final char getBeginChar()
    { return this.start; }

    public final char getEndChar()
    { return this.end; }

    public static CharRange copyOf(CharRange range)
    { return new CharRange(range.getBeginChar(), range.getEndChar()); }
}
