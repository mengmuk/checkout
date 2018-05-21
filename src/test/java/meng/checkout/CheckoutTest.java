package meng.checkout;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import meng.checkout.pricing.MultipriceOffer;
import meng.checkout.pricing.NoPricingRuleFoundException;
import meng.checkout.pricing.PricingRule;
import meng.checkout.product.Item;

import static org.junit.Assert.assertEquals;

public class CheckoutTest {

	private Checkout checkout;
	private Random random;
	private int unitPriceInPenceA;
	private int unitPriceInPenceB;
	private int unitPriceInPenceC;
	private int unitPriceInPenceD;
	private Item itemA;
	private Item itemB;
	private Item itemC;
	private Item itemD;
	private int multipriceOfferA;
	private int multipriceOfferB;

	@Before
	public void setUp() {
		random = new Random();
		unitPriceInPenceA = generateRandomPrice();
		unitPriceInPenceB = generateRandomPrice();
		unitPriceInPenceC = generateRandomPrice();
		unitPriceInPenceD = generateRandomPrice();
		multipriceOfferA = 130;
		multipriceOfferB = 45;
		itemA = new Item("A");
		itemB = new Item("B");
		itemC = new Item("C");
		itemD = new Item("D");
		checkout = new Checkout(Arrays.asList(
				new PricingRule(itemA, unitPriceInPenceA, new MultipriceOffer(3, multipriceOfferA)),
				new PricingRule(itemB, unitPriceInPenceB, new MultipriceOffer(2, multipriceOfferB)),
				new PricingRule(itemC, unitPriceInPenceC),
				new PricingRule(itemD, unitPriceInPenceD)
			)
		);
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

		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		assertEquals(multipriceOfferA, checkout.total());
	}

	@Test
	public void checkoutWithMultipriceOfferAndNonOffer() {

		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		checkout.scan(itemA);
		assertEquals(multipriceOfferA + unitPriceInPenceA, checkout.total());
	}

	@Test
	public void checkoutWithNoOfferItems() {

		checkout.scan(itemC);
		checkout.scan(itemD);
		assertEquals(unitPriceInPenceC + unitPriceInPenceD, checkout.total());
	}

	@Test
	public void checkoutWithMixedItems() {

		checkout.scan(itemB);
		checkout.scan(itemA);
		checkout.scan(itemB);
		checkout.scan(itemC);
		checkout.scan(itemD);
		assertEquals(multipriceOfferB + unitPriceInPenceA + unitPriceInPenceC + unitPriceInPenceD, checkout.total());
	}

	@Test(expected = NoPricingRuleFoundException.class)
	public void checkoutWithUnpricedItem() {

		Item itemE = new Item("E");
		checkout.scan(itemE);
		checkout.total();
	}

	public int generateRandomPrice() {
		return random.nextInt(100);
	}
}
