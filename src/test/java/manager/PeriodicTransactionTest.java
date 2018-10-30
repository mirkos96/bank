package manager;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.text.ParseException;
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
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;
import model.AccountType;
import model.Address;
import model.Agency;
import model.Bank;
import model.Category;
import model.CountryCode;
import model.CpCity;


@ManagedBean
public class PeriodicTransactionTest extends EjbContainerTest{

	@EJB
	private PeriodicTransactionManager periodicTransactionManager;
	@EJB
	private AccountManager accountManager;
	@EJB
	private AgencyManager agencyManager;
	@EJB
	private CountryCodeManager countryCodeManager;
	@EJB
	
	private AccountTypeManager accountTypeManager;
	private PeriodicTransaction transaction;
	private TransactionType transactionTypeTest;
	private TargetTransaction targetTransactionTest;
	private Category categoryTest;
	private Date today;
	private Agency agencyTest;
	private CountryCode countryCodeTest;
	private AccountType accountTypeTest;
	private Account account;
	
	@Before
	public void init() throws Exception{
		tx.begin();
		/**
		 * Setting up transaction
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.today = sdf.parse(sdf.format(new Date()));
		TransactionType transactionType = new TransactionType("Virement");
		em.persist(transactionType);
		TargetTransaction targetTransaction = new TargetTransaction("Target", "FR3456789123456789123456789");
		em.persist(targetTransaction);
		Category category = new Category("catégorie", null);
		em.persist(category);

		/**
		 * Setting up the account linked to the transaction
		 */
		CpCity cpCity = new CpCity("40120", "Roquefort");
		em.persist(cpCity);
		Address address = new Address("line 1", "line 2", cpCity);
		em.persist(address);
		Bank bank = new Bank("bank", "bank_code");
		em.persist(bank);
		Agency agency = new Agency("agency", "12345", address, bank);
		em.persist(agency);
		AccountType accountType = new AccountType("Compte courant");
		em.persist(accountType);
		CountryCode countryCode = new CountryCode("FR");
		em.persist(countryCode);
		tx.commit();

		agencyTest = agencyManager.findById(agency.getId());
		countryCodeTest = countryCodeManager.findById(countryCode.getId());
		accountTypeTest = accountTypeManager.findById(accountType.getId());	
		this.account = new Account("number",today, 20.0, 0, 0, agencyTest, countryCodeTest, accountTypeTest, 0);
		
		transactionTypeTest = em.find(TransactionType.class, transactionType.getId());
		targetTransactionTest = em.find(TargetTransaction.class, targetTransaction.getId());
		categoryTest = em.find(Category.class, category.getId());
		
		transaction = new PeriodicTransaction("transaction", 20.0, today, null, 0, "transaction test", 
											  transactionTypeTest, targetTransactionTest, categoryTest, null);
	}
	
	@Test
	public void persistTransaction() throws Exception {
		
		periodicTransactionManager.saveTransaction(transaction);
		PeriodicTransaction retrievedTransaction = periodicTransactionManager.findById(transaction.getId());
		
		assertThat(retrievedTransaction.getCategory().getWording(), is(transaction.getCategory().getWording()));
		assertThat(retrievedTransaction.getDateOperation(), is(transaction.getDateOperation()));
		assertThat(retrievedTransaction.getTargetTransaction().getIBAN(), is(transaction.getTargetTransaction().getIBAN()));
		assertThat(retrievedTransaction.getTransactionType().getWording(), is(transaction.getTransactionType().getWording()));
		assertThat(retrievedTransaction.getWording(), is(transaction.getWording()));
		assertThat(retrievedTransaction.getDescription(), is(transaction.getDescription()));
		assertThat(retrievedTransaction.getTransactionValue(), is(transaction.getTransactionValue()));

	}
	
	@Test
	public void retrieveTransactionsByAccount() throws Exception{
		accountManager.save(account);
		transaction.setAccount(accountManager.findById(account.getId()));
		periodicTransactionManager.saveTransaction(transaction);
		PeriodicTransaction retrievedTransaction = periodicTransactionManager.findById(transaction.getId());
		
		List<PeriodicTransaction> transactions = periodicTransactionManager.findAllByAccountId(retrievedTransaction.getAccount().getId());
		assertThat(transactions.get(0).getWording(), is(retrievedTransaction.getWording()));
	}
	
	@Test(expected=ItemDoesNotExistException.class)
	public void deleteTransaction() throws ItemDoesNotExistException{
		periodicTransactionManager.saveTransaction(transaction);
		periodicTransactionManager.DeleteTransaction(transaction.getId());
		periodicTransactionManager.findById(transaction.getId());
		
	}
	
}
