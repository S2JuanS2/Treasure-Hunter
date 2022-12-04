package treasureHunter.treasureHunterApp;

import java.io.Serializable;

public class Engine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public static final int INITIAL_FUEL = 1500;
	public static final int INITIAL_POWER = 1;
	public static final int INITIAL_VELOCITY = 2;
	
	//attributes
	private float fuel;
	private int power;
	private int velocity;

	public Engine() {
		this.fuel = INITIAL_FUEL;
		this.power = INITIAL_POWER;
		this.velocity = INITIAL_VELOCITY;
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
	 * DEVUELVE LA VELOCIDAD DEL MOTOR
	 */
	public int getVelocity() {
		return velocity;
	}

	/*
	 * RESETEA LA VELOCIDAD DEL MOTOR A SU ESTADO INICIAL
	 */
	public void resetVelocity() {
		this.velocity = INITIAL_VELOCITY;
	}
	
	/*
	 * REDUCE EL COMBUSTIBLE CON LA CANTIDAD RECIBIDA POR PARAMETRO
	 */
	public void deductFuel(double fuel) {
		this.fuel -= fuel;
	}

	/*
	 * AUMENTA EL COMBUSTIBLE CON LA CANTIDAD RECIBIDA POR PARAMETRO
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
	 * REDUCE LA VELOCIDAD DEL MOTOR EN CASO DE QUE EL PESO RECIBIDO POR PARAMETRO
	 * SEA MAYOR A LA POTENCIA DEL MOTOR
	 */
	public void enoughPower(int weight) {	
		if(weight > this.power) {
			this.velocity--;			
		}
	}	
}
