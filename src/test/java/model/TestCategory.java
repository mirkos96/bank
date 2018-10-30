package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Category;

public class TestCategory {
	
	private Category tested;
	
	@Before
	public void initTested(){
		this.tested = new Category("blblblb", null);
		this.tested.setId(1);
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testCategory_Wording_isInvalid() {
		new Category("", null);
	}
	@Test(expected=NullPointerException.class)
	public void testCategory_Wording_isNull() {
		new Category(null, null);
	}
	@Test 
	public void testCategory() {
		new Category("blblblb", null);
	}
	
	
	@Test
	public void testGetId() {
		assertEquals(1, tested.getId().intValue());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetId_isInvalid() {
		tested.setId(-1);	
	}
	@Test
	public void testSetId() {
		tested.setId(1);	
	}
	
	
	@Test
	public void testGetWording() {
		assertEquals("blblblb", tested.getWording());
	}
	@Test(expected=NullPointerException.class)
	public void testSetWording_Null(){
		tested.setWording(null);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetWording_Empty(){
		tested.setWording("");
	}
	@Test
	public void testSetWording_Valid(){
		tested.setWording("blabla");
	}
	

	@Test
	public void testGetCategory() {
		assertEquals(null, tested.getCategory());
	}
	@Test 
	public void testSetCategory_Valid(){
		Category tested2=new Category();
		tested2.setCategory(tested);
	}
	
	
	@Test
	public void testEquals_True(){
		Category tested2 = new Category("blblblb", null);
		assertTrue(tested2.equals(tested));
	}
	@Test
	public void testEquals_False(){
		Category tested2 = new Category("blbb", null);
		assertFalse(tested2.equals(tested));
	}
	

}
