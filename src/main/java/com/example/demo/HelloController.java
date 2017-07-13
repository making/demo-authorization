package com.example.demo;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/")
	public String hello(@AuthenticationPrincipal UserDetails userDetails) {
		return "Hello " + userDetails.getUsername();
	}

	@RequestMapping("/foo/{id}")
	public String foo(@PathVariable String id) {
		return "foo " + id;
	}

	@RequestMapping("/bar/{id}")
	public String bar(@PathVariable String id) {
		return "bar " + id;
	}
}
