package com.example.customer.relationship.management.service.impl;

import com.example.customer.relationship.management.dao.CustomerDao;
import com.example.customer.relationship.management.entity.Customer;
import com.example.customer.relationship.management.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    // Constructor-based dependency injection for CustomerDao.
    public CustomerServiceImpl(CustomerDao customerDao) {
        super();
        this.customerDao = customerDao;  // Correctly assign the injected CustomerDao to the field.
    }

    @Override
    public String insertCustomer(Customer customer) {
        return customerDao.insertCustomer(customer);
    }

    @Override
    public List<Customer> getCustomerList() {
        return customerDao.getCustomerList();
    }

    @Override
    public Customer getCustomerByID(int id) {
        return customerDao.getCustomerByID(id);
    }

    @Override
    public String insertMultipleCustomers(List<Customer> customerList) {
        return customerDao.insertMultipleCustomer(customerList);
    }

    @Override
    public String updateCustomerDetails(Customer customer) {
        return customerDao.updateCustomerData(customer);
    }

    @Override
    public String deleteCustomerByID(int id) {
        return customerDao.deleteCustomerByID(id);
    }

    @Override
    public List<Customer> getCustomerByFirstName(String firstName) {
        return customerDao.ViewAllCustomerWithName(firstName);
    }

    @Override
    public List<Customer> getCustomerByLessAge(int age) {
        return customerDao.getCustomerByLessThanAge(age);
    }

    @Override
    public List<Customer> getCustomerByAge(int age) {
        return customerDao.getCustomerOfGivenAge(age);
    }
}

