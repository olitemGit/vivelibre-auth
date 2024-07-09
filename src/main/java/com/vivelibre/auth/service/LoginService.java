package com.vivelibre.auth.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vivelibre.auth.dto.LoginDataDTO;

import net.minidev.json.JSONObject;

@Service
public class LoginService {
		
	@Autowired
	private RestTemplate restTemplate;
	
	public String getLoginToken(LoginDataDTO data) throws Exception {

		URI uri =  UriComponentsBuilder.fromHttpUrl("http://localhost:8080").path("/token").build().toUri();
				
		RequestEntity<?> request = buildRequest(uri, data.getUserName(), data.getPassword());
		ResponseEntity<String> responseEntity = null;
		responseEntity = restTemplate.exchange(request, String.class);
	
		return responseEntity.getBody();
	}
	

	private RequestEntity<?> buildRequest(URI uri, String username, String password) {

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
		JSONObject body = new JSONObject();
		body.put("username", username);
		body.put("password", password);
		return new RequestEntity<>(body, headers, HttpMethod.POST, uri);
	}
}
