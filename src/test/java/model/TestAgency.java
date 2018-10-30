package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Test;

import model.Address;
import model.Agency;
import model.Bank;

public class TestAgency {

	private Agency tested;
	private Address mockAddress=mock(Address.class);
	private Bank mockBank=mock(Bank.class);
	
	private void initAgency(){
		tested=new Agency("Beaulieu","12015",mockAddress,mockBank); 
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAgency_Name_isEmpty() {
		new Agency("","12015",mockAddress,mockBank);
	}
	@Test(expected=NullPointerException.class)
	public void testAgency_Name_isNull() {
		new Agency(null,"12015",mockAddress,mockBank);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testAgency_CounterCode_HasInvalidLength() {
		new Agency("Beaulieu","115",mockAddress,mockBank);
	}
	@Test(expected=NullPointerException.class)
	public void testAgency_Address_isNull() {
		new Agency("Beaulieu","12015",null,mockBank);
	}
	@Test(expected=NullPointerException.class)
	public void testAgency_Bank_isNull() {
		new Agency("Beaulieu","12015", mockAddress,null);
	}
	@Test
	public void testAgency() {
		initAgency();
	}
	
	
	@Test
	public void testGetId() {
		initAgency();
		tested.setId(2);
		assertEquals(2, tested.getId().intValue());
	}
	@Test
	public void testSetId() {
		initAgency();
		tested.setId(2);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetId_Invalid() {
		initAgency();
		tested.setId(-2);
	}
	
	
	@Test
	public void testGetAgencyName() {
		initAgency();
		assertEquals("Beaulieu", tested.getAgencyName());
	}
	@Test(expected=NullPointerException.class)
	public void testSetAgencyName_Null(){
		initAgency();
		tested.setAgencyName(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetAgencyName_Empty(){
		initAgency();
		tested.setAgencyName("");
	}
	@Test
	public void testSetAgencyName(){
		initAgency();
		tested.setAgencyName("Bali");
	}
	

	
	@Test
	public void testGetCounterCode() {
		initAgency();
		assertEquals("12015", tested.getCounterCode());
	}
	@Test(expected=NullPointerException.class)
	public void testSetCounterCode_Null(){
		initAgency();
		tested.setCounterCode(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetCounterCode_InvalidLength(){
		initAgency();
		tested.setCounterCode("123");
	}
	@Test
	public void testSetCounterCode(){
		initAgency();
		tested.setCounterCode("12345");
	}
	

	@Test
	public void testGetAddress() {
		initAgency();
		assertEquals(mockAddress,tested.getAddress());
	}
	@Test(expected=NullPointerException.class)
	public void testSetAddress_Null(){
		initAgency();
		tested.setAddress(null);
	}
	@Test
	public void testSetAddress(){
		initAgency();
		tested.setAddress(mockAddress);
	}
	
	
	@Test
	public void testGetBank() {
		initAgency();
		assertEquals(mockBank, tested.getBank());
	}
	@Test(expected=NullPointerException.class)
	public void testSetBank_Null(){
		initAgency();
		tested.setBank(null);
	}
	@Test
	public void testSetBank(){
		initAgency();
		tested.setBank(mockBank);
	}
	
	
	@Test
	public void equals_Valid(){
		initAgency();
		Agency tested2=new Agency("Beaulieu","12015",mockAddress,mockBank);
		assertTrue(tested2.equals(tested));
	}
	@Test
	public void equals_Invalid(){
		initAgency();
		Agency tested2=new Agency("Beaeu","12015",mockAddress,mockBank);
		assertFalse(tested2.equals(tested));
	}
}
