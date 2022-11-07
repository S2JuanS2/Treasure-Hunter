package treasureHunter;

import java.util.ArrayList;

public class Snapshot extends File{

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
	 * GUARDA EL ESTADO DEL GANCHO Y DEL JUGADOR
	 * EN UN ARCHIVO DE TEXTO
	 */
	public void saveGame() {
		
		playerSave(playerState);
		hookSave(hookState);
		TreasureListSave(treasureState);
	}
	
	/*
	 * DEVUELVE UN JUGADOR RECIBIDO DESDE UN 
	 * ARCHIVO DE TEXTO
	 */
	public Player loadPlayer() {
		return(playerUpload());
	}
	
	/*
	 * DEVUELVE UN GANCHO RECIBIDO DESDE UN 
	 * ARCHIVO DE TEXTO
	 */
	public Hook loadHook() {
		return(hookUpload());
	}
	
	public ArrayList<Treasure> loadTreasures(){
		return(treasureListUpload());
	}

	public ArrayList<Treasure> getTreasureState() {
		return treasureState;
	}

	public void setTreasureState(ArrayList<Treasure> treasureState) {
		this.treasureState = treasureState;
	}
	
}
