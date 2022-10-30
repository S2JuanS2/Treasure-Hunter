package tpAlgo3;

public class TreasureHunter extends Interactions{

	public static void main(String[] args) {
		
		Memento memento = new Memento(null,null);
		TreasureHunterGame treasureHunterGame = new TreasureHunterGame();
		
		Interactions.menu(treasureHunterGame,memento);
		treasureHunterGame.start(memento);
						
	}

}
