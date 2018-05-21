package meng.checkout.pricing;

import org.junit.Test;

import meng.checkout.product.Item;

import static org.junit.Assert.assertEquals;

public class PricingRuleTest {

	@Test
	public void calculatePriceForZero() {

		Item item = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, 130);
		PricingRule pricingRule = new PricingRule(item, 50, multipriceOffer);
		assertEquals(0, pricingRule.calculateFor(0));
	}

	@Test
	public void calculatePriceForNonOfferItem() {

		Item item = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, 130);
		PricingRule pricingRule = new PricingRule(item, 50, multipriceOffer);
		assertEquals(50, pricingRule.calculateFor(1));
	}

	@Test
	public void calculatePriceForOfferItem() {

		Item item = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, 130);
		PricingRule pricingRule = new PricingRule(item, 50, multipriceOffer);
		assertEquals(130, pricingRule.calculateFor(3));
	}

	@Test
	public void calculatePriceForOfferItemAndNonOfferItem() {

		Item item = new Item("A");
		MultipriceOffer multipriceOffer = new MultipriceOffer(3, 130);
		PricingRule pricingRule = new PricingRule(item, 50, multipriceOffer);
		assertEquals(180, pricingRule.calculateFor(4));
	}

	@Test
	public void calculatePriceForItemWithoutOffers() {

		Item item = new Item("A");
		PricingRule pricingRule = new PricingRule(item, 50);
		assertEquals(200, pricingRule.calculateFor(4));
	}
}
