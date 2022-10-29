package tpAlgo3;

import java.io.Serializable;

public class Coordinate implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int x;
	private int y;
	
	// GENERA COORDENADAS RANDOM
	public Coordinate() {
		
		int xRandom = (int)(Math.random()*270);
		int yRandom = (int)(Math.random()*270+30);
		this.x = xRandom;
		this.y = yRandom;
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
	
	void oneAddX() {
		x++;
	}
	
	void oneAddY() {
		y++;
	}
	
	void oneLessX() {
		x--;
	}
	
	void oneLessY() {
		y--;
	}

	boolean equals(int x, int y) {
		return(this.x == x && this.y == y);
	}
	
	@Override
	public String toString() {
		return "[x=" + x + ", y=" + y + "]";
	}

	
}
