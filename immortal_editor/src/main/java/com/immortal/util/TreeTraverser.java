package com.immortal.util;

public class TreeTraverser<V>
{
    private final TreeNode<V> root;
    private final TreeRule<V> searchRule;

    public TreeTraverser(ITree<V> tree) 
    {
        this.root = tree.getRoot();
        this.searchRule = tree.getSortRule();
    }

    public TreeNode<V> getNode(V target)
    {
        return null;
    }

    public boolean find(V target)
    {
        boolean result = _recursiveFind(target, this.root);

        return result;
    }

    private boolean _recursiveFind(V target, TreeNode<V> currentNode)
    {
        boolean result = false;

        for (TreeNode<V> child : currentNode.getChildren())
        {
            V childValue = child.getValue();
            if (childValue.equals(target))
            {
                result = true;
            }
            else if (this.searchRule.execute(currentNode.getValue(), childValue))
            {
                result = _recursiveFind(target, child);
            }
        }

        return result;
    }
}
