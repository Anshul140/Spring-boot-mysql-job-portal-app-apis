package com.anshul.jobportal.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.anshul.jobportal.repositories.CandidateRepository;
import com.anshul.jobportal.repositories.RecruiterRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
	
	@Configuration
	@RequiredArgsConstructor
	@Order(2)
	public class ApplicationConfigCand {
		private final CandidateRepository candidateRepository;
		
		@Bean
		public UserDetailsService userDetailsService() {
		    return username -> candidateRepository.findByEmail(username)
		        .orElseThrow(() -> new UsernameNotFoundException("Candidate not found"));
		}
		
		@Bean
		public AuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		    authProvider.setUserDetailsService(userDetailsService());
		    authProvider.setPasswordEncoder(passwordEncoder());
		    return authProvider;
		}
	}
	
	@Configuration
	@RequiredArgsConstructor
	@Order(1)
	public class ApplicationConfigRecr {
		private final RecruiterRepository recruiterRepository;
		
		@Bean
		public UserDetailsService userDetailsService() {
		    return username -> recruiterRepository.findByEmail(username)
		        .orElseThrow(() -> new UsernameNotFoundException("Recruiter not found"));
		}
		  
		@Bean
		public AuthenticationProvider authenticationProvider() {
		    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		    authProvider.setUserDetailsService(userDetailsService());
		    authProvider.setPasswordEncoder(passwordEncoder());
		    return authProvider;
		}
	}

	@Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	 }
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	 }
}
