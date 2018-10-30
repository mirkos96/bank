package manager;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.CountryCode;

@Stateless
public class CountryCodeManager {
	@PersistenceContext(unitName="MyBankPersistence")
	private EntityManager em;
	
	public CountryCode findById(int id){
		return em.find(CountryCode.class, id);
	}
	
}
