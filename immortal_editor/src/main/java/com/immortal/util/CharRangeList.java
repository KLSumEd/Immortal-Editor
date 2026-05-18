package com.immortal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class CharRangeList implements CharGroup
{
    private final List<CharRange> charRanges;
    
    @SafeVarargs public CharRangeList(CharRange... ranges)
    { this.charRanges = new ArrayList<>(Arrays.asList(ranges)); }
    
    public CharRangeList(Collection<CharRange> ranges)
    { this.charRanges = new ArrayList<>(ranges); }
    
    public CharRangeList()
    { this.charRanges = new ArrayList<>(); }
    
    @Override public boolean checkChar(char c)
    {
        boolean found = false;
        
        for (CharRange range : this.charRanges)
        {
            found |= range.checkChar(c);
            if (found) break;
        }
        
        return found;
    }
    
    public final void addCharRange(CharRange range)
    { this.charRanges.add(range); }
    
    @SafeVarargs public final void addCharRanges(CharRange... charRanges)
    { this.charRanges.addAll(Arrays.asList(charRanges)); }
    
    public final void addCharRanges(Collection<CharRange> charRanges)
    { this.charRanges.addAll(charRanges); }
    
    public final List<CharRange> toList()
    { return List.copyOf(this.charRanges); }
    
    public static CharRangeList copyOf(CharRangeList rangeList)
    { return new CharRangeList(rangeList.toList()); }
}
