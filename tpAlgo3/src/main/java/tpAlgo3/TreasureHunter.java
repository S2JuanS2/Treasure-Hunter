package tpAlgo3;

public class TreasureHunter {

	public static final java.util.Scanner keyboard = new java.util.Scanner(System.in);
	public static final java.io.PrintStream screen = new java.io.PrintStream(System.out);
	
	static final int MAX_TREASURES = 20;
	static final int DIMENSION_MAP = 300;
	static final String LEFT = "A";
	static final String RIGHT = "D";
	static final String DOWN = "E";
	static final String BUY = "B";
	
	
	public static void main(String[] args) {
		
		screen.print("TREASURE HUNTER\n");
		screen.print("Ingrese nombre del personaje:");
		//String name = keyboard.nextLine();
		String name = "test";
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
		screen.print(treasureHunterGame.showHookStats());
		screen.print("\n");
		screen.print("\n");
		
		for (int i = 0; i < 100; i++) {
			
			screen.print("A(Izquierda) || D(Derecha) || E(Bajar) || B(Alargar soga 10m ($200.0): ");
			String mover = keyboard.nextLine();
			
			switch(mover) {
			case LEFT:
				treasureHunterGame.getHook().getPositionHook().oneLessX();
				break;
			case RIGHT:
				treasureHunterGame.getHook().getPositionHook().oneAddX();
				break;
			case DOWN:
				treasureHunterGame.goDownHook();
				break;
			case BUY:
				treasureHunterGame.improveHook();
				break;
			}
				
			treasureHunterGame.goUpHook();
			
			screen.print("\n");
			treasureHunterGame.showTreasures();
			screen.print("\n");
			screen.print(treasureHunterGame.showPlayerStats());
			screen.print("\n");
			screen.print(treasureHunterGame.showHookStats());
			screen.print("\n");
		}
	}

}
