package jwt.restController;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UserDTO;
import jwt.model.User;
import jwt.security.UserPrincipal;
import jwt.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	

	private final UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	//create a new user in database
	@PostMapping("/signup")
	public ResponseEntity<User> signup(@RequestBody @Valid UserDTO userDTO) {
		User user = userService.signup(userDTO);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	@PostMapping("/signin")
	public ResponseEntity<UserPrincipal> signin(@RequestBody @Valid UserDTO userDTO) {
		UserPrincipal userPrincipal =  (UserPrincipal) userService.signin(userDTO).getPrincipal();
		return new ResponseEntity<UserPrincipal>(userPrincipal, HttpStatus.OK);
	}
}
