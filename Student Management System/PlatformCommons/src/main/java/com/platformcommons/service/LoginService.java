package com.platformcommons.service;

import com.platformcommons.exceptions.LoginException;
import com.platformcommons.model.UserLogin;

public interface LoginService {

    public String login (UserLogin userLogin) throws LoginException;
	
	public String logout (String Key) throws LoginException;

}
