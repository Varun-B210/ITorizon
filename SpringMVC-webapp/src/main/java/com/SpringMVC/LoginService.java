package com.SpringMVC;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validate(String user, String password) {
		
		return user.equalsIgnoreCase("varun")&&password.equalsIgnoreCase("acharya");
	}

}
