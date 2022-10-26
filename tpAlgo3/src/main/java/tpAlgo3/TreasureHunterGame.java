package tpAlgo3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TreasureHunterGame {

	private Hook hook;
	private ArrayList<Treasure> treasure;
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

	public List<Treasure> getTreasure() {
		return treasure;
	}

	public Player getPlayer() {
		return player;
	}

	public Map getTablero() {
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
	
	void collectTreasure() {
		Iterator<Treasure> it = treasure.iterator();
		while(it.hasNext()) {
			Treasure treasureAux = it.next();
			if(treasureAux.getPosition().getX() == hook.getPositionHook().getX() && 
			   treasureAux.getPosition().getY() == hook.getPositionHook().getY()) {
				player.setBalance(treasureAux.getPrice());
				
				//ELIMINAR TESORO
				treasureAux.setPosition(0,0);
			}

		}
	}
}
