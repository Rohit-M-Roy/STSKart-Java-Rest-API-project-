package com.Shopping.Services;

import com.Shopping.Exception.LoginException;
import com.Shopping.Model.Login;

public interface LoginServices {

	public String login(Login log) throws LoginException;

	public String Logout(String uuid) throws LoginException;
}
