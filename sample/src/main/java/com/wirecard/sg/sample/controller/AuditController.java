package com.wirecard.sg.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.sg.sample.dto.AuditLog;
import com.wirecard.sg.sample.model.ResponsePayload;
import com.wirecard.sg.sample.service.impl.AuditService;

@RestController
@RequestMapping("/audit")
public class AuditController {
	@Autowired
	AuditService service;

	// Get Audit by specific user
	@GetMapping(value = "/")
	public ResponsePayload<AuditLog> get(Authentication authentication) {
		return service.get(authentication);
	}

	// All Audit Log for Admin Role user
	@GetMapping(value = "/all/")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponsePayload<AuditLog> getAll() {
		return service.getAll();
	}
}
