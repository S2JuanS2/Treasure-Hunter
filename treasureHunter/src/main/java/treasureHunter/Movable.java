package treasureHunter;

public abstract class Movable {
	
	public static final int MOVE_FUEL_COST = 1;
	public static final int INITIAL_POSITION_Y = 20;
	
	public static final String NO_FUEL = "Combustible insuficiente\n";
	
	private Coordinate position;
	private Engine engine;
	
	public void setState(Coordinate position, Engine engine) {
		this.position = position;
		this.engine = engine;
	}
	
	/*
	 *  MUEVE UNA POSICION A LA IZQUIERDA EL GANCHO
	 */
	public void moveLeft() {
		if(position.getX()-1 > 0 && thereIsFuel()) {
			position.oneLessX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * MUEVE UNA POSICION A LA DERECHA EL GANCHO
	 */
	public void moveRight(int width) {
		if(position.getX() < width && thereIsFuel()) {
			position.oneAddX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * DESCIENDE EL GANCHO
	 */
	public void goDown(int width, int depth) {
		if( !(collisionBorderMap(width, depth)) && thereIsFuel() ){		
			position.oneAddY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * VUELVE AL GANCHO A SU POSICION ORIGINAL
	 */
	public void goUp() {
		while (position.getY() > INITIAL_POSITION_Y && thereIsFuel()) {
			position.oneLessY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * DEVUELVE TRUE SI HAY COMBUSTIBLE
	 */
	public boolean thereIsFuel() {
		if(engine.getFuel() > 0) {
			return true;
		}else{
			System.out.println(NO_FUEL);
			return false;
		}
	}
	
	/*
	 * DEVUELVE TRUE SI COLISIONÓ CON EL BORDE DEL MAPA
	 */
	public boolean collisionBorderMap(int width, int depth){
		
		return(position.getX() <= 0 || position.getX() >= width || position.getY() >= depth);
	}
}
