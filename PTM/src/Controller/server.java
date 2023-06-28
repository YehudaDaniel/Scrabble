package Controller;

import Model.Client;
import java.net.InetAddress;
import java.util.Observable;
import java.util.Observer;
import javafx.beans.property.SimpleStringProperty;



public class server<SimpleStringProperty> extends Observable implements Observer {

    public Client myclient;
    
    public SimpleStringProperty Startmygame;
    
    public SimpleStringProperty clientNumOfClients;
    
    public SimpleStringProperty nameclient;

    

    @Override
    public void update(Observable o, Object my) {

        if (my != null && my.toString().equals("s"))
        {
            Startmygame.setValue("start");
            System.out.println(Startmygame.getValue());
            System.out.println("CVM");
        }

        else if (my != null && my.toString().equals("t")) {
            int turn = Integer.parseInt(this.nameclient.getValue());
            turn = 1 + (turn % Integer.parseInt(clientNumOfClients.getValue()));
            this.nameclient = new SimpleStringProperty(String.valueOf(turn));
            System.out.println(this.nameclient.getValue());
        }

        else if (my != null && my.toString().equals("p")) {
        clientNumOfClients.setValue(String.valueOf(Integer.parseInt(clientNumOfClients.getValue()) + 1));
        }

       
    }

public server(int port, InetAddress ip) {
        this.myclient = new Client(port, ip);
        this.nameclient = new SimpleStringProperty();
        this.Startmygame = new SimpleStringProperty();
        this.clientNumOfClients = new SimpleStringProperty();
        myclient.addObserver(this);
    }

}