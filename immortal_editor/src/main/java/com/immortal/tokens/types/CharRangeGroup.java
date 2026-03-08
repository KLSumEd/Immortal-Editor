package com.immortal.tokens.types;

import java.util.List;
import java.util.Set;

public class CharRangeGroup 
{
    private final List<CharRange> charRanges;
    private final Set<Character> extraChars;

    public CharRangeGroup(List<CharRange> charRanges, Set<Character> extraChars) 
    {
        this.charRanges = charRanges;
        this.extraChars = extraChars;
    }

    public List<CharRange> getCharRanges() { return this.charRanges; }
    public Set<Character> getExtraChars() { return this.extraChars; }
}
