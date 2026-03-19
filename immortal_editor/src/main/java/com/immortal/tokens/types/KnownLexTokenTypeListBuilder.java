package com.immortal.tokens.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class KnownLexTokenTypeListBuilder
{
    public static <T extends KnownLexTokenType> 
      List<KnownLexTokenType> build(Class<? extends T> TTEnumClass) 
        throws NullPointerException
    {
        T[] enumConstantsArray = TTEnumClass.getEnumConstants();

        if (enumConstantsArray == null) 
        { throw new NullPointerException("Enum Class cannot be null."); }

        List<KnownLexTokenType> enumConstantsList = Arrays.asList(enumConstantsArray);

        return enumConstantsList;
    }

    public static <T extends KnownLexTokenType> 
      List<KnownLexTokenType> buildFrom(Collection<? extends Class<? extends T>> classCollection)
    {
        final List<KnownLexTokenType> lexList = new ArrayList<>();

        for (Class<? extends T> TTEnumClass : classCollection)
        {
            lexList.addAll(build(TTEnumClass));
        }

        final List<KnownLexTokenType> result = List.copyOf(lexList);
        return result;
    }

    // public static <T extends Enum<T> & KnownLexTokenType> List<KnownLexTokenType> build(Class<T>[] TTEnumClassArray)
    // {
    //     List<Class<T>> TTEnumClassList = Arrays.asList(TTEnumClassArray);
    //     List<KnownLexTokenType> result = KnownLexTokenTypeListBuilder.build(TTEnumClassList);
    //     return result;
    // }
}