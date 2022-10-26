package tpAlgo3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreasureHunterTest {

	@Test
	public void testInitial() {
		var treasure = new TreasureHunterGame("test", 300);
		assertEquals(0,0, treasure.getPlayer().getBalance());
		assertEquals(0, treasure.getPlayer().getResistance());
		assertEquals(0, treasure.getPlayer().getStrengh());
		assertEquals(80, treasure.getHook().getLenght());
		assertEquals(150, treasure.getHook().getPositionHook().getX());
		assertEquals(20, treasure.getHook().getPositionHook().getY());
		
	}
	@Test
	public void treasureTest() {
		
		var treasureGame = new TreasureHunterGame("test", 300);
		Treasure treasureGranite = new Treasure(TreasureType.GRANITE, new Coordinate(150,40), 1, 1, 200);
		treasureGame.addTreasure(treasureGranite);
		
		//baja el gancho y colisiona
		treasureGame.goDownHook();
		assertEquals(40, treasureGame.getHook().getPositionHook().getY());
		
		//elimina el tesoro
		assertFalse(treasureGame.collisionTreasure());
		
		//sube el gancho
		treasureGame.goUpHook();
		assertEquals(20, treasureGame.getHook().getPositionHook().getY());
		
		//recibe 200
		assertEquals(200,0, treasureGame.getPlayer().getBalance());
		
		//mejora el gancho y pierde 200
		treasureGame.improveHook();
		assertEquals(0,0, treasureGame.getPlayer().getBalance());
		assertEquals(90,treasureGame.getHook().getLenght());
		
		
		
		
	}
}
