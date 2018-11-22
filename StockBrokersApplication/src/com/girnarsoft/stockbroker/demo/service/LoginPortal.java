package com.girnarsoft.stockbroker.demo.service;

import java.util.List;
import java.util.Scanner;

import com.girnarsoft.stockbroker.demo.model.User;
import com.girnarsoft.stockbroker.demo.util.UserLoginException;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;
/**
 * This class here is for the login portal 
 * @author sarthak
 *
 */
public class LoginPortal {

	private Scanner scan = new Scanner(System.in);
	private Validator validator = new Validator();
	private DataLoader loader = new DataLoader();
/**
 * This method here launches the login portal for the application
 * @return
 */
	public String[] start() {
		String result[] = new String[2];
		String choice;
		String name;
		while (true) {
			System.out.println(Constants.LOGIN);
			choice = scan.nextLine().trim();
			if (validator.isvalidLogin(choice))
				break;
			System.out.println(Constants.INVALID);
		}
		List<User> users = null;
		if (choice.equals(Constants.ONE))
			users = loader.loadUserData(Constants.USERLIST);
		else if (choice.equals(Constants.TWO))
			users = loader.loadUserData(Constants.ADMINLIST);
		while (true) {
			System.out.println(Constants.USERNAME);
				name = scan.nextLine().trim();
				if (validator.isValidUname(name, users) == false)
				{
					try {
						throw new UserLoginException(Constants.INVALIDUNAME);
					} catch (UserLoginException e) {
						continue;
						// TODO Auto-generated catch block
						//e.printStackTrace();
					}
				}
			System.out.println(Constants.PASSWD);
			String pass = scan.nextLine().trim();
			if (validator.isValidEntry(name, users, pass))
				break;
			System.out.println(Constants.INVALIDCRED);

		}
		System.out.println(Constants.GREET + name);
		result[0] = choice;
		result[1] = name;
		return result;
	}

}
