package model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import tools.Tools;
import model.Account;
import model.AccountType;
import model.Agency;
import model.Category;
import model.CountryCode;
import model.PeriodUnit;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;

public class TestAccount {
	
	Agency mockAgency = mock(Agency.class);
	CountryCode mockCountryCode = mock(CountryCode.class);
	AccountType mockAccountType = mock(AccountType.class);
	Account account;
	
	@Before
	public void setUp() throws Exception {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, 200, 0, 
				this.mockAgency, this.mockCountryCode, this.mockAccountType, 0);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testAccount_accountHasInvalidLength() {
		this.account = new Account("fg144265fr459",Tools.pastDate(), 120.25, 200, 0, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAccount_accountHasNullLength() {
		this.account = new Account("",Tools.pastDate(), 120.25, 200, 0, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=NullPointerException.class)
	public void testAccount_accountHasNullDate() {
		this.account = new Account("fg1265fr459",null, 120.25, 200, 0, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAccount_accountHasInvalidDate() {
		this.account = new Account("fg1265fr459",Tools.futureDate(), 120.25, 200, 0, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAccount_accountHasInvalidOverdraft() {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, -250, 0, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAccount_accountHasInvalidInterestRate() {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, 250, -0.6, 
				mock(Agency.class), mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=NullPointerException.class)
	public void testAccount_accountHasNullAgency() {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, 250, 0, 
				null, mock(CountryCode.class), mock(AccountType.class), 0);
	}
	@Test(expected=NullPointerException.class)
	public void testAccount_accountHasNullCountryCode() {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, 250, 0, 
				mock(Agency.class), null, mock(AccountType.class), 0);
	}
	@Test(expected=NullPointerException.class)
	public void testAccount_accountHasNullAccountType() {
		this.account = new Account("fg1265fr459",Tools.pastDate(), 120.25, 250, 0, 
				null, mock(CountryCode.class), null, 0);
	}
	
	
	@Test
	public void testGetId() {
		this.account.setId(1);
		assertEquals(1, this.account.getId().intValue());
	}
	@Test
	public void testSetId(){
		this.account.setId(1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetId_isInvalid() {
		this.account.setId(-1);
	}
	
	
	@Test
	public void testGetAccountNumber() {
		assertEquals("fg1265fr459", this.account.getAccountNumber());
	}
	@Test
	public void testSetAccountNumber(){
		this.account.setAccountNumber("03216203147");
	}
	@Test(expected=NullPointerException.class)
	public void testSetAccountNumber_Null(){
		Account tested2=new Account ();
		tested2.setAccountNumber(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetAccountNumber_Empty(){
		Account tested2=new Account ();
		tested2.setAccountNumber("");
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetAccountNumber_InvalidLength(){
		Account tested2=new Account ();
		tested2.setAccountNumber("fg144265fr459");
	}
	
	
	@Test
	public void testGetCreationDate() {
		assertEquals(Tools.pastDate(), this.account.getCreationDate());
	}
	@Test
	public void testSetCreationDate(){
		this.account.setCreationDate(Tools.pastDate());
	}
	@Test(expected=NullPointerException.class)
	public void testSetCreationDate_Null(){
		Account tested2=new Account ();
		tested2.setCreationDate(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetCreationDate_Invalid(){
		Account tested2=new Account ();
		tested2.setCreationDate(Tools.futureDate());
	}
	
	
	@Test
	public void testGetFirstTotal() {
		assertEquals(120.25, this.account.getFirstTotal(), 0);
	}
	@Test
	public void testSetFirstTotal(){
		this.account.setFirstTotal(120.25);
	}

	
	@Test
	public void testGetOverdraft() {
		assertEquals(200, this.account.getOverdraft());
	}
	@Test
	public void testSetOverdraft(){
		this.account.setOverdraft(200);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetOverdraft_Invalid(){
		Account tested2=new Account ();
		tested2.setOverdraft(-250);
	}
	

	@Test
	public void testGetInterestRate() {
		assertEquals(0, this.account.getInterestRate(), 0);
	}
	@Test
	public void testSetInterestRate(){
		this.account.setInterestRate(0.6);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetInterestRate_Invalid(){
		Account tested2=new Account ();
		tested2.setInterestRate(-0.6);
	}

	
	@Test
	public void testGetAgency() {
		assertEquals(this.mockAgency, this.account.getAgency());
	}
	@Test
	public void testSetAgency(){
		this.account.setAgency(mock(Agency.class));
	}
	@Test(expected=NullPointerException.class)
	public void testSetAgency_Null(){
		Account tested2=new Account ();
		tested2.setAgency(null);
	}

	
	@Test
	public void testGetCountryCode() {
		assertEquals(this.mockCountryCode, this.account.getCountryCode());
	}
	@Test
	public void testSeCountryCode(){
		this.account.setCountryCode(mock(CountryCode.class));
	}
	@Test(expected=NullPointerException.class)
	public void testSetCountryCode_Null(){
		Account tested2=new Account ();
		tested2.setCountryCode(null);
	}

	
	@Test
	public void testGetAccountType() {
		assertEquals(this.mockAccountType, this.account.getAccountType());
	}
	@Test
	public void testSetAccountType(){
	this.account.setAccountType(mock(AccountType.class));
	}
	@Test(expected=NullPointerException.class)
	public void testSetAccountType_Null(){
		Account tested2=new Account ();
		tested2.setAccountType(null);
	}

	@Test
	public void testGetAlertThresh() {
		assertEquals(0, this.account.getAlertThresh());
	}
	@Test 
	public void testSetAlertThresh(){
		this.account.setAlertThresh(0);
	}

	
	@Test
	public void testGetTransactions() {
		assertEquals(new ArrayList<PeriodicTransaction>(), this.account.getTransactions());
	}
	@Test
	public void testSetTransactions(){
		this.account.setTransactions(new ArrayList<PeriodicTransaction>());
	}
	@Test(expected=NullPointerException.class)
	public void testSetTransactions_Null(){
		this.account.setTransactions(null);
	}

	
	@Test
	public void testAddTransactions() {
		Account tmp_acc = this.account;
		PeriodicTransaction tmp = new PeriodicTransaction("Ligne 1",(Double)21.23, Tools.pastDate(), Tools.pastDate(), 2, "Ligne de test", 
				mock(TransactionType.class), mock(TargetTransaction.class), mock(Category.class), mock(PeriodUnit.class));
		int numberTransaction = this.account.getTransactions().size();
		tmp_acc.addTransactions(tmp);
		assertEquals(numberTransaction+1, this.account.getTransactions().size());
		
	}

}
