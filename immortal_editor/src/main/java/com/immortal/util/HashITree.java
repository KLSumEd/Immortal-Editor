package com.immortal.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    
    private HashITree(
            TreeRule<V> sortRule, Map<V, HashTreeNode<V>> premadeMap,
            List<V> roots
    )
    {
        super(sortRule);
        
        this.treeMap = new HashMap<>();
        this.treeMap.putAll(premadeMap);
        
        this.roots = new ArrayList<>();
        this.roots.addAll(roots);
    }
    
    private V traverseToNearest(V target)
    {
        V currentNodeValue = contains(target) ? target
                : findNextNodeToTarget(this.roots, target);
        boolean found = currentNodeValue == null;
        
        while (!found)
        {
            
            if (target.equals(currentNodeValue))
            {
                found = true;
            }
            else
            {
                HashTreeNode<V> currentNode = this.treeMap
                        .get(currentNodeValue);
                V child = checkChildren(currentNode, target);
                
                if (child != null)
                {
                    currentNodeValue = child;
                }
                else
                {
                    found = true;
                }
                
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
        
        for (V node : possibleNodes)
        {
            
            if (getSortRule().execute(target, node))
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
        V nearestNodeValue = traverseToNearest(value);
        
        if (nearestNodeValue != null)
        {
            Set<V> nodeChildren = new HashSet<>();
            
            for (V child : this.treeMap.get(nearestNodeValue).getChildren())
            {
                if (getSortRule().execute(child, value))
                    nodeChildren.add(child);
            }
            
            for (V newChild : nodeChildren)
            {
                this.treeMap.get(nearestNodeValue).removeChild(newChild);
                node.addChild(newChild);
            }
            
            this.treeMap.get(nearestNodeValue).addChild(value);
        }
        else
        {
            Set<V> nodeChildren = new HashSet<>();
            
            for (V root : this.roots)
            { if (getSortRule().execute(root, value)) nodeChildren.add(root); }
            
            for (V newChild : nodeChildren)
            {
                this.roots.remove(newChild);
                node.addChild(newChild);
            }
            
            this.roots.add(value);
        }
        
        this.treeMap.put(value, node);
    }
    
    @Override public void remove(V value)
    {
        if (!this.treeMap.containsKey(value)) return;
        
        Collection<V> targetChildren = this.treeMap.get(value).getChildren();
        this.treeMap.remove(value);
        V nearestNodeValue = traverseToNearest(value);
        
        if (nearestNodeValue != null)
        {
            
            for (V child : targetChildren)
            { this.treeMap.get(nearestNodeValue).addChild(child); }
            
        }
        else
        {
            
            for (V child : targetChildren)
            { this.roots.add(child); }
            
            this.roots.remove(value);
        }
        
    }
    
    @Override public boolean contains(V target)
    { return this.treeMap.containsKey(target); }
    
    @Override public int size()
    { return this.treeMap.size(); }
    
    @Override public Collection<V> getRoots()
    { return this.roots; }
    
    @Override public Collection<V> getValues()
    { return this.treeMap.keySet(); }
    
    @Override public Collection<TreeNode<V>> getRootNodes()
    {
        List<TreeNode<V>> rootNodes = new ArrayList<>(this.roots.size());
        
        for (V root : this.roots)
        { rootNodes.add(this.treeMap.get(root)); }
        
        return List.copyOf(rootNodes);
    }
    
    @Override public ITree<V> subtree(V value) throws NullPointerException
    {
        
        if (!contains(value))
        {
            throw new NullPointerException(
                    "Cannot create subtree. Given value does not occur within tree.");
        }
        
        HashMap<V, HashTreeNode<V>> copyMap = new HashMap<>();
        copyMap.putAll(this.treeMap);
        
        HashMap<V, HashTreeNode<V>> subtreeMap = new HashMap<>();
        boolean traversed = false;
        
        while (!traversed)
        {
            V deepNode = depthFirst(copyMap, value);
            copyMap.remove(deepNode);
            subtreeMap.put(deepNode, this.treeMap.get(deepNode));
            traversed = !copyMap.containsKey(value);
        }
        
        HashITree<V> subtree = new HashITree<>(getSortRule(), subtreeMap,
                List.of(value));
        return subtree;
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
            { if (map.containsKey(child)) children.add(child); }
            
            if (children.isEmpty())
            {
                reachedBottom = true;
            }
            else currentKey = children.getFirst();
            
        }
        
        return currentKey;
    }
    
    @Override public String toString()
    { return recursiveToString(); }
    
    public String recursiveToString()
    {
        String result = recursiveToString(this.roots, "", 0);
        return result.substring(0, result.length() - 1);
    }
    
    private static final int MAX_DEPTH = 20;
    
    private String recursiveToString(
            Collection<V> currentNodes, String prev, int depth
    )
    {
        String result = prev;
        if (depth > MAX_DEPTH) return result; // Stops string from being
                                              // unnecessarily big
        
        for (V node : currentNodes)
        {
            
            for (int i = 0; i < depth; i++)
            { result += " "; }
            
            result += depth > 0 ? "| " + String.valueOf(node)
                    : String.valueOf(node);
            result += "\n";
            
            Collection<V> children = this.treeMap.get(node).getChildren();
            
            if (!children.isEmpty())
            { result = recursiveToString(children, result, depth + 1); }
            
        }
        
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
    
    @Override public void addChild(V value)
    { this.children.add(value); }
    
    @Override public void removeChild(V value)
    { this.children.remove(value); }
    
    @Override public boolean hasChild(V value)
    { return this.children.contains(value); }
    
    @Override public Collection<V> getChildren()
    { return this.children; }
}