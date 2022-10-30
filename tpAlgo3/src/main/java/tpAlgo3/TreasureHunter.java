package tpAlgo3;

public class TreasureHunter {

	public static void main(String[] args) {
		
		Memento memento = new Memento(null,null);

		TreasureHunterGame treasureHunterGame = new TreasureHunterGame();
		
		Menu.menu(treasureHunterGame,memento);
		
		treasureHunterGame.start(memento);
						
	}

}
