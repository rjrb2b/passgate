package rjr.studio.passgate.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecurityUtility {

	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public SecurityUtility(BCryptPasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}

	public void passwordMatches(String username, String userPassword, String dbPassword) {
		
		if (null == userPassword || !passwordEncoder.matches(userPassword, dbPassword)) {
			throw new BadCredentialsException("The username " + username + " and password entered are not correct");
		}
	}


}