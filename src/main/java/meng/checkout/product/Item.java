package meng.checkout.product;

/**
 * Item is a prduct item that can be bought. Items can be identified by stock keeping units or SKUs.
 */
public class Item {

	private final String sku;

	public Item(String sku) {
		this.sku = sku;
	}
}
