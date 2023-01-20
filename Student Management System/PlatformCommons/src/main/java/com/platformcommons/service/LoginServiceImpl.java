package com.platformcommons.service;

import com.platformcommons.exceptions.LoginException;
import com.platformcommons.model.Admin;
import com.platformcommons.model.CurrentUserSession;
import com.platformcommons.model.UserLogin;
import com.platformcommons.repository.AdminRepo;
import com.platformcommons.repository.CurrentSessionRepo;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	private AdminRepo adminRepo;

	@Autowired
	private CurrentSessionRepo currentSessionRepo;


	/*-------------------------------------------   Login   --------------------------------------------------*/
	@Override
	public String login(UserLogin userLogin) throws LoginException {

        List<Admin> admin = adminRepo.findAdminByMobile(userLogin.getMobileNumber());

		Admin existing = admin.get(0);

		if(existing == null) {
			throw new LoginException("Invalid MobileNumber!");
		}


		// Validation Current User
		Optional<CurrentUserSession> optional =  currentSessionRepo.findByUserId(existing.getId());
		if(optional.isPresent()) {
			throw new LoginException("User Already Exists in the System.");
		}
		
		if(existing.getPassword().equals(userLogin.getPassword())) {
			
			String key= RandomString.make(6);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(existing.getId(),key,LocalDateTime.now());
			
			currentSessionRepo.save(currentUserSession);

			return currentUserSession.toString();
		}

		throw new LoginException("Wrong password");
		
	}

	
	/*-------------------------------------   Logout   ----------------------------------------*/
	@Override
	public String logout(String key) throws LoginException {

		// Validation Current User
        CurrentUserSession currentUserSession = currentSessionRepo.findByUuid(key);
		if(currentUserSession == null) {
			throw new LoginException("Invalid Unique userId (Session Key).");
			
		}
		
		currentSessionRepo.delete(currentUserSession);
		
		return "Logged Out Successfully!";
	}

}
