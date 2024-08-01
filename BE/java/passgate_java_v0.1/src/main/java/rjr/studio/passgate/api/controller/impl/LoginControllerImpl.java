package rjr.studio.passgate.api.controller.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.LoginController;
import rjr.studio.passgate.api.view.model.LoginRequest;
import rjr.studio.passgate.business.LoginBusiness;
import rjr.studio.passgate.conf.security.SecurityJwtToken;

@RestController
public class LoginControllerImpl implements LoginController {
	
	private LoginBusiness loginBusiness;
	private SecurityJwtToken securityJwtToken;
	
	@Autowired
	public LoginControllerImpl(SecurityJwtToken securityJwtToken, LoginBusiness loginBusiness) {
		this.securityJwtToken = securityJwtToken;
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
		String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        if (!securityJwtToken.jwtTokenValidate(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }
        
		loginBusiness.passwordChange(loginRequest);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
