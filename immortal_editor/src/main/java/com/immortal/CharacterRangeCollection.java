package com.immortal;

public enum CharacterRangeCollection
{  
    VALID(CharacterRange.values(), new char[]{'_'}),
    ID_FIRST(new CharacterRange[] {CharacterRange.UPPER, CharacterRange.LOWER}, new char[]{'_'}),
    ID(new CharacterRange[] {CharacterRange.UPPER, CharacterRange.LOWER, CharacterRange.DIGIT}, new char[]{'_'}),
    NUM(new CharacterRange[] {CharacterRange.DIGIT}, null);


    private enum CharacterRange
    {
        UPPER('A', 'Z'),
        LOWER('a', 'z'),
        DIGIT('0', '9');

        private final char begin, end;

        private CharacterRange(char begin, char end) { this.begin = begin; this.end = end; }

        /// Character Checking ///

        // public boolean inCharRange(char c) { return this.begin < c && c < this.end; }
        public boolean inCharRangeInclusive(char c) { return this.begin <= c && c >= this.end; }
    }

    private final CharacterRange[] ranges;
    private final char[] extra;

    private CharacterRangeCollection(CharacterRange[] ranges, char[] extra) 
    { this.ranges = ranges; this.extra = extra; }

    public boolean inCharRanges(char c)
    {
        for (CharacterRange range : this.ranges) { if (range.inCharRangeInclusive(c)) return true; }
        for (char e : this.extra) { if (c == e) return true; }

        return false;
    }
}
