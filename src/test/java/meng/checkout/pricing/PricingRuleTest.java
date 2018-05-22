package meng.checkout.pricing;

import org.junit.Before;
import org.junit.Test;

import meng.checkout.product.Item;

import static org.junit.Assert.assertEquals;

public class PricingRuleTest {

	private PricingRule pricingRuleWithOffer;
	private PricingRule pricingRuleWithoutOffer;

	@Before
	public void setUp() {

		Item itemA = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, 130);
		pricingRuleWithOffer = new PricingRule(itemA, 50, multipriceOffer);

		Item itemB = new Item("B");
		pricingRuleWithoutOffer = new PricingRule(itemB, 50);
	}

	@Test
	public void calculatePriceForZero() {

		assertEquals(0, pricingRuleWithOffer.calculateFor(0));
	}

	@Test
	public void calculatePriceForNonOfferItem() {

		assertEquals(50, pricingRuleWithOffer.calculateFor(1));
	}

	@Test
	public void calculatePriceForOfferItem() {

		assertEquals(130, pricingRuleWithOffer.calculateFor(3));
	}

	@Test
	public void calculatePriceForOfferItemAndNonOfferItem() {

		assertEquals(180, pricingRuleWithOffer.calculateFor(4));
	}

	@Test
	public void calculatePriceForItemWithoutOffers() {

		assertEquals(200, pricingRuleWithoutOffer.calculateFor(4));
	}
}
