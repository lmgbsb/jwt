package jwt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UserDTO;
import jwt.service.AuthenticationService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	
	private final AuthenticationService authenticationService;
	
	
	public AuthController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	@PostMapping("/signup")
	public ResponseEntity cadastrarUsuario(@RequestBody UserDTO userDTO) {		
		authenticationService.cadastrarUsuario(userDTO);
		return new ResponseEntity(HttpStatus.OK);
	}
}