package rjr.studio.passgate.dao.service;

import rjr.studio.passgate.dao.entity.UserEntity;

public interface LoginService {

	UserEntity passwordMatch(String username, String password);
	
	UserEntity passwordChange(String username, String password, String newPassword);
	
}
