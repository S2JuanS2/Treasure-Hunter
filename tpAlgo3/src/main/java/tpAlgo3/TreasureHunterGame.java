package tpAlgo3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class TreasureHunterGame implements Sound{

	static final int WIDTH = 300;
	static final int DEPTH = 500;
	
	//opt
	static final String LEFT = "A";
	static final String DOUBLE_LEFT = "AA";
	static final String RIGHT = "D";
	static final String DOUBLE_RIGHT = "DD";
	static final String DOWN = "E";
	static final String BUY_HOOK = "B";
	static final String BUY_FUEL = "G";
	static final String END = "F";
	
	//attributes
	private Player player;
	private Hook hook;
	private Map map;
	private List<Treasure> treasure;
	
	public TreasureHunterGame(String name) {
		
		this.player = new Player(name);
		this.hook = new Hook();
		this.map = new Map(WIDTH, WIDTH);
		this.treasure = new ArrayList<Treasure>();
		
	}

	public Player getPlayer() {
		return player;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public Hook getHook() {
		return hook;
	}

	public void setHook(Hook hook) {
		this.hook = hook;
	}

	public Map getMap() {
		return map;
	}
	
	public List<Treasure> getTreasure() {
		return treasure;
	}

	public void addTreasure(Treasure treasure) {
		
		this.treasure.add(treasure);
		
	}
	
	public void showTreasures() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public String showPlayerStats() {
		return (this.getPlayer().toString());
	}
	
	public String showHookStats() {
		return (this.getHook().toString());
	}
	
	public void playSound(String sound) {
		
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
			TreasureHunter.screen.print("Error al reproducir el sonido.");
		}
	}
		
	public void lineBreak(int i) {
		
		for( int j = 0; j < i; j++) {
			TreasureHunter.screen.print("\n");
		}
	}
	
	//PRINCIPIO DE RESPONSABILIDAD UNICA  -> BUSCA, APREMIA, ELIMINA		;podría devolver el tesoro
	public boolean collisionTreasure() {
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(getHook().getPositionHook())) {
				playSound(TREASURE_COLLISION_SOUND);
				player.setBalance(treasureAux.getPrice());
				TreasureHunter.screen.print("recurso recolectado: " + treasureAux.getType());
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public boolean collisionBorderMap(){
		
		return(getHook().getPositionHook().getX() <= 0 || getHook().getPositionHook().getX() >= map.getWidth() || getHook().getPositionHook().getY() >= map.getDepth());
	}

	public void goDownHook() {
		
		if(getHook().thereIsFuel()) {
			playSound(HOOK_SOUND);
			
			int contador = 0;
			while( ( (!( collisionTreasure() || collisionBorderMap() || contador >= getHook().getLenght() ) && getHook().thereIsFuel()) ) ) {
				getHook().goDown();
				contador++;
			}
		}else {
			TreasureHunter.screen.print("Combustible insuficiente");
			playSound(NO_FUEL);
		}
	}

	public void improveHook() {
		if(player.getBalance() >= Hook.COST_CHAIN) {
			
			if(hook.getLenght() < Hook.MAX_LENGHT) {		
				playSound(BOUGTH);
				player.setBalance(-Hook.COST_CHAIN);
				hook.setLenght(Hook.IMPROVE_LENGHT);
			}else {
				TreasureHunter.screen.print("Longitud maxima alcanzada.");
				playSound(NO_FUEL);
			}
		}else {
			TreasureHunter.screen.print("Dinero insuficiente\n");
			playSound(ERROR_MONEY);
		}
	}
	
	public void buyFuel() {
		if(player.getBalance() >= Hook.FUEL_COST) {
			playSound(BOUGTH);
			player.setBalance(-Hook.FUEL_COST);
			getHook().setFuel(Hook.RECHARGE_FUEL);
		}else {
			System.out.println("Dinero insuficiente\n");
			playSound(ERROR_MONEY);
		}
	}

	public boolean inGame() {
		return(getHook().getFuel() > 0 || getPlayer().getBalance() >= Hook.FUEL_COST);
	}
	
	public void selectOption(String option) {
			
		if(getHook().getFuel() >= Hook.MOVE_FUEL_COST) {
			switch(option) {
				case LEFT:
					getHook().moveLeft();
					playSound(MOVE_HOOK);
					break;
				case RIGHT:
					getHook().moveRight();
					playSound(MOVE_HOOK);
					break;
			}
		}else {
			TreasureHunter.screen.print("Combustible insuficiente");
			playSound(NO_FUEL);
		}
		if(getHook().getFuel() >= (Hook.MOVE_FUEL_COST*10)){
			switch(option) {
				case DOUBLE_LEFT:
					playSound(MOVE_HOOK);
					for(int i = 0; i < Hook.MOVE_FUEL_COST*10; i++) {
						getHook().moveLeft();
					}
					break;
				case DOUBLE_RIGHT:
					playSound(MOVE_HOOK);
					for(int i = 0; i < Hook.MOVE_FUEL_COST*10; i++) {	
						getHook().moveRight();
					}
					break;
			}
		}else {
			TreasureHunter.screen.print("Combustible insuficiente");
			playSound(NO_FUEL);
		}
		
		switch(option) {
			case DOWN:
				goDownHook();
				break;
			case BUY_HOOK:
				improveHook();
				break;
			case BUY_FUEL:
				buyFuel();
				break;
		}
	}
	
	public void start(Memento memento) {

		playSound(PLAY);
		boolean end = false;
		
		while (inGame() && !(end)) {
			
			lineBreak(2);
			showTreasures();
			lineBreak(1);
			TreasureHunter.screen.print(showPlayerStats());
			TreasureHunter.screen.print(showHookStats());
			lineBreak(1);
			
			TreasureHunter.screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [" + Hook.COST_CHAIN + "]) || G(Recargar combustible ["+ Hook.FUEL_COST + "]): ");
			String option = TreasureHunter.keyboard.nextLine();
			
			switch(option){
				case END:
					end = true;
					break;
				default:
					selectOption(option);
			}
			getHook().goUp();
							
			memento.setPlayerState(getPlayer());
			memento.setHookState(getHook());
			memento.saveGame();
		}
		
		TreasureHunter.screen.print("La mina se derrumbo\n");
		playSound(DEFEAT);
		TreasureHunter.screen.print("Pulse una tecla para finalizar");
	}
}
