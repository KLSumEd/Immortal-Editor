package com.immortal.lex.checker;

import java.util.List;

import com.immortal.tokens.types.EnumListBuilder;
import com.immortal.tokens.types.KnownLexTokenType;
import com.immortal.tokens.types.SingleCharTokenType;
import com.immortal.tokens.types.SingleDoubleCharTokenType;

public class KnownTokenLexChecker implements LexChecker
{
    private static final KnownTokenTree TOKEN_TREE;

    static 
    {
        final List<Class<? extends KnownLexTokenType>> tokenTypeEnumClassList = List.of(
            SingleCharTokenType.class, 
            SingleDoubleCharTokenType.class
        );

        final List<KnownLexTokenType> tokenTypes = EnumListBuilder.buildFrom(tokenTypeEnumClassList);

        TOKEN_TREE = new KnownTokenTree(tokenTypes);
    }

    @Override public boolean checkLex(String lexeme)
    {
        return TOKEN_TREE.contains(lexeme);
    }
}
