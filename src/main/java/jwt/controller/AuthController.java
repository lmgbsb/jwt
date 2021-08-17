package jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UserDTO;
import jwt.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	

	private final AuthService authService;
	
	
	public AuthController(AuthService authService) {
		this.authService=authService;
	}
	//create a new user in database
	@PostMapping("/signup")
	public ResponseEntity signup(@RequestBody UserDTO userDTO) {
		authService.signup(userDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}
