package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.CountryCode;

public class TestCountryCode {

	@Test(expected  = NullPointerException.class)
	public void testCountryCode_codeIsNull() {
		new CountryCode(null);
	}
	@Test(expected  = IllegalArgumentException.class)
	public void testCountryCode_codeIsTooLong() {
		new CountryCode("ZZZ");
	}
	@Test(expected  = IllegalArgumentException.class)
	public void testCountryCode_codeIsEmpty() {
		new CountryCode("");
	}
	
	
	@Test
	public void testGetId() {
		tested.setId(1);
		assertEquals(1,tested.getId().intValue());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testSetId_Invalid() {
		tested.setId(-2);
	}
	@Test
	public void testSetId_Valid() {
		tested.setId(2);
	}

	
	@Test
	public void testGetCountryCode() {
		assertEquals("ZZ",tested.getCountryCode());
	}
	@Test 
	public void testSetCountryCode(){
		tested.setCountryCode("ZZ");
	}
	@Test(expected=IllegalArgumentException.class) 
	public void testSetCountryCode_Invalid(){
		tested.setCountryCode("Z");
	}
	@Test(expected=NullPointerException.class) 
	public void testSetCountryCode_Null(){
		tested.setCountryCode(null);
	}
	
	
	@Test
	public void testEquals_IsValid() {
		CountryCode tested2 = new CountryCode("ZZ");
		assertTrue(tested.equals(tested2));
	}
	@Test
	public void testEquals_IsInvalid() {
		CountryCode tested2 = new CountryCode("AA");
		assertFalse(tested.equals(tested2));
	}
	
	private CountryCode tested=new CountryCode("ZZ");
}
