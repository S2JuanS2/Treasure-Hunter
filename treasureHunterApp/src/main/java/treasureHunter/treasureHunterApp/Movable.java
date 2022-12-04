package treasureHunter.treasureHunterApp;

public abstract class Movable {
	
	public static final double MOVE_FUEL_COST = 1;
	public static final int INITIAL_POSITION_Y = 90;
		
	private Coordinate position;
	private Engine engine;
	private int loweredMeter = 0;
	private boolean moveRight = true;
	
	public void setState(Coordinate position, Engine engine) {
		this.position = position;
		this.engine = engine;
	}
	
	public int getLoweredMeter() {
		return loweredMeter;
	}

	/*
	 * REINICIA LA DISTANCIA DE METROS RECORRIDOS A CERO
	 */
	public void resetLoweredMeter() {
		this.loweredMeter = 0;
	}
	
	public void addLoweredMeter() {
		loweredMeter++;
	}
	
	/*
	 * DEVUELVE TRUE SI HAY COMBUSTIBLE
	 */
	public boolean thereIsFuel() {
		return(engine.getFuel() > 0);
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
		if(position.getY() == INITIAL_POSITION_Y) {
			engine.resetVelocity();
			resetLoweredMeter();
			return true;
		}
		return false;
	}
	
	/*
	 * MUEVE EL GANCHO DE FORMA HORIZONTAL
	 */
	public void horizontalMovement() {
		
		if(moveRight){
			moveRight(610);
			if(!collisionBorderMap(610)) {
				moveRight = false;
			}
		}else{
			moveLeft();
			if(!collisionBorderMap()) {
				moveRight = true;
			}
		}
	}
	
	/*
	 *  MUEVE TRES POSICION A LA IZQUIERDA EL GANCHO
	 */
	public void moveLeft() {
		if( collisionBorderMap() && thereIsFuel()) {
			position.decreaseX();
			position.decreaseX();
			position.decreaseX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * MUEVE TRES POSICION A LA DERECHA EL GANCHO
	 */
	public void moveRight(int width) {		
		if(collisionBorderMap(width) && thereIsFuel()) {
			position.increaseX();
			position.increaseX();
			position.increaseX();
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * DESCIENDE EL GANCHO
	 */
	public void goDown(int width, int depth) {
		if( !(collisionBorderMap(width, depth)) && thereIsFuel() ){	
			position.increaseY(engine.getPower()+1);
			loweredMeter++;			
			engine.deductFuel(MOVE_FUEL_COST);
		}
	}
	
	/*
	 * ELEVA EL GANCHO HASTA SU POSICION INICIAL
	 */
	public void goUp() {
		for(int i = 0; i < engine.getVelocity()*engine.getPower(); i++) {
			if ( (!initialPosition()) && thereIsFuel() ) {
				position.decreaseY(1);
			}
		}
		engine.deductFuel(MOVE_FUEL_COST);	
	}
}
