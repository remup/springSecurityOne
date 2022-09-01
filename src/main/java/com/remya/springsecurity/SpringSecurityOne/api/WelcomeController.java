package com.remya.springsecurity.SpringSecurityOne.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.remya.springsecurity.SpringSecurityOne.model.AuthenticationRequest;
import com.remya.springsecurity.SpringSecurityOne.model.AuthenticationResponse;
import com.remya.springsecurity.SpringSecurityOne.model.Customer;
import com.remya.springsecurity.SpringSecurityOne.model.User;
import com.remya.springsecurity.SpringSecurityOne.model.UserDetails;
import com.remya.springsecurity.SpringSecurityOne.service.UserDetailsServiceCustom;
import com.remya.springsecurity.SpringSecurityOne.utils.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
//@Api(value="Customer Management System", description="Operations pertaining to employee in Customer Management System")
public class WelcomeController {

	@Autowired
	private UserDetailsServiceCustom service;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtTokenUtil;

	//@ApiOperation(value = "View a list of available customers", response = List.class)
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/test/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Customer>> putAndGetData(@PathVariable("id") String id)

	{
		List<Customer> customerList = new ArrayList<Customer>();
		HttpHeaders headers = new HttpHeaders();
		headers.add("DataPrsent", "yes");
		customerList=service.getCutomers();
		//Optional<User> u =service.getCutomers();
		return ResponseEntity.ok().headers(headers).body(customerList);
	}
	
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		final Authentication auth;
		try {
			 auth =authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
					);
		}
		catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
			//System.out.println("bad credentials");
		}

		/* below both options work for getting Userdetails */
//		final UserDetails userDetails = service
//				.loadUserByUsername(authenticationRequest.getUsername());
		final UserDetails userDetails = (UserDetails) auth.getPrincipal();
		System.out.println(userDetails.getUsername()+"  "+userDetails.getPassword());
		final String jwt = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}


}
