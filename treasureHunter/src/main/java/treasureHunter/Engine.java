package treasureHunter;

import java.io.Serializable;

public class Engine implements Serializable{
	
	private static final long serialVersionUID = 1L;

	static final int INITIAL_FUEL = 1000;
	static final int INITIAL_POWER = 1;
	static final int RECHARGE_FUEL = 200;
	static final int MAX_POWER = 3;
	
	private double fuel;
	private int power;
	
	public Engine() {
		this.fuel = INITIAL_FUEL;
		this.power = INITIAL_POWER;
	}
	
	public double getFuel() {
		return fuel;
	}
	
	public int getPower() {
		return power;
	}

	public void deductFuel(double fuel) {
		this.fuel -= fuel;
	}

	public void accreditFuel() {
		this.fuel += RECHARGE_FUEL;
	}

	public void improvePower() {
		this.power++;
		
	}
	
	public boolean noMaxPower() {
		if(power < MAX_POWER) {
			return true;
		}else {
			System.out.println("Potencia maxima alcanzada");
			return false;
		}
	}
	
	public boolean enoughPower(int weight) {
		if(power >= weight) {
			return true;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return "Engine [fuel=" + fuel + ", power=" + power + "]";
	}



	
}
