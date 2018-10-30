package manager;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.junit.Before;
import org.junit.Test;

import model.AccountType;
import model.ItemDoesNotExistException;

@ManagedBean
public class AccountTypeManagerTest extends EjbContainerTest{

	@EJB
	private AccountTypeManager accountTypeManager;
	AccountType accountType1;
	AccountType accountType2;
	AccountType accountType3;
	
	@Before
	public void init(){
		accountType1 = new AccountType("type de compte");
		accountType2 = new AccountType("type de compte1");
		accountType3 = new AccountType("type de compte2");
	}
	
	@Test
	public void persistTransactionType() throws Exception{
		accountTypeManager.save(accountType1);
		AccountType a = accountTypeManager.findByName("type de compte");
		assertThat(a.getAccountType(), is("type de compte"));
		
	}
	
	@Test(expected=ItemDoesNotExistException.class)
	public void accountTypeDoesNotExist() throws ItemDoesNotExistException{
		accountTypeManager.findByName("type de compte inexistant");
	}
	
	@Test
	public void retrieveAllAccountType(){
		accountTypeManager.save(accountType1);
		accountTypeManager.save(accountType2);
		accountTypeManager.save(accountType3);
		
		List<AccountType> types = accountTypeManager.findAll();
		List<String> retrivedTypes = new ArrayList<String>();
		List<String> expectedTypes = new ArrayList<String>();
		expectedTypes.add("type de compte");
		expectedTypes.add("type de compte1");
		expectedTypes.add("type de compte2");
		

		for(AccountType a : types){
			retrivedTypes.add(a.getAccountType());
		}
		
		assertThat(retrivedTypes, is(expectedTypes));

	}
}
