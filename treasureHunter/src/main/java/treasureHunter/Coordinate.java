package treasureHunter;

import java.io.Serializable;

public class Coordinate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	
	public Coordinate() {
		
		this.x = (int)(Math.random()*300);
		this.y = (int)(Math.random()*470+30);
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
	
	public void oneAddX() {
		x++;
	}
	
	public void oneAddY() {
		y++;
	}
	
	public void oneLessX() {
		x--;
	}
	
	public void oneLessY() {
		y--;
	}

	/*
	 * DEVUELVE TRUE SI LAS POSICIONES SON IGUALES
	 */
	public boolean equals(Coordinate coords) {
		return(this.x == coords.getX() && this.y == coords.getY());
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

	
}
