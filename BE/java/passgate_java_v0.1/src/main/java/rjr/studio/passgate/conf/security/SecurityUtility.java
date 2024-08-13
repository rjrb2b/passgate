package rjr.studio.passgate.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtility {
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SecurityUtility(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	public String passwordEncoder(String password) {
		return bCryptPasswordEncoder.encode(password);
	}

	public void passwordMatches(String username, String userPassword, String dbPassword) {
		
		if (null == userPassword || !bCryptPasswordEncoder.matches(userPassword, dbPassword)) {
			throw new BadCredentialsException("The username " + username + " and password entered are not correct");
		}
	}

}
