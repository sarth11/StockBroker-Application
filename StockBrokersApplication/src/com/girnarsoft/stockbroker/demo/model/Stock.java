package com.girnarsoft.stockbroker.demo.model;

/*
 * Stock class for maintaining the Stock data like stockname,stockid,stockprice and quantity
 */
public class Stock {
	public Stock(String stockName, String stockId, String stockPrice,
			String quantity) {
		this.stockName = stockName;
		this.stockId = stockId;
		this.stockPrice = stockPrice;
		this.quantity = quantity;
	}

	/*
	 * @Override public String toString() { return "Stock [stockName=" +
	 * stockName + ", stockId=" + stockId + ", stockPrice=" + stockPrice +
	 * ", quantity=" + quantity + "]"; }
	 */

	private String stockName;
	private String stockId;
	private String stockPrice;
	private String quantity;
/*
 * Getters and setters for the various Stock elements can be found below
 */
	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(String stockPrice) {
		this.stockPrice = stockPrice;
	}

	public String getStockId() {
		return stockId;
	}

	public void setStockId(String stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

}
