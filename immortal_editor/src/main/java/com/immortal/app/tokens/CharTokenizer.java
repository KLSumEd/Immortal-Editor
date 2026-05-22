package com.immortal.app.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CharTokenizer implements Tokenizer
{
    private static final Pattern UNICODE_PATTERN = Pattern
            .compile("\\\\u\\+?[a-f0-9]{4}", Pattern.CASE_INSENSITIVE);
    
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        final char literal;
        
        try
        {
            String literalStr = lexeme;
            final Matcher matcher = UNICODE_PATTERN.matcher(literalStr);
            
            literalStr = matcher.replaceAll(match ->
            {
                final String uEscStr = match.group();
                final String codePointStr = uEscStr
                        .substring(uEscStr.length() - 4);
                final int codePoint = Integer.parseInt(codePointStr, 16);
                return Character.toString(codePoint);
            });
            
            literalStr = literalStr.translateEscapes();
            
            if (literalStr.length() != 3)
            {
                final String msg = "Could not translate %s to valid Unicode Character";
                throw new IllegalArgumentException(msg.formatted(literalStr));
            }
            else literal = literalStr.charAt(1);
        }
        catch (final IllegalArgumentException | IndexOutOfBoundsException e)
        {
            throw new IllegalArgumentException("Unrecognized character", e);
        }
        
        final Token token = new CharToken(lexeme, literal, line);
        
        return token;
    }
}
