package com.Sofiane.Projetreseau.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/secured")
public class SecuredController {
	@GetMapping
	public String getSecuredString() {
		return "endpoint securiser";
	}

	@GetMapping("/admin")
	public String getAdminEndpoint() {
		return "endpoint admin securiser";
	}
}
