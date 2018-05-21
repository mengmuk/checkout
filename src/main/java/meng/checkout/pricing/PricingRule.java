package meng.checkout.pricing;

import meng.checkout.product.Item;

public class PricingRule {

	private final Item item;
	private final int unitPriceInPence;
	private final MultipriceOffer multipriceOffer;

	public PricingRule(Item item, int unitPriceInPence) {
		this.item = item;
		this.unitPriceInPence = unitPriceInPence;
		this.multipriceOffer = new MultipriceOffer(1, unitPriceInPence);
	}

	public PricingRule(Item item, int unitPriceInPence, MultipriceOffer multipriceOffer) {
		this.item = item;
		this.unitPriceInPence = unitPriceInPence;
		this.multipriceOffer = multipriceOffer;
	}

	public int calculateFor(int quantity) {

		int groupedOffers = quantity / multipriceOffer.getQuantity();
		int nonOfferQuantity = quantity % multipriceOffer.getQuantity();

		return groupedOffers * multipriceOffer.getOfferPriceInPence()
				+ nonOfferQuantity * unitPriceInPence;
	}

	public Item getItem() {
		return item;
	}
}
