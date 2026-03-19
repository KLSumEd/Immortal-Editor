package com.immortal.util;

import java.util.ArrayList;
import java.util.List;

public class TreeNode<V>
{
    private V value;
    private TreeNode<V> parent;
    private final List<TreeNode<V>> children;
    
    public TreeNode(V value, TreeNode<V> parent) 
    { 
        this.value = value; 
        this.parent = parent;
        this.children = new ArrayList<>();
    }

    public V getValue() { return this.value; }
    public void setValue(V value) { this.value = value; }

    public TreeNode<V> getParent() { return this.parent; }
    public void setParent(TreeNode<V> parent) { this.parent = parent; }

    public List<TreeNode<V>> getChildren() { return this.children; }
    public void addChild(TreeNode<V> child) { this.children.add(child); }
}
