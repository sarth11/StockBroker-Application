package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Stack;

import com.girnarsoft.stockbroker.demo.model.Stock;
import com.girnarsoft.stockbroker.demo.model.User;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;

/**
 * This class here enables the admin to use the service of reconcillation
 * 
 * @author sarthak
 *
 */
public class ReconcilationService {
	private DataLoader loader = new DataLoader();
	private DashboardPortal dashboard= new DashboardPortal();
	/**
	 * This method here puts the BUY and SELL shares into 2 different stacks and
	 * then performs a allocation check for each share of different users and
	 * prints out the result in reconcillation.txt
	 * 
	 * @param id
	 */
	public void start(String id) {
		String buy[] = new String[50];
		String sell[] = new String[50];
		String poolQty = null;
		String name;
		Stack<String> buyStack = new Stack<String>();
		Stack<String> sellStack = new Stack<String>();
		int index1 = 0;
		int index2 = 0;
		int buyQty = 0;
		int flagAll = 0;
		int sellQty = 0;
		int toRemove = 0;
		int flagSell = 1;
		List<Stock> stocks = loader.loadStockData(Constants.STOCKLIST);
		List<User> users = loader.loadUserData(Constants.USERLIST);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					Constants.TRA + Constants.TXT));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] words = line.split(" ");
				if (words[1].equals(id)) {
					if (words[5].equalsIgnoreCase(Constants.WRITEBUY))
						buy[index1++] = words[4];
					else if (words[5].equalsIgnoreCase(Constants.WRITESELL))
						sell[index2++] = words[4];
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
		for (int i = 0; i < index1; i++) {
			buyStack.push(buy[i]);
		}
		for (int i = 0; i < index2; i++) {
			sellStack.push(sell[i]);
		}
		for (Stock s : stocks) {
			if (id.equals(s.getStockId())) {
				poolQty = s.getQuantity();
			}
		}
		// sumQty += (Integer.parseInt(poolQty));
		for (String s : buyStack) {
			int flag = 0;
			int num = Integer.parseInt(s);
			int pool = Integer.parseInt(poolQty);
			if (num <= pool) {
				pool -= num;
				flag = 1;
			}
			if (pool <= 0)
				break;
			toRemove++;
			if (flag == 1) {
				try (FileWriter fileWriter = new FileWriter(Constants.RECONIST, true);
						BufferedWriter bufferwriter = new BufferedWriter(fileWriter);
						PrintWriter writer = new PrintWriter(bufferwriter)) {
					writer.write(Constants.SPACE + Constants.APPROVE);
					writer.println();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		while (toRemove-- != 0) {
			buyStack.pop();
		}
		if (buyStack.isEmpty()) {
			flagSell = 0;
		}
		if (flagSell == 0) {
			while (!sellStack.isEmpty()) {
				try (FileWriter fileWriter = new FileWriter(Constants.RECONIST, true);
						BufferedWriter bufferwriter = new BufferedWriter(fileWriter);
						PrintWriter writer = new PrintWriter(bufferwriter)) {
					writer.write(Constants.SPACE + Constants.DENY);
					writer.println();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				sellStack.pop();
			}
		} else if (flagSell == 1) {
			/*
			 * while(!buyStack.isEmpty()) { int
			 * numb=(Integer.parseInt(buyStack.pop()));
			 * while(!sellStack.isEmpty()) { int
			 * nums=Integer.parseInt(sellStack.pop()); if(numb==nums) { try
			 * (FileWriter fw = new FileWriter(Constants.RECONIST, true);
			 * BufferedWriter bufferwriter = new BufferedWriter(fw); PrintWriter
			 * writer = new PrintWriter(bufferwriter)) {
			 * writer.write(Constants.SPACE + Constants.MATCHAPPROVE); } catch
			 * (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } break; }
			 * 
			 * } }
			 */
			for (String s : buyStack) {
				int num = Integer.parseInt(s);
				buyQty += num;
			}
			for (String s : buyStack) {
				int num = Integer.parseInt(s);
				sellQty += num;
			}
			if (buyQty == sellQty)
				flagAll = 1;
			if (flagAll == 1) {
				try (FileWriter fileWriter = new FileWriter(Constants.RECONIST, true);
						BufferedWriter bufferwriter = new BufferedWriter(fileWriter);
						PrintWriter writer = new PrintWriter(bufferwriter)) {
					writer.write(Constants.SPACE + Constants.PENDAPPROVE);
					writer.println();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				

			}

		}
		dashboard.start(Constants.REC);
		/*
		 * System.out.println("BUY STACK"); for (String s : buyStack) {
		 * System.out.println(s); } System.out.println("SELL STACK"); for
		 * (String s : sellStack) { System.out.println(s); }
		 */
	}
}
