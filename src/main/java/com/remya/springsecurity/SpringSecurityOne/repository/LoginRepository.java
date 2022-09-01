package com.remya.springsecurity.SpringSecurityOne.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.remya.springsecurity.SpringSecurityOne.model.User;

@Repository
public interface LoginRepository extends JpaRepository<User,String> {

	//List<User> findByUserName(String s);
	
	
}
