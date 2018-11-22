package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.girnarsoft.stockbroker.demo.util.Constants;

/**
 * This class here is for the Dashboard ortal for a user showing all the
 * transactions related to a User
 * 
 * @author sarthak
 *
 */
public class DashboardPortal {
	/**
	 * This method here reads from the User file of transactions and writes the
	 * same on the console
	 * 
	 * @param name
	 */
	public void start(String name) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name
					+ Constants.TXT));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println(Constants.NOTRANS);
			//e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}

	}

}
