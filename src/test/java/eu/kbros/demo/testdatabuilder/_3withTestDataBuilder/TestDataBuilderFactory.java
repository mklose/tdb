package eu.kbros.demo.testdatabuilder._3withTestDataBuilder;

public class TestDataBuilderFactory {
    public static CustomerBuilder aCustomer() {
        return new CustomerBuilder();
    }

    public static OrderBuilder anOrder() {
        return new OrderBuilder();
    }
}
