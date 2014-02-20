package eu.kbros.demo.testdatabuilder._2withImproveReadability;

import eu.kbros.demo.testdatabuilder.Address;
import eu.kbros.demo.testdatabuilder.Customer;
import eu.kbros.demo.testdatabuilder.Order;
import eu.kbros.demo.testdatabuilder.PaymentMethod;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class CustomerPaymentMethodsTest {
    private Customer aCustomer;

    @Before
    public void init() {
    	Address aAddress = new Address("Meisenstrasse 96", "33607", "Bielefeld");
        aCustomer = new Customer("Mustermann AG", aAddress);
    }

    @Test
    public void a_customer_with_score_zero_can_only_pay_in_advance() {
        assertThat(aCustomer.getPossiblePaymentMethods(), containsInAnyOrder(PaymentMethod.prepayment));
    }

    @Test
    public void a_customer_with_a_score_of_4_can_pay_in_advance_and_on_delivery() {
        aCustomer.addOrder(createOrderWithValueOf(4000));

        assertThat(aCustomer.getPossiblePaymentMethods(),
                containsInAnyOrder(PaymentMethod.prepayment, PaymentMethod.cashOnDelivery));
    }

    @Test
    public void a_customer_with_a_score_of_8_can_pay_in_advance_on_delivery_and_on_account() {
        aCustomer.addOrder(createOrderWithValueOf(8000));

        assertThat(aCustomer.getPossiblePaymentMethods(),
                containsInAnyOrder(PaymentMethod.prepayment, PaymentMethod.cashOnDelivery, PaymentMethod.account));
    }

    private static Order createOrderWithValueOf(double value) {
        Order order = new Order();
        order.setValue(value);
        order.setPayed(true);
        order.setOrderDate(new Date());
        return order;
    }
}
