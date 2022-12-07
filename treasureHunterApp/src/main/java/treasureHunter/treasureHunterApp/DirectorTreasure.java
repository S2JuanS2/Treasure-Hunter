package treasureHunter.treasureHunterApp;

public class DirectorTreasure {
	
	public static final int COMMON = 1;
	public static final int LITTLE_COMMON = 4;
	public static final int RARE = 6;
	public static final int EPIC = 8;
	public static final int LEGENDARY = 9;
	public static final int MYTHICAL = 10;

	/*
	 * PASOS DE CONSTRUCCION DE UN TESORO
	 */		
	public void constructTreasure(Builder builder, TreasureType type, int rarity, String nameImage) {
		
		builder.setTreasureType(type);
		builder.setPosition(new Coordinate());
		builder.setRarity(rarity);
		builder.setWeight();
		builder.setPrice();	
		builder.setNameImage(nameImage);
	}
	/*
	 * CONSTRUYE UN TESORO ALEATORIO
	 */
	public void constructRandomTreasure(Builder builder) {
		
		int randomProbability = (int)(Math.random()*100+1);
		
		if(randomProbability <= 17) {
			constructTreasure(builder,TreasureType.GRANITE,COMMON, "piedra");
		}else if(randomProbability <= 34) {
			constructTreasure(builder,TreasureType.PLANT,COMMON, "planta");
		}else if(randomProbability <= 44){
			constructTreasure(builder,TreasureType.CALAVERA,LITTLE_COMMON, "calavera");
		}else if(randomProbability <= 54){
			constructTreasure(builder,TreasureType.IRON,LITTLE_COMMON, "iron");
		}else if(randomProbability <= 64){
			constructTreasure(builder,TreasureType.CHEST,LITTLE_COMMON, "cofre");
		}else if(randomProbability <= 72){
			constructTreasure(builder,TreasureType.SACO,RARE, "saco");
		}else if(randomProbability <= 80){
			constructTreasure(builder,TreasureType.CALDERO,RARE, "caldero");
		}else if(randomProbability <= 88){
			constructTreasure(builder,TreasureType.MONEDA,RARE, "moneda");
		}else if(randomProbability <= 94){
			constructTreasure(builder,TreasureType.RUBY,EPIC, "ruby");
		}else if(randomProbability <= 98){
			constructTreasure(builder,TreasureType.GOLD,LEGENDARY, "oro");		
		}else if(randomProbability <= 100){
			constructTreasure(builder,TreasureType.DIAMOND,MYTHICAL, "diamond");
		}
	}
}
