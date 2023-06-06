package test;

public class testBloomFilter {
    public static void main(String[] args) {
        // Create a BloomFilter with size 100 and hash functions MD5 and SHA-1
        BloomFilter bloomFilter = new BloomFilter(100, "MD5", "SHA-1");

        // Add words to the BloomFilter
        bloomFilter.add("apple");
        bloomFilter.add("banana");
        bloomFilter.add("cherry");

        // Check if words are present in the BloomFilter
        System.out.println("Contains apple: " + bloomFilter.contains("apple"));
        System.out.println("Contains banana: " + bloomFilter.contains("banana"));
        System.out.println("Contains cherry: " + bloomFilter.contains("cherry"));
        System.out.println("Contains grape: " + bloomFilter.contains("grape"));
    }
}
