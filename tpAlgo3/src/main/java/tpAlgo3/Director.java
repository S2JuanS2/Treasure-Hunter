package tpAlgo3;

public class Director {
	
	static final int COMMON = 1;
	static final int LITTLE_COMMON = 2;
	static final int RARE = 3;
	static final int EPIC = 4;
	static final int LEGENDARY = 5;
	static final int MYTHICAL = 6;

	public void constructGraniteTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.GRANITE);
		builder.setPosition(new Coordinate());
		builder.setRarity(COMMON);
		builder.setWeight();
		builder.setPrice();
	}

	public void constructBasaltTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.BASALT);
		builder.setPosition(new Coordinate());
		builder.setRarity(COMMON);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructQuartziteTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.QUARTZITE);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();
	}
	public void constructIronTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.IRON);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructCopperTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.COPPER);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();	
	}

	public void constructMarbleTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.MARBLE);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();
	}
	
	public void constructBoneTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.BONE);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructAmberTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.AMBER);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructRubyTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.RUBY);
		builder.setPosition(new Coordinate());
		builder.setRarity(EPIC);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructGoldTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.GOLD);
		builder.setPosition(new Coordinate());
		builder.setRarity(LEGENDARY);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructDiamondTreasure(TreasureBuilder builder) {
		builder.setTreasureType(TreasureType.DIAMOND);
		builder.setPosition(new Coordinate());
		builder.setRarity(MYTHICAL);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructRandomTreasure(TreasureBuilder builder) {
		
		int treasureRandom = (int)(Math.random()*11);
		
		
		switch(treasureRandom) {
		case 0:
			this.constructGraniteTreasure(builder);
			break;
		case 1:
			this.constructBasaltTreasure(builder);
			break;
		case 2:
			this.constructQuartziteTreasure(builder);
			break;
		case 3:
			this.constructIronTreasure(builder);
			break;
		case 4:
			this.constructCopperTreasure(builder);
			break;
		case 5:
			this.constructMarbleTreasure(builder);
			break;
		case 6:
			this.constructBoneTreasure(builder);
			break;
		case 7:
			this.constructAmberTreasure(builder);
			break;
		case 8:
			this.constructRubyTreasure(builder);
			break;
		case 9:
			this.constructGoldTreasure(builder);
			break;
		case 10:
			this.constructDiamondTreasure(builder);
			break;
		}
	}
}
