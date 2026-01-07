package com.learn.SpareZone.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.SpareZone.Dtos.UserDto;
import com.learn.SpareZone.Repositories.UserRepository;
import com.learn.SpareZone.Service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
@Autowired
	private UserService userService;

@Autowired

private UserRepository userRepository;
@PostMapping("/register")
 public ResponseEntity<UserDto> register(@RequestBody  @Valid UserDto  userDto){
	 UserDto savedDto = userService.createUser(userDto);
	 return new ResponseEntity<UserDto>(savedDto,HttpStatus.OK);
 }
 public boolean checkEmail(@PathVariable String email) {
	return userRepository.existsByEmail(email);
	 }
}
