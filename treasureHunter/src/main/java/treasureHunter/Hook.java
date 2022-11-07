package treasureHunter;

import java.io.Serializable;

public class Hook extends Movable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final int INITIAL_LENGHT = 80;
	public static final int MAX_LENGHT = 280;
	public static final int IMPROVE_LENGHT = 10;
	public static final int INITIAL_POSITION_X = 150;
	public static final int INITIAL_POSITION_Y = 20;
	
	private Coordinate position;
	private Engine engine;
	private int lenght;
	
	public Hook() {
		this.position = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
		this.engine = new Engine();
		this.lenght = INITIAL_LENGHT;
		
		setState(position, engine);
	}

	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	/*
	 * DEVUELVE LA LONGITUD DEL GANCHO
	 */
	public int getLenght() {
		return lenght;
	}
	
	/*
	 * DEVUELVE EL MOTOR
	 */
	public Engine getEngine() {
		return engine;
	}

	/*
	 * DEVUELVE LA POSICION DEL GANCHO
	 */
	public Coordinate getPosition() {
		return position;
	}
	
	/*
	 * AUMENTA EL LARGO DEL GANCHO DE FORMA CONSTANTE
	 */
	public void increaseLenght() {
		this.lenght += IMPROVE_LENGHT;
	}
	
	/*
	 * DEVUELVE TRUE SI EL LARGO DEL GANCHO NO SUPERA LA LONGITUD MAXIMA
	 */
	public boolean noMaxLength() {
		if(getLenght() < MAX_LENGHT) {
			return true;
		}else {
			System.out.println("Longitud maxima alcanzada.\n");
			return false;
		}
	}
	
	/*
	 * RECIBE LA CANTIDAD DE METROS RECORRIDOS
	 * DEVUELVE TRUE SI NO FUE SUPERADO POR LA LONGITUD DEL GANCHO
	 */
	public boolean canKeepGoingDown(int loweredMeter) {
		
		return (loweredMeter <= getLenght());
	}

	@Override
	public String toString() {
		return "Hook [position=" + position + ", engine=" + engine + ", lenght=" + lenght + "]";
	}
	

}


