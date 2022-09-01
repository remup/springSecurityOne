package com.remya.springsecurity.SpringSecurityOne.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.remya.springsecurity.SpringSecurityOne.customerRepository.CustomerRepository;
import com.remya.springsecurity.SpringSecurityOne.model.Customer;
import com.remya.springsecurity.SpringSecurityOne.model.User;
import com.remya.springsecurity.SpringSecurityOne.model.UserDetails;
import com.remya.springsecurity.SpringSecurityOne.repository.LoginRepository;



@Service
public class UserDetailsServiceCustom implements UserDetailsService
{

	
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private CustomerRepository customerRepository;
	
	public List<Customer> getCutomers() {
		
		List<Customer> customers = new ArrayList<Customer>();
		  
		customerRepository.findAll().forEach(customers::add);
		// UserDetails u = new UserDetails(users.get(0));
		// System.out.println(u.getUsername()+"  "+u.getPassword()+"  "+u.isEnabled()+"  "+u.getAuthorities());
		return customers;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Optional<User> userFromDB = loginRepository.findById(username);
		System.out.println("Password is :"+userFromDB.get().getPassword());
		return userFromDB.map(UserDetails::new).get();
	}

	



}
