package treasureHunter.treasureHunterApp;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {

	private Resources resources;
	private Stage stage;
	private Group group;
	private Scene sceneGame;
	private Scene sceneMenu;
	private Canvas canvasMenu;
	private Canvas canvasGame;
	private GraphicsContext graphicsGame;
	private GraphicsContext graphicsMenu;
	private Button btnBuyFuel;
	private Button btnBuyImproveHook;
	private Button btnBuyImprovePower;
	private Button btnSave;
	private Button btnPause;
	private Button btnBack;
	private Button btnBackGame;
	private Button btnMusicPause;
	private Button btnNewGame;
	private Button btnContinueGame;
	private Button btnExitGame;
	private Button btnHelp;
	private Button btnAbout;
	private Button btnContinue; 
	private Button btnPlayGame;
	private Button btnGoDown;
	private Button btnStart;
	private Button btnFinish;
	private Label lbHelp;
	private Label lbAbout;
	private Label lbPause;
	private Label lbError;
	private TextField name;
	
	public View(Stage stage) {
			
		this.stage = stage;
		
		resources = new Resources();
		
		group = new Group();
		btnNewGame = new Button();
		btnContinueGame = new Button();
		btnExitGame = new Button();
		btnHelp = new Button();
		btnAbout = new Button();
		btnContinue = new Button();
		btnStart = new Button();
		btnFinish = new Button();
		
		lbAbout = new Label();
		lbHelp = new Label();
		lbError = new Label();
		name = new TextField();
		
		btnBuyFuel = new Button();
		btnSave = new Button();
		btnBuyImproveHook = new Button();
		btnBuyImprovePower = new Button();
		btnPause = new Button();
		btnBack = new Button();
		btnBackGame = new Button();
		btnMusicPause = new Button();
		btnPlayGame = new Button();
		btnGoDown = new Button();
		lbPause = new Label("PAUSA");
		
		generateGameButtonStyle();
		generateMenuButtonStyle();
		
	}
	
	public Resources getResources() {
		return resources;
	}
	
	public String getTextField() {
		return(name.getText());
	}
	
	
	
	public Button getBtnBuyFuel() {
		return btnBuyFuel;
	}

	public Button getBtnBuyImproveHook() {
		return btnBuyImproveHook;
	}

	public Button getBtnBuyImprovePower() {
		return btnBuyImprovePower;
	}

	public Button getBtnSave() {
		return btnSave;
	}

	public Button getBtnPause() {
		return btnPause;
	}

	public Button getBtnGoDown() {
		return btnGoDown;
	}

	public boolean maxLengthTextField() {
		return (name.getLength() > 8);
	}
	
	public void actionPlayGame() {
		group.getChildren().remove(btnPlayGame);
		btnPause.setDisable(false);
		resources.getSounds().get("soundSave").play();

	}
	
	public void actionGoDown() {
		if(!group.getChildren().contains(btnPlayGame)) {
			resources.getSounds().get("soundSave").play();
			resources.getSounds().get("soundChain").play();
		}
	}
	
	public void actionBuyFuel() {
		resources.getSounds().get("soundBuy").play();
	}
	
	public void actionPause(boolean stop) {
		if(!stop) {
			resources.getSounds().get("soundChain").stop();
			resources.getSounds().get("soundSave").play();
			group.getChildren().add(lbPause);
			btnBuyFuel.setDisable(true);
			btnBuyImproveHook.setDisable(true);
			btnBuyImprovePower.setDisable(true);
			btnGoDown.setDisable(true);

		}else {
			resources.getSounds().get("soundSave").play();
			group.getChildren().remove(lbPause);
			btnBuyFuel.setDisable(false);
			btnBuyImproveHook.setDisable(false);
			btnBuyImprovePower.setDisable(false);

		}
	}
	
	public void actionMusicPause(boolean musicPause) {
		if(!musicPause) {
			resources.getSounds().get("ambiencePlay").stop();
			resources.getSounds().get("soundSave").play();	
		}else {
			resources.getSounds().get("soundSave").play();
			resources.getSounds().get("ambiencePlay").play();
		}
	}
	
	public void actionBackGame() {
		resources.getSounds().get("soundChain").stop();
		resources.getSounds().get("ambiencePlay").stop();
		resources.getSounds().get("soundSave").play();
	}
	
	public void actionNewGame() {
		resources.getSounds().get("soundClick").play();
		group.getChildren().remove(btnNewGame);
		group.getChildren().remove(btnContinueGame);
		group.getChildren().remove(btnHelp);
		group.getChildren().remove(btnAbout);
		group.getChildren().remove(btnExitGame);
	}
	
	public void actionHelp() {
		resources.getSounds().get("soundClick").play();
		btnNewGame.setDisable(false);
		group.getChildren().remove(btnNewGame);
		group.getChildren().remove(btnContinueGame);
		group.getChildren().remove(btnHelp);
		group.getChildren().remove(btnAbout);
		group.getChildren().remove(btnExitGame);
		helpScene();
	}
	
	public void actionAbout() {
		resources.getSounds().get("soundClick").play();
		group.getChildren().remove(btnNewGame);
		group.getChildren().remove(btnContinueGame);
		group.getChildren().remove(btnHelp);
		group.getChildren().remove(btnAbout);
		group.getChildren().remove(btnExitGame);
		aboutScene();
	}
	
	public void actionContinue() {
		resources.getSounds().get("soundSave").play();
		group.getChildren().remove(btnContinue);
		group.getChildren().remove(lbAbout);
		group.getChildren().remove(lbHelp);
		group.getChildren().add(btnNewGame);
		group.getChildren().add(btnContinueGame);
		group.getChildren().add(btnHelp);
		group.getChildren().add(btnAbout);
		group.getChildren().add(btnExitGame);
	}
	
	public void actionStart() {
		resources.getSounds().get("soundSave").play();
		lbError.setText("*El nombre no puede estar vacío o tener mas de 8 digitos.");
	}
	
	public void recordListenPlayGame(EventHandler<ActionEvent> eventHandler) {
		btnPlayGame.setOnAction(eventHandler);
	}	
	
	public void recordListenGoDown(EventHandler<ActionEvent> eventHandler) {
		btnGoDown.setOnAction(eventHandler);
	}	
	
	public void recordListenBuyFuel(EventHandler<ActionEvent> eventHandler) {
		btnBuyFuel.setOnAction(eventHandler);
	}	
	
	public void recordListenImproveHook(EventHandler<ActionEvent> eventHandler) {
		btnBuyImproveHook.setOnAction(eventHandler);
	}	
	
	public void recordListenImprovePower(EventHandler<ActionEvent> eventHandler) {
		btnBuyImprovePower.setOnAction(eventHandler);
	}	
	
	public void recordListenSave(EventHandler<ActionEvent> eventHandler) {
		btnSave.setOnAction(eventHandler);
	}	
	
	public void recordListenPause(EventHandler<ActionEvent> eventHandler) {
		btnPause.setOnAction(eventHandler);
	}	
	
	public void recordListenMusicPause(EventHandler<ActionEvent> eventHandler) {
		btnMusicPause.setOnAction(eventHandler);
	}	
	
	public void recordListenBackGame(EventHandler<ActionEvent> eventHandler) {
		btnBackGame.setOnAction(eventHandler);
	}	
	
	public void recordListenNewGame(EventHandler<ActionEvent> eventHandler) {
		btnNewGame.setOnAction(eventHandler);
	}
	
	public void recordListenContinueGame(EventHandler<ActionEvent> eventHandler) {
		btnContinueGame.setOnAction(eventHandler);
	}
	
	public void recordListenExitGame(EventHandler<ActionEvent> eventHandler) {
		btnExitGame.setOnAction(eventHandler);
	}
	
	public void recordListenHelp(EventHandler<ActionEvent> eventHandler) {
		btnHelp.setOnAction(eventHandler);
	}
	
	public void recordListenAbout(EventHandler<ActionEvent> eventHandler) {
		btnAbout.setOnAction(eventHandler);
	}
	
	public void recordListenContinue(EventHandler<ActionEvent> eventHandler) {
		btnContinue.setOnAction(eventHandler);
	}
	
	public void recordListenStart(EventHandler<ActionEvent> eventHandler) {
		btnStart.setOnAction(eventHandler);
	}
	

	public void recordListenBack(EventHandler<ActionEvent> eventHandler) {
		btnBack.setOnAction(eventHandler);
	}
	
	public void recordListenFinish(EventHandler<ActionEvent> eventHandler) {
		btnFinish.setOnAction(eventHandler);
	}	
	
	public void refreshCanvas(TreasureHunterGame game) {
		
		graphicsGame.setFont(Font.font(10));
		graphicsGame.clearRect(0,0,canvasGame.getWidth(),canvasGame.getHeight());
		graphicsGame.drawImage(resources.getImages().get("fondo"), 0, 0);
		game.getHook().draw(graphicsGame, resources);
		
		if(!game.getTreasure().isEmpty()) {
			Iterator<Treasure> it = game.getTreasure().iterator();
			while(it.hasNext()) {
				it.next().draw(graphicsGame, resources);
			}
		}
		
		if(game.getPlayer().getTreasure() != null) {
			graphicsGame.drawImage(resources.getImages().get(game.getPlayer().getTreasure().getNameImage()), game.getHook().getPosition().getX(), game.getHook().getPosition().getY()+10);
		}
		
		graphicsGame.setFill(Color.GRAY);
		graphicsGame.fillRect(20, 9, 2000/5, 11);
		graphicsGame.setFill(Color.DARKRED);
		graphicsGame.setFont(Font.font(12));
		graphicsGame.fillRect(20, 9, game.getHook().getEngine().getFuel()/5, 11);
		graphicsGame.setFill(Color.WHITE);
		graphicsGame.fillText(String.valueOf(game.getHook().getEngine().getFuel()), 20, 19);
		graphicsGame.setFill(Color.BLACK);
		graphicsGame.setFont(Font.font(14));
		graphicsGame.fillText(game.getPlayer().getName(), 460, 18);
		graphicsGame.setFill(Color.GREEN);
		graphicsGame.fillText("$" + String.valueOf(game.getPlayer().getBalance()) + ",00", 460, 33);
		graphicsGame.setFont(Font.font(10));
		graphicsGame.setFill(Color.BLACK);
		graphicsGame.fillText(String.valueOf("$" +Store.FUEL_COST), 30,68);
		graphicsGame.fillText(String.valueOf("$" +Store.COST_UPGRADE_HOOK), 107,68);
		graphicsGame.fillText(String.valueOf("$" +Store.COST_UPGRADE_ENGINE), 148,68);
		graphicsGame.fillText("Save", 67,68);
		graphicsGame.fillText("Down", 204,68);
		graphicsGame.fillText("Back", 605,71);
		graphicsGame.fillText("Pause", 575,34);
		graphicsGame.fillText("Mute", 605,34);
		graphicsGame.setFill(Color.BURLYWOOD);
		graphicsGame.fillRect(game.getHook().getPosition().getX()+14, 76, 3, game.getHook().getPosition().getY()-73);
		graphicsGame.fillRect(10, 76, 2, 2);
		graphicsGame.fillRect(10, 78, 2, 2);
		graphicsGame.fillRect(10, 77, 620, 2);
		graphicsGame.fillRect(628, 76, 2, 2);
		graphicsGame.fillRect(628, 78, 2, 2);

		for (int i = 10; i < 640; i = i + 40) {
			graphicsGame.fillRect(i, game.getHook().getLength()+120, 20, 2);				
		}
	}

	public void generateGameButtonStyle() {
		
		btnBuyFuel.setLayoutX(20);
		btnBuyFuel.setLayoutY(28);
		btnBuyFuel.setPrefSize(27, 32);
		btnBuyFuel.setBackground(resources.getBackground().get("gas"));
		btnBuyFuel.setCursor(new ImageCursor(resources.getImages().get("handHover")));
				
		btnSave.setLayoutX(60);
		btnSave.setLayoutY(28);
		btnSave.setPrefSize(28, 32);
		btnSave.setBackground(resources.getBackground().get("save"));
		btnSave.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnBuyImproveHook.setLayoutX(100);
		btnBuyImproveHook.setLayoutY(28);
		btnBuyImproveHook.setPrefSize(32, 32);
		btnBuyImproveHook.setBackground(resources.getBackground().get("improveHook"));
		btnBuyImproveHook.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnBuyImprovePower.setLayoutX(140);
		btnBuyImprovePower.setLayoutY(28);
		btnBuyImprovePower.setPrefSize(32, 32);
		btnBuyImprovePower.setBackground(resources.getBackground().get("improveEngine"));
		btnBuyImprovePower.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnPause.setLayoutX(573);
		btnPause.setLayoutY(0);
		btnPause.setPrefSize(32, 32);
		btnPause.setScaleX(0.7);
		btnPause.setScaleY(0.7);
		btnPause.setBackground(resources.getBackground().get("pause"));
		btnPause.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnMusicPause.setLayoutX(600);
		btnMusicPause.setLayoutY(0);
		btnMusicPause.setPrefSize(32, 32);
		btnMusicPause.setScaleX(0.6);
		btnMusicPause.setScaleY(0.6);
		btnMusicPause.setBackground(resources.getBackground().get("musicPause"));
		btnMusicPause.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnBackGame.setLayoutX(600);
		btnBackGame.setLayoutY(35);
		btnBackGame.setScaleX(0.8);
		btnBackGame.setScaleY(0.8);
		btnBackGame.setPrefSize(32, 32);
		btnBackGame.setBackground(resources.getBackground().get("back"));
		btnBackGame.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnPlayGame.setLayoutX(270);
		btnPlayGame.setLayoutY(20);
		btnPlayGame.setPrefSize(120,50);
		btnPlayGame.setScaleX(0.8);
		btnPlayGame.setScaleY(0.8);
		btnPlayGame.setBackground(resources.getBackground().get("play"));
		btnPlayGame.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnGoDown.setLayoutX(200);
		btnGoDown.setLayoutY(28);
		btnGoDown.setPrefSize(32,32);
		btnGoDown.setBackground(resources.getBackground().get("goDown"));
		btnGoDown.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		lbPause.setLayoutX(320);
		lbPause.setLayoutY(90);
		lbPause.setTextFill(Color.WHITE);
		
	}
	
	public void generateMenuButtonStyle() {
		
		btnNewGame.setPrefSize(155, 22);
		btnNewGame.setBackground(resources.getBackground().get("newGame"));
		btnNewGame.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnNewGame.setLayoutX(240);
		btnNewGame.setLayoutY(260);
		
		btnContinueGame.setPrefSize(190, 22);
		btnContinueGame.setBackground(resources.getBackground().get("continueGame"));
		btnContinueGame.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnContinueGame.setLayoutX(240);
		btnContinueGame.setLayoutY(300);
		
		btnExitGame.setPrefSize(60, 22);
		btnExitGame.setBackground(resources.getBackground().get("exit"));
		btnExitGame.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnExitGame.setLayoutX(240);
		btnExitGame.setLayoutY(420);
		
		btnHelp.setPrefSize(75, 22);
		btnHelp.setBackground(resources.getBackground().get("help"));
		btnHelp.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnHelp.setLayoutX(240);
		btnHelp.setLayoutY(340);
		
		btnAbout.setPrefSize(100, 22);
		btnAbout.setBackground(resources.getBackground().get("about"));
		btnAbout.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnAbout.setLayoutX(240);
		btnAbout.setLayoutY(380);
		
		btnContinue.setBackground(resources.getBackground().get("continue"));
		btnContinue.setPrefSize(32,32);
		btnContinue.setLayoutX(520);
		btnContinue.setLayoutY(410);
		btnContinue.setCursor(new ImageCursor(resources.getImages().get("handHover"))); //general
		
		btnFinish.setBackground(resources.getBackground().get("continue"));
		btnFinish.setPrefSize(32,32);
		btnFinish.setLayoutX(420);
		btnFinish.setLayoutY(340);
		btnFinish.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnStart.setBackground(resources.getBackground().get("continue"));
		btnStart.setLayoutX(520);
		btnStart.setLayoutY(410);
		btnStart.setPrefSize(32,32);
		btnStart.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnBack.setLayoutX(562);
		btnBack.setLayoutY(410);
		btnBack.setPrefSize(32,32);
		btnBack.setBackground(resources.getBackground().get("back"));
		btnBack.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		lbAbout.setBackground(resources.getBackground().get("aboutText"));
		lbAbout.setPrefSize(350, 300);
		lbAbout.setLayoutX(150);
		lbAbout.setLayoutY(300);
		
		lbHelp.setBackground(resources.getBackground().get("helpText"));
		lbHelp.setPrefSize(550, 110);
		lbHelp.setLayoutX(100);
		lbHelp.setLayoutY(300);
		
		lbError.setLayoutX(135);
		lbError.setLayoutY(420);
		lbError.setFont(Font.font(14));
		lbError.setTextFill(Color.DARKRED);
		
		name.setBackground(resources.getBackground().get("pergaminoName"));
		name.setPrefSize(300,140);
		name.setLayoutX(150);
		name.setLayoutY(280);
		name.setFont(new Font(28));
		name.setAlignment(Pos.CENTER);
		name.setCursor(new ImageCursor(resources.getImages().get("pluma")));

	}
	
	public void loadStageGame() {
		
		group = new Group();	
		sceneGame = new Scene(group, 640, 480);
		sceneGame.setCursor(new ImageCursor(resources.getImages().get("hand")));
		
		canvasGame = new Canvas(640, 480);
		graphicsGame = canvasGame.getGraphicsContext2D();
		
		group.getChildren().add(canvasGame);
		group.getChildren().add(btnBuyFuel);
		group.getChildren().add(btnSave);
		group.getChildren().add(btnBuyImproveHook);
		group.getChildren().add(btnBuyImprovePower);
		group.getChildren().add(btnPause);
		group.getChildren().add(btnMusicPause);
		group.getChildren().add(btnBackGame);
		group.getChildren().add(btnPlayGame);
		group.getChildren().add(btnGoDown);
					
		stage.setScene(sceneGame);
		stage.setTitle("Treasure Hunter");
		stage.setResizable(false);
		stage.show();
		
		resources.getSounds().get("soundAmbience").stop();
		resources.getSounds().get("ambiencePlay").play();
	}
	
	public void loadStageMenu() {
		
		group = new Group();
		
		stage.getIcons().add(resources.getImages().get("cofre"));
				
		sceneMenu = new Scene(group, 640, 480);
		sceneMenu.setCursor(new ImageCursor(resources.getImages().get("hand")));
		
		canvasMenu = new Canvas(640, 480);
		
		group.getChildren().add(canvasMenu);
		group.getChildren().add(btnNewGame);
		group.getChildren().add(btnContinueGame);
		group.getChildren().add(btnExitGame);
		group.getChildren().add(btnHelp);
		group.getChildren().add(btnAbout);
		
		graphicsMenu = canvasMenu.getGraphicsContext2D();
		graphicsMenu.drawImage(resources.getImages().get("fondoMenu"), 0, 0);
		graphicsMenu.drawImage(resources.getImages().get("logo"), 50, 30);
		
		stage.setScene(sceneMenu);
		stage.setTitle("Treasure Hunter");
		stage.setResizable(false);
		stage.show();
	}
	
	public void gameScene() {
				
		loadStageGame();								
	}
	
	public void menuScene(boolean existFile) {
		
		loadStageMenu();
		
		if(!resources.getSounds().get("soundAmbience").isPlaying()) {
			resources.getSounds().get("soundAmbience").play();			
		}			
		if(existFile) {
			btnContinueGame.setDisable(true);
			btnNewGame.setDisable(true);
		}else {
			btnContinueGame.setDisable(false);
		}					
	}
	
	public void aboutScene() {
				
		group.getChildren().add(btnContinue);
		group.getChildren().add(lbAbout);
	}
	
	public void helpScene() {
					
		group.getChildren().add(btnContinue);
		group.getChildren().add(lbHelp);
		
	}
	
	public void nameScene() {
		
		group.getChildren().add(btnStart);
		group.getChildren().add(name);
		group.getChildren().add(btnBack);
		group.getChildren().add(lbError);		
	}
	
	public void endScene() {
		
		group.getChildren().add(btnFinish);
		
	}	
	
	public void disableButtons() {
		btnGoDown.setDisable(true);
		btnBuyFuel.setDisable(true);
		btnBuyImproveHook.setDisable(true);
		btnBuyImprovePower.setDisable(true);
		btnSave.setDisable(true);
		btnPause.setDisable(true);
	}
	
	public void drawParticlePrice(float price, int x, double y) {
		graphicsGame.setFill(Color.GREEN);	
		graphicsGame.setFont(Font.font(14));
		graphicsGame.fillText("+ $" + price, x, y);
	}
	
	public void drawWithoutFuel(int x, int y) {
		graphicsGame.setFill(Color.RED);	
		graphicsGame.setFont(Font.font(12));
		graphicsGame.fillText("Sin combustible", x, y);
	}
	
	public void drawDefeat() {
		resources.getSounds().get("soundNoFuel").stop();
		resources.getSounds().get("ambiencePlay").stop();
		resources.getSounds().get("soundDefeat").play();
		disableButtons();
		graphicsGame.drawImage(resources.getImages().get("pergamino"), 160, 160);
		graphicsGame.drawImage(resources.getImages().get("derrota"), 200, 240);
	}
	
	public void drawVictory() {
		getResources().getSounds().get("ambiencePlay").stop();
		getResources().getSounds().get("soundChain").stop();
		getResources().getSounds().get("soundWin").play();
		disableButtons();
		graphicsGame.drawImage(resources.getImages().get("pergamino"), 160, 160);
		graphicsGame.drawImage(resources.getImages().get("victoria"), 200, 240);
	}
		
}
