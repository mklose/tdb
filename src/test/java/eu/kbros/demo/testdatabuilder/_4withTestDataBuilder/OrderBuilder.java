package eu.kbros.demo.testdatabuilder._4withTestDataBuilder;

import eu.kbros.demo.testdatabuilder.Order;

import java.util.Calendar;
import java.util.Date;

public class OrderBuilder {
	
	//TODO BigDecimal
	private double value = 1000;
	private Date orderDate = thisYear();
	private boolean payed = true;
	
    public OrderBuilder() {
    }

    public OrderBuilder withValue(double value) {
    	this.value = value;
        return this;
    }

    public OrderBuilder withPaymentDefictit() {
        payed = false;
        return this;
    }

    public Order build() {
    	Order innerOrder = new Order();
        innerOrder.setOrderDate(orderDate);
        innerOrder.setPayed(payed);
        innerOrder.setValue(value);
    	return innerOrder;
    }

    private static Date thisYear() {
        return new Date();
    }

    private static Date lastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -2);
        return cal.getTime();
    }

    public OrderBuilder withOrderDateFromThisYear() {
        orderDate = thisYear();
        return this;
    }

    public OrderBuilder withOrderDateMoreThat12MonthAgo() {
    	orderDate = lastYear();
        return this;
    }
}
