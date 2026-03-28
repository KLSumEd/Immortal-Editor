package com.immortal.util;

import java.util.Collection;

public interface ITree<V>
{
    public void put(V value);
    public void remove(V value);
    public boolean contains(V value);
    public ITree<V> subtree(V value);
    public int size();

    public TreeRule<V> getSortRule();
    public Collection<TreeNode<V>> getRootNodes();
    public Collection<V> getRoots();
    public Collection<V> getValues();
}
