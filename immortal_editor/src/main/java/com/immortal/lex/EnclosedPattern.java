package com.immortal.lex;

public final class EnclosedPattern
{
    private final String initiator;
    private final String terminator;

    public EnclosedPattern(String initiator, String terminator)
    {
        this.initiator = initiator;
        this.terminator = terminator;
    }

    public static EnclosedPattern copyOf(EnclosedPattern p)
    { return new EnclosedPattern(p.getInitiator(), p.getTerminator()); }

    public boolean matchBeginning(String s)
    { return s.startsWith(this.initiator); }

    public boolean matchEnd(String s)
    { return s.startsWith(this.terminator); }

    public boolean match(String s)
    { return matchBeginning(s) && checkSingleTerminated(s); }

    private boolean checkSingleTerminated(String s)
    {
        int firstMatchIndex = s.indexOf(this.terminator, this.initiator.length());
        boolean result = firstMatchIndex > 0
                && firstMatchIndex == s.length() - this.terminator.length();
        return result;
    }

    public String getInitiator()
    { return this.initiator; }

    public String getTerminator()
    { return this.terminator; }
}
