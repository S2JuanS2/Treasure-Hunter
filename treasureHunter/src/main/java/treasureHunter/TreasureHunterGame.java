package treasureHunter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame implements ShowStats{

	public static final int MAP_WIDTH = 300;
	public static final int MAP_DEPTH = 500;
	public static final int MAX_TREASURES = 25;
			
	//opt
	public static final String LEFT = "A";
	public static final String RIGHT = "D";
	public static final String DOWN = "E";
	public static final String BUY_HOOK = "B";
	public static final String BUY_FUEL = "G";
	public static final String BUY_POWER = "P";
	public static final String END = "F";
	
	//attributes
	private Store store;
	private Player player;
	private Hook hook;
	private ArrayList<Treasure> treasure;
	
	public TreasureHunterGame() {
		
		this.player = new Player(null);
		this.hook = new Hook();
		this.treasure = new ArrayList<>();
		this.store = new Store();
	}
	
	public Store getStore() {
		return store;
	}

	public Player getPlayer() {
		return player;
	}
	
	public Hook getHook() {
		return hook;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setHook(Hook hook) {
		this.hook = hook;
		this.hook.setState(this.hook.getPosition(), this.hook.getEngine());
	}
	
	public void setTreasure(ArrayList<Treasure> treasure) {
		this.treasure = treasure;
	}
	
	public List<Treasure> getTreasure() {
		return treasure;
	}

	/*
	 * AGREGA UN TESORO A LA LISTA DE TESOROS
	 */
	public void addTreasure(Treasure treasure) {
		this.treasure.add(treasure);
	}
	
	/*
	 * INSTANCIA TESOROS Y LOS AGREGA A LA LISTA
	 */
	public void generateTreasures() {
		
		DirectorTreasure director = new DirectorTreasure();
		TreasureBuilder builder = new TreasureBuilder();
		
		for(int i = 0; i < MAX_TREASURES; i++) {
			director.constructRandomTreasure(builder); 
			Treasure treasure = builder.getResults();
			addTreasure(treasure);
		}
	}
	
	/*
	 * MUESTRA LAS ESTADISTICAS EL JUGADOR
	 */
	public void showPlayerStats() {
		screen.print(player + "\n");
	}
	
	/*
	 * MUESTRA LAS ESTADISTICAS DEL GANCHO
	 */
	public void showHookStats() {
		screen.print(hook  + "\n");
	}
	
	/*
	 * MUESTRA LA LISTA DE TESOROS
	 */
	public void showTreasures() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			screen.print(it.next()+"\n");
		}
	}
	
	/*
	 * DEVUELVE TRUE SI EL GANCHO COLISIONA CON UN TESORO, 
	 * ELIMINA DE LA LISTA EL TESORO CON EL CUAL COLISONÓ
	 * SE LE ACREDITA AL JUGADOR EL PRECIO DEL MISMO
	 */
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
	
	/*
	 * SOLICITA AL GANCHO QUE DESCIENDA
	 */
	public void goDownHook() {
		
		for(int i = 1; !(collisionTreasure()) && hook.thereIsFuel() && hook.canKeepGoingDown(i); i++) {		
			hook.goDown(MAP_WIDTH, MAP_DEPTH);
		}
	}

	/*
	 * RECIBE LA OPCION ELEGIDA POR EL USUARIO
	 * Y LA INVOCA
	 */
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
				store.improveHook(player, hook);
				break;
			case BUY_FUEL:
				store.buyFuel(player, hook);
				break;
			case BUY_POWER:
				store.improveEngine(player, hook);
				break;
		}
	}
	
	/*
	 * DEVUELVE TRUE SI EL GANCHO TIENE COMBUSTIBLE O SI EL JUGADOR
	 * PUEDE COMPRAR EL MISMO.
	 */
	public boolean inGame() {
		return(hook.thereIsFuel() || player.canBuyUpgrade(Store.FUEL_COST));
	}
	
	/*
	 *  MUESTRA LOS TESOROS AL USUARIO Y SUS ESTADISTICAS
	 * LE PIDE AL USUARIO QUE ELIJA UNA OPCION
	 */
	public void start(Snapshot snapshot) throws IOException {

		String option;
		boolean end = false;
				
		while (inGame() && !(end)) {
			
			snapshot.saveGame(player, hook, treasure);
			
			showTreasures();
			showPlayerStats();
			showHookStats();
			
			screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar cadena 10m [$" + Store.COST_UPGRADE_HOOK + "]) " 
						+ "|| G(Recargar combustible [$"+ Store.FUEL_COST + "]) || P(Mejorar motor [$"+ Store.COST_UPGRADE_ENGINE + "]) || F(Salir): ");
			option = keyboard.nextLine();
			
			switch(option){
				case END:
					end = true;
					break;
				default:
					selectOption(option);
			}
		}
		
		if(inGame()) {
			screen.print("Partida guardada.\n");
		}else {
			screen.print("\n");
			showPlayerStats();
			showHookStats();
			screen.print("GAME OVER");
		}
	}
}
