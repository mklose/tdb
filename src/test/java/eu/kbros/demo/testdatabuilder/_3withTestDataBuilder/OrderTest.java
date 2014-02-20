package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class OrderTest {

	@Test
	public void two_orders_are_not_equal_when_value_is_not_equal() throws Exception {
		OrderBuilder anOrder = new OrderBuilder()
			.withValue(1000)
			.withOrderDate("01.01.2014")
			.withIsPayed(true);
		
		OrderBuilder anotherOrder = 
				anOrder
				.but().withValue(2000);
		
		assertThat(anOrder.build(), not(equalTo(anotherOrder.build())));
	}
	
	@Test
	public void two_orders_are_not_equal_when_value_is_not_equal_via_copy_constructor() throws Exception {
		OrderBuilder anOrder = new OrderBuilder()
			.withValue(1000)
			.withOrderDate("01.01.2014")
			.withIsPayed(true);
		
		OrderBuilder anotherOrder = new OrderBuilder(anOrder)
			.withValue(2000);
		
		assertThat(anOrder.build(), not(equalTo(anotherOrder.build())));
	}
	
	@Test
	public void two_orders_are_not_equal_when_payment_is_not_equal() throws Exception {
		OrderBuilder anOrder = new OrderBuilder()
			.withValue(1000)
			.withOrderDate("01.01.2014")
			.withIsPayed(true);
		
		OrderBuilder anotherOrder = 
				anOrder
				.but().withIsPayed(false);
		
		assertThat(anOrder.build(), not(equalTo(anotherOrder.build())));
	}
	
	@Test
	public void two_orders_are_not_equal_when_orderDate_is_not_equal() throws Exception {
		OrderBuilder anOrder = new OrderBuilder()
			.withValue(1000)
			.withOrderDate("01.01.2014")
			.withIsPayed(true);
		
		OrderBuilder anotherOrder = 
				anOrder
				.but().withOrderDate("02.01.2014");
		
		assertThat(anOrder.build(), not(equalTo(anotherOrder.build())));
	}
}
