package model;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import org.junit.Test;

import model.Address;
import model.CpCity;

public class TestAddress {

	private Address tested;
	private CpCity mockCpCity = mock(CpCity.class);
	
	public void setAddress(){
		this.tested = new Address("Route des landes", null, this.mockCpCity);
		this.tested.setId(1);
	}
	
	@Test(expected=NullPointerException.class)
	public void testAddress_Line_isNull() {
		new Address(null, null, mock(CpCity.class));
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAddress_Line_isEmpty() {
		new Address("", null, mock(CpCity.class));
	}
	@Test(expected=NullPointerException.class)
	public void testAddress_CpCity_isNull() {
		new Address("Route des landes", null, null);
	}
	
	@Test
	public void testGetId() {
		setAddress();
		assertEquals(1, tested.getId().intValue());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetId_isInvalid() {
		setAddress();
		tested.setId(0);
	}
	@Test
	public void testSetId_isValid() {
		setAddress();
		tested.setId(2);
	}
	
	
	@Test
	public void testGetLine1() {
		setAddress();
		assertEquals("Route des landes", tested.getLine1());
	}
	@Test 
	public void testSetLine1(){
		setAddress();
		tested.setLine1("Route des landes");
	}
	@Test (expected=NullPointerException.class)
	public void testSetLine1_Null(){
		setAddress();
		tested.setLine1(null);
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetLine1_Empty(){
		setAddress();
		tested.setLine1("");
	}
	
	
	@Test
	public void testGetLine2() {
		this.setAddress();
		assertEquals("", this.tested.getLine2());
	}
	@Test
	public void testSetLine2() {
		this.setAddress();
		tested.setLine2(null);
	}

	
	@Test
	public void testGetCpCity() {
		setAddress();
		assertEquals(this.mockCpCity, this.tested.getCpCity());
	}
	@Test(expected=NullPointerException.class)
	public void testSetCpCity_null(){
		setAddress();
		tested.setCpCity(null);
	}
	@Test
	public void testSetCpCity(){
		setAddress();
		tested.setCpCity(this.mockCpCity);
	}
	
	
	@Test 
	public void testEquals_isValid(){
		setAddress();
		Address tested_Address = new Address("Route des landes", null, this.mockCpCity);
		tested_Address.equals(tested);
	}
	@Test 
	public void testEquals_isInvalid(){
		setAddress();
		Address tested_Address = new Address("Route landes", null, this.mockCpCity);
		tested_Address.equals(tested);
	}
}
