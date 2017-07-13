package com.example.demo;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DemoPermissionEvaluator implements PermissionEvaluator {
	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject,
			Object permission) {
		return hasPermission(authentication, "FIXME",
				targetDomainObject.getClass().getSimpleName(), permission);
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId,
			String targetType, Object permission) {
		System.out.println("hasPermission(" + authentication + ", " + targetId + ", "
				+ targetType + ", " + permission + ")");
		switch (targetType) {
		case "Foo":
			return checkFoo(authentication, (String) permission);
		case "Bar":
			return checkBar(authentication, (String) permission);
		}
		return false;
	}

	private boolean checkFoo(Authentication authentication, String permission) {
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		switch (permission) {
		case "read":
			return authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
		case "write":
			return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return false;
	}

	private boolean checkBar(Authentication authentication, String permission) {
		Collection<? extends GrantedAuthority> authorities = authentication
				.getAuthorities();
		switch (permission) {
		case "read":
			return authorities.contains(new SimpleGrantedAuthority("ROLE_USER"));
		case "write":
			return authorities.contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		return false;
	}

}
