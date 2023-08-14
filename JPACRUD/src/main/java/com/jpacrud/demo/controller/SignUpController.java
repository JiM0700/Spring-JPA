package com.jpacrud.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.jpacrud.demo.model.Response;
import com.jpacrud.demo.model.SignUpModel;
import com.jpacrud.demo.service.SignUpService;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class SignUpController {

	@Autowired
	SignUpService jpa;

	@PostMapping("/create")
	public ResponseEntity<Response> createUser(@RequestBody SignUpModel values) {
		return ResponseEntity.ok(jpa.createUser(values));
	}

	@GetMapping("/select")
	public ResponseEntity<Response> getAllUser() {
		return ResponseEntity.ok(jpa.getAllUser());
	}

	@PutMapping("/update")
	public ResponseEntity<Response> updateEmail(@RequestBody SignUpModel values) {
		return ResponseEntity.ok(jpa.updateEmail(values));
	}

	@GetMapping("/selectone")
	public ResponseEntity<Response> getOneUser(@RequestParam SignUpModel values) {
		return ResponseEntity.ok(jpa.getOneUser(values));
	}

	@DeleteMapping("/deleteuser")
	public ResponseEntity<Response> deleteUser(@RequestParam String sNo) {
		return ResponseEntity.ok(jpa.deleteUser(sNo));
	}

	@PostMapping("/login")
	public ResponseEntity<Response> userLogin(@RequestParam String emailId,@RequestParam String password) {
		return ResponseEntity.ok(jpa.userLogin(emailId,password));
	}

	@PutMapping("/inactive")
	public ResponseEntity<Response> userInactive(@RequestParam String sNo) {
		return ResponseEntity.ok(jpa.userInactive(sNo));
	}

}
