package com.immortal.lex;

import java.util.ArrayList;
import java.util.Collection;

import com.immortal.scan.Scanner;
import com.immortal.tokens.IdentifierLiteralToken;
import com.immortal.tokens.KnownLexToken;
import com.immortal.tokens.Token;
import com.immortal.tokens.Tokenizer;
import com.immortal.tokens.types.IdentifierLiteralTokenType;
import com.immortal.tokens.types.KnownLexTokenType;

public class Lexer 
{
    private boolean hadLexError = false;
    private final Scanner scanner;
    private final Tokenizer tokenizer;

    public Lexer(Scanner scanner, Tokenizer tokenizer) 
    {
        this.scanner = scanner;
        this.tokenizer = tokenizer;
    }

    public Token lex() throws IndexOutOfBoundsException
    {
        scanner.advance();
        throw new UnsupportedOperationException();
    }

    public Collection<Token> lexExpression()
    {
        Collection<Token> tokens = new ArrayList<>();
        
        boolean continueLex = true;
        String currentWord = "";
        while (!this.scanner.isAtEnd() && continueLex)
        {
            char c = this.scanner.advance();
            String extWord = currentWord + c;
            
            Collection<String> possibleKnownLexemes = KnownLexTokenType.getPossibleLexemes(extWord);
            Collection<IdentifierLiteralTokenType> possibleUnknownLexemes 
              = IdentifierLiteralTokenType.getPossibleTokens(extWord);

            if (possibleKnownLexemes.isEmpty() && possibleUnknownLexemes.isEmpty()) 
            {
                KnownLexTokenType knownLexTokenType = KnownLexTokenType.getTokenType(currentWord);
                Token token;
                if (knownLexTokenType == null)
                {
                    IdentifierLiteralTokenType idLitTokenType 
                      = IdentifierLiteralTokenType.getTokenType(currentWord);
                    Object literal = IdentifierLiteralTokenType.castToLiteral(currentWord);
                    token = new IdentifierLiteralToken(
                        idLitTokenType, 
                        currentWord, 
                        literal, 
                        this.scanner.getLine()
                    );
                }
                else
                {
                    token = new KnownLexToken(knownLexTokenType, this.scanner.getLine());
                }

                tokens.add(token);
                currentWord = String.valueOf(c);
            }
        }

        throw new UnsupportedOperationException();
    }

    public Collection<Token> lexAll() 
    {
        throw new UnsupportedOperationException();
    }

    protected void error() { this.hadLexError = true; }
    public boolean hasErrorOccurred() { return this.hadLexError; }
    public boolean hasNext() 
    {
        return false;
    }
}
