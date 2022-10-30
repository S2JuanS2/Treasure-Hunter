package tpAlgo3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame{

	public static final int MAP_WIDTH = 300;
	public static final int MAP_DEPTH = 500;
	public static final int MAX_TREASURES = 10;
	
	public static final String NO_FUEL = "Combustible insuficiente\n";
	public static final String NO_MONEY = "Dinero insuficiente\n";
	
	//opt
	public static final String LEFT = "A";
	public static final String DOUBLE_LEFT = "AA";
	public static final String RIGHT = "D";
	public static final String DOUBLE_RIGHT = "DD";
	public static final String DOWN = "E";
	public static final String BUY_HOOK = "B";
	public static final String BUY_FUEL = "G";
	public static final String END = "F";
	
	//attributes
	private Player player;
	private Hook hook;
	private Map map;
	private ArrayList<Treasure> treasure;
	
	public TreasureHunterGame() {
		
		this.player = new Player(null);
		this.hook = new Hook();
		this.map = new Map(MAP_WIDTH, MAP_DEPTH);
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

	public Map getMap() {
		return map;
	}
	
	public List<Treasure> getTreasure() {
		return treasure;
	}

	public void addTreasure(Treasure treasure) {
		
		this.treasure.add(treasure);
		
	}
	
	public void generateTreasures() {
		
		Director director = new Director();
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
		return (this.getPlayer().toString());
	}
	
	public String showHookStats() {
		return (this.getHook().toString());
	}
		
	public void lineBreak(int i) {
		
		for( int j = 0; j < i; j++) {
			Menu.screen.print("\n");
		}
	}
	
	public boolean collisionTreasure() {
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(getHook().getPositionHook())) {
				player.setBalance(treasureAux.getPrice());
				Menu.screen.print("recurso recolectado: " + treasureAux.getType());
				it.remove();
				Sound.playSound(Sound.TREASURE_COLLISION_SOUND);
				return true;
			}
		}
		return false;
	}
	
	public void goDownHook() {
		
		if(getHook().thereIsFuel()) {
			Sound.playSound(Sound.HOOK_SOUND);
			
			int loweredMeter = 0;
			while( ( (!( collisionTreasure() || getHook().collisionBorderMap(map) || loweredMeter >= getHook().getLenght() ) && getHook().thereIsFuel()) ) ) {
				getHook().goDown();
				loweredMeter++;
			}
		}else {
			Menu.screen.print(NO_FUEL);
			Sound.playSound(Sound.NO_FUEL_SOUND);
		}
	}

	public void improveHook() {
		if(player.canBuyUpgradeHook()) {
			if(hook.noMaxLength()) {		
				Sound.playSound(Sound.BOUGTH_SOUND);
				player.setBalance(-Hook.COST_CHAIN);
				hook.setLenght(Hook.IMPROVE_LENGHT);
			}else {
				Menu.screen.print("Longitud maxima alcanzada.\n");
				Sound.playSound(Sound.NO_FUEL_SOUND);
			}
		}else {
			Menu.screen.print(NO_MONEY);
			Sound.playSound(Sound.ERROR_MONEY_SOUND);
		}
	}
	
	public void buyFuel() {
		if(player.canBuyFuel()) {
			Sound.playSound(Sound.BOUGTH_SOUND);
			player.setBalance(-Hook.FUEL_COST);
			getHook().setFuel(Hook.RECHARGE_FUEL);
		}else {
			System.out.println(NO_MONEY);
			Sound.playSound(Sound.ERROR_MONEY_SOUND);
		}
	}

	public boolean inGame() {
		return(getHook().thereIsFuel() || getPlayer().canBuyFuel());
	}
	
	public void selectOption(String option) {		//YA NO PASA LOS BORDES PERO CONSUME COMBUSTIBLE IGUAL.
			
		if(getHook().thereIsFuel()) {
			switch(option) {
				case LEFT:
					getHook().moveLeft();
					if(getHook().collisionBorderMap(map)) {
						getHook().moveRight();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
					}
					break;
				case RIGHT:
					getHook().moveRight();
					if(getHook().collisionBorderMap(map)) {
						getHook().moveLeft();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);			
					}
					break;
			}
		}else {
			Menu.screen.print(NO_FUEL);
			Sound.playSound(Sound.NO_FUEL_SOUND);
		}
		if(getHook().getFuel() >= (Hook.MOVE_FUEL_COST*10)){
			switch(option) {
				case DOUBLE_LEFT:
					for(int i = 0; (i < Hook.MOVE_FUEL_COST*10) && (!getHook().collisionBorderMap(map)); i++) {
						getHook().moveLeft();
					}
					if(getHook().collisionBorderMap(map)){
						getHook().moveRight();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
					}
					break;
				case DOUBLE_RIGHT:
					for(int i = 0; (i < Hook.MOVE_FUEL_COST*10) && (!getHook().collisionBorderMap(map)); i++) {	
						getHook().moveRight();
					}
					if(getHook().collisionBorderMap(map)) {
						getHook().moveLeft();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
					}
					break;
			}
		}else {
			Menu.screen.print(NO_FUEL);
			Sound.playSound(Sound.NO_FUEL_SOUND);
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

		String option;
		boolean end = false;
		
		Sound.playSound(Sound.PLAY_SOUND);
		generateTreasures();
		
		while (inGame() && !(end)) {
			
			lineBreak(2);
			showTreasures();
			lineBreak(1);
			Menu.screen.print(showPlayerStats());
			Menu.screen.print(showHookStats());
			lineBreak(1);
			
			Menu.screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + Hook.COST_CHAIN + "]) || G(Recargar combustible [$"+ Hook.FUEL_COST + "]): ");
			option = Menu.keyboard.nextLine();
			
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
		
		if(inGame()) {
			Menu.screen.print("Partida guardada.\n");
		}else {
			Menu.screen.print("La mina se derrumbo\n");
			Sound.playSound(Sound.DEFEAT_SOUND);
		}
		Menu.screen.print("Pulse una tecla para finalizar");
		option = Menu.keyboard.nextLine();
	}
}
