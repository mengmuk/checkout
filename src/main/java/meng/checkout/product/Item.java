package meng.checkout.product;

public class Item {

	private final String sku;
	private final int unitPriceInPence;

	public Item(String sku, int unitPriceInPence) {
		this.sku = sku;
		this.unitPriceInPence = unitPriceInPence;
	}

	public int getUnitPriceInPence() {
		return unitPriceInPence;
	}
}
