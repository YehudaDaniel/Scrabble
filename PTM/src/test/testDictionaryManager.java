package test;

public class testDictionaryManager {
    public static void main(String[] args) {
        // Create an instance of the DictionaryManager
        DictionaryManager dm = DictionaryManager.get();

        // Test the query method
        boolean queryResult1 = dm.query("book1", "book2", "query");
        System.out.println("Query Result 1: " + queryResult1);

        boolean queryResult2 = dm.query("book1", "book2", "book3", "query");
        System.out.println("Query Result 2: " + queryResult2);

        // Test the challenge method
        boolean challengeResult1 = dm.challenge("book1", "book2", "challenge");
        System.out.println("Challenge Result 1: " + challengeResult1);

        boolean challengeResult2 = dm.challenge("book1", "book2", "book3", "challenge");
        System.out.println("Challenge Result 2: " + challengeResult2);

        // Test the getSize method
        int size = dm.getSize();
        System.out.println("Dictionary Size: " + size);
    }
}
