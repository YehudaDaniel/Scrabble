package server;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Dictionary {
    CacheManager cacheLRU;
    CacheManager cacheLFU;
    BloomFilter bloomFilter;
    String[] filenames;

    public Dictionary(String...fileNames) {
        filenames=fileNames;
        this.cacheLRU = new CacheManager(400, new LRU());
        this.cacheLFU = new CacheManager(100, new LFU());
        this.bloomFilter = new BloomFilter(256, "MD5","SHA1");
        Scanner scanner=null;
        for(String file:fileNames){
            try {
                scanner=new Scanner(new BufferedReader(new FileReader(file)));
                while(scanner.hasNext()){
                    bloomFilter.add(scanner.next());
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }scanner.close();
        }
    }

    //given a word, checks in the existing words, then in the non existing words
    //then in the bloomFilter
    public boolean query(String word){
        if(cacheLRU.query(word))
            return true;
        else if(cacheLFU.query(word))
            return false;
        else {
            if(bloomFilter.contains(word)){
                cacheLRU.add(word);
                return true;
            }
            else {
                cacheLFU.add(word);
                return false;
            }
        }
    }

    //given a word, using the IOSearcher search the word and add it to the cache
    public boolean challenge(String word){
        try {
            if (IOSearcher.search(word,filenames)){
                cacheLRU.add(word);
                return true;
            } else {
                cacheLFU.add(word);
                return false;
            }
        }
        catch (Exception e){return false;}
    }
}
