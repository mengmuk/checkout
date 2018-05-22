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

/**
 * Checkout tracks the items that are being scanned through and totals up the amounts. Pricing rules will need to be
 * passed in to setup the checkout process since pricing changes frequently.
 */
public class Checkout {

	private final List<Item> items = new ArrayList<>();
	private final List<PricingRule> pricingRules;

	public Checkout(List<PricingRule> pricingRules) {
		this.pricingRules = pricingRules;
	}

	private int calculateTotalForItem(Map.Entry<Item,Long> quantityPerItem) {

		Item item = quantityPerItem.getKey();
		int quantity = quantityPerItem.getValue().intValue();
		PricingRule pricingRule = pricingRules.stream()
				.filter(pr -> pr.getItem().equals(item))
				.findFirst()
				.orElseThrow(NoPricingRuleFoundException::new);

		return pricingRule.calculateFor(quantity);
	}

	/**
	 * Calculates the total amount of all Items scanned through the checkout.
	 * @return the total amount in pence for all the added Items
	 */
	public int total() {

		Map<Item, Long> quantityPerItem =
				items.stream()
					.collect(groupingBy(identity(), counting()));

		return quantityPerItem.entrySet().stream()
				.mapToInt(this::calculateTotalForItem)
				.sum();
	}

	/**
	 * Scans an Item and tracks it for totalling up later.
	 * @param item the product Item that is added to the checkout
	 */
	public void scan(Item item) {
		items.add(item);
	}
}
