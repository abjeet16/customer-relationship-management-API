package com.example.customer.relationship.management.controller;

import com.example.customer.relationship.management.entity.Customer;
import com.example.customer.relationship.management.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Marks the class as a REST controller, which means it can handle HTTP requests.
@RestController
// Specifies the base URL path for all request mappings in this controller.
@RequestMapping("/api/customers")
public class CustomerController {

    // Declares a dependency on CustomerService.
    private final CustomerService customerService;

    // Constructor for dependency injection of CustomerService.
    @Autowired  // Optional: @Autowired can be omitted on constructor-based injection from Spring 4.3 onwards.
    public CustomerController(CustomerService customerService){
        super();
        this.customerService = customerService;
    }

    // Handles HTTP POST requests to /api/customers/insert for inserting a new customer.
    @PostMapping("/insert")
    public String insertCustomer(@RequestBody Customer customer){
        // Delegates the insertion of a customer to the service layer.
        return customerService.insertCustomer(customer);
    }

    // Handles HTTP GET requests to /api/customers for retrieving the list of customers.
    @GetMapping("/viewALlCustomer")
    public List<Customer> getCustomerList(){
        return customerService.getCustomerList();
    }
    @GetMapping("/{id}")
    //@PathVariable takes the variable value passed in the path
    public Customer getCustomerByID(@PathVariable int id){
        return customerService.getCustomerByID(id);
    }
    @PostMapping("/insertMultipleCustomer")
    public String insertMultipleCustomer(@RequestBody List<Customer> customerList){
        return customerService.insertMultipleCustomers(customerList);
    }
    @PutMapping("/updateCustomerDetail")
    public String updateCustomerDetail(@RequestBody Customer customer){
        return customerService.updateCustomerDetails(customer);
    }
    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomerByID(@PathVariable int id){
        return customerService.deleteCustomerByID(id);
    }
    @GetMapping("/getUsersByFirstName/{firstName}")
    public List<Customer> getCustomerListByFirstName(@PathVariable String firstName){
        return customerService.getCustomerByFirstName(firstName);
    }
    @GetMapping("/getALlCustomerLessThen/{age}")
    public List<Customer> getCustomerLessThen(@PathVariable int age){
        return customerService.getCustomerByLessAge(age);
    }
    @GetMapping("/getCustomersOfAge/{age}")
    public List<Customer> getCustomerOfAge(@PathVariable int age){
        return customerService.getCustomerByAge(age);
    }
}


