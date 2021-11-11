package com.dollarsbank.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.lang.model.element.NestingKind;
import javax.swing.TransferHandler;

import com.dollarsbank.model.Accounts;
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
					System.out.println(ANSI_GREEN + "\nEnter Choice (1,2 or 3)" + ANSI_RESET);
					String select = scanner.nextLine();
					
					if (select.equals("1")) {
						
						customerArray = ConsolePrinterUtility.createAccount(scanner);
						BigDecimal decimal = ConsolePrinterUtility.initialDeposit(scanner);
						
						customer = new Customer(customerArray[0], customerArray[1], customerArray[2], customerArray[3], customerArray[4], decimal, new BigDecimal(0));
						
						String balance = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
						
						String deposit = DataGeneratorUtility.formatDollars(decimal);
						
						ArrayList<String> transaction = new ArrayList<String>();
						transaction.add("Initial Deposit - Deposit Amount: " + deposit + " into Checking - \nChecking Account Balance: " + balance + 
								" - as on " + newDate + " " + LocalTime.now());
						
						customer.setTransaction(transaction);
						
						
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
								System.out.println(ANSI_RED + "Invaild Input");
							}
						}
					}else if (select.equals("3")) {
						
						System.out.println("\nExting Good Bye!!!!");
						System.exit(0);
					}else {
						System.out.println(ANSI_RED + "Invaild Input");
						welcome();
					}

					
				} catch (InputMismatchException e) {
					System.out.println(ANSI_RED + "Please Input a Number");
				}
			}
			scanner.close();
		}

		public static void customerPage(Customer customer, String newDate) {
			Scanner scanner = new Scanner(System.in);
			ArrayList<String> transaction = customer.getTransaction();
			boolean done = false;
			
			while(!done) {
				try {
						ConsolePrinterUtility.customerPage();
						System.out.println(ANSI_GREEN + "Enter Choice (1, 2, 3, 4, 5 or 6" + ANSI_RESET);
						String select = scanner.nextLine();
						
						
						// Customer Deposit
						
						if (select.equals("1")) {
							
							boolean accountValid = false;
							String account= "";
							while(!accountValid) {
								
								
								account = ConsolePrinterUtility.depositAccount(scanner);
								if (account.equals("1") || account.equals("2")) {
									accountValid = true;
								} else {
									System.out.println(ANSI_RED + "Invalid Selection. Expecting 1 or 2" + ANSI_RESET);
								}
							}
							
							
							BigDecimal amountDeposite = ConsolePrinterUtility.depositAmount(scanner);
							String deposit = DataGeneratorUtility.formatDollars(amountDeposite);
							
							//Deposit to checking
							if (account.equals("1")) {
								BigDecimal checking = customer.getCheckingAccount().getBalance();
								customer.getCheckingAccount().setBalance(checking.add(amountDeposite));
								
								String balance = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
								
								transaction.add("Deposit into Checking - Deposit Amount: " + deposit + " - \nChecking Account Balance: " + balance + 
											" - as on " + newDate + " " + LocalTime.now());
						
								
							//Deposit to savings	
							} else if (account.equals("2")) {
								BigDecimal savings = customer.getSavingsAccount().getBalance();
								customer.getSavingsAccount().setBalance(savings.add(amountDeposite));
								
								String balance = DataGeneratorUtility.formatDollars(customer.getSavingsAccount().getBalance());
								transaction.add("Deposit into Savings - Deposit Amount: " + deposit + " - \nSavings Account Balance: " + balance + 
											" - as on " + newDate + " " + LocalTime.now());

							} else {
								
							}
							
							
							
						//Withdraw
						} else if (select.equals("2")) {
							
							boolean accountValid = false;
							String account= "";
							while(!accountValid) {
								
								account = ConsolePrinterUtility.withdrawAccount(scanner);
								if (account.equals("1") || account.equals("2")) {
									accountValid = true;
								} else {
									System.out.println(ANSI_RED + "Invaild Input" + ANSI_RESET);
								}
							}
							
							BigDecimal amountWithdarwal = ConsolePrinterUtility.withdrawAmount(scanner);
							String withdraw = DataGeneratorUtility.formatDollars(amountWithdarwal);
							
							
							
							//Withdraw from checking
							if (account.equals("1")) {
								
								BigDecimal checkingBalance = customer.getCheckingAccount().getBalance();
								
								if (amountWithdarwal.compareTo(checkingBalance) == 1) {
									System.out.println(ANSI_RED + "Insufficient Funds");
								} else {
									
									customer.getCheckingAccount().setBalance(checkingBalance.subtract(amountWithdarwal));
									
									String balance = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
									transaction.add("Withdraw from Checking - Withdraw Amount: " + withdraw + " - \nChecking Account Balance: " + 
														balance + " - as on " + newDate + " " + LocalTime.now());
									
								}
								
							//Withdraw from savings
							} else if (account.equals("2")) {
								
								BigDecimal savingsBalance = customer.getSavingsAccount().getBalance();
								
								if (amountWithdarwal.compareTo(savingsBalance) == 1) {
									System.out.println(ANSI_RED + "Insufficient Funds");
								
								} else {
									customer.getSavingsAccount().setBalance(savingsBalance.subtract(amountWithdarwal));;
									
									String balance = DataGeneratorUtility.formatDollars(customer.getSavingsAccount().getBalance());
									transaction.add("Withdraw from Savings - Amount of : " + withdraw + 
														" - \nSavings Account Balance: " + balance + " - as of " 
														+ newDate + " " + LocalTime.now());


							}
								customer.setTransaction(transaction);	
								
							}
							
						//Transfer funds
						} else if (select.equals("3")) {
							boolean accountValid = false;
							String account= "";
							while(!accountValid) {
								
								account = ConsolePrinterUtility.transferAccount(scanner);
								if (account.equals("1") || account.equals("2")) {
									accountValid = true;
								} else {
									System.out.println(ANSI_RED + "Invaild Input"  + ANSI_RESET);
								}
							}
							
							BigDecimal transfer =  new BigDecimal(ConsolePrinterUtility.transferAmount(scanner));
							String amountToTransfer = DataGeneratorUtility.formatDollars(transfer);
							
							
							//Transfer from checking to savings
							if (account.equals("1")) {
								
								if (transfer.compareTo(customer.getCheckingAccount().getBalance()) == 1) {
									System.out.println(ANSI_RED + "Insufficient Funds");
								} else {
									BigDecimal checkingsAccountBalance = customer.getCheckingAccount().getBalance();
									BigDecimal savingsAccountBalance = customer.getSavingsAccount().getBalance();
									
									customer.getCheckingAccount().setBalance(checkingsAccountBalance.subtract(transfer));
									customer.getSavingsAccount().setBalance(savingsAccountBalance.add(transfer));
									
									String checking = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
									String savings = DataGeneratorUtility.formatDollars(customer.getSavingsAccount().getBalance());
									transaction.add("Transfer funds from Checking into Savings ----  Amount of : " + 
														amountToTransfer + " - \nChecking Account Balance: " + ANSI_GREEN + checking + ANSI_RESET + 
														" Saving Account Balance: " + ANSI_GREEN + savings + ANSI_RESET + 
														" - as of " + newDate + " " + LocalTime.now());

									
									customer.setTransaction(transaction);
								}
							//Transfer from savings to Checking	
							} else if (account.equals("2")) {
								
								if (transfer.compareTo(customer.getSavingsAccount().getBalance()) == 1) {
									System.out.println(ANSI_RED + "Insufficient Funds");
								} else {
									
									BigDecimal savingsAccountBalance = customer.getSavingsAccount().getBalance();
									BigDecimal checkingsAccountBalance = customer.getCheckingAccount().getBalance();
									
									customer.getSavingsAccount().setBalance(savingsAccountBalance.subtract(transfer));
									customer.getCheckingAccount().setBalance(checkingsAccountBalance.add(transfer));
									
									String checking = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
									String savings = DataGeneratorUtility.formatDollars(customer.getSavingsAccount().getBalance());
									transaction.add("Transfer funds from Savings into Checking - Amount of: " + 
														amountToTransfer + " - \nSavings Account Balance: " + savings + 
														" - Checking Account Balance: " + checking + 
														" - as of " + newDate + " " + LocalTime.now());
				
									
									customer.setTransaction(transaction);
								}
							}
							
							
						// 5 Recent transactions
						} else if (select.equals("4")) {
							ConsolePrinterUtility.lastFiveTrans(transaction);									
						
						//Customer Profile
						} else if (select.equals("5")) {
							String formattedUserInfo = DataGeneratorUtility.formatUserInfo(customer);
							
							ConsolePrinterUtility.customerInformation(formattedUserInfo);
						
							
						//Log off	
						} else if (select.equals("6")) {
							
							FileStorageUtility.saveCustomer(customer);
							
							System.out.println("Logging off...");
							done = true;
						} 
						
						
					} catch (InputMismatchException e){
						System.out.println(ANSI_RED + "Invalid input");
					}
			}
			
		}
}
