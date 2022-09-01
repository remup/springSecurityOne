package com.remya.springsecurity.SpringSecurityOne.customerRepository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.remya.springsecurity.SpringSecurityOne.model.Customer;

@Repository
public interface CustomerRepository extends  CrudRepository<Customer,Integer> {

}
