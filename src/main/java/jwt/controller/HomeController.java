package jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	
	@GetMapping("/landing")
	public String landingPage(Authentication authentication, Model model) {
		model.addAttribute("username", authentication.getName());	
		return "index.html";
	}
	@GetMapping("/test")
	public String testAuthentication(Authentication authentication, Model model) {
		model.addAttribute("authenticated", authentication.getName());	
		return "test.html";
	}
}
