package com.immortal.app.scan;

public final class Scanner
{
    private final String src;
    private int index = 0;
    private int line = 0;
    
    public Scanner(String src)
    { this.src = src; }
    
    public String advance(int dist) throws IndexOutOfBoundsException
    {
        String s = "";
        
        for (int i = 0; i < dist; i++)
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
    
    public String advanceUntil(String terminator, boolean inclusive)
            throws IndexOutOfBoundsException
    {
        int termLen = terminator.length();
        String s = "";
        int termPointer = 0;
        
        while (termPointer < termLen)
        {
            char c = advance();
            s += c;
            termPointer = c == terminator.charAt(termPointer) ? termPointer + 1
                    : 0;
        }
        
        if (!inclusive)
        {
            s = s.substring(0, s.length() - termLen);
            int numLinesReversed = 0;
            
            for (char c : terminator.toCharArray())
            { if (c == '\n') numLinesReversed++; }
            
            this.line -= numLinesReversed;
            this.index -= termLen;
        }
        
        return s;
    }
    
    public String advanceUntil(String terminator)
            throws IndexOutOfBoundsException
    { return advanceUntil(terminator, false); }
    
    public String advanceUntil(char terminator, boolean inclusive)
            throws IndexOutOfBoundsException
    { return advanceUntil(String.valueOf(terminator), inclusive); }
    
    public String advanceUntil(char terminator) throws IndexOutOfBoundsException
    { return advanceUntil(String.valueOf(terminator), false); }
    
    public String peek(int dist)
    {
        String s = "";
        
        for (int i = 0; i < dist; i++)
        {
            
            if (isAtEnd(this.index + i))
            {
                s += '\0';
                break;
            }
            else
            {
                s += this.src.charAt(this.index + i);
            }
            
        }
        
        return s;
    }
    
    public char peek()
    { return peek(1).charAt(0); }
    
    public char peekIndex(int index)
    {
        char c = isAtEnd(index) ? '\0' : this.src.charAt(index);
        return c;
    }
    
    public String peekUntil(String terminator, boolean inclusive)
    {
        int termLen = terminator.length();
        String s = "";
        int currentIndex = this.index;
        int termPointer = 0;
        
        while (termPointer < termLen && !isAtEnd(currentIndex))
        {
            char c = peekIndex(currentIndex);
            s += c;
            currentIndex++;
            termPointer = c == terminator.charAt(termPointer) ? termPointer + 1
                    : 0;
        }
        
        if (!inclusive && !isAtEnd(currentIndex))
        { s = s.substring(0, s.length() - termLen); }
        
        return s;
    }
    
    public String peekUntil(String terminator)
    { return peekUntil(terminator, false); }
    
    public String peekUntil(char terminator, boolean inclusive)
    { return peekUntil(String.valueOf(terminator), inclusive); }
    
    public String peekUntil(char terminator)
    { return peekUntil(String.valueOf(terminator), false); }
    
    public int getIndex()
    { return this.index; }
    
    public int getLine()
    { return this.line; }
    
    public void setIndex(int index)
    { this.index = index; }
    
    public void setLine(int line)
    { this.line = line; }
    
    public void incrementIndex(int increment)
    { this.index += increment; }
    
    public void incrementIndex()
    { incrementIndex(1); }
    
    public void incrementLine(int increment)
    { this.line += increment; }
    
    public void incrementLine()
    { incrementLine(1); }
    
    public boolean isAtEnd()
    { return this.index >= this.src.length(); }
    
    public boolean isAtEnd(int index)
    { return index >= this.src.length(); }
}
