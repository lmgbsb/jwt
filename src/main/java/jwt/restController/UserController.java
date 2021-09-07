package jwt.restController;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.dto.UserDTO;
import jwt.jwt.JwtTokenUtil;
import jwt.model.User;
import jwt.security.UserPrincipal;
import jwt.service.UserService;

@RestController
@RequestMapping("/api/public")
public class UserController {
	

	private final UserService userService;
	private final JwtTokenUtil jwtTokenUtil;
	
	
	public UserController(UserService userService, JwtTokenUtil jwtTokenUtil) {
		this.userService=userService;
		this.jwtTokenUtil = jwtTokenUtil;
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
	@PostMapping("/signin3")
	public ResponseEntity<UserPrincipal> signin3(@RequestBody @Valid UserDTO userDTO) {
		try {
			UserPrincipal userPrincipal =  (UserPrincipal) userService.signin(userDTO).getPrincipal();
            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION, 
                    		jwtTokenUtil.createToken(userPrincipal.getUser(), 
                    				userPrincipal.getAuthorities()))
                    .body(userPrincipal);
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
	}
}

