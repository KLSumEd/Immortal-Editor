package com.immortal.lex.checker.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.immortal.tokens.types.KnownTokenLexTokenType;

public class KnownTokenLexCheckerSetBuilder<T extends List<Class<? extends T>> & KnownTokenLexTokenType>
{
    private class _KnownTokenLexCheckerSet implements KnownTokenLexCheckerSet
    {
        private final Set<Character> firstCharSet;
        private final Set<String> lexSet;

        public _KnownTokenLexCheckerSet(Set<Character> firstCharSet, Set<String> lexSet)
        {
            this.firstCharSet = Set.copyOf(firstCharSet);
            this.lexSet = Set.copyOf(lexSet);
        }

        @Override public Set<Character> getFirstCharSet() { return this.firstCharSet; }
        @Override public Set<String> getLexSet() { return this.lexSet; }
    }

    public KnownTokenLexCheckerSet build(T tokenTypeList) 
    {
        final Set<Character> firstCharSetBuilder = new HashSet<>();
        final Set<String> lexSetBuilder = new HashSet<>();

        for (Class<? extends KnownTokenLexTokenType> TTClass : tokenTypeList)
        {
            for (KnownTokenLexTokenType tokenType : TTClass.getEnumConstants())
            {
                firstCharSetBuilder.add(tokenType.getFirstChar());
                lexSetBuilder.add(tokenType.getLexeme());
            }
        }

        return new _KnownTokenLexCheckerSet(firstCharSetBuilder, lexSetBuilder);
    }
}