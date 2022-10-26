package tpAlgo3;

public class TreasureHunter {

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	public static final int MAX_TREASURES = 20;
	public static final int DIMENSION_MAP = 20;
	
	
	public static void main(String[] args) {
		
		screen.print("TREASURE HUNTER\n");
		screen.print("Ingrese nombre del personaje:");
		String name = keyboard.nextLine();
		screen.print("\n");
		TreasureHunterGame treasureHunterGame = new TreasureHunterGame(name, DIMENSION_MAP);
		
		Director director = new Director();
		TreasureBuilder builder = new TreasureBuilder();
		
		
		//ITERADOR PARA GENERAR TESOROS
		for(int i = 0; i < MAX_TREASURES; i++) {
			
			director.constructRandomTreasure(builder); 
			Treasure treasure = builder.getResults();
			treasureHunterGame.addTreasure(treasure);
		}
		
		//MOSTRAR ESTADISTICAS
		treasureHunterGame.showTreasures();
		screen.print("\n");
		screen.print(treasureHunterGame.showPlayerStats());
		screen.print("\n");
		screen.print("\n");
		screen.print(treasureHunterGame.showHookStats());
		screen.print("\n");
		
		
		//POSICIONAR EL GANCHO PARA RECOLECTAR
		for(int i = 0; i < 10; i++) {
			screen.print("X:");
			int a = Integer.parseInt(keyboard.nextLine());
			screen.print("Y:");
			int b = Integer.parseInt(keyboard.nextLine());
			
			treasureHunterGame.getHook().getPositionHook().setX(a);
			treasureHunterGame.getHook().getPositionHook().setY(b);
			treasureHunterGame.collectTreasure();
			screen.print(treasureHunterGame.showPlayerStats());
			screen.print("\n");
			treasureHunterGame.showTreasures();
			screen.print("\n");
			
		}

	}

}
