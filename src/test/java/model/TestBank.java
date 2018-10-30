package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Bank;

public class TestBank {

	private Bank tested;
	
	@Before
	public void initBank(){
		tested=new Bank("foo","bar");
	}
	
	
	@Test(expected  = NullPointerException.class)
	public void testCountryCode_BankNameIsNull() {
		new Bank(null,"bar");
	}
	@Test(expected  = IllegalArgumentException.class)
	public void testCountryCode_BankNameIsEmpty() {
		new Bank("","bar");
	}
	
	@Test(expected  = NullPointerException.class)
	public void testCountryCode_BankCodeIsNull() {
		new Bank("foo",null);
	}
	@Test(expected  = IllegalArgumentException.class)
	public void testCountryCode_BankCodeIsEmpty() {
		new Bank("foo","");
	}

	@Test
	public void testGetId() {
		initBank();
		tested.setId(1);
		assertEquals(1,tested.getId().intValue());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetId_Invalid() {
		initBank();
		tested.setId(-2);
	}
	@Test
	public void testSetId() {
		initBank();
		tested.setId(2);
	}
	
	
	@Test
	public void testGetBankName() {
		assertEquals("foo",tested.getBankName());
	}
	@Test(expected=NullPointerException.class)
	public void testSetBankName_Null(){
		tested.setBankName(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetBankName_Empty(){
		tested.setBankName("");
	}
	@Test
	public void testSetBankName(){
		tested.setBankName("ba");
	}
	

	@Test
	public void testGetBankCode() {
		assertEquals("bar",tested.getBankCode());
	}
	@Test(expected=NullPointerException.class)
	public void testSetBankCode_Null(){
		tested.setBankCode(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetBankCode_Empty(){
		tested.setBankCode("");
	}
	@Test
	public void testSetBankCode(){
		tested.setBankCode("fo");
	}
	

	@Test
	public void testEquals_isValid(){
		Bank tested_bank=new Bank("foo","bar");
		assertTrue(tested.equals(tested_bank));
	}
	@Test
	public void testEquals_isInvalid(){
		Bank tested_bank=new Bank("fo","bar");
		assertFalse(tested.equals(tested_bank));
	}	

}
