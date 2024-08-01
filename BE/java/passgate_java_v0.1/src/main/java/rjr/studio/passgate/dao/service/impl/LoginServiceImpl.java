package rjr.studio.passgate.dao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.conf.security.SecurityUtility;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.service.LoginService;
import rjr.studio.passgate.dao.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {
	
	private final UserService userService;
	private final SecurityUtility securityUtility;
	
	
	@Autowired
	public LoginServiceImpl(UserService userService, SecurityUtility securityUtility) {
		this.userService = userService;
		this.securityUtility = securityUtility;
	}
	
	public UserEntity passwordMatch(String username, String password) {
		
		UserEntity userEntity = userService.findByUsername(username);
		
		securityUtility.passwordMatches(username, password, userEntity.getPassword());
		
		return userEntity;
	}

	@Override
	public UserEntity passwordChange(String username, String password, String newPassword) {
		
		UserEntity userEntity = this.passwordMatch(username, password);

		return userService.save(userEntity, newPassword);
	}

}
