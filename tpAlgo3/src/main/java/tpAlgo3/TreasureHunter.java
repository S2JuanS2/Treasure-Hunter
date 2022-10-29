package tpAlgo3;

public class TreasureHunter {

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	static final int MAX_TREASURES = 25;
	static final String YES = "S";
	static final String NO = "N";
	
	public static void main(String[] args) {
		
		Memento memento = new Memento(null,null);
		String name = null;
		boolean correctOpt = false;
		
		screen.print("TREASURE HUNTER\n");
		screen.print("Deseas continuar la partida? (S o N):");
		String optContinue = keyboard.nextLine();	
		
		TreasureHunterGame treasureHunterGame = new TreasureHunterGame(name);
		
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
		
		Director director = new Director();
		TreasureBuilder builder = new TreasureBuilder();
		
		//ITERADOR PARA GENERAR TESOROS
		for(int i = 0; i < MAX_TREASURES; i++) {
			director.constructRandomTreasure(builder); 
			Treasure treasure = builder.getResults();
			treasureHunterGame.addTreasure(treasure);
		}
		
		treasureHunterGame.start(memento);
						
		optContinue = keyboard.nextLine();
	}

}
