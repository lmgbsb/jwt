package jwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@GetMapping("/landing")
	public ModelAndView landingPage(Authentication authentication) {
		ModelAndView modelAndView = new ModelAndView("index.html");
		if(authentication != null) {
			modelAndView.addObject("username", authentication.getName());				
		}
		return modelAndView;
	}
	@GetMapping("/test")
	public ModelAndView testAuthentication(Authentication authentication, Model model) {
		ModelAndView modelAndView = new ModelAndView("test.html");
		modelAndView.addObject("authenticated", authentication.getName());	
		return modelAndView;
	}
}
