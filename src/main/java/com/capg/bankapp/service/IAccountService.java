package com.capg.bankapp.service;

import com.capg.bankapp.model.Account;
import com.capg.bankapp.model.Customer;
import com.capg.bankapp.util.InsufficientOpeningBalanceException;

public interface IAccountService {

	public Account createAccount(Customer customer, double amount)throws InsufficientOpeningBalanceException ;

	public Account findAccount(int accountNo);

	public Account deposit(Account account, double amount);
	public Account withdrawal(Account account, double amount) throws InsufficientOpeningBalanceException;
	public void executeloop();
	
	public int sumOfNumbers(int[] arr);
}
