package Model;
import java.util.*;
import java.lang.String;

public class DictionaryManager extends Dictionary
{
    public HashMap<String, Dictionary> Mymap;
    private static DictionaryManager dictionaryManager = null;

    private DictionaryManager(String... args) {
        this.Mymap = new HashMap<>();
    }

    public static DictionaryManager get() {
        if (dictionaryManager == null) { dictionaryManager = new DictionaryManager(); }
        return dictionaryManager;
    }

    public boolean query(String...args) {
        String wordToBeSearch = args[args.length - 1];
        boolean wordExists;
        int flag = 0;

        for (int j = 0; j < args.length-1; j++) {
            if (!Mymap.containsKey(args[j]))
            {
                String Mystring = args[j];
                Dictionary dic = new Dictionary(Mystring);
                Mymap.put(Mystring, dic);
            }

            wordExists = Mymap.get(args[j]).query(wordToBeSearch);
            if (wordExists) {
                Mymap.get(args[j]).existsCacheManager.words.add((wordToBeSearch));
                dictionaryManager.existsCacheManager.words.add(wordToBeSearch);
                flag = 1;
            }
            else
                Mymap.get(args[j]).notExistsCacheManager.words.add((wordToBeSearch));
        }
        if (flag == 1)
            return true;
        else {
            dictionaryManager.notExistsCacheManager.words.add(wordToBeSearch);
            return false;
        }
    }

    public boolean challenge(String... args)
    {
        String wordToBeSearch = args[args.length - 1];
        boolean wordExists;
        int B = 0;

        for (int j = 0; j < args.length-1; j++) {
            if (!Mymap.containsKey(args[j]))
            {
                String s = args[j];
                Dictionary dic = new Dictionary(s);
                Mymap.put(s, dic);
            }

            wordExists = Mymap.get(args[j]).challenge(wordToBeSearch);
            if (wordExists) {
                Mymap.get(args[j]).existsCacheManager.words.add((wordToBeSearch));
                dictionaryManager.existsCacheManager.words.add(wordToBeSearch);
                B = 1;
            }
            else
                Mymap.get(args[j]).notExistsCacheManager.words.add((wordToBeSearch));
        }
        if (B == 1)
            return true;
        else {
            dictionaryManager.notExistsCacheManager.words.add(wordToBeSearch);
            return false;
        }
    }
    public int getSize() { return this.Mymap.keySet().size(); }
}
