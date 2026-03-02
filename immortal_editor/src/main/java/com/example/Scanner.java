package com.example;

import java.util.ArrayList;
import java.util.List;

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
            case '"' -> {caseString();}
            case '\n' -> {this.line++;}
            case '\r' -> {}
            case '\t' -> {}
            case ' ' -> {}
            default -> {Parser.error(this.line, "Unexpected character.");}
        }
    }

    private char advance() {return this.source.charAt(this.current++);}

    private boolean match(char expected) 
    {
        if (isAtEnd()) {return false;}
        if (this.source.charAt(this.current) != expected) {return false;}

        this.current++;
        return true;
    }

    private char peek() 
    {
        if (isAtEnd()) return '\0';
        return this.source.charAt(this.current);
    }

    private void caseString()
    {
        while (peek() != '"' && !isAtEnd()) {advance();}
        if (isAtEnd()) 
        {
            Parser.error(this.line, "Unterminated String.");
            return;
        }

        advance();
        String value = this.source.substring(this.start + 1, this.current-1);
        this.addToken(TokenType.STRING, value);
    }

    private void addToken(TokenType type) {addToken(type, null);}

    private void addToken(TokenType type, Object literal) 
    {
        String text = this.source.substring(this.start, this.current);
        tokens.add(new Token(type, text, literal, this.line));
    }

    private boolean isAtEnd() {return this.current >= this.source.length();}
}