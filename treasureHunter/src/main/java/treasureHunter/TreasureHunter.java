package treasureHunter;

public class TreasureHunter{

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	public static final String YES = "S";
	public static final String NO = "N";
	
	public static void main(String[] args) {
		
		boolean correctOpt = false;	
		Snapshot memento = new Snapshot(null,null);
		TreasureHunterGame treasureHunterGame = new TreasureHunterGame();				
		
		screen.print("TREASURE HUNTER\n");
		screen.print("Deseas continuar la partida? (S o N):");
		String optContinue = keyboard.nextLine();
				
		while((!correctOpt)) {
				
			switch(optContinue) {
			case NO:
				screen.print("Ingrese nombre del personaje:"); 
				treasureHunterGame.getPlayer().setName(keyboard.nextLine());
				screen.print("\n");
				correctOpt = true;
				break;
			case YES:
				treasureHunterGame.setPlayer(memento.loadPlayer());
				treasureHunterGame.setHook(memento.loadHook());
				correctOpt = true;
				break;	
			default:
				screen.print("Opcion incorrecta\n");
				screen.print("Deseas continuar la partida? (S o N):");
				optContinue = keyboard.nextLine();
			}
		}
		treasureHunterGame.start(memento);						
	}
}
