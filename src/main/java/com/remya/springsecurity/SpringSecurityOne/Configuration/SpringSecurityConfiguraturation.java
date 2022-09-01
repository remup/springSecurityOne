package com.remya.springsecurity.SpringSecurityOne.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import com.remya.springsecurity.SpringSecurityOne.filters.JwtRequestFilter;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, 
 securedEnabled = true, 
 jsr250Enabled = true)
@EnableWebSecurity
public class SpringSecurityConfiguraturation extends WebSecurityConfigurerAdapter {

	@Autowired
    private MyBasicAuthenticationEntryPoint authenticationEntryPoint;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	
	 @Override
	    protected void configure(AuthenticationManagerBuilder auth)
	      throws Exception {
//	        auth
//	          .inMemoryAuthentication()
//	          .withUser("user")
//	            .password(passwordEncoder().encode("user"))
//	            .roles("USER")
//	            .and()
//	          .withUser("admin")
//	            .password(passwordEncoder().encode("admin"))
//	            .roles("USER", "ADMIN");
		 
		 auth.userDetailsService(userDetailsService);
		 //.passwordEncoder(passwordEncoder());
	    }
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        http
	        .authorizeRequests().antMatchers("/authenticate").permitAll()
	          //.antMatchers("/getData/**").hasRole("ADMIN")
	            .anyRequest().authenticated()
	            .and().
				exceptionHandling()
	          .and()
	          .httpBasic()
	          .authenticationEntryPoint(authenticationEntryPoint)
	          .and()
	          .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	          .and()
	          .sessionManagement().maximumSessions(2);
	        http.csrf().disable();
	        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	        
	    }
	    
	    @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	    
	    @Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}
	    
	    @Bean
	    public HttpSessionEventPublisher httpSessionEventPublisher() {
	        return new HttpSessionEventPublisher();
	    }
	    
//	    private static final String[] AUTH_WHITELIST = {
//	            "/swagger-resources/**",
//	            "/swagger-ui.html",
//	            "/v2/api-docs",
//	            "/webjars/**"
//	    };

	    @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v2/api-docs",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html",
	                                   "/webjars/**");
	    }

}
