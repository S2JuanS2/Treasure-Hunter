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
	private boolean moveLeft = false;
	private boolean moveRight = true;
	private boolean action = false;
	private boolean goDown = false;
	private boolean goUp = false;
	private boolean stop = false;
	private boolean start = false;
	private boolean musicPause = false;
	private float price;

	public TreasureHunter(TreasureHunterGame game, View view) {
		
		this.game = game;
		this.view = view;
	}
	
	public boolean checkFiles() {
		File archivoP = new File(Persistence.FILE_PLAYER);
		File archivoH = new File(Persistence.FILE_HOOK);
		File archivoT = new File(Persistence.FILE_TREASURE);
		return (!(archivoP.exists() && archivoH.exists() && archivoT.exists()));
	}
	
	public void start() {
					
		view.menuScene(checkFiles());
			
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
				view.getResources().getSounds().get("soundClick").play();
				view.gameScene();
				timeStart();
			}
		});
		
		view.recordListenExitGame(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				view.getResources().getSounds().get("soundClick").play();
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
				if(view.getTextField() != "" && !view.maxLengthTextField()) {
					view.getResources().getSounds().get("soundSave").play();
					game.getPlayer().setName(view.getTextField());
					view.gameScene();
					timeStart();
				}else{
					view.actionStart();
				}
			}
			
		});
		
		view.recordListenBack(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.getResources().getSounds().get("soundSave").play();
				view.menuScene(checkFiles());
			}
			
		});
		
		view.recordListenFinish(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				view.getResources().getSounds().get("soundSave").play();
				view.menuScene(checkFiles());
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
				if(game.getHook().thereIsFuel() && !action && !stop) {
					view.actionGoDown();
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
					view.actionBuyFuel();
					start = true;
				}
			}
		});	
		
		view.recordListenImproveHook(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					view.getResources().getSounds().get("soundBuy").play();
					game.getStore().improveHook(game.getPlayer(), game.getHook());
				}
			}
		});	
		
		view.recordListenImprovePower(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(game.inGame()) {
					view.getResources().getSounds().get("soundBuy").play();
					game.getStore().improveEngine(game.getPlayer(), game.getHook());
				}
			}
		});	
		
		view.recordListenSave(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					view.getResources().getSounds().get("soundSave").play();
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
				view.menuScene(checkFiles());
			}
		});	
	}
	
	public void timeStart() {
		timer = new AnimationTimer() {
					
					int n = 0;
					@Override
					public void handle(long now) {
						
						if(start) {
							
							var particle = new AnimationTimer() {
								
								double n = 0;
								@Override
								public void handle(long now) {
									if(!stop && game.getHook().thereIsFuel() && !game.getTreasure().isEmpty()) {
										view.drawParticlePrice(price, game.getHook().getPosition().getX()+32,game.getHook().getPosition().getY()+50-n);
										n = n + 0.5;
										if(n == 25) {
											n = 0;
											this.stop();
										}						
									}
									
								}
							};
							
							n++;
							view.refreshCanvas(game);
							
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
											view.getResources().getSounds().get("soundCollision").play();								
										}
										goDown = false;
										goUp = true;
									}
								}if((!game.collisionTreasure()) && (goUp) && (!game.getHook().initialPosition()) ) {
									game.getHook().goUp();
								}else if(game.getHook().initialPosition()){
									if(game.getPlayer().getTreasure() != null) {
										view.getResources().getSounds().get("soundCollect").play();
										price = game.getPlayer().getTreasure().getPrice();
										particle.start();
										game.collect();							
									}
									view.getResources().getSounds().get("soundChain").stop();
									action = false;
									goUp = false;
								}
							}else {
								view.getResources().getSounds().get("soundNoFuel").play();
								view.getResources().getSounds().get("soundChain").stop();
								view.drawWithoutFuel(game.getHook().getPosition().getX()-32,game.getHook().getPosition().getY()+45);
								start = false;
								if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.FUEL_COST)) {
									this.stop();
									view.drawDefeat();
									view.endScene();
								}
							}
							if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.FUEL_COST) || !game.getStore().noMaxFuel(game.getHook().getEngine().getFuel())) {
								view.getBtnBuyFuel().setDisable(true);
							}else {
								view.getBtnBuyFuel().setDisable(false);
							}
							if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_HOOK) || !game.getStore().noMaxLength(game.getHook().getLength())) {
								view.getBtnBuyImproveHook().setDisable(true);
							}else {
								view.getBtnBuyImproveHook().setDisable(false);
							}
							if(!game.getStore().canBuy(game.getPlayer().getBalance(), Store.COST_UPGRADE_ENGINE) || !game.getStore().noMaxPower(game.getHook().getEngine().getPower())) {
								view.getBtnBuyImprovePower().setDisable(true);
							}else {
								view.getBtnBuyImprovePower().setDisable(false);
							}
							if(action) {
								view.getBtnSave().setDisable(true);
								view.getBtnGoDown().setDisable(true);
							}else {
								view.getBtnSave().setDisable(false);
								view.getBtnGoDown().setDisable(false);
							}
							
							if(!game.getHook().thereIsFuel()) {
								view.getBtnBuyImproveHook().setDisable(true);
								view.getBtnBuyImprovePower().setDisable(true);
								view.getBtnGoDown().setDisable(true);
							}
							
							if(game.getTreasure().isEmpty() && game.getHook().initialPosition() && game.getPlayer().getTreasure() == null) {
								
								view.drawVictory();
								view.refreshCanvas(game);
								this.stop();
								start = false;
								view.endScene();
							}
							
							if(n%2500 == 0 && !musicPause) {
								view.getResources().getSounds().get("ambiencePlay").stop();
								view.getResources().getSounds().get("ambiencePlay").play();
							}	
						}
						
					}	
				};
				
				timer.start();
				
				view.disableButtons();
				view.refreshCanvas(game);	
	}
		
}
