package com.example.demo;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationService {
	private static final Logger log = LoggerFactory.getLogger(AuthorizationService.class);

	public boolean isAuthorized(String id, UserDetails userDetails) {
		log.info("Authorize {}, {}", id, userDetails);
		return Objects.equals(id, userDetails.getUsername());
	}
}
