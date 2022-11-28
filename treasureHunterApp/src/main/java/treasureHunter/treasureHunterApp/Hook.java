package treasureHunter.treasureHunterApp;

import java.io.Serializable;

import javafx.scene.canvas.GraphicsContext;

public class Hook extends Movable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final int INITIAL_LENGTH = 200;
	public static final int INITIAL_POSITION_X = 320;
	public static final int INITIAL_POSITION_Y = 75;
	
	private Coordinate position;
	private Engine engine;
	private int length;
	private String nameImage;
	
	public Hook() {
		this.position = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
		this.engine = new Engine();
		this.length = INITIAL_LENGTH;
		this.nameImage = "hook";
		
		setState(position, engine);
	}
	
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
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
	public int getLength() {
		return length;
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
	 * AUMENTA LA LONGITUD DEL GANCHO DE FORMA CONSTANTE
	 */
	public void increaseLength(int improveLength) {
		this.length += improveLength;
	}
	
	/*
	 * RECIBE LA CANTIDAD DE METROS RECORRIDOS
	 * DEVUELVE TRUE SI NO FUE SUPERADO POR LA LONGITUD DEL GANCHO
	 */
	public boolean canKeepGoingDown() {
		
		return (getLoweredMeter()*(engine.getPower()+1) < getLength());
	}

	@Override
	public String toString() {
		return "Hook [position=" + position + ", engine=" + engine + ", length=" + length + "]";
	}
	
	public void draw(GraphicsContext graphics) {
		graphics.drawImage(View.images.get(nameImage), getPosition().getX(), getPosition().getY());
	}

}


