package com.example.customer.relationship.management.dao;

import com.example.customer.relationship.management.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository  // Marks this class as a Spring-managed bean and a DAO component.
public class CustomerDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public CustomerDao(SessionFactory sessionFactory){
        super();
        this.sessionFactory = sessionFactory;
    }

    // Method to insert a customer into the database.
    public String insertCustomer(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error inserting customer";
        } finally {
            session.close();
        }

        return "Customer insert done";
    }

    // Method to retrieve a list of all customers.
    public List<Customer> getCustomerList(){
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        try {
            // Use Hibernate query language (HQL) to retrieve customers.
            customers = session.createQuery("from Customer", Customer.class).list();
        } finally {
            session.close();
        }
        return customers;
    }
}


