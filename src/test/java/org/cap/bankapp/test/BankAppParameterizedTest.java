package org.cap.bankapp.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import com.capg.bankapp.model.Address;
import com.capg.bankapp.model.Customer;
import com.capg.bankapp.service.AccountServiceImpl;
import com.capg.bankapp.service.IAccountService;

@DisplayName("Test -> Parameterized Test cases")
public class BankAppParameterizedTest {
	
	
	private IAccountService accountService;
	
	@BeforeEach
	public void setUp() {
		accountService=new AccountServiceImpl();
		
		
	}
	
	@DisplayName("Sum of two numbers.")
	@ParameterizedTest(name = "{index} => a= {0}, b= {1}, sum= {2}")
	/*
	 * @CsvSource({ "1,2,3", "11,22,33", "1,1,2", "1,-1,0", "1,5,6", })
	 */
	@MethodSource("argprovider")
	public void test_SumOfnum(int a,int b, int sum) {
		assertEquals(sum, a+b);
	}
	
	
	private static Stream<Arguments> argprovider(){
		return Stream.of(
				Arguments.of(1,1,2),
				Arguments.of(11,21,32),
				Arguments.of(1,-1,0),
				Arguments.of(100,-1,99)
				);
	}
	

	
	@DisplayName("Sum of Array Test.")
	  @ParameterizedTest
	  //@CsvSource({ "{1,2,3}, 6" , "{5,5,5}, 15" , "{1,-1,0}, 0" , })
	  @MethodSource("arrayArgProvider")
	  public void 	test_sumOfNumbers(int[] arr,int sum) { assertEquals(
	  sum,accountService.sumOfNumbers(arr));
	  
	  }
	
	
	
	private static Stream<Arguments> arrayArgProvider(){
		return Stream.of(
				Arguments.of(new int[]{1,2,3},6),
				Arguments.of(new int[]{2,2,2,2},8),
				Arguments.of(new int[]{1,2,1},4),
				Arguments.of(new int[]{1,2,2},5)
				);
	}
}
