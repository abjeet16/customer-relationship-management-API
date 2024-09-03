package com.example.customer.relationship.management.service;

import com.example.customer.relationship.management.entity.Customer;

import java.util.List;

public interface CustomerService {

    String insertCustomer(Customer customer);
    List<Customer> getCustomerList();
    Customer getCustomerByID(int id);
    String insertMultipleCustomers(List<Customer> customerList);
}
