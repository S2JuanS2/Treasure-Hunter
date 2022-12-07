package treasureHunter.treasureHunterApp;

import java.io.File;
import java.io.IOException;

import javafx.animation.AnimationTimer;

public class TreasureHunter{
	
	private TreasureHunterGame game;
	private View view;
	
	AnimationTimer timer;
	AnimationTimer priceEffect;
	
	private boolean start = false;
	private boolean action = false;
	private boolean goDown = false;
	private boolean goUp = false;
	private boolean pause = false;
	private boolean musicPause = false;
	private float price;

	public TreasureHunter(TreasureHunterGame game, View view) {
		
		this.game = game;
		this.view = view;
	}
	
	/*
	 * VERIFICA LA EXISTENCIA DE PARTIDAS GUARDADAS EN ARCHIVOS
	 */
	public boolean checkFiles() {
		File archivoP = new File(Persistence.FILE_PLAYER);
		File archivoH = new File(Persistence.FILE_HOOK);
		File archivoT = new File(Persistence.FILE_TREASURE);
		return ((archivoP.exists() && archivoH.exists() && archivoT.exists()));
	}
	
	/*
	 * REGISTRA ESCUCHAS DE LA VISTA, INICIA LA ESCENA PRINCIPAL
	 */
	public void start() {
					
		view.menuScene(!checkFiles());
			
		view.recordListenNewGame(e -> {	
			view.actionNewGame();
			view.nameScene();	
		});
				
		view.recordListenContinueGame(e ->{	
			try {
				game.setTreasure(game.getSnapshot().loadTreasures());
				game.setPlayer(game.getSnapshot().loadPlayer());
				game.setHook(game.getSnapshot().loadHook());
			} catch (ClassNotFoundException | IOException exc) {
				exc.printStackTrace();
			}
			view.getResources().playSound(Resources.SOUND_CLICK);
			view.loadStageGame();
			timeStart();
		});
		
		view.recordListenExitGame(e -> {
			view.getResources().playSound(Resources.SOUND_CLICK);
			System.exit(0);
		});
		
		view.recordListenHelp(e -> {
			view.actionHelp();
		});
		
		view.recordListenAbout(e -> {
			view.actionAbout();
		});
		
		view.recordListenContinue(e -> {
			view.actionContinue();
		});
		
		view.recordListenStart(e ->  {
			if(!view.errorTextField()) {
				view.getResources().playSound(Resources.SOUND_SAVE);
				game.reset();
				game.getPlayer().setName(view.getTextField());
				game.generateTreasures();
				view.loadStageGame();
				timeStart();
			}else{
				view.actionStart();
			}		
		});
		
		view.recordListenBack(e ->  {
			view.getResources().playSound(Resources.SOUND_SAVE);
			view.menuScene(!checkFiles());
		});
		
		view.recordListenFinish(e ->  {
			view.getResources().playSound(Resources.SOUND_SAVE);
			view.menuScene(!checkFiles());	
		});	
		
		view.recordListenPlayGame(e ->  {
			view.actionPlayGame();
			start = true;	
		});	
		
		view.recordListenGoDown(e ->  {
			if(start) {
				view.getResources().playSound(Resources.SOUND_SAVE);
				view.getResources().playSound(Resources.SOUND_CHAIN);
				goDown = true;
				action = true;
			}
		});	
		
		view.recordListenBuyFuel(e -> {
			if(game.inGame()) {
				game.getStore().buyFuel(game.getPlayer(), game.getHook().getEngine());
				view.getResources().playSound(Resources.SOUND_BUY);
				start = true;
			}
		});	
		
		view.recordListenImproveHook(e ->  {
			if(game.inGame()) {
				view.getResources().getSounds().get(Resources.SOUND_BUY).play();
				game.getStore().improveHook(game.getPlayer(), game.getHook());
			}
		});	
		
		view.recordListenImprovePower(e -> {
			if(game.inGame()) {
				view.getResources().getSounds().get(Resources.SOUND_BUY).play();
				game.getStore().improveEngine(game.getPlayer(), game.getHook());
			}
		});	
		
		view.recordListenSave(e ->  {
			try {
				view.getResources().getSounds().get(Resources.SOUND_SAVE).play();
				game.saveGame();
			} catch (IOException exc) {
				exc.printStackTrace();
			}
		});	
		
		view.recordListenPause(e ->  {
			view.actionPause(pause);
			if(!pause) {
				pause = true;
				start = false;
			}else {
				pause = false;
				start = true;
			}
		});	
		
		view.recordListenMusicPause(e ->  {
			view.actionMusicPause(musicPause);
			if(!musicPause) {
				musicPause = true;
			}else {
				musicPause = false;
			}
		});	
		
		view.recordListenBackGame(e ->  {
			timer.stop();
			view.actionBackGame();
			action = false;
			goDown = false;
			goUp = false;
			pause = false;
			start = false;
			view.menuScene(!checkFiles());
		});	
	}
	
	/*
	 * ACTIVA O DESACTIVA LOS BOTONES SEGÚN EL ESTADO DEL JUEGO
	 */
	public void buttonsState() {
		view.getBtnBuyFuel().setDisable(!game.canBuyFuel());
		view.getBtnBuyImproveHook().setDisable(!game.canBuyImproveHook() || !game.getHook().thereIsFuel());
		view.getBtnBuyImprovePower().setDisable(!game.canBuyImproveEngine() || !game.getHook().thereIsFuel());
		view.getBtnSave().setDisable(!game.getHook().initialPosition());
		view.getBtnGoDown().setDisable(!game.getHook().initialPosition() || !game.getHook().thereIsFuel());
	}
	
	/*
	 * ARRANCA EL JUEGO
	 * LOOP PRINCIPAL DEL JUEGO
	 */
	public void timeStart() {
		
		start = false;
		
		if(!checkFiles()) {
			try {
				game.saveGame();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		priceEffect = new AnimationTimer() {
			
			double n = 0;
			@Override
			public void handle(long now) {
				if(!pause && game.getHook().thereIsFuel() && !game.getTreasure().isEmpty()) {
					view.drawEffectPrice(price, game.getHook().getPosition().getX()+32,game.getHook().getPosition().getY()+50-n);
					n = n + 0.5;
					if(n == 25) {
						n = 0;
						this.stop();
					}						
				}
			}
		};
		timer = new AnimationTimer() {
					
					@Override
					public void handle(long now) {
						
						if(start) {
													
							view.refreshCanvas(game);
							
							if(!action) {						
								game.getHook().horizontalMovement();
							}		
							if(goDown){
								game.goDownHook();
								if(!game.getHook().canKeepGoingDown() || game.collisionTreasure()) {
									goDown = false;
									goUp = true;
									if(game.getHook().canKeepGoingDown()) {
										view.getResources().playSound(Resources.SOUND_COLLISION);								
									}
								}
							}	
							if(goUp) {					
								if(!game.collisionTreasure()) {
									game.getHook().goUp();
									if(game.getPlayer().getTreasure() != null) {
										price = game.getPlayer().getTreasure().getPrice();
									}
								}
								if(game.getHook().initialPosition()){
									view.getResources().stopSound(Resources.SOUND_CHAIN);
									action = false;
									goUp = false;
								}						
							}	
							if(game.collect()){
								view.getResources().playSound(Resources.SOUND_COLLECT);		
								priceEffect.start();
							}			
							if(!game.getHook().thereIsFuel()){
								view.refreshCanvas(game);
								view.drawWithoutFuel(game.getHook().getPosition().getX()-32,game.getHook().getPosition().getY()+45);
								start = false;
								if(!game.canBuyFuel()) {
									this.stop();
									priceEffect.stop();
									view.endScene(game.winCondition());
								}
							}
							
							buttonsState();
							
							if(!musicPause && start) {
								view.getResources().loopSound(Resources.SOUND_AMBIENCE);	
							}
							
							if(game.winCondition()) {
								this.stop();
								priceEffect.stop();
								view.refreshCanvas(game);
								view.endScene(game.winCondition());
							}	
						}			
					}	
				};
				timer.start();
				view.disableButtons();
				view.refreshCanvas(game);	
	}	
}
