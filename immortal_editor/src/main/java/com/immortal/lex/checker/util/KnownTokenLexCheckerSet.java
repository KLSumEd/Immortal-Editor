package com.immortal.lex.checker.util;

import java.util.Set;

public interface KnownTokenLexCheckerSet 
{
    public Set<Character> getFirstCharSet();
    public Set<String> getLexSet();
}