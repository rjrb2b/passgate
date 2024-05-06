package rjr.studio.passgate.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rjr.studio.passgate.dao.entity.UserEntity;
import rjr.studio.passgate.dao.service.UserService;

@RestController
@RequestMapping("")
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@GetMapping("")
	public String test() {
		return "Applicazione online!";
	}

	@GetMapping("/user")
	public ResponseEntity<?> findAll() throws Exception {
		List<UserEntity> entities = userService.findAll();
		return new ResponseEntity<List<UserEntity>>(entities, HttpStatus.OK);
	}

	@GetMapping("/user/byId/{id}")
	public ResponseEntity<?> findById(@PathVariable(value = "id", required = true) Integer id) throws Exception {
		UserEntity entity = userService.findById(id);
		return new ResponseEntity<UserEntity>(entity, HttpStatus.OK);
	}

	@GetMapping("/user/byUsername/{username}")
	public ResponseEntity<?> findByUsername(@PathVariable(value = "username", required = true) String username)
			throws Exception {
		UserEntity entity = userService.findByUsername(username);
		return new ResponseEntity<UserEntity>(entity, HttpStatus.OK);
	}

}
