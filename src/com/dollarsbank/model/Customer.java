package com.dollarsbank.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Customer implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5268830798838347882L;

	private String name;
	private String phonenumber;
	private String address;
	private String username;
	private String password;
	
	private CheckingAccount checkingAccount;
	
	private SavingsAccount savingsAccount;
	
	private ArrayList<String> transaction;

	public Customer(String name, String phonenumber, String address, String username, String password,
			CheckingAccount checkingAccount, SavingsAccount savingsAccount, ArrayList<String> transaction) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.checkingAccount = checkingAccount;
		this.savingsAccount = savingsAccount;
		this.transaction = transaction;
	}
	
	// customer constutor for after the users has loged in.
	public Customer(String name, String phonenumber, String address, String username, String password,
			BigDecimal firstDeposit, BigDecimal initialSavings, ArrayList<String> transaction) {
		super();
		this.name = name;
		this.phonenumber = phonenumber;
		this.address = address;
		this.username = username;
		this.password = password;
		this.checkingAccount = new CheckingAccount(firstDeposit);
		this.savingsAccount = new SavingsAccount(initialSavings);
		this.transaction = transaction;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public ArrayList<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(ArrayList<String> transaction) {
		this.transaction = transaction;
	}

	public String toFileString() {
		String transactionsString = String.join(", ", transaction);
		return "" + name +", Adddress: "+ address +", Phone Number: "+ phonenumber +", Username: "+ username +", Password: "+ password +", Checking Account Balance: "+ checkingAccount.getBalance() +", Savings Account Balance: "+ savingsAccount.getBalance() +" "+ transactionsString;
	}
	
	
	
}
