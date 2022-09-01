package com.remya.springsecurity.SpringSecurityOne.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "[User]")
public class User {

	@Id
	@Column(name="user_name")
	private String user_name;
	@Column(name="password")
	private String password;
	@Column(name="active")
	private String active;
	@Column(name="authorities")
	private String authorities;
	
	public User() {
		super();
	}
	public User(String userName, String password, String active, String authorities) {
		super();
		this.user_name = userName;
		this.password = password;
		this.active = active;
		this.authorities = authorities;
	}
	
	public String getUserName() {
		return user_name;
	}
	public void setUserName(String userName) {
		this.user_name = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getAuthorities() {
		return authorities;
	}
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}
	
	public boolean isActive()
	{
		if(this.active.equalsIgnoreCase("true"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}
