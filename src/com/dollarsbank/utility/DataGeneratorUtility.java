package com.dollarsbank.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import com.dollarsbank.model.Customer;

public class DataGeneratorUtility implements ColorsUtility {
	
	public static String formatDollars(BigDecimal balance) {
		
		NumberFormat usdFormat = NumberFormat.getCurrencyInstance(Locale.US);
		
		usdFormat.setMinimumFractionDigits(2);
		usdFormat.setMaximumFractionDigits(2);
		
		return usdFormat.format(balance);
	}
	
	public static String formatTransaction(String transaction) {
		
		String[] arrs = transaction.split(" - ");
		String tranString="";
			
			for (String words : arrs) {
				 tranString += words+"\n";
			}
		
		return  tranString;
		
	}
	
	public static String formatUserInfo(Customer customer) {
		String checking = DataGeneratorUtility.formatDollars(customer.getCheckingAccount().getBalance());
		String savings = DataGeneratorUtility.formatDollars(customer.getSavingsAccount().getBalance());
		
		String customerInfo = ANSI_CYAN + "Customer Profile\n" + ANSI_RESET +
							"\nName: " + customer.getName() +
							"\nAddress: " + customer.getAddress() +
							"\nPhone Number: " + customer.getPhonenumber() +
							"\nUsername: " + customer.getUsername() + 
							"\nPassword: " + customer.getPassword() + 
							ANSI_CYAN + "\n\nAccount Info" + ANSI_RESET +
							"\nChecking Balance: " + ANSI_GREEN + checking + ANSI_RESET + 
							"\nSavings Balance: " + ANSI_GREEN + savings + ANSI_RESET;
		
		return customerInfo;
		
	}

}
