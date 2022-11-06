package treasureHunter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame {

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
	
	public void showTreasures() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	public String showPlayerStats() {
		return (getPlayer().toString());
	}
	
	public String showHookStats() {
		return (getHook().toString());
	}
	
	public boolean collisionTreasure() {
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(getHook().getPosition())) {
				player.accreditBalance(treasureAux.getPrice());
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public void goDownHook() {
		
		for(int i = 1; !(collisionTreasure()) && getHook().thereIsFuel() && getHook().canKeepGoingDown(i); i++) {	//ORDEN DE PRIORI		
			getHook().goDown(MAP_WIDTH, MAP_DEPTH);
		}
	}

	public void improveHook() {
		if(player.canBuyUpgradeHook()) {
			if(hook.noMaxLength()) {		
				player.deductBalance(Player.COST_UPGRADE);
				hook.increaseLenght();
			}
		}
	}
	
	public void buyFuel() {
		if(player.canBuyFuel()) {
			player.deductBalance(Player.FUEL_COST);
			getHook().accreditFuel(Hook.RECHARGE_FUEL);
		}
	}

	public boolean inGame() {
		return(getHook().thereIsFuel() || getPlayer().canBuyFuel());
	}
	
	public void selectOption(String option) {
			
		switch(option) {
			case LEFT:
				getHook().moveLeft();
				break;
			case RIGHT:
				getHook().moveRight(MAP_WIDTH);
				break;
			case DOWN:
				goDownHook();
				getHook().goUp();
				break;
			case BUY_HOOK:
				improveHook();
				break;
			case BUY_FUEL:
				buyFuel();
				break;
		}
	}
	
	public void start(Snapshot memento) {

		String option;
		boolean end = false;
		
		generateTreasures();
		
		while (inGame() && !(end)) {
			
			memento.setPlayerState(getPlayer());
			memento.setHookState(getHook());
			memento.saveGame();
			
			showTreasures();
			System.out.println(showPlayerStats());
			System.out.println(showHookStats());
			
			System.out.println("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + Player.COST_UPGRADE + "]) "
					           + "|| G(Recargar combustible [$"+ Player.FUEL_COST + "]) F(Salir): ");
			option = TreasureHunter.keyboard.nextLine();
			
			switch(option){
				case END:
					end = true;
					break;
				default:
					selectOption(option);
			}						
		}
		
		System.out.println(showPlayerStats());
		System.out.println(showHookStats());
		
		if(inGame()) {
			System.out.println("Partida guardada.\n");
		}else {
			System.out.println("GAME OVER: La mina se derrumbo\n");
		}
	}
}
