package rjr.studio.passgate.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.model.LoginRequest;
import rjr.studio.passgate.business.LoginBusiness;
import rjr.studio.passgate.dao.service.LoginService;

@Component
public class LoginBusinessImpl implements LoginBusiness {
	
	private LoginService loginService;
	
	@Autowired
	public LoginBusinessImpl(LoginService loginService) {
		this.loginService = loginService;
	}

	@Override
	public void passwordMatch(LoginRequest loginRequest) {
		loginService.passwordMatch(loginRequest.getUsername(), loginRequest.getPassword());
	}

	@Override
	public void passwordChange(LoginRequest loginRequest) {
		loginService.passwordChange(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getNewPassword());
		
	}

}
