package manager;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.ejb.EJB;

import org.junit.Before;
import org.junit.Test;

import model.Address;
import model.Agency;
import model.Bank;
import model.CpCity;
import model.ItemAlreadyExistingException;
@ManagedBean
public class AgencyManagerTest extends EjbContainerTest{

	@EJB
	private AgencyManager agencyManager;
	
	private Agency agency;
	private Address addressTest;
	private Bank bankTest;
	
	@Before
	public void init() throws Exception{
		tx.begin();
		CpCity cpCity = new CpCity("40120", "Roquefort");
		em.persist(cpCity);
		Address address = new Address("line 1", "line 2", cpCity);
		em.persist(address);
		Bank bank = new Bank("bank", "bank_code");
		em.persist(bank);
		tx.commit();
		
		addressTest = em.find(Address.class, address.getId());
		bankTest = em.find(Bank.class, bank.getId());
		agency = new Agency("agency", "12345", addressTest, bankTest);
	}
	
	@Test
	public void persistAgency() throws Exception{
		agencyManager.save(agency);
		Agency expectedAgency = agencyManager.findById(agency.getId());
		assertThat(expectedAgency.getAgencyName(), is("agency"));
		assertThat(expectedAgency.getCounterCode(), is("12345"));
		assertThat(expectedAgency.getAddress().getId(), is(addressTest.getId()));
		assertThat(expectedAgency.getBank().getId(), is(bankTest.getId()));
		
	}
	
	@Test
	public void retrieveById() throws ItemAlreadyExistingException{
		agencyManager.save(agency);
		Agency retrievedAgency = agencyManager.findById(agency.getId());
		assertThat(retrievedAgency.getId(), is(agency.getId()));
	}
	
	@Test(expected=ItemAlreadyExistingException.class)
	public void agencyAlreadyExist() throws ItemAlreadyExistingException{
		agencyManager.save(agency);
		agencyManager.save(agency);
	}
	
	@Test
	public void retrieveAllAgencies() throws ItemAlreadyExistingException{
		Agency agency = new Agency("agency", "12345", addressTest, bankTest);
		Agency agency2 = new Agency("agency2", "12345", addressTest, bankTest);
		Agency agency3 = new Agency("agency3", "12345", addressTest, bankTest);
		agencyManager.save(agency);
		agencyManager.save(agency2);
		agencyManager.save(agency3);
		
		List<Agency> agencies = agencyManager.findAll();
		List<String> agenciesName = new ArrayList<String>();
		List<String> expectedAgenciesName = new ArrayList<String>();
		expectedAgenciesName.add(agency.getAgencyName());
		expectedAgenciesName.add(agency2.getAgencyName());
		expectedAgenciesName.add(agency3.getAgencyName());
		
		for(Agency a : agencies){
			agenciesName.add(a.getAgencyName());
		}
		
		assertThat(expectedAgenciesName, is(agenciesName));
	}
	
}
