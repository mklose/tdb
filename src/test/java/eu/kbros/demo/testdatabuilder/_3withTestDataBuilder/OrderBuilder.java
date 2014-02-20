package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

import eu.kbros.demo.testdatabuilder.Order;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class OrderBuilder {
    private Order innerOrder;

    public OrderBuilder() {
        innerOrder = new Order();
        innerOrder.setOrderDate(thisYear());
        innerOrder.setPayed(true);
        innerOrder.setValue(1000);
    }

    public OrderBuilder(OrderBuilder anOrder) {
    	this();
    	Order tocopy = anOrder.build();
		withValue(tocopy.getValue());
		withIsPayed(tocopy.isPayed());
		withOrderDate(tocopy.getOrderDate());
    }

	public OrderBuilder withValue(double value) {
        innerOrder.setValue(value);
        return this;
    }

    public OrderBuilder withPaymentDefictit() {
        innerOrder.setPayed(false);
        return this;
    }

    public Order build() {
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
        innerOrder.setOrderDate(thisYear());
        return this;
    }

    public OrderBuilder withOrderDateMoreThat12MonthAgo() {
        innerOrder.setOrderDate(lastYear());
        return this;
    }
    
    public OrderBuilder withOrderDate(Date orderDate) {
    	innerOrder.setOrderDate(orderDate);
    	return this;
    }
    
    public OrderBuilder withOrderDate(String orderDate) throws ParseException {
    	SimpleDateFormat format = new SimpleDateFormat("dd.mm.yyyy");
    	innerOrder.setOrderDate(format.parse(orderDate));
    	return this;
    }
    
    public OrderBuilder withIsPayed(boolean isPayed) {
    	innerOrder.setPayed(isPayed);
    	return this;
    }
    
    public OrderBuilder but() {
    	return new OrderBuilder()
    		.withValue(innerOrder.getValue())
    		.withIsPayed(innerOrder.isPayed())
    		.withOrderDate(innerOrder.getOrderDate());
    }
}
