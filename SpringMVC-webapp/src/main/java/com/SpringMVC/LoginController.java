package com.SpringMVC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String msg() {
		return "Login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String msg1(@RequestParam String name,String password, ModelMap model) {
		if(!service.validate(name, password)) {
			model.put("errormsg", "invalid credentials");
			return "Login";
		}
		model.put("name", name);
		model.put("password", password);
		return "Welcome";
		
	}
}
