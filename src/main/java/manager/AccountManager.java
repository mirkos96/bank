package manager;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Account;
import model.ItemAlreadyExistingException;
import model.ItemDoesNotExistException;

@Stateless
public class AccountManager {

	@PersistenceContext(unitName="MyBankPersistence")
	private EntityManager em;
	
	public void save(Account o) throws ItemAlreadyExistingException {
		try {
			if(this.findByAccountNumber(o.getAccountNumber()) != null){
				throw new ItemAlreadyExistingException();
			}
		} catch (ItemDoesNotExistException e) {
		
		}
		em.persist(o);
	}

	/**
	 * Sauvegarde en base un changement sur un compte
	 */
	public void save(Account o, int id) {
		//Récupère le compte à modifier
		Account currentAccount = (Account)(findById(id));
		//Copy tout les éléments sauf l'id du nouveau compte vers l'ancien
		currentAccount = copy((Account)o);
		em.persist(currentAccount);
	}

	
	public Account findById(int id) {
		return em.find(Account.class, id);
	}

	public Account findByAccountNumber(String number) throws ItemDoesNotExistException{
		Query q =  em.createQuery("SELECT a from Account a where a.account_number = :number", Account.class);
		q.setParameter("number",number);
		try{
			return (Account) q.getSingleResult();
		}
		catch(Exception e){
			throw new ItemDoesNotExistException();
		}
	}
	
	private Account copy(Account a){
		return new Account(a.getAccountNumber(), a.getCreationDate(), a.getFirstTotal(), a.getOverdraft(),
					a.getInterestRate(), a.getAgency(), a.getCountryCode(), a.getAccountType(), a.getAlertThresh());
	}

	public List<Account> findAll() {
		return em.createQuery("SELECT a from Account a", Account.class).getResultList();
	}
	
	/**
	 *
	 * @param a nouveau compte
	 * @param id du compte à moidifier
	 */
	public void edit(Account a, int id){
		Account account = em.find(Account.class, id);
		
		account.setAccountNumber(a.getAccountNumber());
		account.setAccountType(a.getAccountType());
		account.setAgency(a.getAgency());
		account.setAlertThresh(a.getAlertThresh());
		account.setCountryCode(a.getCountryCode());
		account.setCreationDate(a.getCreationDate());
		account.setFirstTotal(a.getFirstTotal());
		account.setInterestRate(a.getInterestRate());
		account.setOverdraft(a.getOverdraft());
		account.setTransactions(a.getTransactions());
		
		em.persist(account);
		
	}

	public EntityManager getEntityManager() {
		// TODO Auto-generated method stub
		return this.em;
	}
	

}
