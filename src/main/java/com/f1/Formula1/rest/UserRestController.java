package com.f1.Formula1.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.f1.Formula1.model.User;
import com.f1.Formula1.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/") // Defines the base URL for all routes within this controller
public class UserRestController {

	@Autowired // Spring automatically inject an instance of the object
	private UserService userService;

	private String path = "/users/";

	/*
	 * Get Users
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.getAll();

		if (users.isEmpty()) {
			return ResponseEntity.noContent().header("message", "No users found").build();
		}

		return ResponseEntity.ok(users);
	}

	/*
	 * Get User by Id
	 */
	@GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	// @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> getUserById(@PathVariable Long id) {
		User user = userService.getById(id);

		if (user == null) {
			return ResponseEntity.noContent().header("message", "User not found with id: " + id).build();
		}

		return ResponseEntity.ok(user);
	}

	/*
	 * Create User
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		try {
			User userSaved = userService.create(user);
			URI uri = new URI(path.concat(userSaved.getId().toString()));

			return ResponseEntity.created(uri).body(userSaved);
			// return ResponseEntity.ok(user);
		} catch (URISyntaxException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	/*
	 * Update User
	 */
	@PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUserById(@PathVariable long id, @Valid @RequestBody User user) {
		User updatedUser = userService.update(user);

		if (updatedUser == null) {
			return ResponseEntity.notFound().header("message", "User not found with id: " + id).build();
		}
		return ResponseEntity.ok(updatedUser);
	}

	/*
	 * Delete User
	 */
	@DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	private ResponseEntity<User> deleteUser(@PathVariable Long id) {
		User userDeleted = userService.delete(id);

		if (userDeleted == null) {
			return ResponseEntity.notFound().header("message", "User not found with id: " + id).build();
		}

		return ResponseEntity.ok(userDeleted);
	}
}
