package treasureHunter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame{

	public static final int MAP_WIDTH = 300;
	public static final int MAP_DEPTH = 500;
	public static final int MAX_TREASURES = 25;
	
	public static final String NO_FUEL = "Combustible insuficiente\n";
	public static final String NO_MONEY = "Dinero insuficiente\n";
	
	//opt
	public static final String LEFT = "A";
	public static final String RIGHT = "D";
	public static final String DOWN = "E";
	public static final String BUY_HOOK = "B";
	public static final String BUY_FUEL = "G";
	public static final String END = "F";
	
	//attributes
	private Player player;
	private Hook hook;  //atributo de Player
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
		
	public boolean collisionTreasure() {  //necesito un boolean para chequear que colisionó con un tesoro y al mismo tiempo necesito que me devuelva el tesoro con el cual pasó
		
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().equals(getHook().getPositionHook())) { //player.collisionTreasureHook(treasureAux)
				player.setBalance(treasureAux.getPrice()); //esto deberia ser un metodo de Player, en donde recibe un tesoro y lo suma al balance.
				System.out.println("recurso recolectado: " + treasureAux.getType());
				it.remove();
				return true;
			}
		}
		return false;
	}
	
	public void goDownHook() {
		
		if(getHook().thereIsFuel()) {
			
			int loweredMeter = 0;
			while( ( (!( collisionTreasure() || getHook().collisionBorderMap(MAP_WIDTH, MAP_DEPTH) || loweredMeter >= getHook().getLenght() ) && getHook().thereIsFuel()) ) ) {
				getHook().goDown();
				loweredMeter++;
			}
		}
	}

	public void improveHook() {  //metodo de player,
		if(player.canBuyUpgradeHook()) {
			if(hook.noMaxLength()) {		
				player.setBalance(-Hook.COST_CHAIN);
				hook.setLenght(Hook.IMPROVE_LENGHT);
			}else {
				System.out.println("Longitud maxima alcanzada.\n");
			}
		}else {
			System.out.println(NO_MONEY);
		}
	}
	
	public void buyFuel() {
		if(player.canBuyFuel()) {
			player.setBalance(-Hook.FUEL_COST);
			getHook().setFuel(Hook.RECHARGE_FUEL);
		}else {
			System.out.println(NO_MONEY);
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
					if(getHook().collisionBorderMap(MAP_WIDTH, MAP_DEPTH)) {
						getHook().moveRight();
					}else {
						getHook().setFuel(-1);
					}
					break;
				case RIGHT:
					getHook().moveRight();
					if(getHook().collisionBorderMap(MAP_WIDTH, MAP_DEPTH)) {
						getHook().moveLeft();
					}else {
						getHook().setFuel(-1);
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
			System.out.println(NO_FUEL);
		}
	}
	
	public void start(Memento memento) {

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
			
			System.out.println("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + Hook.COST_CHAIN + "]) "
					           + "|| G(Recargar combustible [$"+ Hook.FUEL_COST + "]) F(Salir): ");
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
