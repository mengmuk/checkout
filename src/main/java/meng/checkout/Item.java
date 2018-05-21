package meng.checkout;

public class Item {

	private final String sku;
	private final int unitPriceInPence;

	public Item(String sku, int unitPriceInPence) {
		this.sku = sku;
		this.unitPriceInPence = unitPriceInPence;
	}

	public String getSku() {
		return sku;
	}

	public int getUnitPriceInPence() {
		return unitPriceInPence;
	}
}
