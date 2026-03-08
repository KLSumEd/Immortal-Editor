package com.immortal.scan;

public class Scanner
{
    private final String src;
    private int index = 0;
    private int line = 0;

    public Scanner(String src) { this.src = src; }

    public String advance(int numChars) throws IndexOutOfBoundsException
    {
        String s = "";
        for (int i = 0; i < numChars; i++)
        {
            char c = src.charAt(this.index);
            this.index++;
            this.line = c == '\n' ? this.line + 1 : this.line;
            s += c;
        }
        return s;
    }

    public char advance() throws IndexOutOfBoundsException 
    { return advance(1).charAt(0); }

    public char peekIndex(int index)
    {
        char c = isAtEnd(index) ? '\0' : this.src.charAt(index);
        return c;
    }

    public String peek(int numChars)
    {
        String s = "";
        for (int i = 0; i < numChars; i++)
        {
            if (isAtEnd(this.index + i)) { s += '\0'; break; }
            else {s += this.src.charAt(this.index); }
        }
        return s;
    }

    public char peek() { return peek(1).charAt(0); }

    public int getIndex() { return this.index; }
    public int getLine() { return this.line; }

    public void setIndex(int index) { this.index = index; }
    public void setLine (int line) { this.line = line; }

    public void incrementIndex (int increment) { this.index += increment; }
    public void incrementIndex () { incrementIndex(1); }

    public void incrementLine(int increment) { this.line += increment; }
    public void incrementLine() { incrementLine(1); }

    public boolean isAtEnd() { return this.index >= this.src.length(); }
    public boolean isAtEnd(int index) { return index >= this.src.length(); }
}
