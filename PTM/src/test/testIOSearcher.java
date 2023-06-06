package test;

import server.IOSearcher;

public class testIOSearcher {
    public static void main(String[] args) {
        String word = "example";  // Word to search for
        String[] fileNames = { "file1.txt", "file2.txt", "file3.txt" };  // List of file names to search

        boolean found = IOSearcher.search(word, fileNames);

        if (found) {
            System.out.println("The word '" + word + "' was found in at least one of the files.");
        } else {
            System.out.println("The word '" + word + "' was not found in any of the files.");
        }
    }
}
