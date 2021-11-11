package com.dollarsbank.utility;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsolePrinterUtility implements ColorsUtility{
	
	public static String dashboard(String text) {
		String string;
			if (text.equals("welcome")) {
				string = "+---------------------------+"
						+"\n| DOLLARSBANK Welcomes You! |"
						+"\n+---------------------------+";
				return string;
			}else if (text.equals("create")) {
				
				string = "+---------------------------------+"
						+"\n| Enter Details For New Account |"
						+"\n+-------------------------------+";
				return string;
			}else if (text.equals("login")) {
				string = "+-----------------------+"
						+"\n| Enter Login Details |"
						+"\n+---------------------+";
				return string;
			}else if (text.equals("customer")) {
				string = "+-----------------------+"
						+"\n| WELCOME Customer!!! |"
						+"\n+---------------------+";
				return string;
			}else if (text.equals("deposit")) {
				string = "+-------------------+"
						+"\n|     DEPOSIT     |"
						+"\n+-----------------+";
				return string;
			}else if (text.equals("withdraw")) {
				string = "+-------------------+"
						+"\n|     WITHDRAW     |"
						+"\n+-----------------+";
				return string;
			}else if (text.equals("transfer")) {
				string = "+-------------------------+"
						+"\n|    TRANSFER FUNDS     |"
						+"\n+-----------------------+";
				return string;
			}else if (text.equals("transactions")) {
				string = "+--------------------------------+"
						+"\n|    5 RECENT TRANSACTIONS     |"
						+"\n+------------------------------+";
				return string;
			}else if (text.equals("profile")) {
				string = "+---------------------------+"
						+"\n|    Customer Profiel     |"
						+"\n+-------------------------+";
				return string;
			}
			return string = null;
		

	}
	
	public static void welcome() {
		String welcome =
				"\n 1. Create New Account" +
				"\n 2. Login" +
				"\n 3. Exit";
		
		
		System.out.println(ANSI_BLUE + "\n" + dashboard("welcome") + ANSI_RESET + welcome);
	}
	
	
	public static String[] createAccount(Scanner sc) {
		
		boolean userNameValid = false;
		boolean validPassword = false;
		
		String userName = "";
		String password = "";
		
		System.out.println(ANSI_BLUE + "\n" + dashboard("create") + ANSI_RESET);
		System.out.println("Customer Name:" + ANSI_GREEN);
		String name = sc.nextLine();
		System.out.println(ANSI_RESET+ "Customer Address" + ANSI_GREEN);
		String address = sc.nextLine();
		System.out.println(ANSI_RESET+ "Customer Phone" + ANSI_GREEN);
		String phone = sc.nextLine();
		
		
		
		while(!userNameValid) {
			System.out.println(ANSI_RESET + "Username:" + ANSI_GREEN);
			userName = sc.nextLine();
			
			String userNameMsg = InputValidation.userNameValidation(userName);
			
			if (userNameMsg.equals("strong")) {
				userNameValid = true;
			} else {
				System.out.println(ANSI_RED + userNameMsg + ANSI_RESET);
			}
		}
		
		
		while(!validPassword) {
			
			password = ConsolePrinterUtility.createPassword(sc);
			String strong = InputValidation.passwordValidation(password);
			
			if (strong.equals("strong")) {
				validPassword = true;
			} else {
				System.out.println(ANSI_RED + strong + ANSI_RESET);
			}
		}
		
		
		
		String[] customer = {name, address, phone, userName, password};
		
		return customer;
	}
		
	
	public static String createPassword(Scanner sc) {
		System.out.println(ANSI_RESET + "Password: (6 Characters with Lower case , Upper case and a Number)" + ANSI_GREEN);
		String password = sc.nextLine();
		return password;
	}
		
	
	public static BigDecimal initialDeposit(Scanner sc) {

		System.out.println(ANSI_RESET + "Initial Deposit Amount" + ANSI_GREEN);
		BigDecimal initDeposit = new BigDecimal(sc.nextDouble());
		sc.nextLine();
		return initDeposit;
	}
	
	
	public static String[] login(Scanner sc) {
		String[] arr = new String[2];
		System.out.println(ANSI_BLUE + "\n" + dashboard("login") + ANSI_RESET);
		System.out.println("Username:" + ANSI_GREEN);
		arr[0] = sc.nextLine();
		System.out.println(ANSI_RESET + "Password:" + ANSI_GREEN);
		arr[1] = sc.nextLine();
	
		return arr;
	}
	
	
	public static void customerPage() {
		String customerPage =
				"\n 1. Deposit" +
				"\n 2. Withdraw" +
				"\n 3. Transfer Funds" +
				"\n 4. View 5 Recent Transactions" +
				"\n 5. Display Customer Information" + 
				"\n 6. Sign Out";
		System.out.println(ANSI_BLUE + "\n" + dashboard("customer") + ANSI_RESET + customerPage);
	}
	
	
	public static String depositAccount(Scanner sc) {
		System.out.println(ANSI_BLUE + "\n" + dashboard("deposit") + ANSI_RESET);
		System.out.println("Choose account to deposit $:" +
						"\n1. Checking" +
						"\n2. Savings");
		String account = sc.nextLine();
		return account;
			
		
	}
	
	
	public static BigDecimal depositAmount(Scanner sc) {
		
		System.out.println(ANSI_RESET + "How much would you like to deposit?" + ANSI_GREEN);
		BigDecimal amount = new BigDecimal(sc.nextDouble());
		sc.nextLine();
		
		return amount;
		
	}
	
	
	public static String withdrawAccount(Scanner sc) {
		
		System.out.println(ANSI_BLUE + "\n" + dashboard("withdraw") + ANSI_RESET);
		System.out.println("Choose account to withdraw $:" +
				"\n1. Checking" +
				"\n2. Savings");
		String account = sc.nextLine();
		return account;
		
	}
	
	
	public static BigDecimal withdrawAmount(Scanner sc) {
		System.out.println(ANSI_RESET + "How much would you like to withdraw?" + ANSI_GREEN);
		BigDecimal amount = new BigDecimal(sc.nextDouble());
		sc.nextLine();
		
		return amount;
	}
	
	
	public static String transferAccount(Scanner sc) {
		System.out.println("\n" + ANSI_BLUE + dashboard("transfer") + ANSI_RESET);
		System.out.println("Transfer from:");
		System.out.println("1. Checkings" +
						 "\n2. Savings");
		String account = sc.nextLine();
		return account;
	}
	
	
	public static String transferAmount(Scanner sc) {
		System.out.println(ANSI_YELLOW + "Transfer Amount:" + ANSI_GREEN);
		String amount = sc.nextLine();
		return amount;
	}
	
	
	public static void lastFiveTrans(ArrayList<String>  transactions) {
		System.out.println(ANSI_BLUE + "\n" +  dashboard("transactions") + ANSI_RESET);
		
		int i = 0;
		int j = transactions.size()-1;
		int bullet = 1;
		while (i < 5 && j >=0) {
			
			String transaction = DataGeneratorUtility.formatTransaction(transactions.get(j));
			
			System.out.println(String.valueOf(bullet) +". " + transaction);
			
			System.out.println("\n--------------------------------------------\n");
			i++;
			j--;
			bullet++;
		}
	}
	
	
	
	public static void customerInformation(String info) {
		System.out.println(ANSI_BLUE + "\n" + dashboard("profile") + ANSI_RESET);
		System.out.println(info);
	}

}
