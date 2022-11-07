package treasureHunter;

public interface ShowStats {

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	public void showPlayerStats();
	public void showHookStats();
	public void showTreasures();
	
}
