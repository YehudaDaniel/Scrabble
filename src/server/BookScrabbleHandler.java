package server;
import java.io.*;
import java.util.Scanner;
public class BookScrabbleHandler implements ClientHandler{
    PrintWriter out;
    Scanner in;

    //Read string from the client until \n, 
    //if the string starts with "Q" it will use query method,
    //if the string starts with "C" it will use challenge method
    //the rest of the string are books seperated by ",", and the last string is the query
    @Override
    public void handleClient(InputStream inFromclient, OutputStream outToClient) {
        Boolean answer = null;
        try {
            in=new Scanner(new BufferedReader(new InputStreamReader(inFromclient)));
            out=new PrintWriter(outToClient, true);
            String line = in.next();
            String[] books=line.substring(2).split(",");
            if(line.startsWith("Q")) {
                answer = DictionaryManager.get().query(books);
            }
            else if((line.startsWith("C")) ) {
                answer = DictionaryManager.get().challenge(books);
            }
            in.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.println(answer.toString()+"\n");
        }

    }
    public void close(){}
}
