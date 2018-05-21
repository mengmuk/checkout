package meng.checkout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import meng.checkout.pricing.NoPricingRuleFoundException;
import meng.checkout.pricing.PricingRule;
import meng.checkout.product.Item;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Checkout {

	private List<Item> items = new ArrayList<>();
	private List<PricingRule> pricingRules;

	public Checkout(List<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
	}

	private int calculateTotalForItem(Map.Entry<Item,Long> quantityPerItem) {

		Item item = quantityPerItem.getKey();
		int quantity = quantityPerItem.getValue().intValue();
		PricingRule pricingRule = pricingRules.stream()
				.filter(pr -> pr.getItem().equals(item))
				.findFirst()
				.orElseThrow(() -> new NoPricingRuleFoundException());

		return pricingRule.calculateFor(quantity);
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
