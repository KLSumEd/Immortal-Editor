package com.immortal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CharRangeGroup
{
    private final List<CharRange> charRanges;
    private final Set<Character> extraChars;

    @SafeVarargs public CharRangeGroup(Collection<CharRange> charRanges, Character... extraChars)
    {
        this.charRanges = List.copyOf(charRanges);
        this.extraChars = Set.of(extraChars);
    }

    public CharRangeGroup(Collection<CharRange> charRanges, Collection<Character> extraChars)
    {
        this.charRanges = List.copyOf(charRanges);
        this.extraChars = Set.copyOf(extraChars);
    }

    public CharRangeGroup(Collection<CharRange> charRanges)
    {
        this.charRanges = List.copyOf(charRanges);
        this.extraChars = new HashSet<>();
    }

    public CharRangeGroup()
    {
        this.charRanges = new ArrayList<>();
        this.extraChars = new HashSet<>();
    }

    public final boolean checkChar(char c)
    {
        boolean result = this.extraChars.contains(c);

        if (!result)
        {
            for (CharRange cr : this.charRanges)
            {
                result = cr.checkChar(c);
                if (result) break;
            }

        }

        return result;
    }

    public List<CharRange> getCharRanges()
    { return this.charRanges; }

    public Set<Character> getExtraChars()
    { return this.extraChars; }
}
