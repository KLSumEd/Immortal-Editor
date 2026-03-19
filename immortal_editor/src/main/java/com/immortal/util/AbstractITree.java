package com.immortal.util;

public abstract class AbstractITree<V> implements ITree<V>
{
    private final TreeRule<V> sortRule;

    public AbstractITree(TreeRule<V> sortRule) 
    { 
        this.sortRule = sortRule; 
    }

    @Override public boolean contains(V value) 
    {
        final TreeTraverser<V> traverser = new TreeTraverser<>(this);
        return traverser.find(value);
    }

    @Override public TreeRule<V> getSortRule() { return this.sortRule; }
}