package treasureHunter;

public class Store {

	public static final float FUEL_COST = 80;
	public static final float COST_UPGRADE_HOOK = 100;
	public static final float COST_UPGRADE_ENGINE = 200;
			
	/*
	 * INCREMENTA LA LONGITUD DEL GANCHO DESCONTANDOLE AL JUGADOR 
	 * EL COSTO DE LA MEJORA
	 */
	public void improveHook(Player player, Hook hook) {
		
		if(player.canBuyUpgrade(COST_UPGRADE_HOOK)) {
			if(hook.noMaxLength()) {	
				player.deductBalance(COST_UPGRADE_HOOK); //VIOLA TDA
				hook.increaseLenght();
			}
		}
	}
	
	/*
	 * INCREMENTA LA POTENCIA DEL MOTOR DESCONTANDOLE AL JUGADOR
	 * EL COSTO DE LA MEJORA
	 */
	public void improveEngine(Player player, Hook hook) {
		if(player.canBuyUpgrade(COST_UPGRADE_ENGINE)) {
			if(hook.getEngine().noMaxPower()) {
				player.deductBalance(COST_UPGRADE_ENGINE); //VIOLA TDA
				hook.getEngine().improvePower();
			}
		}
	}
	
	/*
	 * INCREMENTA EL COMBUSTIBLE DEL MOTOR DESCONTANDOLE AL JUGADOR
	 * EL COSTO DEL MISMO
	 */
	public void buyFuel(Player player, Hook hook) {
		if(player.canBuyUpgrade(FUEL_COST)) {
			if(hook.getEngine().noMaxFuel()) {			
				player.deductBalance(FUEL_COST); //VIOLA TDA
				hook.getEngine().accreditFuel();
			}
		}
	}
}
