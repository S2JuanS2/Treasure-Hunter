package tpAlgo3;

public abstract class Interactions{
	
	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	static final String YES = "S";
	static final String NO = "N";
	
	public static void menu(TreasureHunterGame treasureHunterGame, Memento memento) {
		
		boolean correctOpt = false;
		
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
	}
}
