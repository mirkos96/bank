package model;

import static org.junit.Assert.*;

import org.junit.Test;
import model.AccountType;


public class TestAccountType {

	@Test(expected = IllegalArgumentException.class)
	public void testAccountType_IsEmpty() {
		new AccountType("");
	}
	
	@Test(expected = NullPointerException.class)
	public void testAccountType_IsNull() {
		new AccountType(null);
	}  

	@Test
	public void testGetId() {
		this.tested.setId(1);
		assertEquals(1,this.tested.getId().intValue());
	}

	@Test
	public void testSetId(){
		this.tested.setId(1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetId_IsInvalid() {
		this.tested.setId(-3);
	}

	@Test
	public void testGetAccountType() {
		assertEquals("foo",this.tested.getAccountType());
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetAccountType_IsEmpty(){
		tested.setAccountType("");
	}
	
	@Test (expected = NullPointerException.class)
	public void testSetAccountType_IsNull(){
		tested.setAccountType(null);
	}

	@Test 
	public void testEquals_isValid(){
		AccountType tested2=new AccountType("foo");
		assertTrue(this.tested.equals(tested2));
	}
	@Test 
	public void testEquals_isInvalid(){
		AccountType tested2=new AccountType("fool");
		assertFalse(this.tested.equals(tested2));
	}
	
	private AccountType tested=new AccountType("foo");

}
