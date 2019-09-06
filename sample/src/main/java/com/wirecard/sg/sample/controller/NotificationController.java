package com.wirecard.sg.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wirecard.sg.sample.model.FirebaseResponse;
import com.wirecard.sg.sample.model.Push;
import com.wirecard.sg.sample.service.impl.FirebaseNotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	@Autowired
	FirebaseNotificationService service;

	public FirebaseResponse send(@RequestBody Push push) {
		return service.sendNotification(push);
	}
}
