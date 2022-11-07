package treasureHunter;

public class DirectorTreasure {
	
	public static final int COMMON = 1;
	public static final int LITTLE_COMMON = 2;
	public static final int RARE = 3;
	public static final int EPIC = 4;
	public static final int LEGENDARY = 5;
	public static final int MYTHICAL = 6;

	/*
	 * PASOS DE CONSTRUCCION DE UN TESORO
	 */
	public void constructGraniteTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.GRANITE);
		builder.setPosition(new Coordinate());
		builder.setRarity(COMMON);
		builder.setWeight();
		builder.setPrice();
	}

	public void constructBasaltTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.BASALT);
		builder.setPosition(new Coordinate());
		builder.setRarity(COMMON);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructQuartziteTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.QUARTZITE);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();
	}
	public void constructIronTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.IRON);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructCopperTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.COPPER);
		builder.setPosition(new Coordinate());
		builder.setRarity(LITTLE_COMMON);
		builder.setWeight();
		builder.setPrice();	
	}

	public void constructMarbleTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.MARBLE);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();
	}
	
	public void constructBoneTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.BONE);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructAmberTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.AMBER);
		builder.setPosition(new Coordinate());
		builder.setRarity(RARE);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructRubyTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.RUBY);
		builder.setPosition(new Coordinate());
		builder.setRarity(EPIC);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructGoldTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.GOLD);
		builder.setPosition(new Coordinate());
		builder.setRarity(LEGENDARY);
		builder.setWeight();
		builder.setPrice();	
	}
	
	public void constructDiamondTreasure(Builder builder) {
		builder.setTreasureType(TreasureType.DIAMOND);
		builder.setPosition(new Coordinate());
		builder.setRarity(MYTHICAL);
		builder.setWeight();
		builder.setPrice();	
	}
	
	/*
	 * DEVUELVE UN NUMERO ALEATORIO CON UN RANGO DE POSIBILIDADES
	 */
	public int randomOdds() {
		
		int randomProbability = (int)(Math.random()*100+1);
		int treasureRandom  = 0;
		
		if(randomProbability <= 17) {
			treasureRandom = 0;
		}else if(randomProbability <= 34) {
			treasureRandom = 1;
		}else if(randomProbability <= 44){
			treasureRandom = 2;
		}else if(randomProbability <= 54){
			treasureRandom = 3;
		}else if(randomProbability <= 64){
			treasureRandom = 4;
		}else if(randomProbability <= 72){
			treasureRandom = 5;
		}else if(randomProbability <= 80){
			treasureRandom = 6;
		}else if(randomProbability <= 88){
			treasureRandom = 7;
		}else if(randomProbability <= 94){
			treasureRandom = 8;
		}else if(randomProbability <= 98){
			treasureRandom = 9;
		}else if(randomProbability <= 100){
			treasureRandom = 10;
		}
		return treasureRandom;
	}
	
	/*
	 * CONSTRUYE UN TESORO ALEATORIO
	 */
	public void constructRandomTreasure(Builder builder) {
		
		int treasureRandom = randomOdds();
		
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
