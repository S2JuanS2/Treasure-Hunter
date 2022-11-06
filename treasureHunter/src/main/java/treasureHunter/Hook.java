package treasureHunter;

import java.io.Serializable;

public class Hook extends Movable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final int INITIAL_LENGHT = 80;
	static final int MAX_LENGHT = 280;
	static final int IMPROVE_LENGHT = 10;
	static final int INITIAL_POSITION_X = 150;
	static final int INITIAL_POSITION_Y = 20;
	static final int INITIAL_FUEL = 1000;
	static final int RECHARGE_FUEL = 200;
	static final int MOVE_FUEL_COST = 1;
	
	public static final String NO_FUEL = "Combustible insuficiente\n";
	
	private Coordinate position;
	private int lenght;
	private double fuel;
	
	public Hook() {
		this.position = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
		this.lenght = INITIAL_LENGHT;
		this.fuel = INITIAL_FUEL;
	}

	public int getLenght() {
		return lenght;
	}
	
	public double getFuel() {
		return fuel;
	}
	
	public void deductFuel(double fuel) {
		this.fuel -= fuel;
	}

	public void accreditFuel(double fuel) {
		this.fuel += fuel;
	}

	public Coordinate getPosition() {
		return position;
	}
	
	public void increaseLenght() {
		this.lenght += IMPROVE_LENGHT;
	}
	
	@Override
	public String toString() {
		return "Hook [positionHook=" + position + ", lenght=" + lenght + ", fuel=" + fuel + "]";
	}

	public void moveLeft() {
		if(position.getX()-1 > 0 && thereIsFuel()) {
			position.oneLessX();
			deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void moveRight(int width) {
		if(position.getX() < width && thereIsFuel()) {
			position.oneAddX();
			deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void goDown(int width, int depth) {
		if( !(collisionBorderMap(width, depth)) && thereIsFuel() ){		
			position.oneAddY();
			deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void goUp() {
		while (getPosition().getY() > INITIAL_POSITION_Y && thereIsFuel()) {
			position.oneLessY();
			deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public boolean thereIsFuel() {
		if(fuel > 0) {
			return true;
		}else{
			System.out.println(NO_FUEL);
			return false;
		}
	}
	
	public boolean noMaxLength() {
		if(getLenght() < MAX_LENGHT) {
			return true;
		}else {
			System.out.println("Longitud maxima alcanzada.\n");
			return false;
		}
	}
	
	public boolean collisionBorderMap(int width, int depth){
		
		return(getPosition().getX() <= 0 || getPosition().getX() >= width || getPosition().getY() >= depth);
	}
	
	public boolean canKeepGoingDown(int loweredMeter) {
		
		return (loweredMeter <= getLenght());
	}
	
}


