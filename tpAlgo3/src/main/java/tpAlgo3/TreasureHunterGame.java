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
			
	static final int DIMENSION_MAP = 300;
	
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
		this.map = new Map(DIMENSION_MAP);
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
		
	//VIOLA EL PRINCIPIO DE RESPONSABILIDAD UNICA
	boolean collisionTreasure() {
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().getX() == hook.getPositionHook().getX() && 
			   treasureAux.getPosition().getY() == hook.getPositionHook().getY()) {
				playSound(TREASURE_COLLISION_SOUND);
				player.setBalance(treasureAux.getPrice());
				TreasureHunter.screen.print("recurso recolectado: " + treasureAux.getType());
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	boolean collisionBorderMap(){
		
		return(getHook().getPositionHook().getX() <= 0 || getHook().getPositionHook().getX() >= map.getDimension() || getHook().getPositionHook().getY() >= map.getDimension());
	}

	void goDownHook() {
		
		if(getHook().getFuel() > 0) {
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

	public void goUpHook() {
		while (getHook().getPositionHook().getY() > Hook.INITIAL_POSITION_Y) {
			getHook().goUp();
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
	
	public void selectOption(String option, boolean end) {
		
		switch(option) {
		case LEFT:
			if(getHook().getFuel() >= Hook.MOVE_FUEL_COST) {
				getHook().moveLeft();
				playSound(MOVE_HOOK);
			}else {
				TreasureHunter.screen.print("Combustible insuficiente");
				playSound(NO_FUEL);
			}
			break;
		case DOUBLE_LEFT:
			if(getHook().getFuel() >= (Hook.MOVE_FUEL_COST*10)) {
				playSound(MOVE_HOOK);
				for(int i = 0; i < 10; i++) {	
					getHook().moveLeft();
				}
			}else {
				TreasureHunter.screen.print("Combustible insuficiente");
				playSound(NO_FUEL);
			}
			break;
		case RIGHT:
			if(getHook().getFuel() >= Hook.MOVE_FUEL_COST) {
				getHook().moveRight();
				playSound(MOVE_HOOK);
			}else {
				TreasureHunter.screen.print("Combustible insuficiente");
				playSound(NO_FUEL);
			}
			break;
		case DOUBLE_RIGHT:
			if(getHook().getFuel() >= (Hook.MOVE_FUEL_COST*10)) {
				playSound(MOVE_HOOK);
				for(int i = 0; i < 10; i++) {	
					getHook().moveRight();
				}
			}else {
				TreasureHunter.screen.print("Combustible insuficiente");
				playSound(NO_FUEL);
			}
			break;
		case DOWN:
			goDownHook();
			break;
		case BUY_HOOK:
			improveHook();
			break;
		case BUY_FUEL:
			buyFuel();
			break;
		case END:
			end = true;
			break;
		}
	}
	
	public void play(Memento memento) {

		playSound(PLAY);
		boolean end = false;
		
		while (inGame() && !(end)) {
			
			TreasureHunter.screen.print("\n");
			showTreasures();
			TreasureHunter.screen.print("\n");
			TreasureHunter.screen.print(showPlayerStats());
			TreasureHunter.screen.print("\n");
			TreasureHunter.screen.print(showHookStats());
			TreasureHunter.screen.print("\n");	
			
			TreasureHunter.screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [" + Hook.COST_CHAIN + "]) || G(Recargar combustible ["+ Hook.FUEL_COST + "]): ");
			String option = TreasureHunter.keyboard.nextLine();
			
			selectOption(option, end);
			
			goUpHook();
							
			memento.setPlayerState(getPlayer());
			memento.setHookState(getHook());
			memento.saveGame();
		}
		
		TreasureHunter.screen.print("La mina se derrumbo\n");
		TreasureHunter.screen.print("Pulse una tecla para finalizar");
		playSound(DEFEAT);
	}
}
