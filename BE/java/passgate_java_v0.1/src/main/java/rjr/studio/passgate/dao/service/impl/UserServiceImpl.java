package rjr.studio.passgate.dao.service.impl;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new EntityNotFoundException("User not found with username: " + username));
	}

	@Override
	public UserEntity save(UserEntity user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
			throw new DataIntegrityViolationException("Username '" + user.getUsername() + "' already exists");
		} else {
			String encodedPassword = securityUtility.passwordEncoder(user.getPassword());
			user.setPassword(encodedPassword);
			this.checkRoles(user);
			return userRepository.save(user);
		}
	}

	@Override
	public UserEntity put(Integer id, UserEntity updateEntity) throws InstantiationException, IllegalAccessException, Exception {

		UserEntity oldEntity = this.findById(id);
		
		UserEntity newEntity = ObjectUtility.mergeOldNew(oldEntity, updateEntity);

//		newEntity.setPassword(oldEntity.getPassword());
//
//		if (null == newEntity.getRoles() || newEntity.getRoles().isEmpty()) {
//			newEntity.setRoles(oldEntity.getRoles());
//		}

		return userRepository.save(newEntity);
	}

	@Override
	public UserEntity updatePassword(String username, String oldPassword, String newPassword) {

		UserEntity userEntity = this.findByUsername(username); 
		
		securityUtility.chekPassword(username, oldPassword, userEntity.getPassword());

		userEntity.setPassword(securityUtility.passwordEncoder(newPassword));

		return userRepository.save(userEntity);
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