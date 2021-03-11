package org.cap.bankapp.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.capg.bankapp.dao.IAccountDao;
import com.capg.bankapp.model.Account;
import com.capg.bankapp.model.Address;
import com.capg.bankapp.model.Customer;
import com.capg.bankapp.service.AccountServiceImpl;
import com.capg.bankapp.service.IAccountService;
import com.capg.bankapp.util.InsufficientOpeningBalanceException;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.WARN)
class BankAppTest {

	
	private IAccountService accountService;
	
	@Mock private IAccountDao accountDao;
	private Customer customer;
	
	
	@BeforeEach
	public void setUp() {
		accountService=new AccountServiceImpl(accountDao);
		
		
		Address address=new Address("North Avvenue", "Pune");
		
		customer=new Customer();
		customer.setCustomerName("Tom");
		customer.setAddress(address);
	}
	
	@Test
	public void test_createAccount_with_null_customer() throws InsufficientOpeningBalanceException {
		Customer customer1=null;
		
		assertThrows(IllegalArgumentException.class, ()->{
			accountService.createAccount(customer1, 1000);
		});
		
		
	}
	
	@Test
	public void test_invalidBalance_when_createAccount() {
		assertThrows(InsufficientOpeningBalanceException.class, ()->{
			accountService.createAccount(customer, 100);
		});
	}
	
	
	@Disabled
	@Test
	public void test_loop() {
		
		assertTimeout(Duration.ofNanos(1), ()->{
			accountService.executeloop();
		});
		
	}
	
	
	
	@Test
	public void test_deposit_method() {
		Account accountMock=new Account();
		accountMock.setAccountNo(123);
		accountMock.setCustomer(customer);
		accountMock.setOpeningBalance(5000);
		
		
		
		//Mockito Declaration
		Mockito.when(accountDao.findAccount(123)).thenReturn(accountMock);
		
		//Call actual business logic
		//Account account= accountService.findAccount(123);
		accountService.deposit(accountMock, 3000);
		
		
		Mockito.verify(accountDao).findAccount(123);
		
		assertEquals(accountMock.getOpeningBalance(), 8000.0);
	}
	
	
	
	
	@Test
	public void test_withDrawal_transaction() throws InsufficientOpeningBalanceException {
		
		Account accountMock=new Account();
		accountMock.setAccountNo(123);
		accountMock.setCustomer(customer);
		accountMock.setOpeningBalance(5000);
		
		
		Mockito.when(accountDao.findAccount(123)).thenReturn(accountMock);
		
		Account account=accountService.withdrawal(accountMock, 3000.0);
		
		Mockito.verify(accountDao).findAccount(123);
		
		assertEquals(accountMock.getOpeningBalance(), 2000.0);
		
	}
	
	
	
	
	
	@Test
	public void test_createAccount_with_valid_Customer() throws InsufficientOpeningBalanceException {
		
		/*
		 * Address address=new Address("North Avvenue", "Pune");
		 * 
		 * Customer customer=new Customer(); customer.setCustomerName("Tom");
		 * customer.setAddress(address);
		 */
		Account accountMock=new Account();
		accountMock.setAccountNo(123);
		accountMock.setCustomer(customer);
		accountMock.setOpeningBalance(5000);
		
		
		Mockito.when(accountDao.saveAccount(accountMock)).thenReturn(true);
		
		Account account= accountService.createAccount(customer, 5000);
		
		
		Mockito.verify(accountDao).saveAccount(accountMock);
		
		assertEquals(account.getOpeningBalance(), accountMock.getOpeningBalance());
		
	}

}
