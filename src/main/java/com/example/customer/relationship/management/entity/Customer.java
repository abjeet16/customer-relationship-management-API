package com.example.customer.relationship.management.entity;

import jakarta.persistence.*;

// The @Entity annotation specifies that the class is an entity and is mapped to a database table.
@Entity
// The @Table annotation specifies the name of the table in the database that this entity maps to.
@Table(name = "customers")
public class Customer {

    // The @Id annotation specifies the primary key of an entity.
    // The @GeneratedValue annotation provides the specification of generation strategies for the primary key values.
    // GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity using a database identity column.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // The @Column annotation is used to specify the mapped column for a persistent property or field.
    // If the @Column annotation is not specified, the default values are applied.
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;

    private String password;
    private String gender;

    // This field does not have a @Column annotation, so it will be mapped to a column named 'age' by default.
    private int age;

    // Constructor with all fields to initialize the entity with data.
    public Customer(int id, String firstName, String lastName, String email, String mobileNumber, String password, String gender, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.password = password;
        this.gender = gender;
        this.age = age;
    }

    // Default no-argument constructor is required by JPA.
    // JPA uses this constructor to create an instance of the entity class when fetching data from the database.
    public Customer(){
        super();
    }

    // Getters and setters for all fields.
    // These are required for JPA to access the private fields and interact with the database.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}

