package tpAlgo3;

public class Treasure {

	private final TreasureType type;
	private final Coordinate position;
	private final int rarity;
	private final int weight;
	private final float price;
	
	public Treasure(TreasureType type, Coordinate position, int weight, int rarity, float price) {
		super();
		this.type = type;
		this.position = position;
		this.rarity = rarity;
		this.weight = weight;
		this.price = price;
	}

	public TreasureType getType() {
		return type;
	}

	public Coordinate getPosition() {
		return position;
	}

	public int getRarity() {
		return rarity;
	}
	
	public int getWeight() {
		return weight;
	}

	public float getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Treasure [type=" + type + ", position=" + position + ", rarity=" + rarity + ", weight=" + weight
				+ ", price=" + price + "]";
	}

	public void setPosition(int i, int j) {
		position.setX(i);
		position.setY(j);
		
	}
	
	
	



}
