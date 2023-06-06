package test;

import server.CacheManager;
import server.LRU;

public class testLRU {
    public static void main(String[] args) {
        int cacheSize = 3;  // Specify the cache size
        LRU replacementPolicy = new LRU();  // Create an instance of LRU cache replacement policy

        CacheManager cacheManager = new CacheManager(cacheSize, replacementPolicy);

        // Add some key-value pairs to the cache
        cacheManager.add("key1");
        cacheManager.add("key2");
        cacheManager.add("key3");

        // Retrieve values from the cache
        System.out.println(cacheManager.query("key1"));  // true
        System.out.println(cacheManager.query("key4"));  // false
        System.out.println(cacheManager.query("key3"));  // true

        // Add a new key-value pair to the cache, which triggers an eviction
        cacheManager.add("key4");

        // Retrieve values from the cache after eviction
        System.out.println(cacheManager.query("key1"));  // false
        System.out.println(cacheManager.query("key4"));  // true
        System.out.println(cacheManager.query("key3"));  // true
    }
}
