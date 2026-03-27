package com.immortal.util;

public class UtilTest 
{
    public static void main(String[] args) 
    {
        TreeRule<String> testRule = (source, target) -> (source.startsWith(target));
        ITree<String> testTree = new HashITree<>(testRule);

        testTree.put("a");
        testTree.put("aa");
        testTree.put("aaa");
        
        testTree.put("b");
        testTree.put("bc");
        testTree.put("bbc");

        System.out.println(testTree);
    }
}
