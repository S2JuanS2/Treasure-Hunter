package tpAlgo3;

import java.io.Serializable;

public class Hook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final int INITIAL_LENGHT = 80;
	static final int MAX_LENGHT = 280;
	static final int IMPROVE_LENGHT = 10;
	static final int INITIAL_POSITION_X = 150;
	static final int INITIAL_POSITION_Y = 20;
	static final int INITIAL_FUEL = 1000;
	static final int RECHARGE_FUEL = 200;
	static final int MOVE_FUEL_COST = 1;
	static final float FUEL_COST = 80;
	static final float COST_CHAIN = 100;
	
	private Coordinate positionHook;
	private int lenght;
	private double fuel;
	
	public Hook() {
		this.positionHook = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
		this.lenght = INITIAL_LENGHT;
		this.setFuel(INITIAL_FUEL);
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght += lenght;
	}
	
	public double getFuel() {
		return fuel;
	}

	public void setFuel(double fuel) {
		this.fuel += fuel;
	}

	public Coordinate getPositionHook() {
		return positionHook;
	}
	
	@Override
	public String toString() {
		return "Hook [positionHook=" + positionHook + ", lenght=" + lenght + ", fuel=" + fuel + "]";
	}

	public void moveLeft() {
		positionHook.oneLessX();
		setFuel(-1);
	}
	
	public void moveRight() {
		positionHook.oneAddX();
		setFuel(-1);
	}
	
	public void goDown() {
		positionHook.oneAddY();
		setFuel(-1);
	}
	
	public void goUp() {
		positionHook.oneLessY();
		setFuel(-1);
	}
	
	public boolean thereIsFuel() {
		return (fuel > 0);
	}
}
