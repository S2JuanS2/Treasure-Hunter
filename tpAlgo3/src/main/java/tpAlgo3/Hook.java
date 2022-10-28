package tpAlgo3;

import java.io.Serializable;

public class Hook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final int INITIAL_LENGHT = 80;
	static final int POSITION_X = 150;
	static final int POSITION_Y = 20;
	static final int INITIAL_FUEL = 1000;
	
	private Coordinate positionHook;
	private int lenght;
	private double fuel;
	
	public Hook() {
		this.positionHook = new Coordinate(POSITION_X,POSITION_Y);
		this.lenght = INITIAL_LENGHT;
		this.setFuel(INITIAL_FUEL);
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght += lenght;
	}

	public Coordinate getPositionHook() {
		return positionHook;
	}
	
	@Override
	public String toString() {
		return "Hook [positionHook=" + positionHook + ", lenght=" + lenght + ", fuel=" + fuel + "]";
	}

	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel += fuel;
	}
	
}
