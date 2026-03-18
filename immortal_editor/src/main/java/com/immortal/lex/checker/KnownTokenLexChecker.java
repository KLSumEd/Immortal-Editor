package com.immortal.lex.checker;

import java.util.List;
import java.util.Set;

import com.immortal.lex.checker.util.KnownTokenLexCheckerSet;
import com.immortal.lex.checker.util.KnownTokenLexCheckerSetBuilder;
import com.immortal.tokens.types.KnownTokenLexTokenType;
import com.immortal.tokens.types.SingleCharTokenType;
import com.immortal.tokens.types.SingleDoubleCharTokenType;

public class KnownTokenLexChecker implements LexChecker
{
    private final Set<?> firstCharSet;
    private final Set<?> lexSet;

    // TODO: @KLSumEd - Make this class more generic | Due - 19/03/2026
    // Is the tokenTypeClassList relevant/specific to this class?
    // Is this class' functionality unique?

    public KnownTokenLexChecker()
    {
        KnownTokenLexCheckerSetBuilder builder = new KnownTokenLexCheckerSetBuilder();
        List<Class<? extends KnownTokenLexTokenType>> tokenTypeClassList = List.of(SingleCharTokenType.class, SingleDoubleCharTokenType.class);
        KnownTokenLexCheckerSet knownLexCheckerSet = builder.build(tokenTypeClassList);
        this.firstCharSet = knownLexCheckerSet.getFirstCharSet();
        this.lexSet = knownLexCheckerSet.getLexSet();
    }

    @Override public boolean checkLex(String lexeme) { return this.lexSet.contains(lexeme); }
    public boolean checkFirstChar(char firstChar) { return this.firstCharSet.contains(firstChar); }
}