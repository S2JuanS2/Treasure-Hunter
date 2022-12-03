package treasureHunter.treasureHunterApp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	
    @Override
    public void start(Stage stage) throws ClassNotFoundException, IOException {

    	TreasureHunterGame game = new TreasureHunterGame();  //modelo
    	View view = new View(stage); //vista
    	TreasureHunter treasureHunter = new TreasureHunter(game, view);  //controlador
    	treasureHunter.start();
        
    }

    public static void main(String[] args) {
        launch();
    }

}