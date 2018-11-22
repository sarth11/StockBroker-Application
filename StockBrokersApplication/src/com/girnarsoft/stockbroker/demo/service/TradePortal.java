package com.girnarsoft.stockbroker.demo.service;

import java.util.List;
import java.util.Scanner;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;

/**
 * This class here is for the Trade portal of the application
 * 
 * @author sarthak
 *
 */
public class TradePortal {
	private Scanner scan = new Scanner(System.in);
	private Validator validator = new Validator();
	private StockService service = new StockService();
	private DataLoader loader = new DataLoader();
	private TransactionHistoryLoader historyLoader = new TransactionHistoryLoader();

	/**
	 * This method here starts the Trade portal interface of the application
	 * 
	 * @param option
	 * @param username
	 */
	public void start(String option, String username) {
		List<Stock> stocks = loader.loadStockData(Constants.UPDATEDSTOCKLIST);
		String id = null;
		String qty = null;
		String price = null;
		String num = null;
		String sname = null;
		String confirmOption;
		int flag = 0;
		if (option.equals(Constants.ONE)) {
			outer: while (true) {
				System.out.println(Constants.CONFIRM);
				while (true) {
					System.out.println(Constants.BUY);
					id = scan.nextLine().trim();
					if (validator.isValidId(id))
						break;
					System.out.println(Constants.INVALID);
				}
				while (true) {
					System.out.println(Constants.QTY);
					qty = scan.nextLine().trim();
					if (validator.isValidQty(id, qty))
						break;
					System.out.println(Constants.INVALID);
					flag = 0;
					continue outer;
				}
				break;
			}
			for (Stock s : stocks) {
				if ((id).equals(s.getStockId())) {
					sname = s.getStockName();
					price = s.getStockPrice();
					num = s.getQuantity();
					break;
				}
			}
			if(qty.equals("0")==false)
				service.increasePrice(Constants.UPDATEDSTOCKLIST, price);
			service.decreaseQuantity(Constants.UPDATEDSTOCKLIST, num, qty);
			historyLoader.update(username, qty, price, sname, 0, id);
			historyLoader.writeTo(Constants.TRA, username, qty, price, sname,
					0, id);
			historyLoader.writeTo(Constants.RE, username, qty, price,
					sname, 0, id);
			System.out.println(Constants.SUCCESSB);
		}

		else if (option.equals(Constants.TWO)) {
			while (true) {
				System.out.println(Constants.SELL);
				id = scan.nextLine().trim();
				if (validator.isValidId(id)) {
					if (!validator.isPresent(id, username)) {
						System.out.println(Constants.NOSHARE);
						continue;
					}
					break;
				}
			}
			while (true) {
				System.out.println(Constants.QTY);
				qty = scan.nextLine().trim();
				if (validator.isValidQty(id, qty))
					break;
				System.out.println(Constants.INVALID);
			}
			for (Stock s : stocks) {
				if ((id).equals(s.getStockId())) {
					sname = s.getStockName();
					price = s.getStockPrice();
					num = s.getQuantity();
					break;
				}
			}
			service.decreasePrice(Constants.UPDATEDSTOCKLIST, price);
			service.increaseQuantity(Constants.UPDATEDSTOCKLIST, num, qty);
			historyLoader.update(username, qty, price, sname, 1, id);
			historyLoader.writeTo(Constants.TRA, username, qty, price, sname,
					1, id);
			historyLoader.writeTo(Constants.RE, username, qty, price, sname, 1,
					id);
			System.out.println(Constants.SUCCESSS);
		}

	}
}
