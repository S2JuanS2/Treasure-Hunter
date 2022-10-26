package tpAlgo3;

public class Coordinate {
	
	private int x;
	private int y;
	
	// GENERA COORDENADAS RANDOM
	public Coordinate() {
		
		int xRandom = (int)(Math.random()*300);
		int yRandom = (int)(Math.random()*300+30);
		this.x = xRandom;
		this.y = yRandom;
	}
	
	public Coordinate(int i, int j) {
		x = i;
		y = j;
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
