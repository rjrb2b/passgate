package rjr.studio.passgate.dao.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserEntity> findAll() {
		List<UserEntity> entities = userRepository.findAll();
		return entities;
	}

	public UserEntity findById(Integer id) {
		UserEntity entity = userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Autore con ID " + id + "non trovato"));
		return entity;
	}

	public UserEntity findByUsername(String username) {
		UserEntity entity = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		return entity;
	}

}