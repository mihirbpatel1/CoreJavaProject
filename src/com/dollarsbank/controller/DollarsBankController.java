package com.dollarsbank.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;



import com.dollarsbank.model.Customer;
import com.dollarsbank.utility.ColorsUtility;
import com.dollarsbank.utility.ConsolePrinterUtility;
import com.dollarsbank.utility.DataGeneratorUtility;
import com.dollarsbank.utility.FileStorageUtility;

public class DollarsBankController implements ColorsUtility  {

		public static void welcome() {
			Scanner scanner = new Scanner(System.in);
			LocalDateTime presenTime = LocalDateTime.now();
			
			String newDate = presenTime.getDayOfWeek() + " " + presenTime.getMonthValue() + "/" +
								presenTime.getDayOfMonth() + "/" + presenTime.getYear();
			
			
			String[] customerArray;
			
			Customer customer = null;
			
			boolean done = false;
			
			while(!done) {
				try {
					
					ConsolePrinterUtility.welcome();
					System.out.print(YELLOW + "\nEnter Selection (1,2,3)" + RESET);
					String select = scanner.nextLine();
					
					if (select.equals("1")) {
						
						customerArray = ConsolePrinterUtility.createAccount(scanner);
						BigDecimal decimal = ConsolePrinterUtility.initialDeposit(scanner);
						
						customer = new Customer(customerArray[0], customerArray[1], customerArray[2], customerArray[3], customerArray[4], decimal, new BigDecimal(0));
						
						String balance = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
						
						String deposit = DataGeneratorUtility.formatDollars(decimal);
						
						ArrayList<String> transaction = new ArrayList<String>();
						transaction.add("Initial Deposit - Deposit Amount: " + deposit + " into Checking - \nChecking Balance: " + balance + 
								" - as on " + newDate + " " + LocalTime.now());
						
						customer.setTransaction(transaction);
						
						FileStorageUtility.saveCustomer(customer);
						customerPage(customer, newDate);
					
					//Login Page
					}else if (select.equals("2")) {
						
						boolean validate = false;
						
						while(!validate) {
							
							String[] login = ConsolePrinterUtility.login(scanner);
							
							String[] validateLogin = FileStorageUtility.validateLogin(login);
							
							if (validateLogin.length == 0) {
								
								String[] tranStrings = validateLogin[7].split(", ");
								ArrayList<String> transactions = new ArrayList<String>(Arrays.asList(tranStrings));
								
								customer = new Customer(validateLogin[0], validateLogin[1], validateLogin[2], validateLogin[3], 
										validateLogin[4], new BigDecimal(validateLogin[5]), 
										new BigDecimal(validateLogin[6]), transactions);
								validate = true; 
				
								customerPage(customer, newDate);
							}else {
								System.out.println(RED + "Invalid Credentials. Try Again!");
							}
						}
					}else if (select.equals("3")) {
						
						System.out.println("\nExting Good Bye!!!!");
						System.exit(0);
					}else {
						System.out.println(RED + "Invaild Input");
						welcome();
					}

					
				} catch (InputMismatchException e) {
					System.out.println(RED + "Please Input a Number");
				}
			}
			scanner.close();
		}

		private static void customerPage(Customer customer, String newDate) {
			Scanner scanner = new Scanner(System.in);
			
			
		}
}
