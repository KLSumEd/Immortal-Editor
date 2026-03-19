package com.immortal.util;

public interface ITree<V>
{
    public void addToken(V token);
    public boolean contains(V value);
    public TreeTraverser<V> createTraverser();

    public TreeRule<V> getSortRule();
    public TreeNode<V> getRoot();
}
