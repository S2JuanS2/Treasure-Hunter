package tpAlgo3;

import java.io.Serializable;

public class Hook implements Serializable{

	private static final long serialVersionUID = 1L;
	
	static final int INITIAL_LENGHT = 80;
	static final int POSITION_X = 150;
	static final int POSITION_Y = 20;
	
	private Coordinate positionHook;
	private int lenght;
	private int vx;
	private int vy;
	

	public Hook() {
		this.positionHook = new Coordinate(POSITION_X,POSITION_Y);
		this.lenght = INITIAL_LENGHT;
		this.setVx(0);
		this.setVy(0);
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght += lenght;
	}

	public Coordinate getPositionHook() {
		return positionHook;
	}
	
	@Override
	public String toString() {
		return "Hook [positionHook=" + positionHook + ", lenght=" + lenght + ", vx=" + vx + ", vy=" + vy + "]";
	}

	public int getVx() {
		return vx;
	}

	public void setVx(int vx) {
		this.vx = vx;
	}

	public int getVy() {
		return vy;
	}

	public void setVy(int vy) {
		this.vy = vy;
	}
	
}
