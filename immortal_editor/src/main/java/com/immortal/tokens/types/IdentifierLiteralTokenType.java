package com.immortal.tokens.types;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public enum IdentifierLiteralTokenType implements TokenType
{
    IDENTIFIER(IdentifierLexChecker.POS_CHECKER, IdentifierLexChecker.COR_CHECKER, IdentifierLexChecker.CASTER),
    STR(StringLexChecker.POS_CHECKER, StringLexChecker.COR_CHECKER, StringLexChecker.CASTER),
    INT(IntLexChecker.POS_CHECKER, IntLexChecker.COR_CHECKER, IntLexChecker.CASTER),
    FLOAT(FloatLexChecker.POS_CHECKER, FloatLexChecker.COR_CHECKER, FloatLexChecker.CASTER);

    private final PossibleIdentifierLexChecker possibleChecker;
    private final CorrectIdentifierLexChecker correctChecker;
    private final LiteralCaster caster;

    private IdentifierLiteralTokenType(
        PossibleIdentifierLexChecker possibleChecker,
        CorrectIdentifierLexChecker correctChecker,
        LiteralCaster caster
    ) 
    {
        this.possibleChecker = possibleChecker;
        this.correctChecker = correctChecker;
        this.caster = caster;
    }

    public boolean checkIfPossible(String lexeme)
    {
        return this.possibleChecker.execute(lexeme);
    }

    public boolean checkIfCorrect(String lexeme)
    {
        return this.correctChecker.execute(lexeme);
    }

    public static Collection<IdentifierLiteralTokenType> getPossibleTokens(String lexeme)
    {
        List<IdentifierLiteralTokenType> validTypes = new ArrayList<>();

        for (IdentifierLiteralTokenType tokenType : values())
        {
            if (tokenType.checkIfPossible(lexeme)) { validTypes.add(tokenType); }
        }

        return validTypes;
    }

    public static IdentifierLiteralTokenType getTokenType(String lexeme)
    {
        IdentifierLiteralTokenType resultToken = null;

        List<IdentifierLiteralTokenType> posTypes = List.copyOf(getPossibleTokens(lexeme));
        if (posTypes.size() == 1)
        {
            resultToken = posTypes.getFirst();
        }
        else if (posTypes.size() > 1) 
        {
            for (IdentifierLiteralTokenType tokenType : values())
            {
                if (resultToken == null)
                {
                    resultToken = tokenType;
                }
                else if (resultToken.equals(FLOAT) && tokenType.equals(INT))
                {
                    resultToken = INT;
                }
            }
        }

        return resultToken;
    }
    
    // TODO: @KLSumEd — Create a lex checking system that assumes the previous lexeme was valid
    
    public Object toLiteral(String lexeme) throws NullPointerException, NumberFormatException { return this.caster.execute(lexeme); }

    public static Object castToLiteral(String lexeme) throws NullPointerException
    {
        Object result = lexeme;

        for (IdentifierLiteralTokenType tokenType : values())
        {
            try { result = tokenType.toLiteral(lexeme); } 
            catch (NumberFormatException e) {}
        }

        return result;
    }
}