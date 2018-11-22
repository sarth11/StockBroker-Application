package com.girnarsoft.stockbroker.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.girnarsoft.stockbroker.demo.util.Constants;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

/**
 * This is the class servicing the client for the stock services like increase
 * price,decrease price,Increase quantity and decrease quantity
 * 
 * @author sartha
 *
 */
public class StockService {
	/**
	 * This method here increase the price by 10 for a particular share
	 * 
	 * @param fileToUpdate
	 * @param price
	 */
	public void increasePrice(String fileToUpdate, String price) {
		File inputFile = new File(fileToUpdate);
		// Read existing file
		int cost = Integer.parseInt(price);
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(inputFile), Constants.COMMA);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.FILEINVALID);
			// e.printStackTrace();
		}
		List<String[]> csvBody = null;
		try {
			csvBody = reader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.FILEINVALID);
			// e.printStackTrace();
		}
		// get CSV row column and replace with by using row and column
		for (int i = 0; i < csvBody.size(); i++) {
			String[] strArray = csvBody.get(i);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase(price)) { // String to be
															// replaced
					csvBody.get(i)[j] = Integer.toString((cost + 10));
				}
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.FILEINVALID);
				// e.printStackTrace();
			}

			// Write to CSV file which is open
			CSVWriter writer = null;
			try {
				writer = new CSVWriter(new FileWriter(inputFile),
						Constants.COMMA);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.FILEINVALID);
				e.printStackTrace();
			}
			writer.writeAll(csvBody, false);
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.FILEINVALID);
				// e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.FILEINVALID);
				// e.printStackTrace();
			}

		}
	}

	/**
	 * This method here decreases the price of a share by 10
	 * 
	 * @param fileToUpdate
	 * @param price
	 */
	public void decreasePrice(String fileToUpdate, String price) {
		File inputFile = new File(fileToUpdate);

		// Read existing file
		int cost = Integer.parseInt(price);
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(inputFile), Constants.COMMA);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.FILEINVALID);
			// e.printStackTrace();
		}
		List<String[]> csvBody = null;
		try {
			csvBody = reader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}
		// get CSV row column and replace with by using row and column
		for (int i = 0; i < csvBody.size(); i++) {
			String[] strArray = csvBody.get(i);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase(price)) { // String to be
															// replaced
					csvBody.get(i)[j] = Integer.toString((cost - 10));
				}
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

			// Write to CSV file which is open
			CSVWriter writer = null;
			try {
				writer = new CSVWriter(new FileWriter(inputFile),
						Constants.COMMA);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			writer.writeAll(csvBody, false);
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

		}

	}

	/**
	 * This method here increases the quantity when the user sell the stock
	 * share
	 * 
	 * @param fileToUpdate
	 * @param num
	 * @param qty
	 */
	public void increaseQuantity(String fileToUpdate, String num, String qty) {
		File inputFile = new File(fileToUpdate);

		// Read existing file
		int quant = Integer.parseInt(qty);
		int no = Integer.parseInt(num);
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(inputFile), Constants.COMMA);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.FILEINVALID);
			// e.printStackTrace();
		}
		List<String[]> csvBody = null;
		try {
			csvBody = reader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}
		// get CSV row column and replace with by using row and column
		for (int i = 0; i < csvBody.size(); i++) {
			String[] strArray = csvBody.get(i);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase(num)) { // String to be
															// replaced
					csvBody.get(i)[j] = Integer.toString((no + quant));
				}
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

			// Write to CSV file which is open
			CSVWriter writer = null;
			try {
				writer = new CSVWriter(new FileWriter(inputFile),
						Constants.COMMA);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			writer.writeAll(csvBody, false);
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

		}

	}

	/**
	 * This method here decreases the quantity when the user buys the quantity
	 * 
	 * @param fileToUpdate
	 * @param num
	 * @param qty
	 */
	public void decreaseQuantity(String fileToUpdate, String num, String qty) {
		File inputFile = new File(fileToUpdate);

		// Read existing file
		int quant = Integer.parseInt(qty);
		int no = Integer.parseInt(num);
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(inputFile), Constants.COMMA);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.FILEINVALID);
			// e.printStackTrace();
		}
		List<String[]> csvBody = null;
		try {
			csvBody = reader.readAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}
		// get CSV row column and replace with by using row and column
		for (int i = 0; i < csvBody.size(); i++) {
			String[] strArray = csvBody.get(i);
			for (int j = 0; j < strArray.length; j++) {
				if (strArray[j].equalsIgnoreCase(num)) { // String to be
															// replaced
					csvBody.get(i)[j] = Integer.toString((no - quant));
				}
			}
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

			// Write to CSV file which is open
			CSVWriter writer = null;
			try {
				writer = new CSVWriter(new FileWriter(inputFile),
						Constants.COMMA);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			writer.writeAll(csvBody, false);
			try {
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
			try {
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}

		}

	}

}
