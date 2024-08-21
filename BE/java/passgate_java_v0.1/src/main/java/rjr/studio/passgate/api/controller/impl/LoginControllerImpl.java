package rjr.studio.passgate.api.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.LoginController;
import rjr.studio.passgate.api.view.LoginRequest;
import rjr.studio.passgate.business.LoginBusiness;

@RestController
public class LoginControllerImpl implements LoginController {
	
	private LoginBusiness loginBusiness;
	
	@Autowired
	public LoginControllerImpl(LoginBusiness loginBusiness) {
		this.loginBusiness = loginBusiness;
	}

	@Override
	public ResponseEntity<Boolean> passwordMatch(LoginRequest loginRequest) {
		HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(loginBusiness.passwordMatch(loginRequest));
		return new ResponseEntity<>(true, headers, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> passwordChange(HttpServletRequest request, LoginRequest loginRequest) {
		loginBusiness.passwordChange(loginRequest);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
