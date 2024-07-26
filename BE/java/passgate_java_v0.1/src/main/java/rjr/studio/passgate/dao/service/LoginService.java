package rjr.studio.passgate.dao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.conf.security.SecurityUtility;
import rjr.studio.passgate.dao.entity.UserEntity;

@Service
public class LoginService {
	
	private final UserService userService;
	private final SecurityUtility securityUtility;
	
	@Autowired
	public LoginService(UserService userService, SecurityUtility securityUtility) {
		this.userService = userService;
		this.securityUtility = securityUtility;
	}
	
	public UserEntity chekPassword(String username, String password) {

		UserEntity userEntity = userService.findByUsername(username);

		securityUtility.chekPassword(username, password, userEntity.getPassword());

		return userEntity;
	}

}
