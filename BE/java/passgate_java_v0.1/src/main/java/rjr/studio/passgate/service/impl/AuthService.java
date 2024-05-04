package rjr.studio.passgate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.api.view.LoginRequest;
import rjr.studio.passgate.conf.security.AIJwtTokenProvider;

@Service
public class AuthService {

	private final AuthenticationManager authenticationManager;

	private final AIJwtTokenProvider tokenProvider;

	@Autowired
	public AuthService(AuthenticationManager authenticationManager, AIJwtTokenProvider tokenProvider) {
		super();
		this.authenticationManager = authenticationManager;
		this.tokenProvider = tokenProvider;
	}

	public String authenticate(LoginRequest loginRequest) throws Exception {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		return tokenProvider.createToken(loginRequest.getUsername());
	}
}
