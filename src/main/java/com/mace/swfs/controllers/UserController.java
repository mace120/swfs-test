package com.mace.swfs.controllers;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mace.swfs.commands.user.UserCreateCommand;
import com.mace.swfs.commands.user.UserUpdateCommand;
import com.mace.swfs.controllers.response.ResponseWrapper;
import com.mace.swfs.services.UserService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/")
public class UserController {

	UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/v1/users")
	public ResponseEntity<ResponseWrapper> getAllUsersV1() {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.getAllUsers()).build());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/users")
	public ResponseEntity<ResponseWrapper> getAllUsers() {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.getAllUsers()).build());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/user/{uuid}")
	public ResponseEntity<ResponseWrapper> getUserByUuid(@PathVariable String uuid) {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.getUserByUuid(uuid)).build());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public ResponseEntity<ResponseWrapper> saveUser(@RequestBody @Valid UserCreateCommand user) {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.saveUser(user)).build());
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/user/{id}")
	public ResponseEntity<ResponseWrapper> updateById(@PathVariable Long id,
			@RequestBody @Valid UserUpdateCommand user) {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.updateById(id, user)).build());
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
	public ResponseEntity<ResponseWrapper> deleteUser(@PathVariable Long id) {
		return ResponseEntity.ok(ResponseWrapper.builder().data(userService.deleteUserById(id)).build());
	}
}
