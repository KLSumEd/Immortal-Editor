package com.immortal.util;

import java.util.Collection;

public interface ITree<V>
{
    public void put(V value);
    public void remove(V value);
    public boolean contains(V value);

    public TreeRule<V> getSortRule();
    public Collection<TreeNode<V>> getRoots();
    public int size();
}
