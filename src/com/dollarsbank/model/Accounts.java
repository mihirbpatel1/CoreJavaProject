   package com.dollarsbank.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Accounts implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8642632669691837397L;
	
	private BigDecimal balance;
	
	public Accounts(BigDecimal balance) {
		super();
		this.balance = balance;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Accounts [balance=" + balance + "]";
	}
	
	
	
	
	
}
