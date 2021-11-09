package com.dollarsbank.model;

import java.math.BigDecimal;

public class SavingsAccount extends Accounts {
	
	private static final long serialVerisonUID =  1L;
	
	public SavingsAccount(BigDecimal balance) {
			super(balance);
	}
}
