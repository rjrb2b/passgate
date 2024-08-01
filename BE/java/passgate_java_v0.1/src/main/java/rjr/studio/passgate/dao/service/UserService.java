package rjr.studio.passgate.dao.service;

import java.util.List;
import rjr.studio.passgate.dao.entity.UserEntity;

public interface UserService {

	List<UserEntity> findAll();

	UserEntity findById(Integer id);

	UserEntity findByUsername(String username);

	UserEntity save(UserEntity userEntity, String password);

	UserEntity put(Integer id, UserEntity userEntity) throws InstantiationException, IllegalAccessException, Exception;

	void deleteById(Integer id);
	
	void deleteByUsername(String username);

}