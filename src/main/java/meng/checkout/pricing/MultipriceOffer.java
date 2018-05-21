package meng.checkout.pricing;

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
