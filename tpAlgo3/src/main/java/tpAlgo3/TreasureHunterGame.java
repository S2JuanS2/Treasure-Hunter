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

public class TreasureHunterGame {
	
	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	public static final String HOOK_SOUND = "hook.wav";
	public static final String TREASURE_COLLISION_SOUND = "treasure.wav";
	public static final String BOUGTH = "buy.wav";
	public static final String ERROR_MONEY = "errorMoney.wav";
	public static final String MOVE_HOOK = "moveHook.wav";
	public static final String NO_FUEL = "noFuel.wav";
	public static final String DEFEAT = "defeat.wav";
	public static final String PLAY = "play.wav";
	
	static final String LEFT = "A";
	static final String RIGHT = "D";
	static final String DOWN = "E";
	static final String BUY_HOOK = "B";
	static final String BUY_FUEL = "G";
	static final String END = "F";

	

	private Hook hook;
	private List<Treasure> treasure;
	private Player player;
	private Map map;
	
	public TreasureHunterGame(String name, int dimension) {
		
		this.hook = new Hook();
		this.treasure = new ArrayList<Treasure>();
		this.player = new Player(name);
		this.map = new Map(dimension);
		
	}

	public Hook getHook() {
		return hook;
	}

	public void setHook(Hook hook) {
		this.hook = hook;
	}

	public List<Treasure> getTreasure() {
		return treasure;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Map getMap() {
		return map;
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
	
	void playSound(String sound) {
		
		try {
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(sound).getAbsoluteFile());
	        Clip clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.start();
	        
		}catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
	         System.out.println("Error al reproducir el sonido.");
		}
	}
	
	void collectTreasure() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().getX() == hook.getPositionHook().getX() && 
			   treasureAux.getPosition().getY() == hook.getPositionHook().getY()) {
				player.setBalance(treasureAux.getPrice());
				it.remove();
			}

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
				player.setBalance(treasureAux.getPrice()); //LINEA ADD
				System.out.println("recurso recolectado: " + treasureAux.getType());
				it.remove();					// LINEA ADD
				return true;
			}
		}
		return false;
	}
	
	boolean collisionBorderMap(){
		
		return(getHook().getPositionHook().getX() <= 0 || getHook().getPositionHook().getX() >= map.getDimension() || getHook().getPositionHook().getY() >= map.getDimension());
	}

	void goDownHook() {
		
		
		if(getHook().getFuel() >= getHook().getLenght()) {
			playSound(HOOK_SOUND);
			
			int contador = 0;
			while( !( collisionTreasure() || collisionBorderMap() || contador >= getHook().getLenght() ) ) {
				getHook().getPositionHook().oneAddY();
				getHook().setFuel(-1);
				contador++;
			}
		}else {
			screen.print("Combustible insuficiente");
			playSound(NO_FUEL);
		}
		
	}

	public void goUpHook() {
		while (getHook().getPositionHook().getY() > 20) {
			getHook().getPositionHook().oneLessY();
		}
	}

	public void improveHook() {
		if(player.getBalance() >= 100) {
			playSound(BOUGTH);
			player.setBalance(-100);
			hook.setLenght(10);
		}else {
			System.out.println("Dinero insuficiente\n");
			playSound(ERROR_MONEY);
		}
	}
	
	public void buyFuel() {
		if(player.getBalance() >= 80) {
			playSound(BOUGTH);
			player.setBalance(-80);
			getHook().setFuel(200);
		}else {
			System.out.println("Dinero insuficiente\n");
			playSound(ERROR_MONEY);
		}
	}

	public void play() {

		playSound(PLAY);
		
		while (getHook().getFuel() >= getHook().getLenght() || getPlayer().getBalance() >= 80) {
			
			screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar soga 10m [$100.0]) || G(Recargar combustible [$80.0]): ");
			String mover = keyboard.nextLine();
			
			switch(mover) {
			case LEFT:
				if(getHook().getFuel() >= 1) {
					getHook().getPositionHook().oneLessX();
					getHook().setFuel(-1);
					playSound(MOVE_HOOK);
				}else {
					screen.print("Combustible insuficiente");
					playSound(NO_FUEL);
				}
				break;
			case RIGHT:
				if(getHook().getFuel() >= 1) {
					getHook().getPositionHook().oneAddX();
					getHook().setFuel(-1);
					playSound(MOVE_HOOK);
				}else {
					screen.print("Combustible insuficiente");
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
				
				break;
			}
				goUpHook();
				
				screen.print("\n");
				showTreasures();
				screen.print("\n");
				screen.print(showPlayerStats());
				screen.print("\n");
				screen.print(showHookStats());
				screen.print("\n");		
		}
		
		playSound(DEFEAT);
	}
}
