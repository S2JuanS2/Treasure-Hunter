package treasureHunter.treasureHunterApp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	
    @Override
    public void start(Stage stage) throws ClassNotFoundException, IOException {

    	TreasureHunterGame game = new TreasureHunterGame();
    	//TreasureHunter treasureHunter = new TreasureHunter(game);
    	View vista = new View();
    	vista.mainMenu(stage, game);
        
    }

    public static void main(String[] args) {
        launch();
    }

}