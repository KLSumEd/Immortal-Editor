package com.immortal.util;

public interface ITree<V>
{
    public void put(V value);
    public boolean contains(V value);
    public TreeTraverser<V> createTraverser();

    public TreeRule<V> getSortRule();
    public TreeNode<V> getRoot();
}
