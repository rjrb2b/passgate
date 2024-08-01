package rjr.studio.passgate.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.LoginController;
import rjr.studio.passgate.api.view.model.LoginRequest;
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
		loginBusiness.passwordMatch(loginRequest);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> passwordChange(LoginRequest loginRequest) {
		loginBusiness.passwordChange(loginRequest);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
