package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

import eu.kbros.demo.testdatabuilder.Customer;

import org.junit.Test;

import static eu.kbros.demo.testdatabuilder._3withTestDataBuilder.TestDataBuilderFactory.aCustomer;
import static eu.kbros.demo.testdatabuilder._3withTestDataBuilder.TestDataBuilderFactory.anOrder;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CustomerScoreTest {
    private Customer aCustomer;

    @Test
    public void the_score_should_be_one_with_a_single_1k_order() {
    	aCustomer = new CustomerBuilder()
    		.with(
    				new OrderBuilder()
    					.withValue(1000))
    		.build();
    	
        assertThat(aCustomer.getScore(), is(1));
    }

    @Test
    public void the_score_should_be_two_with_two_1k_orders() {
        aCustomer = aCustomer()
                .with(
                        anOrder()
                                .withValue(1000)
                )
                .with(
                        anOrder()
                                .withValue(1000)
                )
                .build();

        assertThat(aCustomer.getScore(), is(2));
    }

    @Test
    public void the_score_should_be_zero_with_a_5k_order_and_a_payment_deficit() {
        aCustomer = aCustomer()
                .with(
                        anOrder()
                                .withValue(5000)
                )
                .with(
                        anOrder()
                                .withPaymentDefictit()
                                .withValue(1000)
                )
                .build();

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void the_lowest_score_should_be_zero() {
        aCustomer = aCustomer()
                .with(
                        anOrder()
                                .withPaymentDefictit()
                                .withValue(1000)
                )
                .with(
                        anOrder()
                                .withPaymentDefictit()
                                .withValue(1000)
                )
                .build();

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void only_orders_from_last_12_month_should_count() {
        aCustomer = aCustomer()
                .with(
                        anOrder()
                                .withOrderDateFromThisYear()
                                .withValue(1000)
                )
                .with(
                        anOrder()
                                .withOrderDateMoreThat12MonthAgo()
                                .withValue(1000)
                )
                .with(
                        anOrder()
                                .withOrderDateMoreThat12MonthAgo()
                                .withPaymentDefictit()
                                .withValue(1000)
                )
                .build();

        assertThat(aCustomer.getScore(), is(1));
    }
}
