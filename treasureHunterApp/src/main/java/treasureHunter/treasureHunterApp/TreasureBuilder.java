package treasureHunter.treasureHunterApp;

public class TreasureBuilder implements Builder{
	
	private TreasureType type;
	private Coordinate position;
	private int rarity;
	private int weight;
	private int price;
	private String nameImage;
	
	@Override
	public void setTreasureType(TreasureType type) {
		this.type = type;
	}
	
	@Override
	public void setPosition(Coordinate position) {
		this.position = position;
	}
	
	@Override
	public void setRarity(int rarity) {
		this.rarity = rarity;
	}
	
	@Override
	public void setWeight() {
		
		int weightRandom = (int)(Math.random()*3+1);
		this.weight = weightRandom;
		
	}
	
	@Override
	public void setPrice() {
		
		int calculatePrice = (this.rarity * this.weight *100)/2;
		this.price = calculatePrice;
	}
	
	@Override
	public void setNameImage(String nameImage) {
		this.nameImage = nameImage;
	}
	
	public Treasure getResults() {
		
		return new Treasure(type, position, weight, rarity, price, nameImage);
	}

}