package meng.checkout.pricing;

/**
 * If an Item with no defined PricingRule is calculated at checkout, NoPricingRuleFoundException will be thrown as
 * there is no known price that can be use for calculations.
 */
public class NoPricingRuleFoundException extends RuntimeException {
}
