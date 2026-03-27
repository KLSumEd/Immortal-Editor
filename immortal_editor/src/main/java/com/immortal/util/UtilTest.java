package com.immortal.util;

import java.util.Random;

public class UtilTest 
{
    public static void main(String[] args) 
    {
        TreeRule<String> testRule = (source, target) -> (source.startsWith(target));
        ITree<String> testTree = new HashITree<>(testRule);
        Random rand = new Random();
        for (int i = 0; i < 50; i++)
        {
            testTree.put(randomString(rand.nextInt(1, 3)));
        }

        System.out.println(testTree);
    }

    private static String randomString(int length) 
    {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = length;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int) 
            (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        return generatedString;
    }
}
