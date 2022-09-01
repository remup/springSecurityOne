package com.remya.springsecurity.SpringSecurityOne.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@SuppressWarnings("serial")
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

	
	private String username;
	private String password;
	private boolean active;
	private List<GrantedAuthority> authorities;
	
	public UserDetails() {
		
	}
	
	public UserDetails(User user) {
		this.username=user.getUserName();
		this.password=user.getPassword();
		this.active=user.isActive();
		this.authorities=Arrays.asList(user.getAuthorities().split(",")).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return password;
	}

	@Override
	public String getUsername() {
		
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return active;
	}

}
