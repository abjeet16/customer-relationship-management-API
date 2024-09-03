package com.example.customer.relationship.management.service;

import com.example.customer.relationship.management.entity.Customer;

import java.util.List;

public interface CustomerService {

    String insertCustomer(Customer customer);
    List<Customer> getCustomerList();
    Customer getCustomerByID(int id);
    String insertMultipleCustomers(List<Customer> customerList);
    String updateCustomerDetails(Customer customer);
    String deleteCustomerByID(int id);
    List<Customer> getCustomerByFirstName(String firstName);
    List<Customer> getCustomerByLessAge(int age);
    List<Customer> getCustomerByAge(int age);
}
