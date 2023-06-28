package Controller;

import Model.GameManager;
import Model.Host;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import java.util.Observable;
import java.util.Observer;

public class game<SimpleStringProperty, SimpleIntegerProperty> implements Observer {

    public GameManager gameManager;     
    
    public SimpleIntegerProperty ourrounds;
    
    public SimpleStringProperty hostPort;
    
    public SimpleStringProperty ourOfClients;
    
    public SimpleStringProperty playerWord;
    
    
    public game(GameManager gameManagerModel) {
        this.ourOfClients = new SimpleStringProperty();
        this.playerWord = new SimpleStringProperty();
        this.hostPort = new SimpleStringProperty();
        this.ourrounds = new SimpleIntegerProperty();
        this.gameManager = gameManagerModel;
        
        
        
        this.gameManager.host.addObserver(this);
    }

    
    
    @Override
    public void update(Observable o, Object arg) {
        int num = this.gameManager.host.ourOfClients;
        int numOfRounds = this.gameManager.host.ourrounds;
        this.ourOfClients.bindBidirectional(new SimpleStringProperty(String.valueOf(num)));
        this.ourrounds.bindBidirectional(new SimpleIntegerProperty(numOfRounds));
        System.out.println("The Update In The ViewModel- Number Of Clients: " + ourOfClients.getValue());
    }

   
}














//    @FXML
//    public ObservableMap<String, List<SimpleObjectProperty<Tile>>> map = FXCollections.observableHashMap();