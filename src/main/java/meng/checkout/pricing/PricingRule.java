package meng.checkout.pricing;

import meng.checkout.product.Item;

/**
 * PricingRule contains the pricing parameters for an Item. Each item must have a unit price in pence defined.
 * Optional promotional multiprice offers can also be defined for an Item.
 */
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

	/**
	 * Calculates the price for a given quantity of the Item.
	 * @param quantity the number of the same Item
	 * @return the calculated amount of the Item
	 */
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
