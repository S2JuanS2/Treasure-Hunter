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
	public void constructTreasure(Builder builder, TreasureType type, int rarity) {
		
		builder.setTreasureType(type);
		builder.setPosition(new Coordinate());
		builder.setRarity(rarity);
		builder.setWeight();
		builder.setPrice();	
	}
	/*
	 * CONSTRUYE UN TESORO ALEATORIO
	 */
	public void constructRandomTreasure(Builder builder) {
		
		int randomProbability = (int)(Math.random()*100+1);
		
		if(randomProbability <= 17) {
			constructTreasure(builder,TreasureType.GRANITE,COMMON);
		}else if(randomProbability <= 34) {
			constructTreasure(builder,TreasureType.BASALT,COMMON);
		}else if(randomProbability <= 44){
			constructTreasure(builder,TreasureType.QUARTZITE,LITTLE_COMMON);
		}else if(randomProbability <= 54){
			constructTreasure(builder,TreasureType.IRON,LITTLE_COMMON);
		}else if(randomProbability <= 64){
			constructTreasure(builder,TreasureType.COPPER,LITTLE_COMMON);
		}else if(randomProbability <= 72){
			constructTreasure(builder,TreasureType.MARBLE,RARE);
		}else if(randomProbability <= 80){
			constructTreasure(builder,TreasureType.BONE,RARE);
		}else if(randomProbability <= 88){
			constructTreasure(builder,TreasureType.AMBER,RARE);
		}else if(randomProbability <= 94){
			constructTreasure(builder,TreasureType.RUBY,EPIC);
		}else if(randomProbability <= 98){
			constructTreasure(builder,TreasureType.GOLD,LEGENDARY);		
		}else if(randomProbability <= 100){
			constructTreasure(builder,TreasureType.DIAMOND,MYTHICAL);
		}
	}
}
