package com.immortal.lex.checker.util;

import java.util.Set;

public interface KnownTokenLexCheckerSet extends LexCheckerSet
{
    public Set<Character> getFirstCharSet();
}