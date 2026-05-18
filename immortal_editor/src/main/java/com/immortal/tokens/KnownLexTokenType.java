package com.immortal.tokens;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.immortal.util.HashITree;
import com.immortal.util.ITree;
import com.immortal.util.TreeRule;

public enum KnownLexTokenType implements TokenType
{
    // Single-Character Tokens
    LEFT_BRACE("{"),
    RIGHT_BRACE("}"),
    LEFT_PAREN("("),
    RIGHT_PAREN(")"),
    LEFT_SQUARE("["),
    RIGHT_SQUARE("]"),
    COMMA(","),
    DOT("."),
    MINUS("-"),
    PLUS("+"),
    SEMICOLON(";"),
    SLASH("/"),
    BACKSLASH("\\"),
    STAR("*"),
    EOF("\0"),
    
    // One-or-Two-Character Tokens
    EXCL("!"),
    EXCL_EQUAL("!="),
    EQUAL("="),
    EQUAL_EQUAL("=="),
    GREATER(">"),
    GREATER_EQUAL(">="),
    LESS("<"),
    LESS_EQUAL("<="),
    
    // Keyword Tokens
    STR("str"),           // <--- For the following entries
    INT("int"),           // <--- ensure the given TokenType
    BOOL("bool"),         // <--- refers to the
    FLOAT("float"),       // <--- TYPE KEYWORD TOKENS,
    CHAR("char"),         // <--- NOT the LITERAL TOKENS
    FUNC("function"),
    AND("and"),
    OR("or"),
    NOT("not"),
    IF("if"),
    ELSE("else"),
    RETURN("return"),
    CLASS("class"),
    SUPER("super"),
    THIS("this"),
    FOR("for"),
    WHILE("while"),
    
    // Secret Literal Keywords - shh!
    TRUE("true"),
    FALSE("false");
    
    ///// STATIC /////
    
    private static final Map<String, KnownLexTokenType> LEX_TOKEN_MAP;
    private static final ITree<String> TOKEN_TREE;
    
    static
    {
        final TreeRule<String> RULE = (source,
                target) -> (source.startsWith(target));
        TOKEN_TREE = new HashITree<>(RULE);
        LEX_TOKEN_MAP = new HashMap<>();
        
        for (KnownLexTokenType tokenType : KnownLexTokenType.values())
        {
            TOKEN_TREE.put(tokenType.getLexeme());
            LEX_TOKEN_MAP.put(tokenType.getLexeme(), tokenType);
        }
        
    }
    
    public static Collection<String> getPossibleLexemes(String lexeme)
    {
        ITree<String> subtree = TOKEN_TREE.subtree(lexeme);
        return subtree.getValues();
    }
    
    public static KnownLexTokenType getTokenType(String lexeme)
    { return LEX_TOKEN_MAP.get(lexeme); }
    
    ///// NON-STATIC /////
    
    private final String lexeme;
    
    private KnownLexTokenType(String lexeme)
    { this.lexeme = lexeme; }
    
    public String getLexeme()
    { return this.lexeme; }
    
    @Override public String toString()
    {
        return switch (this)
        {
            case STR -> "KW_STR";
            case CHAR -> "KW_CHAR";
            case INT -> "KW_INT";
            case FLOAT -> "KW_FLOAT";
            case BOOL -> "KW_BOOL";
            case TRUE -> "LIT_TRUE";
            case FALSE -> "LIT_FALSE";
            default -> name();
        };
    }
}
