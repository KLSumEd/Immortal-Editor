package com.immortal.app.lex;

import com.immortal.app.util.CharGroup;
import com.immortal.app.util.CharRange;
import com.immortal.app.util.CharRangeList;
import com.immortal.app.util.CharSet;
import com.immortal.app.util.CompCharGroup;

/**
 * Provides several static implementations of {@link CharGroup} for the
 * {@link ImmortalLexer}.
 * 
 * @author <a href="https://github.com/KLSumEd">KLSumEd</a>
 * @see CharGroup
 */
public enum ImmortalCharGroup implements CharGroup
{
        /**
         * A {@link CharGroup} representing uppercase latin characters:
         * {@code 'A'-'Z'}.
         */
        UPPER(_CharGroupRepo._upper),
        /**
         * A {@link CharGroup} representing lowercase latin characters:
         * {@code 'a'-'z'}.
         */
        LOWER(_CharGroupRepo._lower),
        /**
         * A {@link CharGroup} representing the arabic numerals:
         * {@code '0'-'9'}.
         */
        DIGIT(_CharGroupRepo._digit),
        /**
         * A {@link CharGroup} representing the lower and uppercase characters
         * of the English alphabet: {@code 'a'-'z'} and {@code 'A'-'Z'}.
         */
        ALPHA(_CharGroupRepo._alpha),
        /**
         * A {@link CharGroup} representing the lower and uppercase characters
         * of the English alphabet: {@code 'a'-'z'} and {@code 'A'-'Z'}; and the
         * arabic numerals: {@code '0'-'9'}.
         */
        ALPHA_NUM(_CharGroupRepo._alphaNum),
        /**
         * A {@link CharGroup} representing the digits: {@code '0'-'9'}; and the
         * english characters: {@code 'a'-'f'} and {@code 'A'-'F'}.
         */
        HEX(_CharGroupRepo._hex),
        /**
         * A {@link CharGroup} matching the regex: {@code [a-zA-Z_]}.
         */
        ID_HEAD(_CharGroupRepo._idHead),
        /**
         * A {@link CharGroup} matching the regex: {@code [a-zA-Z0-9_]}.
         */
        ID_PART(_CharGroupRepo._idPart),
        /**
         * A {@link CharGroup} containing all the symbols from the lexemes in
         * {@link com.immortal.app.tokens.KnownLexTokenType KnownLexTokenType}.
         */
        VALID_SYMS(_CharGroupRepo._validSyms);
        
        private final CharGroup group;
        
        private ImmortalCharGroup(CharGroup group)
        { this.group = group; }
        
        /**
         * {@inheritDoc}
         */
        @Override public boolean checkChar(char c)
        { return this.group.checkChar(c); }
        
        private static final class _CharGroupRepo
        {
                @SuppressWarnings("unused") private _CharGroupRepo()
                {}
                
                /**
                 * A {@link CharRange} taking the parameters: {@code 'A'} and
                 * {@code 'Z'}.
                 */
                public static final CharRange _upper = new CharRange('A', 'Z');
                /**
                 * A {@link CharRange} taking the parameters: {@code 'a'} and
                 * {@code 'z'}.
                 */
                public static final CharRange _lower = new CharRange('a', 'z');
                /**
                 * A {@link CharRange} taking the parameters: {@code '0'} and
                 * {@code '9'}.
                 */
                public static final CharRange _digit = new CharRange('0', '9');
                
                /**
                 * A {@link CharRangeList} of {@link #_upper} and
                 * {@link #_lower}.
                 */
                public static final CharRangeList _alpha = new CharRangeList(
                                _upper, _lower);
                /**
                 * A {@link CharRangeList} of {@link #_upper}, {@link #_lower},
                 * and {@link #_digit}.
                 */
                public static final CharRangeList _alphaNum = new CharRangeList(
                                _upper, _lower, _digit);
                /**
                 * A {@link CharRangeList} of {@link #_digit}, a
                 * <code>{@link CharRange}('a',
                 * 'f')</code>, and a <code>{@link CharRange}('A', 'F')</code>.
                 */
                public static final CharRangeList _hex = new CharRangeList(
                                _digit, new CharRange('a', 'f'),
                                new CharRange('A', 'F'));
                
                // TODO: Define these two in the Identifer Token Type
                /**
                 * A {@link CompCharGroup} of {@link #_alpha} and the
                 * {@code '_'} character.
                 */
                public static final CompCharGroup _idHead = new CompCharGroup(
                                _alpha, '_');
                /**
                 * A {@link CompCharGroup} of {@link #_alphaNum} and the
                 * {@code '_'} character.
                 */
                public static final CompCharGroup _idPart = new CompCharGroup(
                                _alphaNum, '_');
                
                // TODO: Get this from the Symbol Token Type
                /**
                 * A {@link CharSet} of characters from the
                 * {@link com.immortal.app.tokens.KnownLexTokenType
                 * KnownLexTokenType}.
                 */
                public static final CharSet _validSyms = new CharSet('=', '-',
                                '+', '>', '<', '?', '|', '&', '*', '(', ')',
                                '{', '}', '[', ']', ';', ':', '.', '/', '\\',
                                ',', '!', '$', '@');
        }
}