package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Account;
import model.ItemDoesNotExistException;
import model.AccountType;

@Stateless
public class AccountTypeManager {
	@PersistenceContext(unitName="MyBankPersistence")
	protected EntityManager em;
	
	public void save(AccountType a){
		em.persist(a);
	}
	
	public List<AccountType> findAll(){
		List<AccountType> items = em.createQuery("SELECT a from AccountType a", AccountType.class).getResultList();
		return items;
	}
	
	public AccountType findById(int id){
		return em.find(AccountType.class, id);
	}
	public AccountType findByName(String name) throws ItemDoesNotExistException{
		Query q =  em.createQuery("SELECT a from AccountType a where a.accountType = :type", AccountType.class);
		q.setParameter("type",name);
		try{
			return (AccountType) q.getSingleResult();
		}
		catch(Exception e){
			throw new ItemDoesNotExistException();
		}
	}
}
