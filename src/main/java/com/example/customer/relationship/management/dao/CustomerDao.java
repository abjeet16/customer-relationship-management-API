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

    public List<Customer> getCustomerList(){
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        try {
            customers = session.createQuery("from Customer", Customer.class).list();
        } finally {
            session.close();
        }
        return customers;
    }

    public Customer getCustomerByID(int id){
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            customer = session.get(Customer.class, id);
        } finally {
            session.close();
        }
        return customer;
    }

    public String insertMultipleCustomer(List<Customer> customerList){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            for(int i = 0; i < customerList.size(); i++){
                session.save(customerList.get(i));
                if (i % 20 == 0) { // Flush and clear session in batches to manage memory
                    session.flush();
                    session.clear();
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error adding customers";
        } finally {
            session.close();
        }
        return "Adding customers done";
    }

    public String updateCustomerData(Customer customer){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(customer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return "Error updating customer";
        } finally {
            session.close();
        }
        return "Customer details updated";
    }
}



