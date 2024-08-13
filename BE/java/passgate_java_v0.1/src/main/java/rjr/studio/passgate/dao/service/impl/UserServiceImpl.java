package rjr.studio.passgate.dao.service.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.conf.security.SecurityUtility;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.repository.UserRepository;
import rjr.studio.passgate.dao.service.UserService;
import rjr.studio.passgate.utility.ObjectUtility;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final SecurityUtility securityUtility;
	

	@Autowired
	public UserServiceImpl(UserRepository userRepository, SecurityUtility securityUtility) {
		this.userRepository = userRepository;
		this.securityUtility = securityUtility;
	}

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity findById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public UserEntity save(UserEntity userEntity, String password) {

			String encodedPassword = securityUtility.passwordEncoder(password);
			userEntity.setPassword(encodedPassword);
			this.checkRoles(userEntity);
			return userRepository.save(userEntity);
			
	}

	@Override
	public UserEntity put(Integer id, UserEntity userEntity) throws InstantiationException, IllegalAccessException, Exception {

		UserEntity oldEntity = this.findById(id);
		
		UserEntity newEntity = ObjectUtility.mergeOldNew(oldEntity, userEntity);

		return userRepository.save(newEntity);
	}

	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
	
	@Override
	public void deleteByUsername(String username) {
		Integer id = this.findByUsername(username).getId();
		this.deleteById(id);
	}

	private void checkRoles(UserEntity user) {

		if (null == user.getRoles() || user.getRoles().isEmpty()) {
			user.setRoles(Collections.singleton(TypeRoleEntity.builder().code("GUEST").build()));
		}

	}

}