package test;

import server.Dictionary;

public class testDictionary {
    public static void main(String[] args) {
        // Create a Dictionary with two files: "english.txt" and "words.txt"
        Dictionary dictionary = new Dictionary("english.txt", "words.txt");

        // Query words in the dictionary
        System.out.println("Query Results:");
        System.out.println("Query apple: " + dictionary.query("apple"));
        System.out.println("Query banana: " + dictionary.query("banana"));
        System.out.println("Query cherry: " + dictionary.query("cherry"));
        System.out.println("Query grape: " + dictionary.query("grape"));

        // Challenge words in the dictionary
        System.out.println("\nChallenge Results:");
        System.out.println("Challenge apple: " + dictionary.challenge("apple"));
        System.out.println("Challenge banana: " + dictionary.challenge("banana"));
        System.out.println("Challenge cherry: " + dictionary.challenge("cherry"));
        System.out.println("Challenge grape: " + dictionary.challenge("grape"));
    }
}
