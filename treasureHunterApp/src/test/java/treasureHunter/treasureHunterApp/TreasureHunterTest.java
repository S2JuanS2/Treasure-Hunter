package treasureHunter.treasureHunterApp;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreasureHunterTest {
	
	public static final int TREASURE_COST = 200;
	public static final int TREASURE_POSITION_Y = 120;
	
	private TreasureHunterGame treasureGame = new TreasureHunterGame();

	@Test
	public void testInitialPlayer() {
		
		assertEquals(null,treasureGame.getPlayer().getName());
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		
	}
	
	@Test
	public void testInitialHook() {

		assertEquals(200, treasureGame.getHook().getLength());
		assertEquals((TreasureHunterGame.MAP_WIDTH)/2, treasureGame.getHook().getPosition().getX());
		assertEquals(90, treasureGame.getHook().getPosition().getY());
		assertEquals(1500,0, treasureGame.getHook().getEngine().getFuel());
	}
	
	@Test
	public void testInitialEngine() {
		assertEquals(1000,0, treasureGame.getHook().getEngine().getFuel());
		assertEquals(1, treasureGame.getHook().getEngine().getPower());
	}
	
	@Test
	public void addTreasureToListTest() {

		Treasure treasureGranite = new Treasure(TreasureType.GRANITE, new Coordinate(Hook.INITIAL_POSITION_X,40),
											1, DirectorTreasure.COMMON, TREASURE_COST, "oro.png");
		treasureGame.addTreasure(treasureGranite);
		
		assertFalse(treasureGame.getTreasure().isEmpty());
		
	}
	
	@Test
	public void whenTreasureCollisionwithHookThenStop() {
		
		Treasure treasure = new Treasure(TreasureType.GOLD, new Coordinate(Hook.INITIAL_POSITION_X,TREASURE_POSITION_Y),
											1, DirectorTreasure.COMMON, TREASURE_COST, "oro.png");
		treasureGame.addTreasure(treasure);
		
		while(!treasureGame.collisionTreasure()) {
			treasureGame.goDownHook();	
		}
		assertEquals(TREASURE_POSITION_Y-10, treasureGame.getHook().getPosition().getY());	
	}
	
	@Test
	public void whenItDoesNotReachTheLengthOfTheHookThenBrakesBefore() {
		
		Treasure treasure = new Treasure(TreasureType.GOLD, new Coordinate(Hook.INITIAL_POSITION_X,101), 
											1, DirectorTreasure.COMMON, TREASURE_COST, "oro.png");
		treasureGame.addTreasure(treasure);
		
		while(treasureGame.getHook().canKeepGoingDown()) {
			treasureGame.goDownHook();	
		}
		assertEquals(Hook.INITIAL_POSITION_Y+Hook.INITIAL_LENGTH, treasureGame.getHook().getPosition().getY());	
	}
		
	@Test
	public void whenItCollisionWithTheMapBoundaryShouldBrake() {
		
		for(int i = 200; i < Store.MAX_LENGTH; i += 10) {
			treasureGame.getHook().increaseLength(Store.IMPROVE_LENGTH);			
		}
		for(int i = 0; i < Store.MAX_LENGTH+1; i++) {
			treasureGame.goDownHook();
		}
		assertEquals(Hook.INITIAL_POSITION_Y+Store.MAX_LENGTH, treasureGame.getHook().getPosition().getY());		
	}
	
	@Test
	public void whenItReachesOriginalPositionShouldBrake() {
	
		treasureGame.goDownHook();
		treasureGame.getHook().goUp();
		assertEquals(Hook.INITIAL_POSITION_Y, treasureGame.getHook().getPosition().getY());
	}
	
	@Test
	public void whenCollectTreasureShouldPay() {
		
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(Hook.INITIAL_POSITION_X,120), 
											1, DirectorTreasure.COMMON, TREASURE_COST, "oro.png");
		treasureGame.addTreasure(treasure);
		while(!treasureGame.collisionTreasure()) {
			treasureGame.goDownHook();		
		}
		while(!treasureGame.getHook().initialPosition()) {
			treasureGame.getHook().goUp();	
		}
		treasureGame.collect();
		assertEquals(TREASURE_COST, treasureGame.getPlayer().getBalance());
		
	}
	
	@Test
	public void whenNotCollectTreasureNotShouldPay() {
		
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(Hook.INITIAL_POSITION_X,101), 
											1, DirectorTreasure.COMMON, TREASURE_COST, "oro.png");
		treasureGame.addTreasure(treasure);
		treasureGame.goDownHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
	}
		
	@Test
	public void whenThePlayerBuyUpgradesHookShouldDiscountTheCost() {
				
		treasureGame.getPlayer().accreditBalance(Store.COST_UPGRADE_HOOK);

		treasureGame.getStore().improveHook(treasureGame.getPlayer(), treasureGame.getHook());
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
	}
	
	@Test
	public void whenThePlayerBuyUpgradesHookShouldIncreaseTheLength() {
				
		treasureGame.getPlayer().accreditBalance(Store.COST_UPGRADE_HOOK);

		treasureGame.getStore().improveHook(treasureGame.getPlayer(), treasureGame.getHook());
		assertEquals(Hook.INITIAL_LENGTH+10,treasureGame.getHook().getLength());
	}
	
	@Test
	public void whenThePlayerNotBuyUpgradesHookNotShouldDiscountTheCost() {
				
		treasureGame.getPlayer().accreditBalance(Store.COST_UPGRADE_HOOK-1);

		treasureGame.getStore().improveHook(treasureGame.getPlayer(), treasureGame.getHook());
		assertEquals(Store.COST_UPGRADE_HOOK-1, treasureGame.getPlayer().getBalance());
	}
	
	@Test
	public void whenThePlayerNotBuyUpgradesHookNotShouldIncreaseTheLength() {
				
		treasureGame.getPlayer().accreditBalance(Store.COST_UPGRADE_HOOK-1);

		treasureGame.getStore().improveHook(treasureGame.getPlayer(), treasureGame.getHook());
		assertEquals(Hook.INITIAL_LENGTH,treasureGame.getHook().getLength());
	}
	
	@Test
	public void whenThePlayerBuyFuelShouldDiscountTheCost() {
		
		treasureGame.goDownHook();
		treasureGame.getPlayer().accreditBalance(Store.FUEL_COST);
		treasureGame.getStore().buyFuel(treasureGame.getPlayer(), treasureGame.getHook().getEngine());
		
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(Engine.INITIAL_FUEL+497,0,treasureGame.getHook().getEngine().getFuel());
	}
	
	@Test
	public void whenThePlayerNotBuyFuelShouldDiscountTheCost() {
		
		treasureGame.getPlayer().accreditBalance(Store.FUEL_COST-1);

		treasureGame.getStore().buyFuel(treasureGame.getPlayer(), treasureGame.getHook().getEngine());
		assertEquals(Store.FUEL_COST-1, treasureGame.getPlayer().getBalance());
		assertEquals(Engine.INITIAL_FUEL,0,treasureGame.getHook().getEngine().getFuel());
		
	}
	
	@Test
	public void moveHookLeftTest() {

		treasureGame.getHook().moveLeft();
		
		assertEquals(Hook.INITIAL_POSITION_X-3,treasureGame.getHook().getPosition().getX());
	}
	
	@Test
	public void moveHookRightTest() {

		treasureGame.getHook().moveRight(TreasureHunterGame.MAP_WIDTH);
		
		assertEquals(Hook.INITIAL_POSITION_X+3,treasureGame.getHook().getPosition().getX());
	}
	
	@Test
	public void whenTryingToMoveHookAndThereIsNoFuelItShouldNotMove() {
	
		for(int i = 0; i < Engine.INITIAL_FUEL+1; i++) {
			treasureGame.getHook().horizontalMovement();
		}
		
		assertEquals(0,00,treasureGame.getHook().getEngine().getFuel());
	}
}
