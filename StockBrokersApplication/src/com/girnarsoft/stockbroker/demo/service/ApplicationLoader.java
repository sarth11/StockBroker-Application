package com.girnarsoft.stockbroker.demo.service;

import java.io.File;
import java.util.Scanner;

import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;

/**
 * This is the application loader class which is launching our application by
 * creating various objects
 * 
 * @author sarthak
 *
 */
public class ApplicationLoader {
	private Scanner scan = new Scanner(System.in);
	private LoginPortal login = new LoginPortal();
	private StockPortal stock = new StockPortal();
	private AdminPortal admin = new AdminPortal();
	private TradePortal trade = new TradePortal();
	private Validator validator = new Validator();
	private DashboardPortal dashboard = new DashboardPortal();
	private StockDetailsLoader loader = new StockDetailsLoader();

	/**
	 * This method here is launching our application after being called by the
	 * object in the client class
	 */
	public void launch() {
		int qty = 0;
		String option = null;
		String result[];
		outer: while (true) {
			System.out.println(Constants.MSG);
			result = login.start();
			String name = result[1];
			if (result[0].equals(Constants.ONE)) {
				while (true) {
					int flag = 0;
					System.out.println(Constants.ASK);
					System.out.println(Constants.UPORTAL1);
					System.out.println(Constants.UPORTAL2);
					System.out.println(Constants.UPORTAL3);
					System.out.println(Constants.UEXIT);
					inner: while (true) {
						try {
							option = scan.nextLine().trim();
						} catch (Exception e) {
							System.out.println(Constants.STOP);
						}
						if (validator.isValidUserLogin(result[0]))
							break inner;
						System.out.println(Constants.INVALID);
					}
					if (option.equals(Constants.ONE)) {
						flag = 1;
						stock.start(option, name);
					} else if (option.equals(Constants.TWO)) {
						qty = loader.getDetails(name);
						flag = 1;
						if (qty > 0) {
							//stock.start(option, name);
							dashboard.start(name);
						}
					} else if (option.equals(Constants.THREE)) {
						flag = 1;
						dashboard.start(name);
					} else if (option.equals(Constants.FOUR))
						continue outer;
					if (flag == 0)
						System.out.println(Constants.INVALID);
				}

			} else if (result[0].equals(Constants.TWO)) {
				while (true) {
					int flag = 0;
					int entryFlag = 0;
					System.out.println(Constants.ASK);
					System.out.println(Constants.ORIG);
					System.out.println(Constants.CURR);
					System.out.println(Constants.RECO);
					System.out.println(Constants.HIST);
					System.out.println(Constants.AEXIT);
					inner: while (true) {
						try {
							option = scan.nextLine().trim();
						} catch (Exception e) {
							System.out.println(Constants.STOP);
						}
						if (validator.isValidUserLogin(result[0]))
							break inner;
						System.out.println(Constants.INVALID);
					}
					if (option.equals(Constants.ONE)) {
						flag = 1;
						admin.start(1);

					} else if (option.equals(Constants.TWO)) {
						flag = 1;
						admin.start(2);
					} else if (option.equals(Constants.THREE)) {
						/*File file = new File(Constants.RECONIST);
						if(!file.exists())
						{
							System.out.println(Constants.NOTRANST);
							//System.out.println(Constants.FILEINVALID);
							continue;
						}*/
						flag = 1;
						admin.start(3);
					} else if (option.equals(Constants.FOUR)) {
						flag = 1;
						admin.start(4);
					} else if (option.equals(Constants.FIVE))
						continue outer;
					if (flag == 0)
						System.out.println(Constants.INVALID);
				}
			}
		}
	}

}
