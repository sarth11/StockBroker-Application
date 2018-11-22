package com.girnarsoft.stockbroker.demo.model;

/*
 * User class for maintaining the Username and password 
 */
public class User {
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	private String userName;
	private String password;

	/*
	 * Below are the listed setters and getters for the various elements
	 */
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	/*
	 * @Override public String toString() { return "User [userName=" + userName
	 * + ", password=" + password + "]"; }
	 */

}
