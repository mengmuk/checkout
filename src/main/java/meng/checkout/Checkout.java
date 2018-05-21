package meng.checkout;

import java.util.ArrayList;
import java.util.List;

public class Checkout {

	private List<Item> items = new ArrayList<>();

	public int total() {
		return items.stream()
				.mapToInt(Item::getUnitPriceInPence)
				.sum();
	}

	public void scan(Item item) {
		items.add(item);
	}
}
