package rjr.studio.passgate.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("")
	public ResponseEntity<List<UserEntity>> findAll() throws Exception {
		List<UserEntity> entities = userService.findAll();
		return new ResponseEntity<>(entities, HttpStatus.OK);
	}

	@GetMapping("/byId/{id}")
	public ResponseEntity<UserEntity> findById(@PathVariable(value = "id", required = true) Integer id)
			throws Exception {
		UserEntity entity = userService.findById(id);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@GetMapping("/byUsername/{username}")
	public ResponseEntity<UserEntity> findByUsername(@PathVariable(value = "username", required = true) String username)
			throws Exception {
		UserEntity entity = userService.findByUsername(username);
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<UserEntity> registerUser(@RequestBody(required = true) UserEntity user) throws Exception {
		UserEntity savedUser = userService.save(user);
		return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserEntity> updateUser(@PathVariable(value = "id", required = true) Integer id,
			@RequestBody(required = true) UserEntity updateEntity) throws Exception {
		UserEntity updateUser = userService.put(id, updateEntity);
		return new ResponseEntity<>(updateUser, HttpStatus.OK);
	}

	@DeleteMapping("/byId/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id", required = true) Integer id)
			throws Exception {
		userService.deleteById(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@DeleteMapping("/byUsername/{username}")
	public ResponseEntity<Boolean> deleteByUsername(@PathVariable(value = "username", required = true) String username)
			throws Exception {
		userService.deleteByUsername(username);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

}
