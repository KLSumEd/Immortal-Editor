package com.immortal.tokens;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.immortal.tokens.types.EnumListBuilder;
import com.immortal.tokens.types.KnownLexTokenType;
import com.immortal.tokens.types.ReservedWordsTokenType;
import com.immortal.tokens.types.SingleCharTokenType;
import com.immortal.tokens.types.SingleDoubleCharTokenType;
import com.immortal.util.HashITree;
import com.immortal.util.ITree;
import com.immortal.util.TreeRule;

public abstract class KnownTokenTree
{
    private static final ITree<String> TOKEN_TREE;
    private static final Map<String, KnownLexTokenType> LEX_TOKEN_MAP;

    static 
    {
        final TreeRule<String> RULE = (source, target) -> (source.startsWith(target));
        TOKEN_TREE = new HashITree<>(RULE);
        LEX_TOKEN_MAP = new HashMap<>();

        final Set<Class<? extends KnownLexTokenType>> tokenTypeEnumClassList = Set.of(
            SingleCharTokenType.class, 
            SingleDoubleCharTokenType.class,
            ReservedWordsTokenType.class
        );

        final List<KnownLexTokenType> tokenTypes = EnumListBuilder.buildFrom(tokenTypeEnumClassList);
        for (KnownLexTokenType tokenType : tokenTypes)
        {
            TOKEN_TREE.put(tokenType.getLexeme());
            LEX_TOKEN_MAP.put(tokenType.getLexeme(), tokenType);
        }
    }

    public static boolean contains(String lexeme) { return TOKEN_TREE.contains(lexeme); }

    public static Collection<String> getPossibleLexemes(String lexeme)
    {
        ITree<String> subtree = TOKEN_TREE.subtree(lexeme);
        return subtree.getValues();
    }

    public static KnownLexTokenType getTokenType(String lexeme)
    {
        return LEX_TOKEN_MAP.get(lexeme);
    }
}
