package meng.checkout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import meng.checkout.pricing.MultipriceOffer;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Checkout {

	private List<Item> items = new ArrayList<>();
	private Map<Item, MultipriceOffer> multipriceOffers;

	public Checkout(Map<Item, MultipriceOffer> multipriceOffers) {
		this.multipriceOffers = multipriceOffers;
	}

	private int calculateTotalForItem(Map.Entry<Item,Long> quantityPerItem) {

		Item item = quantityPerItem.getKey();
		int quantity = quantityPerItem.getValue().intValue();

		int total = 0;
		if(multipriceOffers.containsKey(item)) {
			MultipriceOffer multipriceOffer = multipriceOffers.get(item);
			int groupedOffers = quantity / multipriceOffer.getQuantity();
			total += groupedOffers * multipriceOffer.getOfferPriceInPence();
			quantity = quantity % multipriceOffer.getQuantity();
		}

		total += quantity * item.getUnitPriceInPence();

		return total;
	}


	public int total() {

		Map<Item, Long> quantityPerItem =
				items.stream()
					.collect(groupingBy(identity(), counting()));

		return quantityPerItem.entrySet().stream()
				.mapToInt(this::calculateTotalForItem)
				.sum();
	}

	public void scan(Item item) {
		items.add(item);
	}
}
