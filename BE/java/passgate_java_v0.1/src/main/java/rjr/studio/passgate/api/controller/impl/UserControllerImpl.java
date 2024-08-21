package rjr.studio.passgate.api.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import rjr.studio.passgate.api.controller.UserController;
import rjr.studio.passgate.api.view.User;
import rjr.studio.passgate.business.UserBusiness;

@RestController
public class UserControllerImpl implements UserController {

	private final UserBusiness userBusiness;

	@Autowired
	public UserControllerImpl(UserBusiness userBusiness) {
		this.userBusiness = userBusiness;
	}

	@Override
	public ResponseEntity<List<User>> findAll() throws Exception {
		List<User> users = userBusiness.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> findById(Integer id) throws Exception {
		User user = userBusiness.findById(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> findByUsername(String username) throws Exception {
		User user = userBusiness.findByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<User> save(User user) throws Exception {
		User savedUser = userBusiness.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<User> put(Integer id, User user) throws Exception {
		User updateUser = userBusiness.put(id, user);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> deleteById(Integer id) throws Exception {
		userBusiness.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Boolean> deleteByUsername(String username) throws Exception {
		userBusiness.deleteByUsername(username);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
