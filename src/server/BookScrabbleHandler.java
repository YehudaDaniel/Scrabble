package server;
import java.io.*;
import java.util.Scanner;
public class BookScrabbleHandler implements ClientHandler{
    PrintWriter out;
    Scanner in;
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
                answer = DictionaryManager.get().query(books);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            out.println(answer.toString()+"\n");
        }

    }
    public void close(){
        try {
            in.close();
            out.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
