package treasureHunter.treasureHunterApp;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class View {
	
	public static HashMap<String, Image> images;
	public static HashMap<String, AudioClip> sounds;
	private static Group group;
	private static Scene sceneGame;
	private static Scene sceneMenu;
	private static Canvas canvasMenu;
	private static Canvas canvasGame;
	private static GraphicsContext graphicsGame;
	private static GraphicsContext graphicsMenu;
	private static Button btnBuyFuel;
	private static Button btnBuyImproveHook;
	private static Button btnBuyImprovePower;
	private static Button btnSave;
	private static Button btnNewGame;
	private static Button btnContinueGame;
	private static Button btnExitGame;
	private boolean moveLeft = false;
	private boolean moveRight = true;
	private boolean action = false;
	private boolean goDown = false;
	private boolean goUp = false;
	
	public View() {
		
		
		images = new HashMap<String,Image>();
		sounds = new HashMap<String,AudioClip>();
		loadImages();
		loadSounds();
		
	}
	
	public void loadImages() {
		images.put("oro", new Image(getClass().getResource("/res/oro.png").toExternalForm()));
		images.put("fondo",new Image(getClass().getResource("/res/fondo.png").toExternalForm()));
		images.put("hook", new Image(getClass().getResource("/res/hook.png").toExternalForm()));
		images.put("diamond", new Image(getClass().getResource("/res/diamante.png").toExternalForm()));
		images.put("iron", new Image(getClass().getResource("/res/hierro.png").toExternalForm()));
		images.put("calavera", new Image(getClass().getResource("/res/calavera.png").toExternalForm()));
		images.put("ruby", new Image(getClass().getResource("/res/ruby.png").toExternalForm()));
		images.put("gas", new Image(getClass().getResource("/res/gasolina.png").toExternalForm()));
		images.put("save", new Image(getClass().getResource("/res/save.png").toExternalForm()));
		images.put("hand", new Image(getClass().getResource("/res/mano.png").toExternalForm()));
		images.put("fondoMenu", new Image(getClass().getResource("/res/fondoMenu.png").toExternalForm()));
		images.put("logo", new Image(getClass().getResource("/res/logo.png").toExternalForm()));
		images.put("nuevaPartida", new Image(getClass().getResource("/res/nuevaPartida.png").toExternalForm()));
		images.put("continuarPartida", new Image(getClass().getResource("/res/continuarPartida.png").toExternalForm()));
		images.put("salir", new Image(getClass().getResource("/res/salir.png").toExternalForm()));
		images.put("handHover", new Image(getClass().getResource("/res/manoHover.png").toExternalForm()));
		images.put("moneda", new Image(getClass().getResource("/res/moneda.png").toExternalForm()));
		images.put("cofre", new Image(getClass().getResource("/res/cofre.png").toExternalForm()));
		images.put("planta", new Image(getClass().getResource("/res/planta.png").toExternalForm()));
		images.put("cadena", new Image(getClass().getResource("/res/cadena.png").toExternalForm()));
		images.put("llave", new Image(getClass().getResource("/res/llave.png").toExternalForm()));
		images.put("saco", new Image(getClass().getResource("/res/saco.png").toExternalForm()));
		images.put("piedra", new Image(getClass().getResource("/res/piedra.png").toExternalForm()));
		images.put("caldero", new Image(getClass().getResource("/res/caldero.png").toExternalForm()));
	}
	
	public void loadSounds() {		
		sounds.put("soundClick", new AudioClip(getClass().getResource("/res/clickMenu.wav").toExternalForm()));
		sounds.put("soundWin", new AudioClip (getClass().getResource("/res/win.wav").toExternalForm()));
		sounds.put("soundBuy", new AudioClip(getClass().getResource("/res/buy.wav").toExternalForm()));
		sounds.put("soundNoFuel", new AudioClip(getClass().getResource("/res/noFuel.wav").toExternalForm()));
		sounds.put("soundChain", new AudioClip(getClass().getResource("/res/cadena.wav").toExternalForm()));
		sounds.put("soundAmbience", new AudioClip(getClass().getResource("/res/ambience.wav").toExternalForm()));
	}
	
	
	public void refreshCanvas(TreasureHunterGame game) {
		
		graphicsGame.clearRect(0,0,canvasGame.getWidth(),canvasGame.getHeight());
		graphicsGame.drawImage(images.get("fondo"), 0, 0);
		graphicsGame.drawImage(images.get("hook"), game.getHook().getPosition().getX(), game.getHook().getPosition().getY());;
		
		if(!game.getTreasure().isEmpty()) {
			Iterator<Treasure> it = game.getTreasure().iterator();
			while(it.hasNext()) {
				it.next().draw(graphicsGame);
			}
		}
		graphicsGame.setFill(Color.WHITE);
		graphicsGame.fillRect(20, 10, 2000/5, 10);
		graphicsGame.setFill(Color.RED);
		graphicsGame.fillRect(20, 10, game.getHook().getEngine().getFuel()/5, 10);
		graphicsGame.setFill(Color.WHITE);
		graphicsGame.fillText(String.valueOf(game.getHook().getEngine().getFuel()), 20, 20);
		graphicsGame.setFill(Color.GREEN);
		graphicsGame.fillText("$" + String.valueOf(game.getPlayer().getBalance()) + ",00", 580, 20);
		graphicsGame.setFill(Color.WHITE);
		graphicsGame.fillText(String.valueOf(game.getHook().getLength()) + "m", 540, 20);
		graphicsGame.fillText(" " + String.valueOf(game.getHook().getEngine().getPower()) + "HP", 580, 38);
		
	}

	public void generateButtons() {
		
	BackgroundImage backgroundImageGas = new BackgroundImage(images.get("gas"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
																BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	Background backgroundGas = new Background(backgroundImageGas);
	
	BackgroundImage backgroundImageSave = new BackgroundImage(images.get("save"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
																BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	Background backgroundSave = new Background(backgroundImageSave);
	
	BackgroundImage backgroundImageImproveHook = new BackgroundImage(images.get("cadena"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
																BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	Background backgroundImproveHook = new Background(backgroundImageImproveHook);
	
	BackgroundImage backgroundImageImproveEngine = new BackgroundImage(images.get("llave"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
																BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
	Background backgroundImproveEngine = new Background(backgroundImageImproveEngine);

	btnBuyFuel.setLayoutX(20);
	btnBuyFuel.setLayoutY(30);
	btnBuyFuel.setPrefSize(28, 32);
	btnBuyFuel.setBackground(backgroundGas);
	btnBuyFuel.setCursor(new ImageCursor(images.get("handHover")));
	
	btnSave.setLayoutX(60);
	btnSave.setLayoutY(30);
	btnSave.setPrefSize(28, 32);
	btnSave.setBackground(backgroundSave);
	btnSave.setCursor(new ImageCursor(images.get("handHover")));
	
	btnBuyImproveHook.setLayoutX(100);
	btnBuyImproveHook.setLayoutY(30);
	btnBuyImproveHook.setPrefSize(28, 32);
	btnBuyImproveHook.setBackground(backgroundImproveHook);
	btnBuyImproveHook.setCursor(new ImageCursor(images.get("handHover")));
	
	btnBuyImprovePower.setLayoutX(140);
	btnBuyImprovePower.setLayoutY(30);
	btnBuyImprovePower.setPrefSize(28, 32);
	btnBuyImprovePower.setBackground(backgroundImproveEngine);
	btnBuyImprovePower.setCursor(new ImageCursor(images.get("handHover")));
		
	}
	
	public void playGame(Stage stage, TreasureHunterGame game) {
		
		group = new Group();
		btnBuyFuel = new Button();
		btnSave = new Button();
		btnBuyImproveHook = new Button();
		btnBuyImprovePower = new Button();
		
		sceneGame = new Scene(group, 640, 480);
		canvasGame = new Canvas(640, 480);
		group.getChildren().add(canvasGame);
		group.getChildren().add(btnBuyFuel);
		group.getChildren().add(btnSave);
		group.getChildren().add(btnBuyImproveHook);
		group.getChildren().add(btnBuyImprovePower);
		graphicsGame = canvasGame.getGraphicsContext2D();
		stage.setScene(sceneGame);
		stage.show();
		
		sceneGame.setCursor(new ImageCursor(images.get("hand")));
		
		generateButtons();
		
		var t = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				
				refreshCanvas(game);
				
				if(game.getHook().thereIsFuel()){
					
					if(moveLeft && !action) {
						game.getHook().moveLeft();
						if(!game.getHook().collisionBorderMap()) {
							moveLeft = false;
							moveRight = true;
						}
					}
					if(moveRight && !action) {
						game.getHook().moveRight(610);
						if(!game.getHook().collisionBorderMap(610)) {
							moveLeft = true;
							moveRight = false;
						}
					}
					if(goDown) {
						game.goDownHook();
						if(!game.getHook().canKeepGoingDown() || game.collisionTreasure()) {
							goDown = false;
							goUp = true;
						}
					}if((!game.collisionTreasure()) && goUp && (!game.getHook().initialPosition())) {
						game.getHook().goUp();
					}else if(game.getHook().initialPosition()){
						sounds.get("soundChain").stop();
						action = false;
						goUp = false;
					}
				}else {
					sounds.get("soundNoFuel").play();
					this.stop();
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.FUEL_COST)) {
					btnBuyFuel.setDisable(true);
				}else {
					btnBuyFuel.setDisable(false);
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_HOOK)) {
					btnBuyImproveHook.setDisable(true);
				}else {
					btnBuyImproveHook.setDisable(false);
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_ENGINE)) {
					btnBuyImprovePower.setDisable(true);
				}else {
					btnBuyImprovePower.setDisable(false);
				}
				if(action) {
					btnSave.setDisable(true);
				}else {
					btnSave.setDisable(false);
				}
				if(game.getTreasure().isEmpty()) {
				
					sounds.get("soundWin").play();
					this.stop();
				}
			}	
		};
		
		t.start();
		
		sceneGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.E && !action) {
					sounds.get("soundChain").play();
					goDown = true;
					action = true;
				}
			}
		});
		
		btnBuyFuel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					sounds.get("soundBuy").play();
					t.start();
					game.getStore().buyFuel(game.getPlayer(), game.getHook().getEngine());
					refreshCanvas(game);
				}
			}
			
			
		});
		
		btnBuyImproveHook.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					sounds.get("soundBuy").play();
					game.getStore().improveHook(game.getPlayer(), game.getHook());
					refreshCanvas(game);
				}
			}
			
			
		});
		
		btnBuyImprovePower.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					sounds.get("soundBuy").play();
					game.getStore().improveEngine(game.getPlayer(), game.getHook().getEngine());
					refreshCanvas(game);
				}
			}
			
			
		});
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					if(!action) {
						
						try {
							game.saveGame();
						} catch (IOException e) {
							e.printStackTrace();
						}
						refreshCanvas(game);
					}
			}
		});
	}
	
	public void mainMenu(Stage stage, TreasureHunterGame game) {
		
		BackgroundImage backgroundImageNewGame = new BackgroundImage(images.get("nuevaPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background backgroundNewGame = new Background(backgroundImageNewGame);

		BackgroundImage backgroundImageContinueGame = new BackgroundImage(images.get("continuarPartida"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background backgroundContinueGame = new Background(backgroundImageContinueGame);
		
		BackgroundImage backgroundImageExit = new BackgroundImage(images.get("salir"),BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,
		BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		Background backgroundExit = new Background(backgroundImageExit);
		
		group = new Group();
		btnNewGame = new Button();
		btnContinueGame = new Button();
		btnExitGame = new Button();
		
		btnNewGame.setPrefSize(190, 22);
		btnNewGame.setBackground(backgroundNewGame);
		btnNewGame.setCursor(new ImageCursor(images.get("handHover")));
		btnContinueGame.setPrefSize(190, 22);
		btnContinueGame.setBackground(backgroundContinueGame);
		btnContinueGame.setCursor(new ImageCursor(images.get("handHover")));
		btnExitGame.setPrefSize(60, 22);
		btnExitGame.setBackground(backgroundExit);
		btnExitGame.setCursor(new ImageCursor(images.get("handHover")));
		
		sceneMenu = new Scene(group, 640, 480);
		canvasMenu = new Canvas(640, 480);
		group.getChildren().add(canvasMenu);
		group.getChildren().add(btnNewGame);
		group.getChildren().add(btnContinueGame);
		group.getChildren().add(btnExitGame);
		graphicsMenu = canvasMenu.getGraphicsContext2D();
		stage.setScene(sceneMenu);
		stage.show();
		
		sceneMenu.setCursor(new ImageCursor(images.get("hand")));
		
		graphicsMenu.drawImage(images.get("fondoMenu"), 0, 0);
		graphicsMenu.drawImage(images.get("logo"), 50, 30);
		btnNewGame.setLayoutX(240);
		btnNewGame.setLayoutY(260);
		btnContinueGame.setLayoutX(240);
		btnContinueGame.setLayoutY(320);
		btnExitGame.setLayoutX(240);
		btnExitGame.setLayoutY(380);
		
		sounds.get("soundAmbience").play();
		
		btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				sounds.get("soundClick").play();
				game.generateTreasures();
				playGame(stage, game);
			}
			
		});
		
		btnContinueGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				File archivoP = new File(Persistence.FILE_PLAYER);
				File archivoH = new File(Persistence.FILE_HOOK);
				File archivoT = new File(Persistence.FILE_TREASURE);
				if( (archivoP.exists() && archivoH.exists() && archivoT.exists()) ) {
					try {
						game.setTreasure(game.getSnapshot().loadTreasures());
						game.setPlayer(game.getSnapshot().loadPlayer());
						game.setHook(game.getSnapshot().loadHook());
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
				}else {
					game.generateTreasures();
				}
				sounds.get("soundClick").play();
				playGame(stage, game);
				
			}
			
		});
		
		btnExitGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				sounds.get("soundClick").play();
				System.exit(0);
				
			}
			
		});
		
	}	
	
}
