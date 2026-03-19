package com.immortal.util;

import java.util.HashMap;

public class HashITree<V> extends AbstractITree<V>
{
    private final HashMap<V, TreeNode<V>> treeMap;

    public HashITree(TreeRule<V> sortRule) 
    {
        super(sortRule);
        this.treeMap = new HashMap<>();
    }

    @Override public void addToken(V token) 
    {

        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public TreeNode<V> getRoot() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override public TreeTraverser<V> createTraverser() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
