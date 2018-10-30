package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Person;

public class TestPerson {

	//Creation of an inner class "ConcretePerson" to test the abstract class "person"
	public class ConcretePerson extends Person{
		public ConcretePerson(String string, String string2, String string3, String string4) {
			super(string, string2, string3, string4);
		}
	}
		private ConcretePerson tested;
		
		@Before
		public void initPerson(){
			this.tested= new ConcretePerson("Gerard", "Majax", "0645537031", "g.majax@gmail.com");
			tested.setId(1);
		}
		
		
		@Test(expected=IllegalArgumentException.class)
		public void testPerson_Name_isEmpty() {
			new ConcretePerson("", "Majax", "0645537031", "g.majax@gmail.com");
		}
		@Test(expected=NullPointerException.class)
		public void testPerson_Name_isNull() {
			new ConcretePerson(null, "Majax", "0645537031", "g.majax@gmail.com");
		}
		@Test(expected=IllegalArgumentException.class)
		public void testPerson_FirstName_isEmpty() {
			new ConcretePerson("Gerard", "", "0645537031", "g.majax@gmail.com");
		}
		@Test(expected=NullPointerException.class)
		public void testPerson_FirstName_isNull() {
			new ConcretePerson("Gerard", null, "0645537031", "g.majax@gmail.com");
		}
		@Test(expected=IllegalArgumentException.class)
		public void testPerson_Phone_HasInvalidLength() {
			new ConcretePerson("Gerard", "Majax", "0644565537031", "g.majax@gmail.com");
		}
		@Test(expected=NullPointerException.class)
		public void testPerson_Phone_isNull() {
			new ConcretePerson("Gerard", "Majax", null, "g.majax@gmail.com");
		}
		@Test(expected=IllegalArgumentException.class)
		public void testPerson_Email_isInvalid() {
			new ConcretePerson("Gerard", "Majax", "0645537031", "g.majax@gmailcom");
		}
		@Test(expected=NullPointerException.class)
		public void testPerson_Email_isNull() {
			new ConcretePerson("Gerard", "Majax", "0645537031", null);
		}
		
		
		@Test
		public void testGetId() {
			assertEquals(1, tested.getId());
		}
		@Test
		public void testSetId() {
			tested.setId(1);
		}
		@Test(expected=IllegalArgumentException.class)
		public void testSetId_isInvalid() {
			tested.setId(-1);
		}
	
		
		@Test
		public void testGetName() {
			assertEquals("Gerard", tested.getName());
		}
		@Test(expected=NullPointerException.class)
		public void testSetName_Null(){
			tested.setName(null);
		}
		@Test(expected=IllegalArgumentException.class)
		public void testSetName_Empty(){
			tested.setName("");
		}
		@Test
		public void testSetName_Valid(){
			this.tested.setName("b4");
		}
	
	
		@Test
		public void testGetFirstName() {
			assertEquals("Majax", tested.getFirstName());
		}
		@Test(expected=NullPointerException.class)
		public void testSetFirstName_Null(){
			tested.setFirstName(null);
		}
		@Test(expected=IllegalArgumentException.class)
		public void testSetFirstName_Empty(){
			tested.setFirstName("");
		}
		@Test
		public void testSetFirstName_Valid(){
			this.tested.setFirstName("5476dfg");
		}
		
		
		@Test
		public void testGetPhoneNumber() {
			assertEquals("0645537031", this.tested.getPhoneNumber());
		}
		@Test(expected=IllegalArgumentException.class)
		public void testSetPhoneNumber_Invalid(){
			this.tested.setPhoneNumber("012");
		}
		@Test
		public void testSetPhoneNumber_Valid(){
			this.tested.setPhoneNumber("12345678910");
		}
	
		
		@Test
		public void testGetMail() {
			assertEquals("g.majax@gmail.com", tested.getMail());
		}
		@Test(expected=IllegalArgumentException.class)
		public void testSetMail_Invalid(){
			this.tested.setMail("54768");
		}
		@Test
		public void testSetMail_Valid(){
			this.tested.setMail("oihyf@hjgfs.frfr");
		}
		
		
		@Test
		public void testEquals_Valid(){
			ConcretePerson tested2=new ConcretePerson("Gerard", "Majax", "0645537031", "g.majax@gmail.com");
			assertTrue(tested.equals(tested2));
		}
		@Test
		public void testEquals_Invalid(){
			ConcretePerson tested2=new ConcretePerson("Gérard", "Majax", "0645537031", "g.majax@gmail.com");
			assertFalse(tested.equals(tested2));
		}
		
		
	

}
