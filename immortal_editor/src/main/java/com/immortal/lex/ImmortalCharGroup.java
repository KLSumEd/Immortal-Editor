package com.immortal.lex;

import com.immortal.util.CharGroup;
import com.immortal.util.CharRange;
import com.immortal.util.CharRangeList;
import com.immortal.util.CharSet;
import com.immortal.util.CompCharGroup;

public enum ImmortalCharGroup implements CharGroup
{
        UPPER(_CharGroupRepo._upper),
        LOWER(_CharGroupRepo._lower),
        DIGIT(_CharGroupRepo._digit),
        ALPHA(_CharGroupRepo._alpha),
        ALPHA_NUM(_CharGroupRepo._alphaNum),
        HEX(_CharGroupRepo._hex),
        ID_HEAD(_CharGroupRepo._idHead),
        ID_PART(_CharGroupRepo._idPart),
        VALID_SYMS(_CharGroupRepo._validSyms);
        
        private final CharGroup group;
        
        private ImmortalCharGroup(CharGroup group)
        { this.group = group; }
        
        @Override public boolean checkChar(char c)
        { return this.group.checkChar(c); }
        
        private static final class _CharGroupRepo
        {
                @SuppressWarnings("unused") private _CharGroupRepo()
                {}
                
                public static final CharRange _upper = new CharRange('A', 'Z');
                public static final CharRange _lower = new CharRange('a', 'z');
                public static final CharRange _digit = new CharRange('0', '9');
                
                public static final CharRangeList _alpha = new CharRangeList(
                                _upper, _lower);
                public static final CharRangeList _alphaNum = new CharRangeList(
                                _upper, _lower, _digit);
                public static final CharRangeList _hex = new CharRangeList(
                                _digit, new CharRange('a', 'f'),
                                new CharRange('A', 'F'));
                
                // TODO: Define these two in the Identifer Token Type
                public static final CompCharGroup _idHead = new CompCharGroup(
                                _alpha, '_');
                public static final CompCharGroup _idPart = new CompCharGroup(
                                _alphaNum, '_');
                
                // TODO: Get this from the Symbol Token Type
                public static final CharSet _validSyms = new CharSet('=', '-',
                                '+', '>', '<', '?', '|', '&', '*', '(', ')',
                                '{', '}', '[', ']', ';', ':', '.', '/', '\\',
                                ',', '!', '$', '@');
        }
}