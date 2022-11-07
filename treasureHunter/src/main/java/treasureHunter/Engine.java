package treasureHunter;

import java.io.Serializable;

public class Engine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int INITIAL_FUEL = 1000;
	public static final int INITIAL_POWER = 1;
	public static final int RECHARGE_FUEL = 200;
	public static final int MAX_POWER = 3;
	public static final int MAX_FUEL = 2000;
	
	//attributes
	private float fuel;
	private int power;
	
	public Engine() {
		this.fuel = INITIAL_FUEL;
		this.power = INITIAL_POWER;
	}
	
	/*
	 * DEVUELVE EL COMBUSTIBLE
	 */
	public float getFuel() {
		return fuel;
	}
	
	/*
	 * DEVUELVE LA POTENCIA DEL MOTOR
	 */
	public int getPower() {
		return power;
	}

	/*
	 * REDUCE EL COMBUSTIBLE CON LA CANTIDAD RECIBIDA POR PARAMETRO
	 */
	public void deductFuel(double fuel) {
		this.fuel -= fuel;
	}

	/*
	 * AUMENTA EL COMBUSTIBLE
	 */
	public void accreditFuel() {
		this.fuel += RECHARGE_FUEL;
	}

	/*
	 * AUMENTA LA POTENCIA DEL MOTOR
	 */
	public void improvePower() {
		this.power++;
		
	}
	
	/*
	 * DEVUELVE TRUE SI LA EL MOTOR ALCANZO LA POTENCIA MAXIMA
	 */
	public boolean noMaxPower() {
		if(power < MAX_POWER) {
			return true;
		}else {
			System.out.println("Potencia maxima alcanzada");
			return false;
		}
	}
	
	/*
	 * DEVUELVE TRUE SI LA POTENCIA DEL MOTOR ES SUFICIENTE PARA LEVANTAR EL PESO
	 */
	public boolean enoughPower(int weight) {
		if(power >= weight) {
			return true;
		}
		return false;
	}
	
	/*
	 * DEVUELVE TRUE SI LA CAPACIDAD DEL TANQUE ES SUFICIENTE PARA RECARGAR COMBUSTIBLE
	 */
	public boolean noMaxFuel() {
		if(fuel < (MAX_FUEL-RECHARGE_FUEL)) {
			return true;
		}else {
			System.out.println("Tanque lleno");
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Engine [fuel=" + fuel + ", power=" + power + "]";
	}




	
}
