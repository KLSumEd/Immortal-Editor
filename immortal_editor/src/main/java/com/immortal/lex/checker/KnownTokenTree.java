package com.immortal.lex.checker;

import java.util.List;

import com.immortal.tokens.types.KnownLexTokenType;
import com.immortal.util.HashITree;
import com.immortal.util.TreeRule;

public class KnownTokenTree extends HashITree<String>
{
    private static final TreeRule<String> SORT_RULE = (String source, String target) -> {
        int diff = source.compareTo(target);
        return diff > 0;
    };

    public KnownTokenTree(List<KnownLexTokenType> tokenList) 
    {
        super(SORT_RULE);

        for (KnownLexTokenType tokenType : tokenList)
        {
            String lexeme = tokenType.getLexeme();
            addToken(lexeme);
        }
    }
}