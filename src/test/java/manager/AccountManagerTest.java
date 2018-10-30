package manager;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.ItemAlreadyExistingException;
import model.ItemDoesNotExistException;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.CountryCode;
import model.CpCity;

@ManagedBean
public class AccountManagerTest extends EjbContainerTest {

	@EJB
	private AccountManager accountManager;
	@EJB
	private AgencyManager agencyManager;
	@EJB
	private CountryCodeManager countryCodeManager;
	@EJB
	private AccountTypeManager accountTypeManager;
	
	private Agency agencyTest;
	private CountryCode countryCodeTest;
	private AccountType accountTypeTest;
	private Date today;
	
	private Account account;
	
	
	@Before
	public void init() throws Exception{
		
		EntityManager accountEntityManager = accountManager.getEntityManager();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.today = sdf.parse(sdf.format(new Date()));
		
		tx.begin();
		CpCity cpCity = new CpCity("40120", "Roquefort");
		accountEntityManager.persist(cpCity);
		Address address = new Address("line 1", "line 2", cpCity);
		accountEntityManager.persist(address);
		Bank bank = new Bank("bank", "bank_code");
		accountEntityManager.persist(bank);
		Agency agency = new Agency("agency", "12345", address, bank);
		accountEntityManager.persist(agency);
		AccountType accountType = new AccountType("Compte courant");
		accountEntityManager.persist(accountType);
		CountryCode countryCode = new CountryCode("FR");
		accountEntityManager.persist(countryCode);
		tx.commit();

		agencyTest = agencyManager.findById(agency.getId());
		countryCodeTest = countryCodeManager.findById(countryCode.getId());
		accountTypeTest = accountTypeManager.findById(accountType.getId());	
		this.account = new Account("number",today, 20.0, 0, 0, agencyTest, countryCodeTest, accountTypeTest, 0);
		
	}
	
	@Test
	public void persistAccount() throws Exception {
		accountManager.save(this.account);
		
		Account account = accountManager.findByAccountNumber("number");
		
		assertThat(account.getAccountNumber(), is("number"));
		assertThat(account.getAccountType().getId(), is(accountTypeTest.getId()));
		assertThat(account.getAgency().getId(), is(agencyTest.getId()));
		assertThat(account.getAlertThresh(), is(0));
		assertThat(account.getCountryCode().getId(), is(countryCodeTest.getId()));
		assertThat(account.getCreationDate(), is(today));
		assertThat(account.getFirstTotal(), is(20.0));
		assertThat(account.getInterestRate(), is(0.0));
		assertThat(account.getOverdraft(), is(0));
	}
	
	@Test
	public void editAccount() throws Exception{
		accountManager.save(this.account);
		Account retrievedAccount = accountManager.findByAccountNumber("number");
		
		retrievedAccount.setAccountNumber("number2");
		accountManager.edit(retrievedAccount, retrievedAccount.getId());
		retrievedAccount = accountManager.findByAccountNumber("number2");
		
	}
	
	@Test
	public void retrieveAccountById() throws Exception{
		accountManager.save(this.account);
		Account retrievedAccount = accountManager.findByAccountNumber("number");
		retrievedAccount = accountManager.findById(retrievedAccount.getId());
		
		assertThat(retrievedAccount.getAccountNumber(), is("number"));
	}

	@Test(expected=ItemAlreadyExistingException.class)
	public void cannotPersistAlreadyExistingAccount() throws Exception {
		accountManager.save(this.account);
		accountManager.save(this.account);
	}

	@Test(expected=ItemDoesNotExistException.class)
	public void cannotGetUnexistingAccount() throws Exception {
		accountManager.findByAccountNumber("unknown account number");
	}
	
	@Test
	public void getAllAccounts() throws Exception{
		accountManager.save(new Account("number",today, 20.0, 0, 0, agencyTest, countryCodeTest, accountTypeTest, 0));
		accountManager.save(new Account("number1",today, 20.0, 0, 0, agencyTest, countryCodeTest, accountTypeTest, 0));
		accountManager.save(new Account("number2",today, 20.0, 0, 0, agencyTest, countryCodeTest, accountTypeTest, 0));

		List<Account> accounts = accountManager.findAll();
		
		List<String> numbers = new ArrayList<String>();
		List<String> expectedNumbers = new ArrayList<String>();
		expectedNumbers.add("number");
		expectedNumbers.add("number1");
		expectedNumbers.add("number2");

		for(Account a : accounts){
			numbers.add(a.getAccountNumber());
		}
		
		assertThat(numbers, is(expectedNumbers));
	}
	
	@Test
	public void getSingleAccount() throws Exception{
		accountManager.save(this.account);
		accountManager.findByAccountNumber("number");
	}
}
