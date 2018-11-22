package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.girnarsoft.stockbroker.demo.util.Constants;

/**
 * This class here maintains the transaction history for each user in a separate
 * txt file
 * 
 * @author sarthak
 *
 */
public class TransactionHistoryLoader {
	private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	   

	/**
	 * This method updates the transactions to a specific txt file of the
	 * Separate user
	 * 
	 * @param name
	 * @param qty
	 * @param price
	 * @param sname
	 * @param flag
	 * @param id
	 */
	public void update(String name, String qty, String price, String sname,
			int flag, String id) {
		 LocalDateTime now = LocalDateTime.now(); 
		try (FileWriter fw = new FileWriter(name + Constants.TXT, true);
				BufferedWriter bufferwriter = new BufferedWriter(fw);
				PrintWriter writer = new PrintWriter(bufferwriter)) {
			writer.write(sname + Constants.SPACE);
			writer.write(id + Constants.SPACE);
			writer.write(price + Constants.SPACE);
			writer.write(qty + Constants.SPACE);
			if (flag == 0)
				writer.write(Constants.WRITEBUY + Constants.SPACE);
			else
				writer.write(Constants.WRITESELL + Constants.SPACE);
			writer.write(dtf.format(now));
			writer.println();
		} catch (IOException e) {
			System.out.println(Constants.FILEINVALID);
		}
	}

	/**
	 * This method here updates all the processed transactions to the
	 * Transactions.txt file
	 * 
	 * @param name
	 * @param uname
	 * @param qty
	 * @param price
	 * @param sname
	 * @param flag
	 * @param id
	 */
	public void writeTo(String name, String uname, String qty, String price,
			String sname, int flag, String id) {
		 LocalDateTime now = LocalDateTime.now(); 
		try (FileWriter fw = new FileWriter(name + Constants.TXT, true);
				BufferedWriter bufferwriter = new BufferedWriter(fw);
				PrintWriter writer = new PrintWriter(bufferwriter)) {
			writer.write(uname + Constants.SPACE);
			writer.write(id + Constants.SPACE);
			writer.write(sname + Constants.SPACE);
			writer.write(price + Constants.SPACE);
			writer.write(qty + Constants.SPACE);
			if (flag == 0)
				writer.write(Constants.WRITEBUY + Constants.SPACE);
			else
				writer.write(Constants.WRITESELL + Constants.SPACE);
			writer.write(dtf.format(now));
			writer.println();
		} catch (IOException e) {
			System.out.println(Constants.INVALID);
			// e.printStackTrace();
		}
	}

}
