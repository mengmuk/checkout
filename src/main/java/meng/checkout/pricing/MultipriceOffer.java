package meng.checkout.pricing;

/**
 * MultipriceOffer can be set within a PricingRule of an Item to represent offers of:
 * buy n of them and which will cost you y in pence
 */
public class MultipriceOffer {

	private final int quantity;
	private final int offerPriceInPence;

	public MultipriceOffer(int quantity, int offerPriceInPence) {
		this.quantity = quantity;
		this.offerPriceInPence = offerPriceInPence;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getOfferPriceInPence() {
		return offerPriceInPence;
	}
}
