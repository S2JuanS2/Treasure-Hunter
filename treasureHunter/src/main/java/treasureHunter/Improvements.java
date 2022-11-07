package treasureHunter;

public abstract class Improvements {

	static final float FUEL_COST = 80;
	static final float COST_UPGRADE_HOOK = 100;
	static final float COST_UPGRADE_ENGINE = 200;
	
	private Player player;
	private Hook hook;
	
	public abstract Player getPlayer();
	public abstract Hook getHook();
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setHook(Hook hook) {
		this.hook = hook;
	}
	
	public void improveHook() {
		if(player.canBuyUpgrade(COST_UPGRADE_HOOK)) {
			if(hook.noMaxLength()) {		
				player.deductBalance(COST_UPGRADE_HOOK);
				hook.increaseLenght();
			}
		}
	}
	
	public void improveEngine() {
		if(player.canBuyUpgrade(COST_UPGRADE_ENGINE)) {
			if(hook.getEngine().noMaxPower()) {
				player.deductBalance(COST_UPGRADE_ENGINE);
				hook.getEngine().improvePower();
			}
		}
	}
	
	public void buyFuel() {
		if(player.canBuyUpgrade(FUEL_COST)) {
			player.deductBalance(FUEL_COST);
			hook.getEngine().accreditFuel();
		}
	}
}
