package com.girnarsoft.stockbroker.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.girnarsoft.stockbroker.demo.util.Constants;

/**
 * This class here returns the count of the quantity from the users .txt file
 * 
 * @author sartha
 *
 */
public class StockDetailsLoader {
	/**
	 * This method here extracts the count of the QTY column
	 * @param name
	 * @return int
	 */
	public int getDetails(String name) {
		int count = 0;
		Scanner scan = null;
		try {
			scan = new Scanner(new File(name + Constants.TXT));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(Constants.NOBUY);
		}
		List<String> qty = new ArrayList<String>();

		// Skip column headings.

		// Read each line, ensuring correct format.
		try {
			while (scan.hasNext()) {
				scan.next(); // read and skip
				scan.next();
				scan.next();
				qty.add(scan.next()); // read and store qty
				scan.next(); // read and skip
				scan.next();
				scan.next();
			}
			for (String n : qty) {
				int num = (Integer.parseInt(n));
				count += num;
			}
		} catch (Exception e) {
			//e.printStackTrace();
			return count;
		}
		return count;
	}
}
