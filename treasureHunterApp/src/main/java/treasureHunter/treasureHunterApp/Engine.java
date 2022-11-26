package treasureHunter.treasureHunterApp;

import java.io.Serializable;

public class Engine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int INITIAL_FUEL = 1000;
	public static final int INITIAL_POWER = 1;
	
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
	public void accreditFuel(int rechargeFuel) {
		this.fuel += rechargeFuel;
	}

	/*
	 * AUMENTA LA POTENCIA DEL MOTOR
	 */
	public void improvePower() {
		this.power++;
		
	}
	
	/*
	 * DEVUELVE TRUE SI LA POTENCIA DEL MOTOR ES SUFICIENTE PARA LEVANTAR EL PESO
	 */
	public boolean enoughPower(int weight) {
		return(power >= weight);
	}
	
	@Override
	public String toString() {
		return "Engine [fuel=" + fuel + ", power=" + power + "]";
	}




	
}
