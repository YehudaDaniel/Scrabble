package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

//Given a word and a file, searches the whole file for the word given and returns true if found, false otherwise
public class IOSearcher {
    public static boolean search(String word, String...fileNames){
        Scanner scanner=null;
        for(String file:fileNames){
            try {
                scanner=new Scanner(new BufferedReader(new FileReader(file)));
                while(scanner.hasNext()){
                    if(scanner.next().matches(word))
                        return true;
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }scanner.close();
        }return false;
    }

}
