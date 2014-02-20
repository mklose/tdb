package eu.kbros.demo.testdatabuilder._2withImproveReadability;

import eu.kbros.demo.testdatabuilder.Address;
import eu.kbros.demo.testdatabuilder.Customer;
import eu.kbros.demo.testdatabuilder.Order;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CustomerScoreTest {
    private Customer aCustomer;

    @Before
    public void init() {
    	Address aAddress = new Address("Meistenstrasse 96", "33607", "Bielefeld");
        aCustomer = new Customer("Mustermann AG", aAddress);
    }

    @Test
    public void the_score_should_be_one_with_a_single_1k_order() {
        aCustomer.addOrder(createOrderWithValueOf(1000));

        assertThat(aCustomer.getScore(), is(1));
    }

    @Test
    public void the_score_should_be_two_with_two_1k_orders() {
        aCustomer.addOrder(createOrderWithValueOf(1000));
        aCustomer.addOrder(createOrderWithValueOf(1000));

        assertThat(aCustomer.getScore(), is(2));
    }

    @Test
    public void the_score_should_be_zero_with_a_5k_order_and_a_payment_deficit() {
        aCustomer.addOrder(createOrderWithValueOf(5000));
        aCustomer.addOrder(createOrderWithPaymentDeficitAndValueOf(1000));

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void the_lowest_score_should_be_zero() {
        aCustomer.addOrder(createOrderWithPaymentDeficitAndValueOf(1000));
        aCustomer.addOrder(createOrderWithPaymentDeficitAndValueOf(1000));

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void only_orders_from_last_12_month_should_count() {
        aCustomer.addOrder(createOrderFromThisYearWithValue(1000));
        aCustomer.addOrder(createOrderFromLastYearWithValue(1000));
        aCustomer.addOrder(createOrderWithPaymentDeficitFromLastYearWithValueOf(1000));

        assertThat(aCustomer.getScore(), is(1));
    }

    private static Order createOrderWithValueOf(double value) {
        return createOrder(value, true, thisYear());
    }

    private static Order createOrderFromThisYearWithValue(double value) {
        return createOrder(value, true, thisYear());
    }

    private static Order createOrderFromLastYearWithValue(double value) {
        return createOrder(value, true, lastYear());
    }

    private static Order createOrderWithPaymentDeficitAndValueOf(double value) {
        return createOrder(value, false, thisYear());
    }

    private static Order createOrderWithPaymentDeficitFromLastYearWithValueOf(double value) {
        return createOrder(1000, false, lastYear());
    }

    private static Order createOrder(double value, boolean isPayed, Date orderDate) {
        Order order = new Order();
        order.setValue(value);
        order.setPayed(isPayed);
        order.setOrderDate(orderDate);
        return order;
    }

    private static Date thisYear() {
        return new Date();
    }

    private static Date lastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        return cal.getTime();
    }
}
