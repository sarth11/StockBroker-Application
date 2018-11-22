package com.girnarsoft.stockbroker.demo.service;

import java.util.List;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;
/**
 * This class here is for the stock portal of the application
 * @author sarthak
 *
 */
public class StockPortal {
	private DataLoader loader = new DataLoader();
	private Validator validator = new Validator();
	private TradePortal trade = new TradePortal();

	/**
	 * This method here prints the Stock elements from the stock list
	 * @param option
	 * @param username
	 */
	public void start(String option,String username) {
		List<Stock> stocks = loader.loadStockData(Constants.UPDATEDSTOCKLIST);
		System.out.println(Constants.STOCKTABLE);
		for (Stock s : stocks) {
			System.out.println(s.getStockId() + "            "
					+ s.getStockPrice() + "          " + s.getQuantity()
					+ "       " + s.getStockName());
		}
		trade.start(option,username);
	}

}
