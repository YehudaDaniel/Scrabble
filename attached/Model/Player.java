package Model;

import server.Word;

public interface Player {
    default void playType(int type){
        if(type == 1) placeWord(null);
        if(type == 2) challenge(null);
        else System.out.println("Wrong type in function { playType(Type) }");
    }

    int placeWord(Word word);
    boolean challenge(String s);
}
