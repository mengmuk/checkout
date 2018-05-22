# Checkout Kata
## Instructions
Implement the code for a supermarket checkout that calculates the total price of a number of items.

In a normal supermarket, items are identified by ‘stock keeping units’ or ‘SKUs’. In our store, we will use individual 
letters of the alphabet, A, B, C etc, as the SKUs. 

Our goods are priced individually. In addition, some items are multipriced: buy n of them and which will cost you y. 
For example, item A might cost 50 pence individually but this week we have a special offer where you can buy 
3 As for £1.30.

This week’s prices are the following:

| Item  | Unit Price  | Special Price  |
|-------|-------------|----------------|
| A     | 50          | 3 for 130      |
| B     | 30          | 2 for 45       |
| C     | 20          |                |
| D     | 15          |                |

Our checkout accepts items in any order so if we scan a B, then an A, then another B, we will recognise
the two B’s and price them at 45 (for a total price so far of 95).

Extra points: Because the pricing changes frequently we will need to be able to pass in a set of pricing
rules each time we start handling a checkout transaction.

## Pre-requisites
- Java 8+
- JUnit4

## Using Checkout
Create a new checkout transaction and pass in the pricing rules.
```java
Checkout checkout = new Checkout(Arrays.asList(
				new PricingRule(new Item("A"), 10, new MultipriceOffer(3, 20)),
				new PricingRule(new Item("B"), 50));

```

Scan items into checkout.
```java
checkout.scan(item);

```

To calculate the total of the basket of items, total up the checkout, this will return the total in pence. 
The pricing rules and any related promotional multiprice offers will be taken into account in the calculation.
```java
int total = checkout.total();
```
