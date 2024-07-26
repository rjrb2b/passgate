package rjr.studio.passgate.dao.service;

import java.util.List;
import rjr.studio.passgate.dao.entity.UserEntity;

public interface UserService {

	List<UserEntity> findAll();

	UserEntity findById(Integer id);

	UserEntity findByUsername(String username);

	UserEntity save(UserEntity user);

	UserEntity put(Integer id, UserEntity updateEntity) throws InstantiationException, IllegalAccessException, Exception;

	UserEntity updatePassword(String username, String oldPassword, String newPassword);

	void deleteById(Integer id);
	
	void deleteByUsername(String username);;

}