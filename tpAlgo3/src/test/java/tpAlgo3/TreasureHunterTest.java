package tpAlgo3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreasureHunterTest {

	@Test
	public void testInitialPlayer() {
		var treasure = new TreasureHunterGame();
		
		assertEquals(null,treasure.getPlayer().getName());
		assertEquals(0,0, treasure.getPlayer().getBalance());
		
	}
	
	@Test
	public void testInitialHook() {
		var treasure = new TreasureHunterGame();

		assertEquals(80, treasure.getHook().getLenght());
		assertEquals((TreasureHunterGame.MAP_WIDTH)/2, treasure.getHook().getPositionHook().getX());
		assertEquals(20, treasure.getHook().getPositionHook().getY());
		assertEquals(1000,0, treasure.getHook().getFuel());
	}
	
	@Test
	public void addTreasureTest() {
		var treasureGame = new TreasureHunterGame();
		Treasure treasureGranite = new Treasure(TreasureType.GRANITE, new Coordinate(150,40), 1, 1, 200);
		treasureGame.addTreasure(treasureGranite);
		
		assertFalse(treasureGame.getTreasure().isEmpty());
		
	}
	
	@Test
	public void treasureCollisionHookTest() {
		
		var treasureGame = new TreasureHunterGame();
		Treasure treasure = new Treasure(TreasureType.GOLD, new Coordinate(150,40), 1, 1, 200);
		treasureGame.addTreasure(treasure);
		
		treasureGame.goDownHook();
		assertEquals(40, treasureGame.getHook().getPositionHook().getY());	
		assertFalse(treasureGame.collisionTreasure());	
	}
	
	@Test
	public void borderMapCollisionHookTest() {
		
		var treasureGame = new TreasureHunterGame();
		treasureGame.getHook().setLenght((treasureGame.getMap().getDepth()) -20);
		treasureGame.goDownHook();
		assertTrue(treasureGame.getHook().collisionBorderMap(treasureGame.getMap()));		
	}
	
	@Test
	public void resetPositionHookTest() {
		
		var treasureGame = new TreasureHunterGame();
	
		treasureGame.goDownHook();
		treasureGame.getHook().goUp();
		assertEquals(20, treasureGame.getHook().getPositionHook().getY());
	}
	
	@Test
	public void payTreasureCollectPlayerTest() {
		
		var treasureGame = new TreasureHunterGame();
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(150,100), 1, 1, 950);
		treasureGame.addTreasure(treasure);
		treasureGame.goDownHook();
		assertEquals(950,0, treasureGame.getPlayer().getBalance());
		
	}
	
	@Test
	public void notPaymentTreasureNotCollectTest() {
		
		var treasureGame = new TreasureHunterGame();
		Treasure treasure = new Treasure(TreasureType.DIAMOND, new Coordinate(150,101), 1, 1, 950);
		treasureGame.addTreasure(treasure);
		treasureGame.goDownHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
	}
	
	@Test
	public void improveHookTest() {
		
		var treasureGame = new TreasureHunterGame();
		
		treasureGame.getPlayer().setBalance(100);

		treasureGame.improveHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(90,treasureGame.getHook().getLenght());
		
	}
	
	@Test
	public void rechargeFuelTest() {
		
		var treasureGame = new TreasureHunterGame();
		
		treasureGame.getPlayer().setBalance(80);

		treasureGame.buyFuel();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(1200,0,treasureGame.getHook().getFuel());
		
	}
	
	@Test
	public void rechargeFuelBorderTest() {
		
		var treasureGame = new TreasureHunterGame();
		
		treasureGame.getPlayer().setBalance(79);

		treasureGame.buyFuel();
		assertEquals(79,0, treasureGame.getPlayer().getBalance());
		assertEquals(1000,0,treasureGame.getHook().getFuel());
		
	}
	
	@Test
	public void moveLeftHookTest() {
		var treasureGame = new TreasureHunterGame();
		
		treasureGame.getHook().moveLeft();
		
		assertEquals(149,treasureGame.getHook().getPositionHook().getX());
	}
	
	@Test
	public void moveRightHookTest() {
		var treasureGame = new TreasureHunterGame();
		
		treasureGame.getHook().moveRight();
		
		assertEquals(151,treasureGame.getHook().getPositionHook().getX());
	}
	
	
}
