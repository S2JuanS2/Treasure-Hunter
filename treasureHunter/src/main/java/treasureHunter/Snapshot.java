package treasureHunter;

public class Snapshot extends File{

	private Player playerState;
	private Hook hookState;
	
	public Snapshot(Player playerStateToSave, Hook hookStateToSave) {

		this.playerState = playerStateToSave;
		this.hookState = hookStateToSave;
	}

	public Player getPlayerState() {
		return playerState;
	}


	public Hook getHookState() {
		return hookState;
	}

	public void setPlayerState(Player playerState) {
		this.playerState = playerState;
	}


	public void setHookState(Hook hookState) {
		this.hookState = hookState;
	}

	public void saveGame() {
		
		playerSave(playerState);
		hookSave(hookState);
	}
	
	public Player loadPlayer() {
		return(playerUpload());
	}
	
	public Hook loadHook() {
		return(hookUpload());
	}
	
}
