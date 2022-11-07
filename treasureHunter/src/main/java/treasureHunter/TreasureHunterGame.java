package treasureHunter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame extends Improvements implements ShowStats{

	public static final int MAP_WIDTH = 300;
	public static final int MAP_DEPTH = 500;
	public static final int MAX_TREASURES = 25;
			
	//opt
	public static final String LEFT = "A";
	public static final String RIGHT = "D";
	public static final String DOWN = "E";
	public static final String BUY_HOOK = "B";
	public static final String BUY_FUEL = "G";
	public static final String END = "F";
	
	//attributes
	private Player player;
	private Hook hook;
	private ArrayList<Treasure> treasure;
	
	public TreasureHunterGame() {
		
		this.player = new Player(null);
		this.hook = new Hook();
		this.treasure = new ArrayList<>();	
		
		setPlayer(player);
		setHook(hook);
	}

	public Player getPlayer() {
		return player;
	}
	
	public Hook getHook() {
		return hook;
	}
	
	public List<Treasure> getTreasure() {
		return treasure;
	}

	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	public void generateTreasures() {
		
		DirectorTreasure director = new DirectorTreasure();
		TreasureBuilder builder = new TreasureBuilder();
		
		for(int i = 0; i < MAX_TREASURES; i++) {
			director.constructRandomTreasure(builder); 
			Treasure treasure = builder.getResults();
			addTreasure(treasure);
		}
	}
	
	public void showPlayerStats() {
		screen.print(player + "\n");
	}
	
	public void showHookStats() {
		screen.print(hook  + "\n");
	}
	
	public void showTreasures() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			screen.print(it.next()+"\n");
		}
	}
	
	public boolean collisionTreasure() {
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(hook.getPosition()) && hook.getEngine().enoughPower(treasureAux.getWeight())) {					
				player.accreditBalance(treasureAux.getPrice());
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public void goDownHook() {
		
		for(int i = 1; !(collisionTreasure()) && hook.thereIsFuel() && hook.canKeepGoingDown(i); i++) {		
			hook.goDown(MAP_WIDTH, MAP_DEPTH);
		}
	}

	public void selectOption(String option) {
			
		switch(option) {
			case LEFT:
				hook.moveLeft();
				break;
			case RIGHT:
				hook.moveRight(MAP_WIDTH);
				break;
			case DOWN:
				goDownHook();
				hook.goUp();
				break;
			case BUY_HOOK:
				improveHook();
				break;
			case BUY_FUEL:
				buyFuel();
				break;
		}
	}
	
	public boolean inGame() {
		return(hook.thereIsFuel() || player.canBuyUpgrade(FUEL_COST));
	}
	
	public void start(Snapshot snapshot) {

		String option;
		boolean end = false;
		
		generateTreasures();
		
		while (inGame() && !(end)) {
			
			snapshot.setPlayerState(player);
			snapshot.setHookState(hook);
			snapshot.saveGame();
			
			showTreasures();
			showPlayerStats();
			showHookStats();
			
			screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + COST_UPGRADE_HOOK + "]) " 
										+ "|| G(Recargar combustible [$"+ FUEL_COST + "]) F(Salir): ");
			option = keyboard.nextLine();
			
			switch(option){
				case END:
					end = true;
					break;
				default:
					selectOption(option);
			}						
		}
		showPlayerStats();
		showHookStats();
		
		if(inGame()) {
			screen.print("Partida guardada.\n");
		}else {
			screen.print("GAME OVER");
		}
	}
}
