package meng.checkout.pricing;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import meng.checkout.product.Item;

import static org.junit.Assert.assertEquals;

public class PricingRuleTest {

	private Random random;
	private int unitPriceInPenceA;
	private int unitPriceInPenceB;
	private int multipriceOfferA;
	private PricingRule pricingRuleWithOffer;
	private PricingRule pricingRuleWithoutOffer;

	@Before
	public void setUp() {

		random = new Random();
		unitPriceInPenceA = generateRandomPrice();
		unitPriceInPenceB = generateRandomPrice();
		multipriceOfferA = 130;

		Item itemA = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, multipriceOfferA);
		pricingRuleWithOffer = new PricingRule(itemA, unitPriceInPenceA, multipriceOffer);

		Item itemB = new Item("B");
		pricingRuleWithoutOffer = new PricingRule(itemB, unitPriceInPenceB);
	}

	@Test
	public void calculatePriceForZeroItems() {

		assertEquals(0, pricingRuleWithOffer.calculateFor(0));
	}

	@Test
	public void calculatePriceForNonOfferItem() {

		assertEquals(unitPriceInPenceA, pricingRuleWithOffer.calculateFor(1));
	}

	@Test
	public void calculatePriceForOfferItem() {

		assertEquals(multipriceOfferA, pricingRuleWithOffer.calculateFor(3));
	}

	@Test
	public void calculatePriceForOfferItemsAndNonOfferItem() {

		assertEquals(unitPriceInPenceA + (multipriceOfferA * 2), pricingRuleWithOffer.calculateFor(7));
	}

	@Test
	public void calculatePriceForItemWithoutOffers() {

		assertEquals(unitPriceInPenceB * 4, pricingRuleWithoutOffer.calculateFor(4));
	}

	public int generateRandomPrice() {
		return random.nextInt(100);
	}
}
