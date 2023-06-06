package test;

import server.CacheReplacementPolicy;

public class testCacheReplacementPolicy {
    public static void main(String[] args) {
        // Create an instance of your cache replacement policy implementation
        CacheReplacementPolicy policy = new MyCacheReplacementPolicy();

        // Add some words to the cache
        policy.add("word1");
        policy.add("word2");
        policy.add("word3");

        // Remove a word from the cache
        String removedWord = policy.remove();

        // Print the removed word
        System.out.println("Removed word: " + removedWord);
    }

    // Create your cache replacement policy implementation
    static class MyCacheReplacementPolicy implements CacheReplacementPolicy {
        @Override
        public void add(String word) {
            // Implement your add logic here
            System.out.println("Adding word: " + word);
        }

        @Override
        public String remove() {
            // Implement your remove logic here
            String word = "word2";
            System.out.println("Removing word: " + word);
            return word;
        }
    }
}
