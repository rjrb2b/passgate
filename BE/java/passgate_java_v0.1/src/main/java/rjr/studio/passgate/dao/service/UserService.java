package rjr.studio.passgate.dao.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.repository.UserRepository;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	@Autowired
	public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
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
				.orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + username));
	}

	public UserEntity save(UserEntity user) {
		if (userRepository.findByUsername(user.getUsername()).isPresent())
		{
			throw new DataIntegrityViolationException("Username '" + user.getUsername() + "' already exists");
		}
		else {
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        user.setPassword(encodedPassword);
	        return userRepository.save(user);
		}
	}

}