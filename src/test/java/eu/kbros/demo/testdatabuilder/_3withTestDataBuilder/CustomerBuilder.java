package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

import java.util.Date;

import eu.kbros.demo.testdatabuilder.Address;
import eu.kbros.demo.testdatabuilder.Customer;
import eu.kbros.demo.testdatabuilder.Order;

public class CustomerBuilder {
    private Customer innerCustomer;

    public CustomerBuilder() {
    	Address aAddress = new Address("Meisenstrasse 96", "33607", "Bielefeld");
        innerCustomer = new Customer("Mustermann AG", aAddress);
    }
    
    public CustomerBuilder withName(String name){
    	innerCustomer.setName(name);
    	return this;
    }

    public CustomerBuilder withScore(int score) {
        Order order = new Order();
        order.setValue(score*1000);
        order.setPayed(true);
        order.setOrderDate(new Date());
        
        innerCustomer.addOrder(order);
        return this;
    }

    public CustomerBuilder with(OrderBuilder order) {
        innerCustomer.addOrder(
                order.build()
        );
        return this;
    }
   
    public Customer build() {
        return innerCustomer;
    }
}
