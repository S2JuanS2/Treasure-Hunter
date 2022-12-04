package treasureHunter.treasureHunterApp;

import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View extends Interactions{
	
	private static final String TITLE = "Treasure Hunter";
	
	private Resources resources;
	private Stage stage;
	private Group group;
	private Scene sceneGame;
	private Scene sceneMenu;
	private Canvas canvasMenu;
	private Canvas canvasGame;
	private GraphicsContext graphicsGame;
	private GraphicsContext graphicsMenu;
	
	public View(Stage stage) {
			
		super();
		this.stage = stage;
		resources = new Resources();	
		group = new Group();
		generateGameButtonStyle(resources);
		generateMenuButtonStyle(resources);
	}
	
	public Resources getResources() {
		return resources;
	}
	
	public void actionPlayGame() {
		group.getChildren().remove(getBtnPlayGame());
		getBtnPause().setDisable(false);
		resources.playSound(Resources.SOUND_SAVE);

	}
	
	public void actionPause(boolean stop) {
		if(!stop) {
			resources.stopSound(Resources.SOUND_CHAIN);
			resources.playSound(Resources.SOUND_SAVE);
			group.getChildren().add(getLbPause());
			getBtnBuyFuel().setDisable(true);
			getBtnBuyImproveHook().setDisable(true);
			getBtnBuyImprovePower().setDisable(true);
			getBtnGoDown().setDisable(true);
		}else {
			resources.playSound(Resources.SOUND_SAVE);
			group.getChildren().remove(getLbPause());
			getBtnBuyFuel().setDisable(false);
			getBtnBuyImproveHook().setDisable(false);
			getBtnBuyImprovePower().setDisable(false);

		}
	}
	
	public void actionMusicPause(boolean musicPause) {
		
		resources.playSound(Resources.SOUND_SAVE);	
		if(!musicPause) {
			resources.stopSound(Resources.SOUND_AMBIENCE);
		}else {
			resources.playSound(Resources.SOUND_AMBIENCE);
		}
	}
	
	public void actionBackGame() {
		resources.stopSound(Resources.SOUND_CHAIN);
		resources.stopSound(Resources.SOUND_AMBIENCE);
		resources.playSound(Resources.SOUND_SAVE);
	}
	
	public void removeBtn() {
		group.getChildren().remove(getBtnNewGame());
		group.getChildren().remove(getBtnContinueGame());
		group.getChildren().remove(getBtnHelp());
		group.getChildren().remove(getBtnAbout());
		group.getChildren().remove(getBtnExitGame());
	}
	
	public void actionNewGame() {
		resources.playSound(Resources.SOUND_CLICK);
		removeBtn();
	}
	
	public void actionHelp() {
		resources.playSound(Resources.SOUND_CLICK);
		getBtnNewGame().setDisable(false);
		removeBtn();
		helpScene();
	}
	
	public void actionAbout() {
		resources.playSound(Resources.SOUND_CLICK);
		removeBtn();
		aboutScene();
	}
	
	public void actionContinue() {
		resources.playSound(Resources.SOUND_SAVE);
		group.getChildren().remove(getBtnContinue());
		group.getChildren().remove(getLbAbout());
		group.getChildren().remove(getLbHelp());
		group.getChildren().add(getBtnNewGame());
		group.getChildren().add(getBtnContinueGame());
		group.getChildren().add(getBtnHelp());
		group.getChildren().add(getBtnAbout());
		group.getChildren().add(getBtnExitGame());
	}
	
	public void actionStart() {
		resources.playSound(Resources.SOUND_SAVE);
		getLbError().setText("*El nombre no puede estar vacío o tener mas de 8 digitos.");
	}
	
	public void recordListenPlayGame(EventHandler<ActionEvent> eventHandler) {
		getBtnPlayGame().setOnAction(eventHandler);
	}	
	
	public void recordListenGoDown(EventHandler<ActionEvent> eventHandler) {
		getBtnGoDown().setOnAction(eventHandler);
	}	
	
	public void recordListenBuyFuel(EventHandler<ActionEvent> eventHandler) {
		getBtnBuyFuel().setOnAction(eventHandler);
	}	
	
	public void recordListenImproveHook(EventHandler<ActionEvent> eventHandler) {
		getBtnBuyImproveHook().setOnAction(eventHandler);
	}	
	
	public void recordListenImprovePower(EventHandler<ActionEvent> eventHandler) {
		getBtnBuyImprovePower().setOnAction(eventHandler);
	}	
	
	public void recordListenSave(EventHandler<ActionEvent> eventHandler) {
		getBtnSave().setOnAction(eventHandler);
	}	
	
	public void recordListenPause(EventHandler<ActionEvent> eventHandler) {
		getBtnPause().setOnAction(eventHandler);
	}	
	
	public void recordListenMusicPause(EventHandler<ActionEvent> eventHandler) {
		getBtnMusicPause().setOnAction(eventHandler);
	}	
	
	public void recordListenBackGame(EventHandler<ActionEvent> eventHandler) {
		getBtnBackGame().setOnAction(eventHandler);
	}	
	
	public void recordListenNewGame(EventHandler<ActionEvent> eventHandler) {
		getBtnNewGame().setOnAction(eventHandler);
	}
	
	public void recordListenContinueGame(EventHandler<ActionEvent> eventHandler) {
		getBtnContinueGame().setOnAction(eventHandler);
	}
	
	public void recordListenExitGame(EventHandler<ActionEvent> eventHandler) {
		getBtnExitGame().setOnAction(eventHandler);
	}
	
	public void recordListenHelp(EventHandler<ActionEvent> eventHandler) {
		getBtnHelp().setOnAction(eventHandler);
	}
	
	public void recordListenAbout(EventHandler<ActionEvent> eventHandler) {
		getBtnAbout().setOnAction(eventHandler);
	}
	
	public void recordListenContinue(EventHandler<ActionEvent> eventHandler) {
		getBtnContinue().setOnAction(eventHandler);
	}
	
	public void recordListenStart(EventHandler<ActionEvent> eventHandler) {
		getBtnStart().setOnAction(eventHandler);
	}
	
	public void recordListenBack(EventHandler<ActionEvent> eventHandler) {
		getBtnBack().setOnAction(eventHandler);
	}
	
	public void recordListenFinish(EventHandler<ActionEvent> eventHandler) {
		getBtnFinish().setOnAction(eventHandler);
	}	
	
	public void refreshCanvas(TreasureHunterGame game) {
		
		graphicsGame.setFont(Font.font(10));
		graphicsGame.clearRect(0,0,canvasGame.getWidth(),canvasGame.getHeight());
		graphicsGame.drawImage(resources.getImages().get(Resources.FONDO), 0, 0);
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

	public void loadStageGame() {
		
		group = new Group();	
		sceneGame = new Scene(group, TreasureHunterGame.MAP_WIDTH, TreasureHunterGame.MAP_DEPTH);
		sceneGame.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND)));
		
		canvasGame = new Canvas(TreasureHunterGame.MAP_WIDTH, TreasureHunterGame.MAP_DEPTH);
		graphicsGame = canvasGame.getGraphicsContext2D();
		
		group.getChildren().add(canvasGame);
		group.getChildren().add(getBtnBuyFuel());
		group.getChildren().add(getBtnSave());
		group.getChildren().add(getBtnBuyImproveHook());
		group.getChildren().add(getBtnBuyImprovePower());
		group.getChildren().add(getBtnPause());
		group.getChildren().add(getBtnMusicPause());
		group.getChildren().add(getBtnBackGame());
		group.getChildren().add(getBtnPlayGame());
		group.getChildren().add(getBtnGoDown());
					
		stage.setScene(sceneGame);
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.show();
		
		resources.stopSound(Resources.SOUND_AMBIENCE_MENU);
		resources.playSound(Resources.SOUND_AMBIENCE);
	}
	
	public void loadStageMenu() {
		
		group = new Group();
		
		stage.getIcons().add(resources.getImages().get(Resources.ICON_CHEST));
				
		sceneMenu = new Scene(group, TreasureHunterGame.MAP_WIDTH, TreasureHunterGame.MAP_DEPTH);
		sceneMenu.setCursor(new ImageCursor(resources.getImages().get(Resources.HAND)));
		
		canvasMenu = new Canvas(TreasureHunterGame.MAP_WIDTH, TreasureHunterGame.MAP_DEPTH);
		
		group.getChildren().add(canvasMenu);
		group.getChildren().add(getBtnNewGame());
		group.getChildren().add(getBtnContinueGame());
		group.getChildren().add(getBtnExitGame());
		group.getChildren().add(getBtnHelp());
		group.getChildren().add(getBtnAbout());
		
		graphicsMenu = canvasMenu.getGraphicsContext2D();
		graphicsMenu.drawImage(resources.getImages().get(Resources.FONDO_MENU), 0, 0);
		graphicsMenu.drawImage(resources.getImages().get(Resources.LOGO), 50, 30);
		
		stage.setScene(sceneMenu);
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.show();
	}
	
	public void menuScene(boolean existFile) {
		
		loadStageMenu();
		
		resources.loopSound(Resources.SOUND_AMBIENCE_MENU);
		
		if(existFile) {
			getBtnContinueGame().setDisable(true);
			getBtnNewGame().setDisable(true);
		}else {
			getBtnContinueGame().setDisable(false);
		}					
	}
	
	public void aboutScene() {
				
		group.getChildren().add(getBtnContinue());
		group.getChildren().add(getLbAbout());
	}
	
	public void helpScene() {
					
		group.getChildren().add(getBtnContinue());
		group.getChildren().add(getLbHelp());
		
	}
	
	public void nameScene() {
		
		group.getChildren().add(getBtnStart());
		group.getChildren().add(getName());
		group.getChildren().add(getBtnBack());
		group.getChildren().add(getLbError());		
	}
	
	public void endScene(boolean victory) {
		
		graphicsGame.drawImage(resources.getImages().get(Resources.PARCHMENT), 160, 160);
		if(victory) {
			getResources().playSound(Resources.SOUND_WIN);
			graphicsGame.drawImage(resources.getImages().get(Resources.VICTORY), 200, 240);
		}else {
			getResources().playSound(Resources.SOUND_DEFEAT);
			graphicsGame.drawImage(resources.getImages().get(Resources.DEFEAT), 200, 240);
		}
		
		disableButtons();
		getResources().stopSound(Resources.SOUND_AMBIENCE);
		getResources().stopSound(Resources.SOUND_CHAIN);
		getResources().stopSound(Resources.SOUND_NO_FUEL);
		group.getChildren().add(getBtnFinish());
			
	}	
	
	public void disableButtons() {
		getBtnGoDown().setDisable(true);
		getBtnBuyFuel().setDisable(true);
		getBtnBuyImproveHook().setDisable(true);
		getBtnBuyImprovePower().setDisable(true);
		getBtnSave().setDisable(true);
		getBtnPause().setDisable(true);
	}
	
	public void drawEffectPrice(float price, int x, double y) {
		graphicsGame.setFill(Color.GREEN);	
		graphicsGame.setFont(Font.font(14));
		graphicsGame.fillText("+ $" + price, x, y);
	}
	
	public void drawWithoutFuel(int x, int y) {
		graphicsGame.setFill(Color.RED);	
		graphicsGame.setFont(Font.font(12));
		graphicsGame.fillText("Sin combustible", x, y);
		getResources().playSound(Resources.SOUND_NO_FUEL);
		getResources().stopSound(Resources.SOUND_CHAIN);
	}		
}
