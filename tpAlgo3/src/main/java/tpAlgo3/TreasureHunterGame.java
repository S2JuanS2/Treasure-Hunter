package tpAlgo3;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame extends Interactions{

	public static final int MAP_WIDTH = 300;
	public static final int MAP_DEPTH = 500;
	public static final int MAX_TREASURES = 25;
	
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
	private Hook hook;  //atributo de Player
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
			Interactions.screen.print(it.next());
			lineBreak(1);
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
			Interactions.screen.print("\n");
		}
	}
	
	public boolean collisionTreasure() {  //necesito un boolean para chequear que colisionó con un tesoro y al mismo tiempo necesito que me devuelva el tesoro con el cual pasó
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(getHook().getPositionHook())) { //player.collisionTreasureHook(treasureAux)
				player.setBalance(treasureAux.getPrice()); //esto deberia ser un metodo de Player, en donde recibe un tesoro y lo suma al balance.
				Interactions.screen.print("recurso recolectado: " + treasureAux.getType());
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
		}
	}

	public void improveHook() {  //metodo de player,
		if(player.canBuyUpgradeHook()) {
			if(hook.noMaxLength()) {		
				Sound.playSound(Sound.BOUGTH_SOUND);
				player.setBalance(-Hook.COST_CHAIN);
				hook.setLenght(Hook.IMPROVE_LENGHT);
			}else {
				Interactions.screen.print("Longitud maxima alcanzada.\n");
				Sound.playSound(Sound.NO_FUEL_SOUND);
			}
		}else {
			Interactions.screen.print(NO_MONEY);
			Sound.playSound(Sound.ERROR_MONEY_SOUND);
		}
	}
	
	public void buyFuel() {
		if(player.canBuyFuel()) {
			Sound.playSound(Sound.BOUGTH_SOUND);
			player.setBalance(-Hook.FUEL_COST);
			getHook().setFuel(Hook.RECHARGE_FUEL);
		}else {
			Interactions.screen.print(NO_MONEY);
			Sound.playSound(Sound.ERROR_MONEY_SOUND);
		}
	}

	public boolean inGame() {
		return(getHook().thereIsFuel() || getPlayer().canBuyFuel());
	}
	
	public void selectOption(String option) {
			
		if(getHook().thereIsFuel()) {
			switch(option) {
				case LEFT:
					getHook().moveLeft();
					if(getHook().collisionBorderMap(map)) {
						getHook().moveRight();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
						getHook().setFuel(-1);
					}
					break;
				case RIGHT:
					getHook().moveRight();
					if(getHook().collisionBorderMap(map)) {
						getHook().moveLeft();
					}else {
						Sound.playSound(Sound.MOVE_HOOK_SOUND);	
						getHook().setFuel(-1);
					}
					break;
			}
		}
		if(getHook().getFuel() >= (Hook.MOVE_FUEL_COST*10)){
			int fuelCounter = 0;
			switch(option) {
				case DOUBLE_LEFT:
					for(int i = 0; (i < Hook.MOVE_FUEL_COST*10) && (!getHook().collisionBorderMap(map)); i++) {
						getHook().moveLeft();
						fuelCounter--;
					}
					if(getHook().collisionBorderMap(map)){
						getHook().moveRight();
						fuelCounter++;
					}if(fuelCounter != 0){
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
						getHook().setFuel(fuelCounter);
					}
					break;
				case DOUBLE_RIGHT:
					for(int i = 0; (i < Hook.MOVE_FUEL_COST*10) && (!getHook().collisionBorderMap(map)); i++) {	
						getHook().moveRight();
						fuelCounter--;
					}
					if(getHook().collisionBorderMap(map)) {
						getHook().moveLeft();
						fuelCounter++;
					}if(fuelCounter != 0){
						Sound.playSound(Sound.MOVE_HOOK_SOUND);
						getHook().setFuel(fuelCounter);
					}
					break;
			}
		}
		switch(option) {
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
		if(!getHook().thereIsFuel()) {
			Interactions.screen.print(NO_FUEL);
			Sound.playSound(Sound.NO_FUEL_SOUND);
		}
	}
	
	public void start(Memento memento) {

		String option;
		boolean end = false;
		
		Sound.playSound(Sound.PLAY_SOUND);
		generateTreasures();
		
		while (inGame() && !(end)) {
			
			memento.setPlayerState(getPlayer());
			memento.setHookState(getHook());
			memento.saveGame();
			
			lineBreak(2);
			showTreasures();
			lineBreak(1);
			Interactions.screen.print(showPlayerStats());
			Interactions.screen.print(showHookStats());
			lineBreak(1);
			
			Interactions.screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + Hook.COST_CHAIN + "]) || G(Recargar combustible [$"+ Hook.FUEL_COST + "])\n F(Salir): ");
			option = Interactions.keyboard.nextLine();
			
			switch(option){
				case END:
					end = true;
					break;
				default:
					selectOption(option);
			}						
		}
		
		lineBreak(1);
		Interactions.screen.print(showPlayerStats());
		Interactions.screen.print(showHookStats());
		lineBreak(1);
		
		if(inGame()) {
			Interactions.screen.print("Partida guardada.\n");
		}else {
			Interactions.screen.print("GAME OVER: La mina se derrumbo\n");
			Sound.playSound(Sound.DEFEAT_SOUND);
			Interactions.screen.print("Pulse una tecla para finalizar");
			option = Interactions.keyboard.nextLine();
		}
	}
}
