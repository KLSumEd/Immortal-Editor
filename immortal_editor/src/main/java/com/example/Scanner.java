package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Scanner 
{
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    public Scanner(String source) {this.source = source;}

    public List<Token> scanTokens() 
    {
        while (!isAtEnd()) 
        {
            // We are at the beginning of the next lexeme.
            this.start = this.current;
            scanToken();
        }

        this.tokens.add(new Token(TokenType.EOF, "", null, this.line));
        return this.tokens;
    }


    private void scanToken() {
        char c = advance();
        switch (c) 
        {
            case '(' -> addToken(TokenType.LEFT_PAREN);
            case ')' -> addToken(TokenType.RIGHT_PAREN);
            case '{' -> addToken(TokenType.LEFT_BRACE);
            case '}' -> addToken(TokenType.RIGHT_BRACE);
            case ',' -> addToken(TokenType.COMMA);
            case '.' -> addToken(TokenType.DOT);
            case '-' -> addToken(TokenType.MINUS);
            case '+' -> addToken(TokenType.PLUS);
            case ';' -> addToken(TokenType.SEMICOLON);
            case '*' -> addToken(TokenType.STAR);
            case '!' -> addToken(match('=') ? TokenType.EXCL_EQUAL : TokenType.EXCL);
            case '=' -> addToken(match('=') ? TokenType.EQUAL_EQUAL : TokenType.EQUAL);
            case '<' -> addToken(match('=') ? TokenType.LESS_EQUAL : TokenType.LESS);
            case '>' -> addToken(match('=') ? TokenType.GREATER_EQUAL : TokenType.GREATER);
            case '/' -> {
                if (match('/')) 
                {
                    // A comment goes until the end of the line.
                    while (peek() != '\n' && !isAtEnd()) {advance();}
                } else {addToken(TokenType.SLASH);}
            }
            case '"' -> {
                // Check for multiline string
                if (match('"', 2)) {caseMultiline();}
                else {caseString();}
            }
            case '\n' -> {this.line++;}
            case '\r' -> {}
            case '\t' -> {}
            case ' ' -> {}
            default -> {Parser.error(this.line, "Unexpected character.");}
        }
    }


    ///// EXPANDED CASE METHODS /////

    // Runs when Lexeme begins with: "
    private void caseString()
    {
        String s = "";
        boolean isTerminated = false;
        
        // Scan String literal
        while (!isTerminated && !isAtEnd()) 
        {
            if (match('"')) { isTerminated = true; }
            else { s += parseStringChar(); }
        } 

        if (!isTerminated) { Parser.error(this.line, "Unterminated String."); } 
        else { addToken(TokenType.STRING, s); }
    }

    // Runs when Lexeme begins with: """
    private void caseMultiline()
    {
        String s = "";
        boolean isTerminated = false;
        
        // Scan string literal
        while (!isTerminated && !isAtEnd()) 
        {
            if (match('"', 3)) {isTerminated = true;}
            else 
            {   
                char c = parseStringChar();
                // Multi-line Strings should skip whitespace after a newline
                if (c == '\n') 
                {
                    while (match(' ') || match('\t') || match('\r')) {/* Skip whitespace */}
                    c = '\n';
                    this.line++;
                }
                s += c;
            }
        }

        // EOF Error handling
        if (!isTerminated) { Parser.error(this.line, "Unterminated Multi-line String."); }
        else { addToken(TokenType.STRING, s); }
    }


    ///// STRING PARSING /////
    
    private char parseStringChar()
    {
        char result;

        if (!isIndexAtEnd(this.current + 1) && match('\\')) // If user attempts to enter an escape character...
        {
            char c = advance();
            switch (c)
            {
                case '\\' -> result = '\\';
                case 'n' -> result = '\n';
                case 't' -> result = '\t';
                case 'r' -> result = '\r';
                case '"' -> result = '"';
                case '{' -> result = '{';
                case '0' -> result = '\0';
                case 'u' -> {
                    final int CODE_LEN = 4;
                    final Pattern UTF8_PATTERN = Pattern.compile("[0-9a-f]{%d}".formatted(CODE_LEN));
                    if (peekFind(UTF8_PATTERN, CODE_LEN)) 
                    {
                        final String codeStr = advance(CODE_LEN); 
                        final int decCode = Integer.parseInt(codeStr, 16);
                        final char[] uniChars = Character.toChars(decCode);
                        result = uniChars[0];
                    }
                    else {result = ' ';}
                }
                default -> { Parser.error(this.line, "Unrecognised escape character."); result = c; } 
            }
        } else { result = advance(); }
        
        return result;
    }


    ///// HELPER METHODS /////

    /// Advancing ///
    private char advance() {return this.source.charAt(this.current++);}

    // Wrapper for advance() for multiple advances
    private String advance(int num)
    {
        String chars = "";
        for (int i = 0; i < num; i++)
        {
            chars += advance();
        }
        return chars;
    }


    /// Matching ///
    private boolean match(char expected, int repeated)
    {
        for (int i = 0; i < repeated; i++)
        {
            if (peekIndex(this.current + i) != expected) { return false; }
        }
        
        this.current += repeated; // Should only update cursor if all match
        return true;
    }

    // Default wrapper for match() with repeated = 1
    private boolean match(char expected) { return match(expected, 1); }


    /// Peeking ///
    private char peekIndex(int index)
    {
        if (isIndexAtEnd(index)) { return '\0'; }
        else { return this.source.charAt(index); }
    }

    // Wrapper for peekIndex() for multiple consecutive indices
    private String peekIndex(int index, int dist) 
    {
        String chars = "";
        for (int i = 0; i < dist; i++)
        {
            chars += peekIndex(index);
        }
        return chars;
    }
    
    // peek() methods wrap peekIndex() for: index = this.current
    private char peek() {return peekIndex(this.current);}
    private String peek(int dist) {return peekIndex(this.current, dist);}


    /// Pattern Matching ///
    private boolean findPattern(boolean peekMode, Pattern pattern, int dist)
    {
        if (isIndexAtEnd(this.current + dist)) {return false;}
        else 
        {
            final String s = peekMode ? peek(dist) : advance(dist);
            final Matcher m = pattern.matcher(s);
            return m.find();
        }
    }

    private boolean matchFind(Pattern pattern, int dist) { return findPattern(false, pattern, dist); }

    private boolean peekFind(Pattern pattern, int dist) { return findPattern(true, pattern, dist); }


    /// Adding Tokens ///
    private void addToken(TokenType type, Object literal) 
    {
        String text = this.source.substring(this.start, this.current);
        tokens.add(new Token(type, text, literal, this.line));
    }
    // Default wrapper for addToken() with: literal = null
    private void addToken(TokenType type) {addToken(type, null);}


    /// EOF Checking ///
    private boolean isAtEnd() {return this.current >= this.source.length();}
    private boolean isIndexAtEnd(int index) {return index >= this.source.length();}
}