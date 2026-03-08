package com.immortal.lex;

import com.immortal.tokens.types.IdentifierLiteralTokenType;


class IdentifierLiteralLexChecker implements LexChecker {

    @Override public boolean checkLex(String lexeme) 
    {
        boolean valid = true;

        IdentifierLiteralTokenType[] validCharGroups = IdentifierLiteralTokenType.values();

        return valid;
    }

}
