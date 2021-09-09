package jwt.restController;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jwt.model.User;
import jwt.service.UserService;


@RestController
public class TestController {
	
	
	private UserService userService;
	
	
	public TestController(UserService userService) {
		this.userService = userService;
	}	
	@GetMapping("/test/manager")
	//@PreAuthorize("hasRole('ROLE_MANAGER')")
	public List<User> listUsers(){
		return userService.listUsers();
	}
}
