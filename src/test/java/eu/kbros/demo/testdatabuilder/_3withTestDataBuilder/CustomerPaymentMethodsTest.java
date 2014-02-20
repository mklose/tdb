package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

import eu.kbros.demo.testdatabuilder.Customer;
import eu.kbros.demo.testdatabuilder.PaymentMethod;

import org.junit.Test;

import static eu.kbros.demo.testdatabuilder._3withTestDataBuilder.TestDataBuilderFactory.aCustomer;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;

public class CustomerPaymentMethodsTest {
    private Customer aCustomer;

    @Test
    public void a_customer_with_score_zero_can_only_pay_in_advance() {
        aCustomer = aCustomer()
                .withScore(0)
                .build();

        assertThat(aCustomer.getPossiblePaymentMethods(), containsInAnyOrder(PaymentMethod.prepayment));
    }

    @Test
    public void a_customer_with_a_score_of_4_can_pay_in_advance_and_on_delivery() {
        aCustomer = new CustomerBuilder()
                .withScore(4)
                .build();

        assertThat(aCustomer.getPossiblePaymentMethods(),
                containsInAnyOrder(PaymentMethod.prepayment, PaymentMethod.cashOnDelivery));
    }

    @Test
    public void a_customer_with_a_score_of_8_can_pay_in_advance_on_delivery_and_on_account() {
        aCustomer = aCustomer()
                .withScore(8)
                .build();

        assertThat(aCustomer.getPossiblePaymentMethods(),
                containsInAnyOrder(PaymentMethod.prepayment, PaymentMethod.cashOnDelivery, PaymentMethod.account));
    }
}
