package eu.kbros.demo.testdatabuilder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

public class Customer {
    private Collection<Order> orders;
    private Address address;
    private String name;

    public Customer(String name, Address address) {
        orders = new ArrayList<Order>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public int getScore() {
        int score = 0;

        for(Order order : orders) {
            if (isOrderDateOutOfRange(order.getOrderDate())) continue;

            if (!order.isPayed())
                score -= 5;
            else
                score += order.getValue() / 1000;
        }

        return score >= 0 ? score : 0;
    }

    public Collection<PaymentMethod> getPossiblePaymentMethods() {
        Collection<PaymentMethod> possiblePaymentMethods = new ArrayList<PaymentMethod>();
        possiblePaymentMethods.add(PaymentMethod.prepayment);

        int score = this.getScore();
        if (score >= 4)
            possiblePaymentMethods.add(PaymentMethod.cashOnDelivery);
        if (score >= 8)
            possiblePaymentMethods.add(PaymentMethod.account);

        return possiblePaymentMethods;
    }

    private boolean isOrderDateOutOfRange(Date orderDate) {
        return orderDate.getTime() < getDateOneYearAgo().getTime();
    }

    private Date getDateOneYearAgo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        return cal.getTime();
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
