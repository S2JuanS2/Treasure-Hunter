package treasureHunter;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreasureHunterTest {
	
	private TreasureHunterGame treasureGame = new TreasureHunterGame();

	@Test
	public void testInitialPlayer() {
		
		assertEquals(null,treasureGame.getPlayer().getName());
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		
	}
	
	@Test
	public void testInitialHook() {

		assertEquals(80, treasureGame.getHook().getLenght());
		assertEquals((TreasureHunterGame.MAP_WIDTH)/2, treasureGame.getHook().getPosition().getX());
		assertEquals(20, treasureGame.getHook().getPosition().getY());
		assertEquals(1000,0, treasureGame.getHook().getEngine().getFuel());
	}
	
	@Test
	public void testInitialEngine() {
		assertEquals(1000,0, treasureGame.getHook().getEngine().getFuel());
		assertEquals(1, treasureGame.getHook().getEngine().getPower());
	}
	
	@Test
	public void addTreasureTest() {

		Treasure treasureGranite = new Treasure(TreasureType.GRANITE, new Coordinate(150,40), 1, 1, 200);
		treasureGame.addTreasure(treasureGranite);
		
		assertFalse(treasureGame.getTreasure().isEmpty());
		
	}
	
	@Test
	public void treasureCollisionHookTest() {
		
		Treasure treasure = new Treasure(TreasureType.GOLD, new Coordinate(150,100), 1, 1, 200);
		treasureGame.addTreasure(treasure);
		
		treasureGame.goDownHook();
		assertEquals(100, treasureGame.getHook().getPosition().getY());	
	}
	
	@Test
	public void treasureNoCollisionHookTest() {
		
		Treasure treasure = new Treasure(TreasureType.GOLD, new Coordinate(150,101), 1, 1, 200);
		treasureGame.addTreasure(treasure);
		
		treasureGame.goDownHook();
		assertEquals(100, treasureGame.getHook().getPosition().getY());	
	}
		
	@Test
	public void borderMapCollisionHookTest() {
		
		for(int i = 0; i < 40; i++) {
			treasureGame.getHook().increaseLenght();			
		}
		treasureGame.goDownHook();
		assertTrue(treasureGame.getHook().collisionBorderMap(TreasureHunterGame.MAP_WIDTH,TreasureHunterGame.MAP_DEPTH));		
	}
	
	@Test
	public void resetPositionHookTest() {
	
		treasureGame.goDownHook();
		treasureGame.getHook().goUp();
		assertEquals(20, treasureGame.getHook().getPosition().getY());
	}
	
	@Test
	public void payTreasureCollectPlayerTest() {
		
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(150,100), 1, 1, 950);
		treasureGame.addTreasure(treasure);
		treasureGame.goDownHook();
		assertEquals(950,0, treasureGame.getPlayer().getBalance());
		
	}
	
	@Test
	public void notPaymentTreasureNotCollectTest() {
		
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(150,101), 1, 1, 950);
		treasureGame.addTreasure(treasure);
		treasureGame.goDownHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
	}
		
	@Test
	public void improveHookTest() {
				
		treasureGame.getPlayer().accreditBalance(100);

		treasureGame.improveHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(90,treasureGame.getHook().getLenght());
	}
	
	@Test
	public void noImproveHookTest() {
				
		treasureGame.getPlayer().accreditBalance(99);

		treasureGame.improveHook();
		assertEquals(99,0, treasureGame.getPlayer().getBalance());
		assertEquals(80,treasureGame.getHook().getLenght());
	}
	
	@Test
	public void rechargeFuelTest() {
		
		treasureGame.getPlayer().accreditBalance(80);

		treasureGame.buyFuel();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(1200,0,treasureGame.getHook().getEngine().getFuel());
	}
	
	@Test
	public void noRechargeFuelTest() {
		
		treasureGame.getPlayer().accreditBalance(79);

		treasureGame.buyFuel();
		assertEquals(79,0, treasureGame.getPlayer().getBalance());
		assertEquals(1000,0,treasureGame.getHook().getEngine().getFuel());
		
	}
	
	@Test
	public void moveLeftHookTest() {

		treasureGame.getHook().moveLeft();
		
		assertEquals(149,treasureGame.getHook().getPosition().getX());
	}
	
	@Test
	public void moveRightHookTest() {

		treasureGame.getHook().moveRight(TreasureHunterGame.MAP_WIDTH);
		
		assertEquals(151,treasureGame.getHook().getPosition().getX());
	}
	
	@Test
	public void noFuelTest() {
		treasureGame.getPlayer().accreditBalance(80);
		for(int i = 0; i < 6; i++) {
			treasureGame.goDownHook();
			treasureGame.getHook().goUp();
		}
		for(int i = 0; i < 40; i++) {
			treasureGame.getHook().moveLeft();
		}
		treasureGame.getHook().moveLeft();
		assertEquals(110,treasureGame.getHook().getPosition().getX());
		treasureGame.goDownHook();
		assertEquals(20,treasureGame.getHook().getPosition().getY());
	}
}
