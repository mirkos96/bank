package model;

import static org.junit.Assert.*;

import org.junit.Test;

import model.TransactionType;

public class TestTransactionType {

	@Test(expected = IllegalArgumentException.class)
	public void testTransactionType_IsEmpty() {
		new TransactionType("");
	}
	
	@Test(expected = NullPointerException.class)
	public void testTransactionType_IsNull() {
		new TransactionType(null);
	}
	
	@Test
	public void testGetId() {
		this.tested.setId(1);
		assertEquals(1,this.tested.getId().intValue());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetId() {
		this.tested.setId(-3);
	}

	@Test
	public void testGetWording() {
		assertEquals("foo",this.tested.getWording());
	}
	
	@Test
	public void testEquals_IsValid() {
		TransactionType tested2 = new TransactionType("foo");
		assertTrue(tested.equals(tested2));
	}
	@Test
	public void testEquals_IsInvalid() {
		TransactionType tested2 = new TransactionType("fool");
		assertFalse(tested.equals(tested2));
	}
	
	private TransactionType tested = new TransactionType("foo");;

}
