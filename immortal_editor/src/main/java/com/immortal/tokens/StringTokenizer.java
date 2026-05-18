package com.immortal.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTokenizer implements Tokenizer
{
    private static final Pattern UNICODE_PATTERN = Pattern
            .compile("\\\\u\\+?[a-f0-9]{4}", Pattern.CASE_INSENSITIVE);
    private static final TokenType TYPE = IdentifierLiteralTokenType.STR;
    
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        String literal = stripQuotationMarks(lexeme);
        literal = literal.translateEscapes();
        
        final Matcher matcher = UNICODE_PATTERN.matcher(literal);
        
        literal = matcher.replaceAll(match ->
        {
            final String uEscStr = match.group();
            final String codePointStr = uEscStr.substring(uEscStr.length() - 4);
            final int codePoint = Integer.parseInt(codePointStr, 16);
            return Character.toString(codePoint);
        });
        
        final Token token = new LiteralToken(TYPE, lexeme, literal, line);
        return token;
    }
    
    private String stripQuotationMarks(String lexeme)
    {
        String result = lexeme.startsWith("\"\"\"")
                ? lexeme.substring(3, lexeme.length() - 3)
                : lexeme.substring(1, lexeme.length() - 1);
        return result;
    }
}
