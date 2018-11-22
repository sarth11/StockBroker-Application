package com.girnarsoft.stockbroker.demo.util;

/**
 * This class here handels the User login Exception
 * 
 * @author sartha
 *
 */
public class UserLoginException extends Exception {
	public UserLoginException(String msg) {
		System.out.println(Constants.INVALIDUNAME);
	}

}
