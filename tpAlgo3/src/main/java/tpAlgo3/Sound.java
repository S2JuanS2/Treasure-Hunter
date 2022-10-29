package tpAlgo3;

public interface Sound {

	public static final String HOOK_SOUND = "hook.wav";
	public static final String TREASURE_COLLISION_SOUND = "treasure.wav";
	public static final String BOUGTH = "buy.wav";
	public static final String ERROR_MONEY = "errorMoney.wav";
	public static final String MOVE_HOOK = "moveHook.wav";
	public static final String NO_FUEL = "noFuel.wav";
	public static final String DEFEAT = "defeat.wav";
	public static final String PLAY = "play.wav";
	
	void playSound(String sound);
}
