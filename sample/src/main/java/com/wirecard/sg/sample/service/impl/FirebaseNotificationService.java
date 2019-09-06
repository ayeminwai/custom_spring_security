package com.wirecard.sg.sample.service.impl;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wirecard.sg.sample.interceptor.HeaderRequestInterceptor;
import com.wirecard.sg.sample.model.FirebaseResponse;
import com.wirecard.sg.sample.model.Push;

@Service
public class FirebaseNotificationService {
	@Value("${my.fcm.key}")
	private String fcmKey;
	@Value("${my.fcm.package.name}")
	private String packageName;

	private static final String FCM_API = "https://fcm.googleapis.com/fcm/send";

	public FirebaseResponse sendNotification(Push push) {
		HttpEntity<Push> request = new HttpEntity<>(push);
		CompletableFuture<FirebaseResponse> pushNotification = this.send(request);
		CompletableFuture.allOf(pushNotification).join();
		FirebaseResponse firebaseResponse = null;
		try {
			firebaseResponse = pushNotification.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return firebaseResponse;
	}

	@Async
	private CompletableFuture<FirebaseResponse> send(HttpEntity<Push> entity) {
		RestTemplate restTemplate = new RestTemplate();
		ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + fcmKey));
		interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
		restTemplate.setInterceptors(interceptors);
		FirebaseResponse firebaseResponse = restTemplate.postForObject(FCM_API, entity, FirebaseResponse.class);
		return CompletableFuture.completedFuture(firebaseResponse);
	}
}
