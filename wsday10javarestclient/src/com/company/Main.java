package com.company;
import java.util.List;

public class Main {

    static CustomerApi api;

    public static void main(String[] args) throws ApiErrorException {
        api = new CustomerApi("http://localhost:3000");
        List<Customer> list;
        api.updateCustomer(new Customer(5, "pj@gmail.com", "pj", 1));
        api.deleteCustomerById(6);
        try {
            list = api.getAllCustomers();
            for (Customer cust : list) {
                System.out.println(cust);
            }
        } catch (ApiErrorException e) {
            e.printStackTrace();
        }
    }
}
