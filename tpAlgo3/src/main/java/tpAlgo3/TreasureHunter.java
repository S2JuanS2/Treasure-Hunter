package tpAlgo3;

public class TreasureHunter {

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	static final int MAX_TREASURES = 25;
	static final int DIMENSION_MAP = 300;
	static final String YES = "S";
	static final String NO = "N";
	
	public static void main(String[] args) {
		
		screen.print("TREASURE HUNTER\n");
		screen.print("Deseas continuar la partida? (S o N):");
		String optContinue = keyboard.nextLine();
		
		//while (optContinue != YES && optContinue != NO) {
			//screen.print("Opcion incorrecta\n");
			//screen.print("Deseas continuar la partida? (S o N):");
			//optContinue = keyboard.nextLine();
		//}
		
		Memento memento = new Memento(null,null);
		String name = null;
		
		TreasureHunterGame treasureHunterGame = new TreasureHunterGame(name, DIMENSION_MAP);
		
		switch(optContinue) {
			
			case NO:
				screen.print("Ingrese nombre del personaje:"); 
				treasureHunterGame.getPlayer().setName(keyboard.nextLine());
				screen.print("\n");
				break;
			case YES:
				treasureHunterGame.setPlayer(memento.loadPlayer());
				treasureHunterGame.setHook(memento.loadHook());
				break;
		}
		
		Director director = new Director();
		TreasureBuilder builder = new TreasureBuilder();
		
		//ITERADOR PARA GENERAR TESOROS
		for(int i = 0; i < MAX_TREASURES; i++) {
			director.constructRandomTreasure(builder); 
			Treasure treasure = builder.getResults();
			treasureHunterGame.addTreasure(treasure);
		}
		
		treasureHunterGame.play(memento);
						
		optContinue = keyboard.nextLine();
	}

}
