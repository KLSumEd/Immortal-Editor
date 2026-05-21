package com.immortal.tokens;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTokenizer implements Tokenizer
{
    private static final Pattern UNICODE_PATTERN = Pattern
            .compile("\\\\u\\+?[a-f0-9]{4}", Pattern.CASE_INSENSITIVE);
    
    @Override public Token tokenize(String lexeme, int line)
            throws IllegalArgumentException
    {
        String literal = StringType.getStringType(lexeme).format(lexeme);
        
        final Matcher matcher = UNICODE_PATTERN.matcher(literal);
        
        literal = matcher.replaceAll(match ->
        {
            final String uEscStr = match.group();
            final String codePointStr = uEscStr.substring(uEscStr.length() - 4);
            final int codePoint = Integer.parseInt(codePointStr, 16);
            return Character.toString(codePoint);
        });
        
        literal = literal.translateEscapes();
        
        final Token token = new StringToken(lexeme, literal, line);
        return token;
    }
    
    private static enum StringType
    {
        SINGLELINE
        {
            @Override public String format(String lexeme)
            { return lexeme.substring(1, lexeme.length() - 1); }
        },
        MULTILINE
        {
            @Override public String format(String lexeme)
            {
                String result;
                result = lexeme.substring(3, lexeme.length() - 3);
                final int dist = lexeme.length() - lexeme.lastIndexOf('\n') - 1;
                final String endLinePatternRegex = "\\n\\s{%d}".formatted(dist);
                final Pattern endLinePattern = Pattern
                        .compile(endLinePatternRegex);
                result = endLinePattern.matcher(result).replaceAll("\n");
                result = result.substring(1);
                return result;
            }
        };
        
        public abstract String format(String lexeme);
        
        public static StringType getStringType(String lexeme)
        { return lexeme.startsWith("\"\"\"\n") ? MULTILINE : SINGLELINE; }
    }
}
