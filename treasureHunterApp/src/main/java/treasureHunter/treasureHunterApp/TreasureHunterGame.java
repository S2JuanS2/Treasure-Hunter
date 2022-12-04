package treasureHunter.treasureHunterApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame{

	public static final int MAP_WIDTH = 640;
	public static final int MAP_DEPTH = 480;
	public static final int MAX_TREASURES = 40;
				
	//attributes
	private Store store;
	private Player player;
	private Hook hook;
	private ArrayList<Treasure> treasure;
	private Snapshot snapshot;
	
	public TreasureHunterGame() {
		
		this.player = new Player(null);
		this.hook = new Hook();
		this.treasure = new ArrayList<>();
		this.store = new Store();
		this.snapshot = new Snapshot(null,null);
		
	}
	
	public Snapshot getSnapshot() {
		return snapshot;
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
	 * DEVUELVE TRUE SI EL GANCHO COLISIONA CON UN TESORO, 
	 * ELIMINA DE LA LISTA EL TESORO CON EL CUAL COLISONÓ
	 * SE LE ACREDITA AL JUGADOR EL PRECIO DEL MISMO
	 */
	public boolean collisionTreasure() {
		
		if(player.getTreasure() == null) {
			Iterator<Treasure> it = treasure.iterator();
			while(it.hasNext()) {
				Treasure treasureAux = it.next();
				if(treasureAux.getPosition().equals(hook.getPosition())) {
					hook.getEngine().enoughPower(treasureAux.getWeight());
					player.setTreasure(treasureAux);
					it.remove();
					return true;
				}
			}		
		}
		return false;
	}
	
	public boolean collect() {
		if(hook.initialPosition() && player.getTreasure() != null) {
			player.accreditBalance(player.getTreasure().getPrice());
			player.setTreasure(null);
			return true;
		}else {	
			return false;
		}
	}
	
	
	/*
	 * SOLICITA AL GANCHO QUE DESCIENDA
	 */
	public void goDownHook() {
		
		if(hook.canKeepGoingDown()) {		
			hook.goDown(MAP_WIDTH, MAP_DEPTH);
		}
	}

	public boolean canBuyFuel() {
		return(getStore().canBuy(getPlayer().getBalance(), Store.FUEL_COST) && 
			   getStore().noMaxFuel(getHook().getEngine().getFuel()));
	}
	
	public boolean canBuyImproveHook() {
		return(getStore().canBuy(getPlayer().getBalance(), Store.COST_UPGRADE_HOOK) && 
				getStore().noMaxLength(getHook().getLength()));
	}
	
	public boolean canBuyImproveEngine() {
		return(getStore().canBuy(getPlayer().getBalance(), Store.COST_UPGRADE_ENGINE) && 
				getStore().noMaxPower(getHook().getEngine().getPower()));
	}
	
	/*
	 * DEVUELVE TRUE SI EL GANCHO TIENE COMBUSTIBLE O SI EL JUGADOR
	 * PUEDE COMPRAR EL MISMO.
	 */
	public boolean inGame() {
		return(hook.thereIsFuel() || canBuyFuel());
	}
	
	public boolean winCondition() {
		return(getTreasure().isEmpty() && getHook().initialPosition() && getPlayer().getTreasure() == null);
	}
	
	/*
	 *  MUESTRA LOS TESOROS AL USUARIO Y SUS ESTADISTICAS
	 * LE PIDE AL USUARIO QUE ELIJA UNA OPCION
	 */
	public void saveGame() throws IOException {

		snapshot.saveGame(player, hook, treasure);

	}
	
	public void reset() {
		this.player = new Player(null);
		this.hook = new Hook();
		this.treasure = new ArrayList<>();
		this.store = new Store();
		this.snapshot = new Snapshot(null,null);
		this.getHook().getEngine().resetVelocity();
	}
}
