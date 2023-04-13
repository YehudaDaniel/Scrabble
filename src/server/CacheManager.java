package server;


import java.util.HashSet;

public class CacheManager {
    HashSet<String> cache=new HashSet<>();//do we need to overreat hashcode
	int size;
    CacheReplacementPolicy crp;

    public CacheManager(int size, CacheReplacementPolicy crp) {
        this.size = size;
        this.crp = crp;
    }
    public boolean query(String word){
            if(cache.contains(word))
                return true;
        return false;
    }
    public void add(String word){
        crp.add(word);
        cache.add(word);
        if(cache.size()>size)
            cache.remove(crp.remove());
    }
}
