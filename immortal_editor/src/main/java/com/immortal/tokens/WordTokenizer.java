package com.immortal.tokens;

import com.immortal.util.CharGroup;
import com.immortal.util.CharRange;
import com.immortal.util.CharRangeList;
import com.immortal.util.CompCharGroup;

public class WordTokenizer implements Tokenizer
{
    private static final CharGroup WORD_GROUP;

    static
    {
        final CharRange UPPER = new CharRange('A', 'Z');
        final CharRange LOWER = new CharRange('a', 'z');
        final CharRangeList ALPHA = new CharRangeList(UPPER, LOWER);
        WORD_GROUP = new CompCharGroup(ALPHA, '_');
    }

    @Override public Token tokenize(String lexeme, int line)
    {
        final Token token;

        if (KnownLexTokenType.getTokenType(lexeme) != null)
        {
            token = new KnownLexToken(KnownLexTokenType.getTokenType(lexeme), line);
        }
        else
        {
            token = new IdentifierToken(lexeme, line);
        }

        return token;
    }

    @Override public boolean checkLex(String lexeme)
    {
        boolean valid = true;
        for (int i = 0; i < lexeme.length(); i++)
        {
            char c = lexeme.charAt(i);
            valid &= WORD_GROUP.checkChar(c);
            if (!valid) break; // Break early for small performance gain
        }

        return valid;
    }
}