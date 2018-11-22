package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;

/**
 * This class here is basically for the admin portal of the app
 * 
 * @author sarthak
 *
 */
public class AdminPortal {
	private DataLoader loader = new DataLoader();
	private ReconcilationService service = new ReconcilationService();
	private Scanner scan = new Scanner(System.in);
	private Validator validator = new Validator();

	/**
	 * This method here lists the menu interface for the admin portal
	 * 
	 * @param option
	 */
	public void start(int option) {
		String id;
		if (option == (Integer.parseInt(Constants.ONE))) {
			List<Stock> stocks = loader.loadStockData(Constants.STOCKLIST);
			System.out.println(Constants.STOCKTABLE);
			for (Stock s : stocks) {
				System.out.println(s.getStockId() + "            "
						+ s.getStockPrice() + "          " + s.getQuantity()
						+ "       " + s.getStockName());
			}
		} else if (option == (Integer.parseInt(Constants.TWO))) {
			List<Stock> stocks = loader
					.loadStockData(Constants.UPDATEDSTOCKLIST);
			System.out.println(Constants.STOCKTABLE);
			for (Stock s : stocks) {
				System.out.println(s.getStockId() + "            "
						+ s.getStockPrice() + "          " + s.getQuantity()
						+ "       " + s.getStockName());
			}
		} else if (option == (Integer.parseInt(Constants.THREE))) {
			while (true) {
				System.out.println(Constants.ID);
				id = scan.nextLine().trim();
				if (validator.isValidId(id)) {
					if (!validator.isPresent(id, Constants.REC)) {
						System.out.println(Constants.NO);
						continue;
					}
					break;
				}

				System.out.println(Constants.INVALID);
			}
			service.start(id);
			// System.out.println(Constants.TRYLATER);
		} else if (option == (Integer.parseInt(Constants.FOUR))) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(
						Constants.TRA + Constants.TXT));
				String line;
				while ((line = reader.readLine()) != null) {
					System.out.println(line);
				}
				reader.close();
			} catch (FileNotFoundException e) {
				System.out.println(Constants.NOTRANST);
				// e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(Constants.INVALID);
				// e.printStackTrace();
			}
		}

	}

}
