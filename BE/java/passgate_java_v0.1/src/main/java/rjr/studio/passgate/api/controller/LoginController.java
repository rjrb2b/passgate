package rjr.studio.passgate.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import rjr.studio.passgate.api.view.model.LoginRequest;

@RequestMapping("/login")
public interface LoginController {

	@PostMapping("/match")
	ResponseEntity<Boolean> passwordMatch(@RequestBody(required = true) LoginRequest loginrRequest);
	
	@PostMapping("/change")
	ResponseEntity<Boolean> passwordChange(HttpServletRequest request, @RequestBody(required = true) LoginRequest loginrRequest);
	
}
