package com.immortal.util;

import java.util.Collection;

public interface TreeNode<V>
{
    public V getValue();
    public void setValue(V value);
    
    public void addChild(V value);
    public void removeChild(V value);

    public TreeRule<V> getSortRule();
    public Collection<V> getChildren();
}
