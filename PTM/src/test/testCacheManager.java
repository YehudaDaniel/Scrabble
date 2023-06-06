package test;

import server.CacheManager;
import server.CacheReplacementPolicy;
import java.util.LinkedHashMap;
import java.util.Map;

public class testCacheManager {
    public static void main(String[] args) {
        int cacheSize = 100;  // Specify the cache size
        CacheReplacementPolicy replacementPolicy = new LRUCacheReplacementPolicy(100);

        CacheManager cacheManager = new CacheManager(cacheSize, replacementPolicy);

        // Add some words to the cache
        cacheManager.add("word1");
        cacheManager.add("word2");
        cacheManager.add("word3");

        // Query for words in the cache
        System.out.println(cacheManager.query("word1"));  // true
        System.out.println(cacheManager.query("word4"));  // false
        System.out.println(cacheManager.query("word3"));  // true
    }

    public static class LRUCacheReplacementPolicy implements CacheReplacementPolicy {
        private LinkedHashMap<String, Integer> accessOrderMap;
        private int capacity;

        public LRUCacheReplacementPolicy(int capacity) {
            this.capacity = capacity;
            this.accessOrderMap = new LinkedHashMap<String, Integer>(capacity, 0.75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, Integer> eldest) {
                    return size() > capacity;
                }
            };
        }

        @Override
        public void add(String word) {
            accessOrderMap.put(word, 0);
        }

        @Override
        public String remove() {
            return accessOrderMap.entrySet().iterator().next().getKey();
        }
    }
}
