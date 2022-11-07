package treasureHunter;

import java.io.File;

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
				treasureHunterGame.generateTreasures();
				screen.print("\n");
				correctOpt = true;
				break;
			case YES:
				File archivo = new File("datosPlayer.txt");
				if(!(archivo.exists())) {
					screen.print("Ninguna partida encontrada\n");
					screen.print("Deseas continuar la partida? (S o N):");
					optContinue = keyboard.nextLine();
					break;
				}
				treasureHunterGame.setPlayer(memento.loadPlayer());
				treasureHunterGame.setHook(memento.loadHook());
				treasureHunterGame.setTreasure(memento.loadTreasures());
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
