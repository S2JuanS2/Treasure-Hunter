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
	
	/*
	 * DEVUELVE TRUE SI COLISIONÓ CON EL BORDE DEL MAPA
	 */
	public boolean collisionBorderMap() {
		return ( (position.getX()-1) > 0 );
	}
	
	/*
	 * DEVUELVE TRUE SI COLISIONÓ CON EL BORDE DEL MAPA
	 */
	public boolean collisionBorderMap(int width) {
		return (position.getX() < width);
	}
	
	public boolean initialPosition() {
		return(position.getY() == INITIAL_POSITION_Y);
	}
	
	/*
	 *  MUEVE UNA POSICION A LA IZQUIERDA EL GANCHO
	 */
	public void moveLeft() {
		if( collisionBorderMap() && thereIsFuel()) {
			position.decreaseX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * MUEVE UNA POSICION A LA DERECHA EL GANCHO
	 */
	public void moveRight(int width) {
		if(collisionBorderMap(width) && thereIsFuel()) {
			position.increaseX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * DESCIENDE EL GANCHO
	 */
	public void goDown(int width, int depth) {
		if( !(collisionBorderMap(width, depth)) && thereIsFuel() ){		
			position.increaseY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * VUELVE AL GANCHO A SU POSICION ORIGINAL
	 */
	public void goUp() {
		while ( (!initialPosition()) && thereIsFuel() ) {
			position.decreaseY();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
}
