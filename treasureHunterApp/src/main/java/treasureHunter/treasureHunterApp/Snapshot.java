package treasureHunter.treasureHunterApp;

import java.io.IOException;
import java.util.ArrayList;

public class Snapshot extends Persistence{

	private Player playerState;
	private Hook hookState;
	private ArrayList<Treasure> treasureState;
	
	public Snapshot(Player playerStateToSave, Hook hookStateToSave) {

		this.playerState = playerStateToSave;
		this.hookState = hookStateToSave;
	}

	/*
	 * DEVUELVE EL ESTADO DE UN JUGADOR
	 */
	public Player getPlayerState() {
		return playerState;
	}

	/*
	 * DEVUELVE EL ESTADO DE UN GANCHO
	 */
	public Hook getHookState() {
		return hookState;
	}

	/*
	 * ALMACENA EL ESTADO DE UN JUGADOR
	 */
	public void setPlayerState(Player playerState) {
		this.playerState = playerState;
	}

	/*
	 * ALMACENA EL ESTADO DE UN GANCHO
	 */
	public void setHookState(Hook hookState) {
		this.hookState = hookState;
	}

	/*
	 * DEVUELVE LA LISTA DE TESOROS
	 */
	public ArrayList<Treasure> getTreasureState() {
		return treasureState;
	}

	/*
	 * ALMACENA LA LISTA DE TESOROS;
	 */
	public void setTreasureState(ArrayList<Treasure> treasureState) {
		this.treasureState = treasureState;
	}
	
	/*
	 * GUARDA EL ESTADO DEL GANCHO, DEL JUGADOR Y DE LA LISTA DE LOS TESOROS
	 * EN UN ARCHIVO DE TEXTO
	 */
	public void saveGame(Player player, Hook hook, ArrayList<Treasure> treasure) throws IOException {
		
		setPlayerState(player);
		setHookState(hook);
		setTreasureState(treasure);
		playerSave(playerState);
		hookSave(hookState);
		TreasureListSave(treasureState);
	}
	
	/*
	 * DEVUELVE UN JUGADOR RECIBIDO DESDE UN 
	 * ARCHIVO DE TEXTO
	 */
	public Player loadPlayer() throws ClassNotFoundException, IOException {
		return(playerUpload());
	}
	
	/*
	 * DEVUELVE UN GANCHO RECIBIDO DESDE UN 
	 * ARCHIVO DE TEXTO
	 */
	public Hook loadHook() throws ClassNotFoundException, IOException {
		return(hookUpload());

	}
	
	/*
	 * DEVUELVE LA LISTA DE TESOROS RECIBIDA
	 * DESDE UN ARCHIVO
	 */
	public ArrayList<Treasure> loadTreasures() throws ClassNotFoundException, IOException{
		return(treasureListUpload());
	}
}
