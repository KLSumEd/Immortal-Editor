package com.immortal.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class HashITree<V> extends AbstractITree<V>
{
    private final HashMap<V, HashTreeNode<V>> treeMap;
    private V rootValue;

    public HashITree(TreeRule<V> sortRule) 
    {
        super(sortRule);
        this.treeMap = new HashMap<>();
    }

    @Override public void put(V value) 
    {
        HashTreeNode<V> node = new HashTreeNode<>(value, this.getSortRule());
        this.treeMap.put(value, node);
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public V traverse(V target)
    {
        V currentNodeValue = this.rootValue;
        boolean found = false;

        while (!found)
        {
            if (target.equals(currentNodeValue)) { found = true; } 
            else
            {
                HashTreeNode<V> currentNode = this.treeMap.get(currentNodeValue);
                V child = checkChildren(currentNode, target);
                if (child != null) { currentNodeValue = child; } 
                else { found = true; }
            }
        }

        return currentNodeValue;
    }

    private V checkChildren(HashTreeNode<V> currentNode, V target)
    {
        V validChild = null;
        if (currentNode == null) { return null; }
        Collection<V> children = currentNode.getChildren();

        for (V child : children)
        {
            boolean check = this.sortRule.execute(child, target);
            if (check)
            {
                validChild = child;
                break;
            }
        }

        return validChild;
    }

    @Override public TreeNode<V> getRoot() 
    {
        return this.treeMap.get(this.rootValue);
    }

    @Override public TreeTraverser<V> createTraverser() 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

class HashTreeNode<V> extends AbstractTreeNode<V>
{
    private final HashSet<V> children;

    public HashTreeNode(V value, TreeRule<V> sortRule) 
    {
        super(value, sortRule);
        this.children = new HashSet<>();
    }

    // @Override public V get(V target)
    // {
    //     TreeNode<V> node = target.equals(this.value) ? this : null;
        
    //     for (V child : this.children.keySet())
    //     {
    //         if (this.sortRule.execute(target, child))
    //         {
    //             TreeNode<V> currentNode = this.children.get(child);
    //             TreeNode<V> childNode = currentNode.get(target);
    //             node = childNode != null ? childNode : currentNode;
    //             break;
    //         }
    //     }

    //     return node;
    // }

    
    // @Override public void add(V value) 
    // {
    //     throw new UnsupportedOperationException("Unimplemented method 'add'");
    // }    

    @Override public Collection<V> getChildren() { return this.children; }
}