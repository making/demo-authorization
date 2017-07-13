package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	final PermissionEvaluator permissionEvaluator;

	public SecurityConfig(PermissionEvaluator permissionEvaluator) {
		this.permissionEvaluator = permissionEvaluator;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permissionEvaluator);

		http.authorizeRequests() //
				.expressionHandler(expressionHandler) //
				.mvcMatchers(HttpMethod.GET, "/foo/{id}/**")
				.access("hasPermission(#id, 'Foo', 'read')") //
				.mvcMatchers(HttpMethod.POST, "/foo/{id}/**")
				.access("hasPermission(#id, 'Foo', 'write')") //
				.mvcMatchers(HttpMethod.GET, "/bar/{id}/**")
				.access("hasPermission(#id, 'Bar', 'read')") //
				.mvcMatchers(HttpMethod.POST, "/bar/{id}/**")
				.access("hasPermission(#id, 'Bar', 'write')") //
				.anyRequest().denyAll() //
				.and() //
				.csrf().disable().httpBasic();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication() //
				.withUser("user1").password("password").roles("USER")//
				.and() //
				.withUser("user2").password("password").roles("USER", "ADMIN");
	}
}
