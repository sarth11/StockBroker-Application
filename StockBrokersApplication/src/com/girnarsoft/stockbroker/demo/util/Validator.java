package com.girnarsoft.stockbroker.demo.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.model.User;
import com.girnarsoft.stockbroker.demo.service.DataLoader;
import com.opencsv.CSVReader;

/**
 * This is the validator class which is checking the various conditions
 * according to different situations in the various classes of the project
 * 
 * @author sarthak
 *
 */
public class Validator {
	private DataLoader loader = new DataLoader();

	/**
	 * Method for checking a valid login to Application
	 * 
	 * @param choice
	 * @return
	 */
	public boolean isvalidLogin(String choice) {

		if (choice.equals(Constants.ONE) || choice.equals(Constants.TWO))
			return true;
		return false;
	}

	/**
	 * Method for checking a valid user login
	 * 
	 * @param choice
	 * @return
	 */
	public boolean isValidUserLogin(String choice) {

		if (choice.equals(Constants.ONE) || choice.equals(Constants.TWO)
				|| choice.equals(Constants.THREE))
			return true;
		return false;
	}

	/**
	 * Method for checking a valid username
	 * 
	 * @param name
	 * @param users
	 * @return
	 */
	public boolean isValidUname(String name, List<User> users) {
		for (User u : users) {
			if (u.getUserName().equals(name))
				return true;
		}
		return false;
	}

	/**
	 * Method for checking a valid entry to the login portal
	 * 
	 * @param name
	 * @param users
	 * @param pass
	 * @return
	 */
	public boolean isValidEntry(String name, List<User> users, String pass) {

		for (User u : users) {
			if (u.getUserName().equals(name) && u.getPassword().equals(pass))
				return true;
		}
		return false;

	}

	/**
	 * Method for checking a valid id
	 * 
	 * @param id
	 * @return
	 */
	public boolean isValidId(String id) {
		int num = 0;
		try {
			num = Integer.parseInt(id);
		} catch (Exception e) {
			return false;
		}
		if (num >= 101 && num <= 110)
			return true;
		return false;

	}

	/**
	 * Method for checking a valid quantity entry
	 * 
	 * @param id
	 * @param qty
	 * @return
	 */
	public boolean isValidQty(String id, String qty) {
		int q;
		int quant;
		int flag = 0;
		List<Stock> stocks = loader.loadStockData(Constants.UPDATEDSTOCKLIST);
		try {
			quant = Integer.parseInt(qty);

		} catch (Exception e) {
			return false;
		}
		for (Stock s : stocks) {
			if (id.equals(s.getStockId())) {
				try {
					q = Integer.parseInt(s.getQuantity());
				} catch (Exception e) {
					return false;
				}
				if (q >= quant && quant >= 0)
					return true;
				else if (q < quant)
					flag = 1;
			}
		}
		if (flag == 1)
			System.out.println(Constants.INVALIDQTY);
		return false;
	}

	/**
	 * Method for checking if a String is present in the txt file
	 * 
	 * @param id
	 * @param name
	 * @return
	 */
	public boolean isPresent(String id, String name) {
		int flag = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name
					+ Constants.TXT));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");

				for (String word : words) {
					if (word.equals(id)) {
						flag = 1;
						break;
					}
				}
			}
			reader.close();

		} catch (FileNotFoundException e) {
			System.out.println(Constants.NOTRANS);
			// e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}
		if (flag == 1)
			return true;
		return false;

	}

	public boolean isConfirmedLogin(String option) {
		if (option.equals(Constants.ONE) || option.equals(Constants.TWO))
			return true;
		return false;
	}

	public boolean isFileCorrupt(String fileName) {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			System.out.println(Constants.FILEINVALID);
			// TODO Auto-generated //
			// catch block
			// e.printStackTrace();
		}
		String[] nextLine;
		int lineNumber = 0;
		try {
			while ((nextLine = reader.readNext()) != null) {
				int price=Integer.parseInt(nextLine[2]);
				int qty=Integer.parseInt(nextLine[3]);
				if(price<0 || qty<0 || nextLine.equals(Constants.SPACECHAR))
					return true;
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
