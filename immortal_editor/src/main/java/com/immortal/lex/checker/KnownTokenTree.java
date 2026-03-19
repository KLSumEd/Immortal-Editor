package com.immortal.lex.checker;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.immortal.tokens.types.KnownLexTokenType;

public class KnownTokenTree
{
    private final TreeMap<String, KnownLexTokenType> tokenTree = new TreeMap<>();

    public KnownTokenTree(List<KnownLexTokenType> tokenList) 
    {
        for (KnownLexTokenType tokenType : tokenList)
        {
            String lexeme = tokenType.getLexeme();
            this.tokenTree.putIfAbsent(lexeme, tokenType);
        }
    }

    public KnownLexTokenType get(String lexeme) { return this.tokenTree.get(lexeme); }
    public boolean contains(String lexeme) { return this.tokenTree.containsKey(lexeme); }
    
    public Map<String, KnownLexTokenType> toMap() { return Map.copyOf(this.tokenTree); }
    public TreeMap<String, KnownLexTokenType> toTreeMap() { return new TreeMap<>(this.toMap()); }
}