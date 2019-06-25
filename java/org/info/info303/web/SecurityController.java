package org.info.info303.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	
	@RequestMapping("login")
	public String login() {
		
		return "login";
	}
	
	@RequestMapping("/")
	public String home() {
		
		return "redirect:/cites";
	}
	
	@RequestMapping("/403")
	public String accesDenied() {
		
		return "403";
	}
	
	@RequestMapping("/404")
	public String forbiden() {
		
		return "403";
	}
}
