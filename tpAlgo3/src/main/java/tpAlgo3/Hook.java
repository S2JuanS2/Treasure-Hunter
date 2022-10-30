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
	
	private Coordinate position;
	private int lenght;
	private double fuel;
	
	public Hook() {
		this.position = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
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
		return position;
	}
	
	@Override
	public String toString() {
		return "  Hook [positionHook=" + position + ", lenght=" + lenght + ", fuel=" + fuel + "]";
	}

	public void moveLeft() {
		position.oneLessX();
	}
	
	public void moveRight() {
		position.oneAddX();
	}
	
	public void goDown() {
		position.oneAddY();
		setFuel(-1);
	}
	
	public void goUp() {
		while (getPositionHook().getY() > INITIAL_POSITION_Y && thereIsFuel()) {
			position.oneLessY();
			setFuel(-1);
		}
	}
	
	public boolean thereIsFuel() {
		return (fuel > 0);
	}
	
	public boolean noMaxLength() {
		return(getLenght() < MAX_LENGHT);
	}
	
	public boolean collisionBorderMap(Map map){
		
		return(getPositionHook().getX() <= 0 || getPositionHook().getX() >= map.getWidth() || getPositionHook().getY() >= map.getDepth());
	}
}


