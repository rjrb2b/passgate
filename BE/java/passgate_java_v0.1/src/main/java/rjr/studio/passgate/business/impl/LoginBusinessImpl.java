package rjr.studio.passgate.business.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rjr.studio.passgate.api.view.model.LoginRequest;
import rjr.studio.passgate.business.LoginBusiness;
import rjr.studio.passgate.conf.security.JwtTokenProvider;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.service.LoginService;

@Component
public class LoginBusinessImpl implements LoginBusiness {
	
	private LoginService loginService;
	private final JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	public LoginBusinessImpl(LoginService loginService, JwtTokenProvider jwtTokenProvider) {
		this.loginService = loginService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	public String passwordMatch(LoginRequest loginRequest) {
		
		UserEntity userEntity = loginService.passwordMatch(loginRequest.getUsername(), loginRequest.getPassword());
		
		return jwtTokenProvider.generateToken(userEntity);
		
	}

	@Override
	public void passwordChange(LoginRequest loginRequest) {
		loginService.passwordChange(loginRequest.getUsername(), loginRequest.getPassword(), loginRequest.getNewPassword());
		
	}

}
