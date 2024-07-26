package rjr.studio.passgate.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import rjr.studio.passgate.api.view.model.User;

@RequestMapping("/user")
public interface UserController {

	@GetMapping("")
	ResponseEntity<List<User>> findAll() throws Exception;

	@GetMapping("/byId/{id}")
	ResponseEntity<User> findById(@PathVariable(value = "id", required = true) Integer id) throws Exception;

	@GetMapping("/byUsername/{username}")
	ResponseEntity<User> findByUsername(@PathVariable(value = "username", required = true) String username)
			throws Exception;

	@PostMapping("")
	ResponseEntity<User> save(@RequestBody(required = true) User user) throws Exception;

	@PutMapping("/{id}")
	ResponseEntity<User> put(@PathVariable(value = "id", required = true) Integer id,
			@RequestBody(required = true) User user) throws Exception;

	@DeleteMapping("/byId/{id}")
	ResponseEntity<Boolean> deleteById(@PathVariable(value = "id", required = true) Integer id) throws Exception;

	@DeleteMapping("/byUsername/{username}")
	ResponseEntity<Boolean> deleteByUsername(@PathVariable(value = "username", required = true) String username)
			throws Exception;

}
