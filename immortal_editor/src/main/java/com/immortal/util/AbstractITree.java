package com.immortal.util;

public abstract class AbstractITree<V> implements ITree<V>
{
    protected final TreeRule<V> sortRule;

    public AbstractITree(TreeRule<V> sortRule) { this.sortRule = sortRule; }

    @Override public TreeRule<V> getSortRule() { return this.sortRule; }
}