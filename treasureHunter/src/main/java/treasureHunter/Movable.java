package treasureHunter;

public abstract class Movable {
	
	static final int MOVE_FUEL_COST = 1;
	static final int INITIAL_POSITION_Y = 20;
	
	public static final String NO_FUEL = "Combustible insuficiente\n";
	
	private Coordinate position;
	private Engine engine;
	
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	
	public void moveLeft() {
		if(position.getX()-1 > 0 && thereIsFuel()) {
			position.oneLessX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void moveRight(int width) {
		if(position.getX() < width && thereIsFuel()) {
			position.oneAddX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void goDown(int width, int depth) {
		if( !(collisionBorderMap(width, depth)) && thereIsFuel() ){		
			position.oneAddY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public void goUp() {
		while (position.getY() > INITIAL_POSITION_Y && thereIsFuel()) {
			position.oneLessY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	public boolean thereIsFuel() {
		if(engine.getFuel() > 0) {
			return true;
		}else{
			System.out.println(NO_FUEL);
			return false;
		}
	}
	
	public boolean collisionBorderMap(int width, int depth){
		
		return(position.getX() <= 0 || position.getX() >= width || position.getY() >= depth);
	}
}
