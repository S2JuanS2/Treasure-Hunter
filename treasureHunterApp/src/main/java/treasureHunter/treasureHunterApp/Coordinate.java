package treasureHunter.treasureHunterApp;

import java.io.Serializable;

public class Coordinate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	
	public Coordinate() {
		
		this.x = (int)(Math.random()*570+20);
		this.y = (int)(Math.random()*260+180);
	}
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void increaseX() {
		x++;
	}
	
	public void increaseY(int velocity) {
		y+= velocity;
	}
	
	public void decreaseX() {
		x--;
	}
	
	public void decreaseY(int velocity) {
		y-= velocity;
	}

	/*
	 * DEVUELVE TRUE SI LAS POSICIONES SON IGUALES
	 */
	public boolean equals(Coordinate coords) { 
		return(Math.abs(this.x - coords.getX()) <= 15 && Math.abs(this.y - coords.getY()) <= 5);
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

	
}
