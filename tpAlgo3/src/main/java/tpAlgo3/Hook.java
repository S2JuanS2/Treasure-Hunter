package tpAlgo3;

public class Hook {

	private Coordinate positionHook;
	private int lenght;

	public Hook() {
		this.positionHook = new Coordinate();
		this.positionHook.setX(0);
		this.positionHook.setY(0);
		this.setLenght(5);
	}

	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}

	public Coordinate getPositionHook() {
		return positionHook;
	}
	
	@Override
	public String toString() {
		return "Hook [positionHook=" + positionHook + ", lenght=" + lenght + "]";
	}
	
}
