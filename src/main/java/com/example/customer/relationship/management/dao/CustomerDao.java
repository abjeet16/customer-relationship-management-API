package com.example.customer.relationship.management.dao;

import com.example.customer.relationship.management.entity.Customer;
import jakarta.persistence.Query;
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
    public String deleteCustomerByID(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = session.get(Customer.class, id);
            if (customer != null) {
                session.delete(customer);
                transaction.commit();
                return "Customer details deleted";
            } else {
                return "Customer not found with id: " + id;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Consider using a logger here
            return "Error deleting customer";
        } finally {
            session.close();
        }
    }
    public List<Customer> ViewAllCustomerWithName(String firstName) {
        // Initialize the session
        Session session = sessionFactory.openSession();
        List<Customer> customerList = null;

        try {
            // Create a query to select customers with the specified first name
            Query customerQuery = session.createQuery(
                    "from Customer c where c.firstName = :firstName", Customer.class
            );
            customerQuery.setParameter("firstName", firstName);

            // Execute the query and retrieve the result list
            customerList = customerQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with logger.error("Error retrieving customers by name", e);
        } finally {
            // Ensure the session is closed after the operation
            if (session != null) {
                session.close();
            }
        }

        return customerList; // Return the retrieved customer list
    }
    public List<Customer> getCustomerByLessThanAge(int age) {
        // Initialize the session
        Session session = sessionFactory.openSession();
        List<Customer> customerList = null;

        try {
            // Create a query to select customers with age less than the specified value
            Query customerQuery = session.createQuery(
                    "from Customer c where c.age < :age", Customer.class
            );
            customerQuery.setParameter("age", age);

            // Execute the query and retrieve the result list
            customerList = customerQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Consider replacing with logger.error("Error retrieving customers by age", e);
        } finally {
            // Ensure the session is closed after the operation
            if (session != null) {
                session.close();
            }
        }

        return customerList; // Return the retrieved customer list
    }
    public List<Customer> getCustomerOfGivenAge(int age) {
        // Initialize session
        Session session = sessionFactory.openSession();
        List<Customer> customerList = null;

        try {
            // Create HQL query to fetch customers of the specified age
            Query customerQuery = session.createQuery(
                    "from Customer c where c.age = :age", Customer.class
            );
            customerQuery.setParameter("age", age);

            // Execute the query and retrieve the results
            customerList = customerQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Replace with a logger in a production setting
        } finally {
            // Ensure the session is closed after the operation to release resources
            if (session != null) {
                session.close();
            }
        }

        return customerList; // Return the list of customers matching the age criteria
    }

}



