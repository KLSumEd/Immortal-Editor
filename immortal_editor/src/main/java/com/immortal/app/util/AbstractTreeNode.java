package com.immortal.app.util;

public abstract class AbstractTreeNode<V> implements TreeNode<V>
{
    protected V value;
    protected final TreeRule<V> sortRule;
    
    public AbstractTreeNode(V value, TreeRule<V> sortRule)
    {
        this.value = value;
        this.sortRule = sortRule;
    }
    
    @Override public V getValue()
    { return this.value; }
    
    @Override public void setValue(V value)
    { this.value = value; }
    
    @Override public TreeRule<V> getSortRule()
    { return this.sortRule; }
}