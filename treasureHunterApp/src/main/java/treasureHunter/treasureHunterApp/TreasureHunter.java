package treasureHunter.treasureHunterApp;

public class TreasureHunter{
	
	
	private TreasureHunterGame game;
	private View view;

	public TreasureHunter(TreasureHunterGame game, View view) {
		
		this.game = game;
		this.view = view;
	}
	
	public void start() {
		
		view.mainMenu(game);
	}
	

}
