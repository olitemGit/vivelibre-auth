package com.vivelibre.auth.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.vivelibre.auth.dto.LoginDataDTO;
import com.vivelibre.auth.service.LoginService;

@RestController
@RequestMapping(path = "/get-token")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

	@Autowired
    private LoginService loginService;

    @GetMapping("")
    public ResponseEntity<String> getToken(@RequestBody LoginDataDTO body)
    {
        try {
            return ResponseEntity.ok().body(loginService.getLoginToken(body));
        } catch (HttpClientErrorException e) {
        	LOGGER.error("User unauthorized", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User unauthorized");
        } catch (Exception ex) {
            LOGGER.error("Error getting the user", ex);
            return ResponseEntity.badRequest().build();
        }
    }
    
}

