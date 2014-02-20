package eu.kbros.demo.testdatabuilder._1withMethods;

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
    	Address aAddress = new Address("Meisenstrasse 96", "33607", "Bielefeld"); 
        aCustomer = new Customer("Musterman AG", aAddress);
    }

    @Test
    public void the_score_should_be_one_with_a_single_1k_order() {
        aCustomer.addOrder(createOrder(1000));

        assertThat(aCustomer.getScore(), is(1));
    }

    @Test
    public void the_score_should_be_two_with_two_1k_orders() {
        aCustomer.addOrder(createOrder(1000));
        aCustomer.addOrder(createOrder(1000));

        assertThat(aCustomer.getScore(), is(2));
    }

    @Test
    public void the_score_should_be_zero_with_a_5k_order_and_a_payment_deficit() {
        aCustomer.addOrder(createOrder(5000));
        aCustomer.addOrder(createOrder(1000, false));

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void the_lowest_score_should_be_zero() {
        aCustomer.addOrder(createOrder(1000, false));
        aCustomer.addOrder(createOrder(1000, false));

        assertThat(aCustomer.getScore(), is(0));
    }

    @Test
    public void only_orders_from_last_12_month_should_count() {
        aCustomer.addOrder(createOrder(1000, thisYear()));
        aCustomer.addOrder(createOrder(1000, lastYear()));
        aCustomer.addOrder(createOrder(1000, false, lastYear()));

        assertThat(aCustomer.getScore(), is(1));
    }

    private static Order createOrder(double value) {
        return createOrder(value, true);
    }

    private static Order createOrder(double value, Date orderDate) {
        return createOrder(value, true, orderDate);
    }

    private static Order createOrder(double value, boolean isPayed) {
        return createOrder(value, isPayed, thisYear());
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
