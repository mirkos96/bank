package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.CpCity;

public class TestCpCity {

	CpCity cpcity;
	
	@Before
	public void setUp(){
		this.cpcity = new CpCity("64000", "Pau");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testCpCity_PostalCodeHasInvalidLength() {
		new CpCity("6400", "Pau");
	}
	@Test(expected=IllegalArgumentException.class)
	public void testCpCity_CityHasInvalidLength() {
		new CpCity("64000", "");
	}
	@Test(expected=NullPointerException.class)
	public void testCpCity_PostalCodeisNull() {
		new CpCity(null, "Pau");
	}
	@Test(expected=NullPointerException.class)
	public void testCpCity_CityisNull() {
		new CpCity("64000", null);
	}
	
	
	@Test
	public void testGetId() {
		this.cpcity.setId(1);
		assertEquals(1, this.cpcity.getId().intValue());
	}
	@Test
	public void testSetId_Valid() {
		this.cpcity.setId(1);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetId_isInvalid() {
		this.cpcity.setId(-1);
	}
	

	@Test
	public void testGetPostalCode() {
		assertEquals("64000", this.cpcity.getPostalCode());
	}
	@Test(expected=NullPointerException.class)
	public void testSetPostalCode_Null(){
		this.cpcity.setPostalCode(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetPostalCode_Invalid(){
		this.cpcity.setPostalCode("jug");
	}

	
	@Test
	public void testGetCity() {
		assertEquals("Pau", this.cpcity.getCity());
	}
	@Test
	public void testSetCity_Valid(){
		this.cpcity.setCity("Saint-Médard");
	}
	@Test(expected=NullPointerException.class)
	public void testSetCity_Null(){
		this.cpcity.setCity(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetCity_Empty(){
		this.cpcity.setCity("");
	}
	
	
	@Test 
	public void testEquals_isValid(){
		CpCity tested_cpcity =  new CpCity("64000", "Pau");
		assertTrue(tested_cpcity.equals(this.cpcity));
	}
	@Test 
	public void testEquals_isInvalid(){
		CpCity tested_cpcity =  new CpCity("65000", "Pau");
		assertFalse(tested_cpcity.equals(this.cpcity));
	}
}
