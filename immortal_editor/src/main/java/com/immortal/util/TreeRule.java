package com.immortal.util;

public interface TreeRule<V>
{
    public boolean execute(V source, V target);
}