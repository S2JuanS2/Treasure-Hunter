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
	
	public static final String HOOK_SOUND = "hook.wav";
	public static final String TREASURE_COLLISION_SOUND = "treasure.wav";
	public static final String BOUGTH = "buy.wav";
	public static final String ERROR_MONEY = "errorMoney.wav";
	

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
		
		playSound("hook.wav");
		
		int contador = 0;
		while( !( collisionTreasure() || collisionBorderMap() || contador >= getHook().getLenght() ) ) {
			getHook().getPositionHook().oneAddY();
			contador++;
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
}
