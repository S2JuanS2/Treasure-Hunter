package treasureHunter;

public abstract class Improvements {

	public static final float FUEL_COST = 80;
	public static final float COST_UPGRADE_HOOK = 100;
	public static final float COST_UPGRADE_ENGINE = 200;
	
	private Player player;
	private Hook hook;
	
	public abstract Player getPlayer();
	public abstract Hook getHook();
	
	public void setState(Player player) {
		this.player = player;
	}
	
	public void setState(Hook hook) {
		this.hook = hook;
	}
	
	/*
	 * INCREMENTA LA LONGITUD DEL GANCHO DESCONTANDOLE AL JUGADOR 
	 * EL COSTO DE LA MEJORA
	 */
	public void improveHook() {
		if(player.canBuyUpgrade(COST_UPGRADE_HOOK)) {
			if(hook.noMaxLength()) {		
				player.deductBalance(COST_UPGRADE_HOOK);
				hook.increaseLenght();
			}
		}
	}
	
	/*
	 * INCREMENTA LA POTENCIA DEL MOTOR DESCONTANDOLE AL JUGADOR
	 * EL COSTO DE LA MEJORA
	 */
	public void improveEngine() {
		if(player.canBuyUpgrade(COST_UPGRADE_ENGINE)) {
			if(hook.getEngine().noMaxPower()) {
				player.deductBalance(COST_UPGRADE_ENGINE);
				hook.getEngine().improvePower();
			}
		}
	}
	
	/*
	 * INCREMENTA EL COMBUSTIBLE DEL MOTOR DESCONTANDOLE AL JUGADOR
	 * EL COSTO DEL MISMO
	 */
	public void buyFuel() {
		if(player.canBuyUpgrade(FUEL_COST)) {
			if(hook.getEngine().noMaxFuel()) {			
				player.deductBalance(FUEL_COST);
				hook.getEngine().accreditFuel();
			}
		}
	}
}
