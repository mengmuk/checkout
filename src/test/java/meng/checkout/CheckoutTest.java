package meng.checkout;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import meng.checkout.pricing.MultipriceOffer;
import meng.checkout.product.Item;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

	private Checkout checkout;
	private Random random;
	private int unitPriceInPenceA;
	private int unitPriceInPenceB;
	private Item itemA;
	private Item itemB;

	@Before
	public void setUp() {
		checkout = new Checkout(new HashMap<>());
		random = new Random();
		unitPriceInPenceA = generateRandomPrice();
		unitPriceInPenceB = generateRandomPrice();
		itemA = new Item("A", unitPriceInPenceA);
		itemB = new Item("B", unitPriceInPenceB);
	}

	@Test
	public void checkoutWithNoItems() {

		assertEquals(0, checkout.total());
	}

	@Test
	public void checkoutWithOneItem() {

		checkout.scan(itemA);
		assertEquals(unitPriceInPenceA, checkout.total());
	}

	@Test
	public void checkoutWithTwoItems() {

		checkout.scan(itemA);
		checkout.scan(itemB);
		assertEquals(unitPriceInPenceA + unitPriceInPenceB, checkout.total());
	}

	@Test
	public void checkoutWithMultipriceOffer() {

		int offerPriceInPence = 130;
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, offerPriceInPence);
		Map<Item, MultipriceOffer> multipriceOffers = new HashMap<>();
		multipriceOffers.put(itemA, multipriceOffer);
		checkout = new Checkout(multipriceOffers);
		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		assertEquals(offerPriceInPence, checkout.total());
	}

	@Test
	public void checkoutWithMultipriceOfferAndNonOffer() {

		int offerPriceInPence = 130;
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, offerPriceInPence);
		Map<Item, MultipriceOffer> multipriceOffers = new HashMap<>();
		multipriceOffers.put(itemA, multipriceOffer);
		checkout = new Checkout(multipriceOffers);
		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		assertEquals(offerPriceInPence + unitPriceInPenceA, checkout.total());
	}

	public int generateRandomPrice() {
		return random.nextInt(100);
	}
}
