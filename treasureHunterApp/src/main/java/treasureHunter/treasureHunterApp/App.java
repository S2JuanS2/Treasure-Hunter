package treasureHunter.treasureHunterApp;

import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	
	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	public static final String YES = "S";
	public static final String NO = "N";
	
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