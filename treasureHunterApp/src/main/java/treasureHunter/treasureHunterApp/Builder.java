package treasureHunter.treasureHunterApp;

public interface Builder {
	
	void setTreasureType(TreasureType type);
	void setPosition(Coordinate position);
	void setRarity(int rarity);
	void setWeight();
	void setPrice();
	void setNameImage(String nameImage);
	
}
