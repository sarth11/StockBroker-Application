package com.girnarsoft.stockbroker.demo.client;

import java.io.File;

import com.girnarsoft.stockbroker.demo.service.ApplicationLoader;
import com.girnarsoft.stockbroker.demo.util.Constants;
import com.girnarsoft.stockbroker.demo.util.Validator;
/**
 * This is the main client class calling the application launcher.
 * @author sarthak
 *
 */
public class Client {
	
	/**
	 * Main method calling the launch function of applicationloader
	 * @param args
	 */
	public static void main(String args[]) {
		Validator validator = new Validator();
		File userFile = new File(Constants.USERLIST);
		File adminFile = new File(Constants.ADMINLIST);
		File stockFile =  new File(Constants.STOCKLIST);
		File updatedStockFile=new File(Constants.UPDATEDSTOCKLIST);
		if(!userFile.exists() || !adminFile.exists() || !stockFile.exists() || !updatedStockFile.exists())
		{
			System.out.println(Constants.DATAERROR);
			return;
		}
			if(validator.isFileCorrupt(Constants.STOCKLIST))
			{
				System.out.println(Constants.CORRUPT);
				return;
			}
		try{
		ApplicationLoader applicationLoader = new ApplicationLoader();
		applicationLoader.launch();
		}
		catch(Exception e)
		{
			System.out.println(Constants.ERROR);
		}
		/*finally{
			System.out.println(Constants.SHUTDOWN);
		}*/
	}

}
