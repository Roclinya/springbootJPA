package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.entity.dto.UserModel;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegisterController {
@Autowired
private PasswordEncoder passwordEncoder;

@Autowired
	UserRepository userRepository;

	@PostMapping("/register")
	public ResponseEntity<User> getAccountTypeByNumber(@RequestBody UserModel userModel) {
     User user =new User();
		user.setEmail(userModel.getEmail());
		user.setPassword(passwordEncoder.encode(userModel.getPassword())   );
		user.setSubscriptionId(3L);
		userRepository.save(user);
		return ResponseEntity.ok(user);
	}


}
