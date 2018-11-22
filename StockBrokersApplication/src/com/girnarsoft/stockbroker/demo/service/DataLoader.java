package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.model.User;
import com.girnarsoft.stockbroker.demo.util.Constants;

/**
 * This class here is used for data loading relative to a file path
 * 
 * @author sarthak
 *
 */
public class DataLoader {
	/**
	 * This method here is used for data loading from the user
	 * @param fileName
	 * @return List
	 */
	public List<User> loadUserData(String fileName) {
		List<User> users = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader reader = Files.newBufferedReader(pathToFile,
				StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = reader.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				User user = createInfo(attributes);

				// adding user into ArrayList
				users.add(user);

				// read next line before looping
				// if end of file reached, line would be null
				line = reader.readLine();
			}

		} catch (IOException ioe) {
			System.out.println(Constants.INVALID);
			// ioe.printStackTrace();
		}

		return users;
	}
/**
 * This method here extracts the data into a String array 
 * @param metadata
 * @return 
 */
	public User createInfo(String[] metadata) {
		String name = metadata[0];
		String password = metadata[1];

		// create and return user of this metadata
		return new User(name, password);
	}
/**
 * This method here is used for loading the stock list of data
 * @param fileName
 * @return List
 */
	public List<Stock> loadStockData(String fileName) {
		List<Stock> stocks = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);

		// create an instance of BufferedReader
		// using try with resource, Java 7 feature to close resources
		try (BufferedReader reader = Files.newBufferedReader(pathToFile,
				StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = reader.readLine();

			// loop until all lines are read
			while (line != null) {

				// use string.split to load a string array with the values from
				// each line of
				// the file, using a comma as the delimiter
				String[] attributes = line.split(",");

				Stock stock = createStockInfo(attributes);

				// adding stock into ArrayList
				stocks.add(stock);

				// read next line before looping
				// if end of file reached, line would be null
				line = reader.readLine();
			}

		} catch (IOException ioe) {
			System.out.println(Constants.INVALID);
			// ioe.printStackTrace();
		}

		return stocks;
	}
/**
 * This method here extracts the stock list elements into a String array
 * @param metadata
 * @return
 */
	public Stock createStockInfo(String[] metadata) {
		String name = metadata[0];
		String id = (metadata[1]);
		String price = (metadata[2]);
		String qty = (metadata[3]);

		// create and return user of this metadata
		return new Stock(name, id, price, qty);
	}

}
