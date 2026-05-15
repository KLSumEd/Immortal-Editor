package com.immortal.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class EnumListBuilder
{
    public static <T> List<T> build(Class<? extends T> TTEnumClass) throws NullPointerException
    {
        T[] enumConstantsArray = TTEnumClass.getEnumConstants();

        if (enumConstantsArray == null)
        { throw new NullPointerException("Enum Class cannot be null."); }

        List<T> enumConstantsList = Arrays.asList(enumConstantsArray);

        return enumConstantsList;
    }

    public static <T> List<T> buildFrom(Collection<Class<? extends T>> classCollection)
    {
        final List<T> lexList = new ArrayList<>();

        for (Class<? extends T> TTEnumClass : classCollection)
        { lexList.addAll(build(TTEnumClass)); }

        final List<T> result = List.copyOf(lexList);
        return result;
    }

    // public static <T extends Enum<T> & KnownLexTokenType> List<KnownLexTokenType>
    // build(Class<T>[] TTEnumClassArray)
    // {
    // List<Class<T>> TTEnumClassList = Arrays.asList(TTEnumClassArray);
    // List<KnownLexTokenType> result =
    // KnownLexTokenTypeListBuilder.build(TTEnumClassList);
    // return result;
    // }
}