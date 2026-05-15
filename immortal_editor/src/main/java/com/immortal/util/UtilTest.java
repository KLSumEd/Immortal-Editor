package com.immortal.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class UtilTest
{
    public static void main(String[] args)
    {
        TreeRule<String> testRule = (source, target) -> (source.startsWith(target));
        ITree<String> wordTree = new HashITree<>(testRule);
        Set<String> wordSet = new HashSet<>();
        Random rand = new Random();
        for (int i = 0; i < 50; i++)
        {
            String s = randomString(rand.nextInt(1, 4), 'a', 'f');
            wordTree.put(s);
            wordSet.add(s);
        }

        System.out.println("Tree:\n" + wordTree);
        System.out.println("Set:\n" + wordSet);

        Set<String> treeValues = Set.copyOf(wordTree.getValues());
        System.out.println("Tree Value Set:\n" + treeValues);
        System.out.println("Both equal? " + wordSet.equals(treeValues));
    }

    private static String randomString(int length)
    { return randomString(length, 'a', 'z'); }

    private static String randomString(int length, char limit1, char limit2)
    {
        int leftLimit = limit1 <= limit2 ? limit1 : limit2; // letter 'a'
        int rightLimit = limit2 >= limit1 ? limit2 : limit1; // letter 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++)
        {
            int randomLimitedInt = leftLimit
                    + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }

        String generatedString = buffer.toString();

        return generatedString;
    }
}
