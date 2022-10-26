package tpAlgo3;

import static org.junit.Assert.*;

import org.junit.Test;

public class TreasureHunterTest {

	@Test
	public void testInitial() {
		var treasure = new TreasureHunterGame("test", 20);
		assertEquals(0, treasure.getPlayer().getBalance());
		assertEquals(0, treasure.getPlayer().getResistance());
		assertEquals(0, treasure.getPlayer().getStrengh());
	}
	
}
