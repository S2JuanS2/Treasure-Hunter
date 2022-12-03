package treasureHunter.treasureHunterApp;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javafx.animation.AnimationTimer;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {

	private Resources resources;
	private Stage stage;
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
	private static Button btnPause;
	private static Button btnBack;
	private static Button btnMusicPause;
	private static Button btnNewGame;
	private static Button btnContinueGame;
	private static Button btnExitGame;
	private static Button btnHelp;
	private static Button btnAbout;
	private static Button btnContinue; 
	private static Button btnPlayGame;
	private static Button btnGoDown;
	private static Label lbHelp;
	private static Label lbAbout;
	private static Label lbPause;
	private static Label lbError;
	private static TextField name;
	private boolean moveLeft = false;
	private boolean moveRight = true;
	private boolean action = false;
	private boolean goDown = false;
	private boolean goUp = false;
	private boolean stop = false;
	private boolean musicPause = false;
	
	private float price;
	
	public View(Stage stage) {
			
		resources = new Resources();
		this.stage = stage;
		group = new Group();
		btnNewGame = new Button();
		btnContinueGame = new Button();
		btnExitGame = new Button();
		btnHelp = new Button();
		btnAbout = new Button();
		btnContinue = new Button();
		
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
		btnMusicPause = new Button();
		btnPlayGame = new Button();
		btnGoDown = new Button();
		lbPause = new Label("PAUSA");
		
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
		
		btnBack.setLayoutX(600);
		btnBack.setLayoutY(35);
		btnBack.setScaleX(0.8);
		btnBack.setScaleY(0.8);
		btnBack.setPrefSize(32, 32);
		btnBack.setBackground(resources.getBackground().get("back"));
		btnBack.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
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
		
	}
	
	public void playGame(TreasureHunterGame game) {
				
		group = new Group();
		
		try {
			game.saveGame();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		resources.getSounds().get("soundAmbience").stop();
		resources.getSounds().get("ambiencePlay").play();
		
		lbPause.setTextFill(Color.WHITE);
			
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
		group.getChildren().add(btnBack);
		group.getChildren().add(btnPlayGame);
		group.getChildren().add(btnGoDown);
					
		stage.setScene(sceneGame);
		stage.setTitle("Treasure Hunter");
		stage.setResizable(false);
		stage.show();
	
		generateGameButtonStyle();
		
		var t = new AnimationTimer() {
			
			int n = 0;
			@Override
			public void handle(long now) {
				
				
				var particle = new AnimationTimer() {
					
					double n = 0;
					@Override
					public void handle(long now) {
						if(!stop && game.getHook().thereIsFuel() && !game.getTreasure().isEmpty()) {
							graphicsGame.setFill(Color.GREEN);	
							graphicsGame.setFont(Font.font(14));
							graphicsGame.fillText("+ $" + String.valueOf(price), game.getHook().getPosition().getX()+32,game.getHook().getPosition().getY()+50-n);
							n = n + 0.5;
							if(n == 25) {
								n = 0;
								this.stop();
							}						
						}
						
					}
				};
						
				n++;
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
							if(game.getHook().canKeepGoingDown()) {
								resources.getSounds().get("soundCollision").play();								
							}
							goDown = false;
							goUp = true;
						}
					}if((!game.collisionTreasure()) && (goUp) && (!game.getHook().initialPosition()) ) {
						game.getHook().goUp();
					}else if(game.getHook().initialPosition()){
						if(game.getPlayer().getTreasure() != null) {
							resources.getSounds().get("soundCollect").play();
							price = game.getPlayer().getTreasure().getPrice();
							particle.start();
							game.collect();							
						}
						resources.getSounds().get("soundChain").stop();
						action = false;
						goUp = false;
					}
				}else {
					resources.getSounds().get("soundNoFuel").play();
					resources.getSounds().get("soundChain").stop();
					graphicsGame.setFill(Color.RED);	
					graphicsGame.setFont(Font.font(12));
					graphicsGame.fillText("Sin combustible", game.getHook().getPosition().getX()-32,game.getHook().getPosition().getY()+45);
					this.stop();
					if(btnBuyFuel.isDisable()) {
						resources.getSounds().get("soundNoFuel").stop();
						resources.getSounds().get("ambiencePlay").stop();
						resources.getSounds().get("soundDefeat").play();
						this.stop();
						btnBuyFuel.setDisable(true);
						btnBuyImproveHook.setDisable(true);
						btnBuyImprovePower.setDisable(true);
						btnSave.setDisable(true);
						graphicsGame.drawImage(resources.getImages().get("pergamino"), 160, 160);
						graphicsGame.drawImage(resources.getImages().get("derrota"), 200, 240);
						endScene(stage, game);
					}
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.FUEL_COST) || !game.getStore().noMaxFuel(game.getHook().getEngine().getFuel())) {
					btnBuyFuel.setDisable(true);
				}else {
					btnBuyFuel.setDisable(false);
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_HOOK) || !game.getStore().noMaxLength(game.getHook().getLength())) {
					btnBuyImproveHook.setDisable(true);
				}else {
					btnBuyImproveHook.setDisable(false);
				}
				if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_ENGINE) || !game.getStore().noMaxPower(game.getHook().getEngine().getPower())) {
					btnBuyImprovePower.setDisable(true);
				}else {
					btnBuyImprovePower.setDisable(false);
				}
				if(action) {
					btnSave.setDisable(true);
					btnGoDown.setDisable(true);
				}else {
					btnSave.setDisable(false);
					btnGoDown.setDisable(false);
				}
				
				if(!game.getHook().thereIsFuel()) {
					btnBuyImproveHook.setDisable(true);
					btnBuyImprovePower.setDisable(true);
					btnGoDown.setDisable(true);
				}
				
				if(game.getTreasure().isEmpty() && game.getHook().initialPosition() && game.getPlayer().getTreasure() == null) {
				
					refreshCanvas(game);
					resources.getSounds().get("ambiencePlay").stop();
					resources.getSounds().get("soundChain").stop();
					resources.getSounds().get("soundWin").play();
					this.stop();
					btnBuyFuel.setDisable(true);
					btnBuyImproveHook.setDisable(true);
					btnBuyImprovePower.setDisable(true);
					btnSave.setDisable(true);
					btnPause.setDisable(true);
					graphicsGame.drawImage(resources.getImages().get("pergamino"), 160, 160);
					graphicsGame.drawImage(resources.getImages().get("victoria"), 200, 240);
					endScene(stage, game);
				}
				
				if(!game.inGame()) {
					btnBuyFuel.setDisable(true);
					btnBuyImproveHook.setDisable(true);
					btnBuyImprovePower.setDisable(true);
					btnSave.setDisable(true);
					btnPause.setDisable(true);
				}
				
				if(n%2500 == 0 && !musicPause) {
					resources.getSounds().get("ambiencePlay").stop();
					resources.getSounds().get("ambiencePlay").play();
				}	
				
			}	
		};
		
		btnBuyFuel.setDisable(true);
		btnBuyImproveHook.setDisable(true);
		btnBuyImprovePower.setDisable(true);
		btnPause.setDisable(true);
		btnGoDown.setDisable(true);
		refreshCanvas(game);
		
		btnPlayGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				group.getChildren().remove(btnPlayGame);
				btnPause.setDisable(false);
				resources.getSounds().get("soundSave").play();
				t.start();				
			}

		});
			
		sceneGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode() == KeyCode.E && !action && !stop && game.getHook().thereIsFuel() && !group.getChildren().contains(btnPlayGame)) {
					resources.getSounds().get("soundChain").play();
					goDown = true;
					action = true;
				}
			}
		});
		
		btnGoDown.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!action && !stop && game.getHook().thereIsFuel() && !group.getChildren().contains(btnPlayGame)) {
					resources.getSounds().get("soundSave").play();
					resources.getSounds().get("soundChain").play();
					goDown = true;
					action = true;
				}
			}
			
			
		});
		
		btnBuyFuel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					resources.getSounds().get("soundBuy").play();
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
					resources.getSounds().get("soundBuy").play();
					game.getStore().improveHook(game.getPlayer(), game.getHook());
					refreshCanvas(game);
				}
			}
			
			
		});
		
		btnBuyImprovePower.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					resources.getSounds().get("soundBuy").play();
					game.getStore().improveEngine(game.getPlayer(), game.getHook());
					refreshCanvas(game);
				}
			}
			
			
		});
		
		btnSave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					if(!action) {
						
						try {
							resources.getSounds().get("soundSave").play();
							game.saveGame();
						} catch (IOException e) {
							e.printStackTrace();
						}
						refreshCanvas(game);
					}
			}
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					t.stop();
					resources.getSounds().get("soundChain").stop();
					resources.getSounds().get("ambiencePlay").stop();
					resources.getSounds().get("soundSave").play();
					action = false;
					goDown = false;
					goUp = false;
					stop = false;
					mainMenu(game);
			}
		});
		
		btnPause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
					if(!stop) {
						resources.getSounds().get("soundChain").stop();
						resources.getSounds().get("soundSave").play();
						group.getChildren().add(lbPause);
						btnBuyFuel.setDisable(true);
						btnBuyImproveHook.setDisable(true);
						btnBuyImprovePower.setDisable(true);
						btnGoDown.setDisable(true);
						stop = true;
						t.stop();
					}else {
						resources.getSounds().get("soundSave").play();
						group.getChildren().remove(lbPause);
						btnBuyFuel.setDisable(false);
						btnBuyImproveHook.setDisable(false);
						btnBuyImprovePower.setDisable(false);
						stop = false;
						t.start();
					}
			}
		});
		
		btnMusicPause.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!musicPause) {
					resources.getSounds().get("ambiencePlay").stop();
					resources.getSounds().get("soundSave").play();	
					musicPause = true;
				}else {
					resources.getSounds().get("soundSave").play();
					resources.getSounds().get("ambiencePlay").play();
					musicPause = false;
				}
				
			}
		});		
	}
	
	public void mainMenu(TreasureHunterGame game) {
		
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
		
		generateMenuButtonStyle();
		
		if(!resources.getSounds().get("soundAmbience").isPlaying()) {
			resources.getSounds().get("soundAmbience").play();			
		}
		
		btnNewGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				game.reset();
				resources.getSounds().get("soundClick").play();
				game.generateTreasures();
				group.getChildren().remove(btnNewGame);
				group.getChildren().remove(btnContinueGame);
				group.getChildren().remove(btnHelp);
				group.getChildren().remove(btnAbout);
				group.getChildren().remove(btnExitGame);
				nameScene(stage, game);
			}
			
		});
		
		File archivoP = new File(Persistence.FILE_PLAYER);
		File archivoH = new File(Persistence.FILE_HOOK);
		File archivoT = new File(Persistence.FILE_TREASURE);
		if( !(archivoP.exists() && archivoH.exists() && archivoT.exists()) ) {
			btnContinueGame.setDisable(true);
			btnNewGame.setDisable(true);
		}else {
			btnContinueGame.setDisable(false);
		}
		
		btnContinueGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				try {
					game.setTreasure(game.getSnapshot().loadTreasures());
					game.setPlayer(game.getSnapshot().loadPlayer());
					game.setHook(game.getSnapshot().loadHook());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				resources.getSounds().get("soundClick").play();
				playGame(game);
				
			}
			
		});
		
		btnExitGame.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resources.getSounds().get("soundClick").play();
				System.exit(0);
				
			}
			
		});
		
		btnHelp.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				btnNewGame.setDisable(false);
				group.getChildren().remove(btnNewGame);
				group.getChildren().remove(btnContinueGame);
				group.getChildren().remove(btnHelp);
				group.getChildren().remove(btnAbout);
				group.getChildren().remove(btnExitGame);
				resources.getSounds().get("soundClick").play();
				helpScene();
				
			}
			
		});
		
		btnAbout.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				group.getChildren().removeAll();
				resources.getSounds().get("soundClick").play();
				group.getChildren().remove(btnNewGame);
				group.getChildren().remove(btnContinueGame);
				group.getChildren().remove(btnHelp);
				group.getChildren().remove(btnAbout);
				group.getChildren().remove(btnExitGame);
				aboutScene();
				
			}
			
		});
		
	}
	
	public void aboutScene() {
			
		group.getChildren().add(btnContinue);
		group.getChildren().add(lbAbout);
		
		btnContinue.setBackground(resources.getBackground().get("continue"));
		btnContinue.setPrefSize(32,32);
		btnContinue.setLayoutX(520);
		btnContinue.setLayoutY(410);
		btnContinue.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		lbAbout.setBackground(resources.getBackground().get("aboutText"));
		lbAbout.setPrefSize(350, 300);
		lbAbout.setLayoutX(150);
		lbAbout.setLayoutY(300);

		
		btnContinue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resources.getSounds().get("soundSave").play();
				group.getChildren().remove(btnContinue);
				group.getChildren().remove(lbAbout);
				group.getChildren().add(btnNewGame);
				group.getChildren().add(btnContinueGame);
				group.getChildren().add(btnHelp);
				group.getChildren().add(btnAbout);
				group.getChildren().add(btnExitGame);
				
			}
			
		});
		
	}
	
	public void helpScene() {
			
		group.getChildren().add(btnContinue);
		group.getChildren().add(lbHelp);
		lbHelp.setBackground(resources.getBackground().get("helpText"));
		lbHelp.setPrefSize(550, 110);
		lbHelp.setLayoutX(100);
		lbHelp.setLayoutY(300);
		btnContinue.setBackground(resources.getBackground().get("continue"));
		btnContinue.setPrefSize(32,32);
		btnContinue.setLayoutX(520);
		btnContinue.setLayoutY(410);
		
		btnContinue.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		btnContinue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resources.getSounds().get("soundSave").play();
				group.getChildren().remove(btnContinue);
				group.getChildren().remove(lbHelp);
				group.getChildren().add(btnNewGame);
				group.getChildren().add(btnContinueGame);
				group.getChildren().add(btnHelp);
				group.getChildren().add(btnAbout);
				group.getChildren().add(btnExitGame);
			}
			
		});
		
	}
	
	public void nameScene(Stage stage, TreasureHunterGame game) {
		
		btnBack.setLayoutX(562);
		btnBack.setLayoutY(410);
		btnBack.setPrefSize(32,32);
		btnBack.setBackground(resources.getBackground().get("back"));
		btnBack.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		btnContinue.setBackground(resources.getBackground().get("continue"));
		btnContinue.setLayoutX(520);
		btnContinue.setLayoutY(410);
		btnContinue.setPrefSize(32,32);
		btnContinue.setCursor(new ImageCursor(resources.getImages().get("handHover")));
			
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

		group.getChildren().add(btnContinue);
		group.getChildren().add(name);
		group.getChildren().add(btnBack);
		group.getChildren().add(lbError);

	
		btnContinue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(name.getText() != "" && name.getLength() <= 8) {
					resources.getSounds().get("soundSave").play();
					game.getPlayer().setName(name.getText());
					resources.getSounds().get("soundClick").play();
					playGame(game);					
				}else{
					resources.getSounds().get("soundSave").play();
					lbError.setText("*El nombre no puede estar vacío o tener mas de 8 digitos.");
				}
			}
			
		});
		
		btnBack.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resources.getSounds().get("soundSave").play();
					mainMenu(game);
			}
		});
		
	}
	
	public void endScene(Stage stage, TreasureHunterGame game) {
		
		btnContinue.setBackground(resources.getBackground().get("continue"));
		btnContinue.setPrefSize(32,32);
		btnContinue.setLayoutX(420);
		btnContinue.setLayoutY(340);
		btnContinue.setCursor(new ImageCursor(resources.getImages().get("handHover")));
		
		group.getChildren().add(btnContinue);
		
		btnContinue.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				resources.getSounds().get("soundSave").play();
				mainMenu(game);
				
			}
			
		});
	}	
}
