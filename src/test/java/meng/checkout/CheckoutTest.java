package meng.checkout;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

	private Checkout checkout;
	private Random random;

	@Before
	public void setUp() {
		checkout = new Checkout();
		random = new Random();
	}

	@Test
	public void checkoutWithNoItems() {

		assertEquals(0, checkout.total());
	}

	@Test
	public void checkoutWithOneItem() {

		int unitPriceInPenceA = generateRandomPrice();
		Item itemA = new Item("A", unitPriceInPenceA);
		checkout.scan(itemA);
		assertEquals(unitPriceInPenceA, checkout.total());
	}

	@Test
	public void checkoutWithTwoItems() {

		int unitPriceInPenceA = generateRandomPrice();
		int unitPriceInPenceB = generateRandomPrice();
		Item itemA = new Item("A", unitPriceInPenceA);
		Item itemB = new Item("B", unitPriceInPenceB);
		checkout.scan(itemA);
		checkout.scan(itemB);
		assertEquals(unitPriceInPenceA + unitPriceInPenceB, checkout.total());
	}

	public int generateRandomPrice() {
		return random.nextInt(100);
	}
}
