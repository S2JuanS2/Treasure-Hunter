package treasureHunter.treasureHunterApp;

import java.io.File;
import java.io.IOException;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class TreasureHunter{
	
	
	private TreasureHunterGame game;
	private View view;
	
	AnimationTimer timer;
	AnimationTimer priceEffect;
	private boolean start = false;
	private boolean action = false;
	private boolean goDown = false;
	private boolean goUp = false;
	private boolean stop = false;
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
	
	public void start() {
					
		view.menuScene(!checkFiles());
			
		view.recordListenNewGame(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				game.reset();
				game.generateTreasures();
				view.actionNewGame();
				view.nameScene();
			}
		});
		
		view.recordListenContinueGame(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				try {
					game.setTreasure(game.getSnapshot().loadTreasures());
					game.setPlayer(game.getSnapshot().loadPlayer());
					game.setHook(game.getSnapshot().loadHook());
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				view.getResources().playSound(Resources.SOUND_CLICK);
				view.loadStageGame();
				timeStart();
			}
		});
		
		view.recordListenExitGame(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getResources().playSound(Resources.SOUND_CLICK);
				System.exit(0);
			}	
		});
		
		view.recordListenHelp(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.actionHelp();
			}
			
		});
		
		view.recordListenAbout(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.actionAbout();
			}
			
		});
		
		view.recordListenContinue(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.actionContinue();
			}
			
		});
		
		view.recordListenStart(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(!view.errorTextField()) {
					view.getResources().playSound(Resources.SOUND_SAVE);
					game.getPlayer().setName(view.getTextField());
					view.loadStageGame();
					timeStart();
				}else{
					view.actionStart();
				}
			}
			
		});
		
		view.recordListenBack(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.getResources().playSound(Resources.SOUND_SAVE);
				view.menuScene(!checkFiles());
			}
			
		});
		
		view.recordListenFinish(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.getResources().playSound(Resources.SOUND_SAVE);
				view.menuScene(!checkFiles());
			}
			
		});	
		
		view.recordListenPlayGame(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.actionPlayGame();
				start = true;	
			}
		});	
		
		view.recordListenGoDown(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(start) {
					view.getResources().playSound(Resources.SOUND_SAVE);
					view.getResources().playSound(Resources.SOUND_CHAIN);
					goDown = true;
					action = true;
				}
			}
		});	
		
		view.recordListenBuyFuel(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					game.getStore().buyFuel(game.getPlayer(), game.getHook().getEngine());
					view.getResources().playSound(Resources.SOUND_BUY);
					start = true;
				}
			}
		});	
		
		view.recordListenImproveHook(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					view.getResources().getSounds().get(Resources.SOUND_BUY).play();
					game.getStore().improveHook(game.getPlayer(), game.getHook());
				}
			}
		});	
		
		view.recordListenImprovePower(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					view.getResources().getSounds().get(Resources.SOUND_BUY).play();
					game.getStore().improveEngine(game.getPlayer(), game.getHook());
				}
			}
		});	
		
		view.recordListenSave(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					view.getResources().getSounds().get(Resources.SOUND_SAVE).play();
					game.saveGame();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});	
		
		view.recordListenPause(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.actionPause(stop);
				if(!stop) {
					stop = true;
					start = false;
				}else {
					stop = false;
					start = true;
				}
			}
		});	
		
		view.recordListenMusicPause(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.actionMusicPause(musicPause);
				if(!musicPause) {
					musicPause = true;
				}else {
					musicPause = false;
				}
			}
		});	
		
		view.recordListenBackGame(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timer.stop();
				view.actionBackGame();
				action = false;
				goDown = false;
				goUp = false;
				stop = false;
				start = false;
				view.menuScene(!checkFiles());
			}
		});	
	}
	
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
				if(!stop && game.getHook().thereIsFuel() && !game.getTreasure().isEmpty()) {
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
									view.endScene(game.winCondition());
								}
							}
							view.getBtnBuyFuel().setDisable(!game.canBuyFuel());
							view.getBtnBuyImproveHook().setDisable(!game.canBuyImproveHook() || !game.getHook().thereIsFuel());
							view.getBtnBuyImprovePower().setDisable(!game.canBuyImproveEngine() || !game.getHook().thereIsFuel());
							view.getBtnSave().setDisable(!game.getHook().initialPosition());
							view.getBtnGoDown().setDisable(!game.getHook().initialPosition() || !game.getHook().thereIsFuel());
														
							if(game.winCondition()) {
								this.stop();
								view.refreshCanvas(game);
								view.endScene(game.winCondition());
							}		
							view.getResources().loopSound(Resources.SOUND_AMBIENCE);
						}			
					}	
				};
				timer.start();
				view.disableButtons();
				view.refreshCanvas(game);	
	}	
}
