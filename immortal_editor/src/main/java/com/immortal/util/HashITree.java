package com.immortal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class HashITree<V> extends AbstractITree<V>
{
    private final HashMap<V, HashTreeNode<V>> treeMap;
    private final List<V> roots;

    public HashITree(TreeRule<V> sortRule)
    {
        super(sortRule);
        this.treeMap = new HashMap<>();
        this.roots = new ArrayList<>();
    }

    private V traverse(V target)
    {
        V currentNodeValue = findNextNodeToTarget(this.roots, target);
        boolean found = currentNodeValue == null;

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

        if (currentNode != null)
        {
            Collection<V> children = currentNode.getChildren();
            validChild = findNextNodeToTarget(children, target);
        }

        return validChild;
    }

    private V findNextNodeToTarget(Collection<V> possibleNodes, V target)
    {
        V nextNode = null;

        for(V node : possibleNodes)
        {
            if (this.sortRule.execute(target, node)) 
            {
                nextNode = node;
                break;
            }
        }

        return nextNode;
    }

    @Override public void put(V value) 
    {
        HashTreeNode<V> node = new HashTreeNode<>(value, this.getSortRule());
        V nearestNodeValue = traverse(value);
        
        if (nearestNodeValue != null) 
        { 
            List<V> nodeChildren = new ArrayList<>();
            for (V child : this.treeMap.get(nearestNodeValue).getChildren())
            {
                if (this.sortRule.execute(child, value)) nodeChildren.add(child);
            }
            for (V newChild : nodeChildren)
            {
                this.treeMap.get(nearestNodeValue).removeChild(newChild);
            }
            this.treeMap.get(nearestNodeValue).addChild(value); 
        }
        else { this.roots.add(value); }
        
        this.treeMap.put(value, node);
    }

    @Override public void remove(V value)
    {
        V nearestNodeValue = traverse(value);
        this.treeMap.get(nearestNodeValue).removeChild(value);
        this.treeMap.remove(value);
    }

    @Override public boolean contains(V target) { return this.treeMap.containsKey(target); }

    @Override public Collection<TreeNode<V>> getRoots() 
    {
        List<TreeNode<V>> rootNodes = new ArrayList<>(this.roots.size());
        for (V root : this.roots) { rootNodes.add(this.treeMap.get(root)); }

        return List.copyOf(rootNodes);
    }

    private static <V> V depthFirst(HashMap<V, HashTreeNode<V>> map, V key)
    {
        V currentKey = key;
        boolean reachedBottom = !map.containsKey(currentKey);

        while (!reachedBottom)
        {
            TreeNode<V> currentNode = map.get(currentKey);

            List<V> children = new ArrayList<>();
            for (V child : currentNode.getChildren())
            {
                if (map.containsKey(child)) children.add(child);
            }

            if (children.isEmpty()) { reachedBottom = true; }
            else currentKey = children.getFirst();
        }

        return currentKey;
    }

    @Override public String toString() 
    {
        String result = "";
        HashMap<V, HashTreeNode<V>> readMap = new HashMap<>();
        readMap.putAll(this.treeMap);

        for (V key : this.roots)
        {
            boolean checkedRoot = false;
            while (!checkedRoot)
            {
                V deepestNode = HashITree.<V>depthFirst(readMap, key);
                readMap.remove(deepestNode);
                result = deepestNode + ", " + result;
                checkedRoot = deepestNode.equals(key);
            }
            result = "\n" + result;
        }
        result = result.substring(1);
        return result;
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
    @Override public void addChild(V value) { this.children.add(value); }
    @Override public void removeChild(V value) { this.children.remove(value); }
    @Override public Collection<V> getChildren() { return this.children; }
}