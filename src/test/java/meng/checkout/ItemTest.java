package meng.checkout;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {

	private Item item;

	@Before
	public void setUp() {
		item = new Item("A", 50);
	}

	@Test
	public void itemHasSkuSet() {

		assertEquals("A", item.getSku());
	}

	@Test
	public void itemHasPriceSet() {

		assertEquals(50, item.getUnitPriceInPence());
	}
}
