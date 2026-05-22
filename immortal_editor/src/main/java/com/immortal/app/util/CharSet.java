package com.immortal.app.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class CharSet implements CharGroup
{
    ///// VARIABLES /////
    
    private final Set<Character> chars;
    
    ///// CONSTRUCTORS /////
    
    public CharSet()
    { this.chars = new HashSet<>(); }
    
    public CharSet(Collection<Character> chars)
    { this.chars = new HashSet<>(chars); }
    
    @SafeVarargs public CharSet(Character... chars)
    { this.chars = new HashSet<>(Arrays.asList(chars)); }
    
    ///// METHODS /////
    
    @Override public boolean checkChar(char c)
    { return this.chars.contains(c); }
    
    public final void addChar(char c)
    { this.chars.add(c); }
    
    @SafeVarargs public final void addChars(Character... chars)
    { this.chars.addAll(List.of(chars)); }
    
    public final Set<Character> toSet()
    { return Set.copyOf(this.chars); }
    
    public static CharSet copyOf(CharSet charSet)
    { return new CharSet(charSet.toSet()); }
}