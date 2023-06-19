package server;


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

//Least Recently Used
public class LRU implements CacheReplacementPolicy {
    LinkedHashSet<String> cache = new LinkedHashSet<>();
    
    @Override
    public void add(String word) {
        cache.remove(word); //if the word is already in the cache, remove it
        cache.add(word); //then add it to the end of the cache
    }

    //remove the first element in the cache because it is the Least Recently Used
    @Override
    public String remove() {
        Iterator<String> iterator = cache.iterator(); //pointing to the first element
        String temp = iterator.next(); //returning the current element and then move to next element
        cache.remove(iterator.next()); //removing the first element

        return temp;
    }
}
