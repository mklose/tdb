package eu.kbros.demo.testdatabuilder._4withTestDataBuilder;

import java.util.ArrayList;
import java.util.List;

import eu.kbros.demo.testdatabuilder.Address;
import eu.kbros.demo.testdatabuilder.Customer;

public class CustomerBuilder {
    private List<OrderBuilder> orders = new ArrayList<OrderBuilder>();
	Address aAddress = new Address("Meisenstrasse 96", "33607", "Bielefeld");
    String name ="Mustermann AG";
    
    public CustomerBuilder() {
    }

    public CustomerBuilder with(OrderBuilder order) {
    	orders.add(order);
    	return this;
    }
    
    public Customer build() {
        Customer customer = new Customer(name, aAddress);
    
    	for (OrderBuilder order : orders){
    		customer.addOrder(
    			order.build()
    		);
    	}
        
    	return customer;
    }
   
   	public int getScore() {
		return build().getScore();
	}
	
}
