package org.jsp.cda.controller;

import org.jsp.cda.dto.AuthUser;
import org.jsp.cda.entity.User;
import org.jsp.cda.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@Operation(summary = "This API will do LOgin Validation", description = "This API will do Login Validation by accepting a request body i.e., AuthUser which has email and password as states")

	@PostMapping("/login")
	public ResponseEntity<?> findByUsernameAndPassword(@RequestBody AuthUser authuser) {
		return service.findByUsernameAndPassword(authuser);
	}

	@PostMapping
	public ResponseEntity<?> saveUser(@RequestBody User user) {
		return service.saveUser(user);
	}

	@Operation(summary = "This API wiil find a user by Id", description = "This API will find a user by ID which we are using in the JSON object")

	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "User Found Successfully") })

	@GetMapping("/{id}")
	public ResponseEntity<?> findUserById(@PathVariable int id) {
		return service.findUserById(id);
	}

	@GetMapping
	public ResponseEntity<?> findAllUsers() {
		return service.findAllUser();
	}

	@PatchMapping("{id}/otp/{otp}")
	public ResponseEntity<?> verifyOtp(@PathVariable int id, @PathVariable int otp) {
		return service.verifyOtp(id, otp);

	}

	@GetMapping("/email/{email}")
	public ResponseEntity<?> findUserByEmail(@PathVariable String email) {
		return service.findUserByEmail(email);
	}

}
