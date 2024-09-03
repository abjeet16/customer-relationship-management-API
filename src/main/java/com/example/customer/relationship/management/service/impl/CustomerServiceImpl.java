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
}

