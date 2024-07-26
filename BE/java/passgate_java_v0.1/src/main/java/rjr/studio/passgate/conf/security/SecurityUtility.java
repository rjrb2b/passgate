package rjr.studio.passgate.conf.security;

import java.util.Objects;

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

	public void chekPassword(String username, String userPassword, String dbPassword) {

		if (null == userPassword || Objects.equals(passwordEncoder(userPassword), dbPassword)) {
			throw new BadCredentialsException("The username " + username + " and password entered are not correct");
		}
	}

	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}
}