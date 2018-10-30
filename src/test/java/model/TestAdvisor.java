package model;

import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.Date;

import org.junit.Test;
import tools.Tools;
import model.Advisor;
import model.Agency;

public class TestAdvisor {

	private Agency mockAgency=mock(Agency.class);
	private Advisor tested;
	
	private void initTested(Date d, Agency a){
		this.tested=new Advisor ("toto","tutu","0345567891","toto.tutu@titi.tata",d,a);
	}
	private void setTested (){
		initTested(Tools.pastDate(),mockAgency);		
	}
	
	
	@Test (expected = NullPointerException.class )
	public void testAdvisor_DateAssignmentIsNull() {
		initTested (null, mockAgency);
	}
	@Test (expected = IllegalArgumentException.class )
	public void testAdvisor_DateAssignmentInTheFuture() {
		initTested (Tools.futureDate(), mockAgency);
	}
	@Test (expected = NullPointerException.class )
	public void testAdvisor_AgencyIsNull() {
		initTested (Tools.pastDate(), null);
	}
	
	
	@Test
	public void testGetDateAssignment() {
		setTested();
		assertEquals(Tools.pastDate(),this.tested.getDateAssignment());	
	}
	@Test (expected=NullPointerException.class)
	public void testSetDateAssignment_Null(){
		setTested();
		this.tested.setDateAssignment(null);
	}
	@Test 
	public void testSetDateAssignment(){
		setTested();
		this.tested.setDateAssignment(Tools.pastDate());
	}
	@Test (expected=IllegalArgumentException.class)
	public void testSetDateAssignment_Future(){
		setTested();
		this.tested.setDateAssignment(Tools.futureDate());
	}
	
	
	@Test
	public void testGetAgency() {
		setTested();
		assertEquals(this.mockAgency,tested.getAgency());
	}
	@Test (expected=NullPointerException.class)
	public void testSetAgency_Null(){
		setTested();
		this.tested.setAgency(null);
	}
	@Test
	public void testSetAgency(){
		setTested();
		this.tested.setAgency(mockAgency);
	}
	
	
	@Test
	public void equals_Valid(){
		setTested();
		Advisor tested2=new Advisor ("toto","tutu","0345567891","toto.tutu@titi.tata",Tools.pastDate(),mockAgency);
		assertTrue(tested2.equals(tested));
	}
	@Test
	public void equals_Invalid(){
		setTested();
		Advisor tested2=new Advisor ("toto","tut","0345567891","toto.tutu@titi.tata",Tools.pastDate(),mockAgency);
		assertFalse(tested2.equals(tested));
	}
}
