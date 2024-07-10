package rjr.studio.passgate.dao.service;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.conf.security.SecurityUtility;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.entity.type.TypeRoleEntity;
import rjr.studio.passgate.dao.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final SecurityUtility securityUtility;
	

	@Autowired
	public UserService(UserRepository userRepository, SecurityUtility securityUtility) {
		super();
		this.userRepository = userRepository;
		this.securityUtility = securityUtility;
	}

	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	public UserEntity findById(Integer id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
	}

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

	public UserEntity put(Integer id, UserEntity user) {

		UserEntity oldEntity = this.findById(id);

		user.setPassword(oldEntity.getPassword());

		if (null == user.getRoles() || user.getRoles().isEmpty()) {
			user.setRoles(oldEntity.getRoles());
		}

		return userRepository.save(user);
	}

	public UserEntity updatePassword(String username, String oldPassword, String newPassword) {

		UserEntity userEntity = this.findByUsername(username); 
		
		securityUtility.chekPassword(username, oldPassword, userEntity.getPassword());

		userEntity.setPassword(securityUtility.passwordEncoder(newPassword));

		return userRepository.save(userEntity);
	}

	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}
	
	public void deleteByUsername(String username) {
		Integer id = this.findByUsername(username).getId();
		userRepository.deleteById(id);
	}

	private void checkRoles(UserEntity user) {

		if (null == user.getRoles() || user.getRoles().isEmpty()) {
			user.setRoles(Collections.singleton(TypeRoleEntity.builder().code("GUEST").build()));
		}

	}

}