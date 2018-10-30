package model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import tools.Tools;
import model.Category;
import model.PeriodUnit;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;

public class TestPeriodicTransaction {
	
	private TransactionType type_tested = mock(TransactionType.class);
	private TargetTransaction target_tested = mock(TargetTransaction.class);
	private Category category_tested = mock(Category.class);
	private PeriodUnit period_tested = mock(PeriodUnit.class);
	private PeriodicTransaction tested;
	
	
	@Before
	public void initTested(){
		tested= new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
		tested.setId(1);
	}
	
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_WordingIsNull() {
		new PeriodicTransaction(
				null,
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
	}
	@Test (expected  = IllegalArgumentException.class)
	public void testPeriodicTransaction_WordingIsEmpty() {
		new PeriodicTransaction(
				"",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
	}
	
	
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_ValueIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				0.0,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
	}
	
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_DateOperationIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				null, 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
	}
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_DayNumberIsZero() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				0,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
		);
	}
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_PeriodUnitIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				null
		);
	}
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_TransactionTypeIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				null,
				target_tested,
				category_tested,
				period_tested
		);
	}
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_TargetTransactionIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				null,
				category_tested,
				period_tested
		);
	}
	@Test (expected  = NullPointerException.class)
	public void testPeriodicTransaction_CategoryIsNull() {
		new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				null,
				period_tested
		);
	}

	
	@Test
	public void testGetId() {
		assertEquals(1,tested.getId().intValue());
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetId_Invalid() {
		tested.setId(-2);
	}
	@Test
	public void testSetId_Valid() {
		tested.setId(3);
	}

	
	@Test
	public void testGetWording() {
		assertEquals("babebiboo",tested.getWording());
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
		tested.setWording("boo");
	}
	

	@Test
	public void testGetTransactionValue() {
		assertEquals(12.7,tested.getTransactionValue(),0);
	}
	@Test(expected=NullPointerException.class)
	public void testSetTransactionValue_Null(){
		tested.setTransactionValue(0.0);
	}
	

	@Test
	public void testGetDateOperation() {
		assertEquals(Tools.pastDate(),tested.getDateOperation()); 
	}
	@Test(expected=NullPointerException.class)
	public void testSetDateOperation_Null(){
		tested.setDateOperation(null);
	}
	@Test
	public void testSetDateOperation_Valid(){
		tested.setDateOperation(Tools.today());
	}
	
	
	@Test
	public void testGetEndDateTransaction(){
		assertEquals(Tools.futureDate(),tested.getEndDateTransaction());
	}
	@Test
	public void testSetEndDateTransaction_Valid(){
		tested.setEndDateTransaction(null);
	}
	

	@Test
	public void testGetDayNumber() {
		assertEquals(4,tested.getDayNumber());
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetDayNumber_Invalid(){
		tested.setDayNumber(0);
	}
	@Test
	public void testSetDayNumber_Valid(){
		tested.setDayNumber(250);
	}

	
	@Test
	public void testGetDescription() {
		assertEquals("foo",tested.getDescription());
	}
	@Test
	public void testSetDescription_Valid(){
		tested.setDescription("346dghdf");
	}


	@Test
	public void testGetTransactionType() {
		assertEquals(type_tested,tested.getTransactionType());
	}
	@Test(expected=NullPointerException.class)
	public void testSetTransactionType_Null(){
		tested.setTransactionType(null);
	}
	@Test
	public void testSetTransactionType_Valid(){
		tested.setTransactionType(type_tested);
	}
	
	
	@Test
	public void testGetTargetTransaction() {
		assertEquals(target_tested,tested.getTargetTransaction());
	}
	@Test(expected=NullPointerException.class)
	public void testSetTargetTransaction_Null(){
		tested.setTargetTransaction(null);
	}
	@Test
	public void testSetTargetTransaction_Valid(){
		tested.setTargetTransaction(target_tested);
	}
	

	@Test
	public void testGetCategory() {
		assertEquals(category_tested,tested.getCategory());
	}
	@Test(expected=NullPointerException.class)
	public void testSetCategory_Null(){
		tested.setCategory(null);
	}
	@Test
	public void testSetCategory_Valid(){
		tested.setCategory(category_tested);
	}


	@Test
	public void testGetPeriodUnit() {
		assertEquals(period_tested,tested.getPeriodUnit());
	}
	@Test(expected=IllegalAccessError.class)
	public void testSetPeriodUnit_Null(){
		tested.setPeriodUnit(null);
	}
	@Test
	public void setPeriodUnit_Valid(){
		tested.setPeriodUnit(period_tested);
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetPeriodParam_DayNumberInvalid(){
		tested.setPeriodParam(period_tested,0);
	}
	@Test(expected=IllegalArgumentException.class)
	public void testSetPeriodParam_PeriodUnitNull(){
		tested.setPeriodParam(null,3);
	}
	
	
	@Test
	public void testEquals_Valid(){
		PeriodicTransaction tested2=new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"foo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
				);
		assertTrue(tested.equals(tested2));
	}
	
	
	@Test
	public void testEquals_Invalid(){
		PeriodicTransaction tested2=new PeriodicTransaction(
				"babebiboo",
				12.7,
				Tools.pastDate(), 
				Tools.futureDate(),
				4,
				"fo", 
				type_tested,
				target_tested,
				category_tested,
				period_tested
				);
		assertFalse(tested.equals(tested2));
	}
	
}
