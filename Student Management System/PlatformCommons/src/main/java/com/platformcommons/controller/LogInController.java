package com.platformcommons.controller;


import com.platformcommons.exceptions.LoginException;
import com.platformcommons.model.UserLogin;
import com.platformcommons.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LogInController {

	@Autowired
	private LoginService loginService;


	/*---------------------------------------------  login  ---------------------------------------------*/
	@PostMapping("/login")
	public ResponseEntity<String> loginMapping(@RequestBody UserLogin userLogin) throws LoginException {
		
		String output = loginService.login(userLogin);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}


	/*---------------------------------------------  logout   ---------------------------------------------*/
	@PostMapping("/logout")
	public ResponseEntity<String> logoutMapping(@RequestParam String key) throws LoginException{
		
		String output = loginService.logout(key);
		
		return new ResponseEntity<String>(output,HttpStatus.OK);
	}
}
