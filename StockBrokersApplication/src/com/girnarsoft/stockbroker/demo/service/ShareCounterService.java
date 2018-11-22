package com.girnarsoft.stockbroker.demo.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.girnarsoft.stockbroker.demo.util.Constants;

public class ShareCounterService {
	public int start(String id, String name) {
		int add = 0;
		int sub = 0;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(name
					+ Constants.TXT));
			String line;
			while ((line = reader.readLine()) != null) {
				String words[] = line.split(Constants.SPACE);
				if (words[4].equalsIgnoreCase(Constants.WRITEBUY)) {
					int num = Integer.parseInt(words[3]);
					add += num;
				} else if (words[4].equalsIgnoreCase(Constants.WRITESELL)) {
					int num = Integer.parseInt(words[3]);
					sub += num;
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
		if (add - sub < 0)
			return 0;
		return 1;
	}

}
