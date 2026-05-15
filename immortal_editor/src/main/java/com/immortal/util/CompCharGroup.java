package com.immortal.util;

import java.util.Collection;

public final class CompCharGroup implements CharGroup
{
    ///// ATTRIBUTES /////

    private final CharSet charSet;
    private final CharRangeList charRanges;

    ///// CONSTRUCTORS /////

    /// Default Constructors ///

    public CompCharGroup()
    {
        this.charSet = new CharSet();
        this.charRanges = new CharRangeList();
    }

    public CompCharGroup(CharRangeList ranges, CharSet set)
    {
        this.charSet = CharSet.copyOf(set);
        this.charRanges = CharRangeList.copyOf(ranges);
    }

    public CompCharGroup(Collection<CharRange> ranges, Collection<Character> chars)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = new CharRangeList(ranges);
    }

    /// Varargs Constructors ///

    @SafeVarargs public CompCharGroup(CharSet set, CharRange... ranges)
    {
        this.charSet = CharSet.copyOf(set);
        this.charRanges = new CharRangeList(ranges);
    }

    @SafeVarargs public CompCharGroup(CharRangeList rangeList, Character... chars)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = CharRangeList.copyOf(rangeList);
    }

    @SafeVarargs public CompCharGroup(Collection<CharRange> ranges, Character... chars)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = new CharRangeList(ranges);
    }

    @SafeVarargs public CompCharGroup(Collection<Character> chars, CharRange... ranges)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = new CharRangeList(ranges);
    }

    @SafeVarargs public CompCharGroup(Character... chars)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = new CharRangeList();
    }

    @SafeVarargs public CompCharGroup(CharRange... ranges)
    {
        this.charSet = new CharSet();
        this.charRanges = new CharRangeList(ranges);
    }

    /// Other Constructors ///

    public CompCharGroup(Collection<CharRange> ranges, CharSet set)
    {
        this.charSet = CharSet.copyOf(set);
        this.charRanges = new CharRangeList(ranges);
    }

    public CompCharGroup(CharRangeList rangeList, Collection<Character> chars)
    {
        this.charSet = new CharSet(chars);
        this.charRanges = CharRangeList.copyOf(rangeList);
    }

    public CompCharGroup(CharSet set)
    {
        this.charSet = CharSet.copyOf(set);
        this.charRanges = new CharRangeList();
    }

    public CompCharGroup(CharRangeList rangeList)
    {
        this.charSet = new CharSet();
        this.charRanges = CharRangeList.copyOf(rangeList);
    }

    ///// METHODS /////

    @Override public boolean checkChar(char c)
    { return charSet.checkChar(c) || charRanges.checkChar(c); }

    public final void addChar(char c)
    { this.charSet.addChar(c); }

    public final void addCharRange(CharRange range)
    { this.charRanges.addCharRange(range); }

    /// Varargs Methods ///

    @SafeVarargs public final void addChars(Character... chars)
    { this.charSet.addChars(chars); }

    @SafeVarargs public final void addCharRanges(CharRange... ranges)
    { this.charRanges.addCharRanges(ranges); }

    /// Static Methods ///

    public static CompCharGroup copyOf(CompCharGroup compCharGroup)
    { return new CompCharGroup(); }
}
