package com.Shopping.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Shopping.Exception.LoginException;
import com.Shopping.Model.CurrentUserSession;
import com.Shopping.Model.Login;
import com.Shopping.Repository.CurrentUserSessionRepo;
import com.Shopping.Repository.CustomerRepo;

@Service
public class LoginServicesImpl implements LoginServices{

	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserRepo;
	
	@Override
	public String login(Login log) {
		
		return null;
	}

	@Override
	public String Logout(String uuid) throws LoginException{
		
		CurrentUserSession activeUserSession= currentUserRepo.findByUuid(uuid);
		
		if(activeUserSession==null) throw new LoginException("Incorrect uuid or userAlready logged out..");
		
		currentUserRepo.delete(activeUserSession);
		return "Logged Out...";
	}

	
}
