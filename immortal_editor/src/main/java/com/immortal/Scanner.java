package com.immortal;

public class Scanner
{
    private final String src;
    private int index = 0;
    private int line = 0;

    public Scanner(String src) { this.src = src; }

    public char advance()
    {
        char c = src.charAt(this.index);
        this.index++;
        this.line = c == '\n' ? this.line + 1 : this.line;
        return c;
    }

    public char peekIndex(int index)
    {
        char c = src.charAt(index);
        return c;
    }

    public int getIndex() { return this.index; }
    public int getLine() { return this.line; }
}
