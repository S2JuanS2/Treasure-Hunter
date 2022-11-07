package treasureHunter;

import java.io.Serializable;

public class Hook extends Movable implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final int INITIAL_LENGHT = 80;
	static final int MAX_LENGHT = 280;
	static final int IMPROVE_LENGHT = 10;
	static final int INITIAL_POSITION_X = 150;
	static final int INITIAL_POSITION_Y = 20;
	
	private Coordinate position;
	private Engine engine;
	private int lenght;
	
	public Hook() {
		this.position = new Coordinate(INITIAL_POSITION_X,INITIAL_POSITION_Y);
		this.lenght = INITIAL_LENGHT;
		this.engine = new Engine();
		
		setPosition(position);
		setEngine(engine);

	}

	public int getLenght() {
		return lenght;
	}
	
	public Engine getEngine() {
		return engine;
	}

	public Coordinate getPosition() {
		return position;
	}
	
	public void increaseLenght() {
		this.lenght += IMPROVE_LENGHT;
	}
	

	public boolean noMaxLength() {
		if(getLenght() < MAX_LENGHT) {
			return true;
		}else {
			System.out.println("Longitud maxima alcanzada.\n");
			return false;
		}
	}
	
	public boolean canKeepGoingDown(int loweredMeter) {
		
		return (loweredMeter <= getLenght());
	}

	@Override
	public String toString() {
		return "Hook [position=" + position + ", engine=" + engine + ", lenght=" + lenght + "]";
	}
	

}


