package jwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	
	@GetMapping("/landing")
	public ModelAndView landingPage() {
		ModelAndView modelAndView = new ModelAndView("test");
	
		return modelAndView;
	}
}
