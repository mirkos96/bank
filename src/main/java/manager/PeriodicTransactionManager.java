package manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import model.Account;
import model.BadArgumentException;
import model.Category;
import model.ItemDoesNotExistException;
import model.PeriodUnit;
import model.PeriodicTransaction;
import model.TargetTransaction;
import model.TransactionType;


@Stateless
public class PeriodicTransactionManager {

	
	@PersistenceContext(unitName="MyBankPersistence")
	private EntityManager em;
	
	public PeriodicTransaction findById(int id) throws ItemDoesNotExistException {
		if(em.find(PeriodicTransaction.class, id) != null){
			return em.find(PeriodicTransaction.class, id);
		}
		else throw new ItemDoesNotExistException();
	}

	
	public List<PeriodicTransaction> findAll() {
		Query q = em.createQuery("SELECT p FROM PeriodicTransaction p");
		return q.getResultList();
	}
	
	public void DeleteTransaction(int id) {
		PeriodicTransaction periodictransaction = em.find(PeriodicTransaction.class, id);
		em.remove(periodictransaction);
	}
	
	public List<PeriodicTransaction> findAllByAccountId(int id){
		Query q = em.createQuery("SELECT p FROM PeriodicTransaction p WHERE p.account = :account");
		q.setParameter("account", em.find(Account.class,id)); //rechercher l'account par son id
		return q.getResultList();
	}
	
	public List<Category> findAllCategories() {
		return em.createNamedQuery("Category.findAll", Category.class).getResultList();
	}

	public List<TransactionType> findAllTypes() {
		return em.createNamedQuery("TransactionType.findAll", TransactionType.class).getResultList();
	}

	public List<TargetTransaction> findAllTargets() {
		return em.createNamedQuery("TargetTransaction.findAll", TargetTransaction.class).getResultList();
	}

	public void saveTransaction(PeriodicTransaction saved){
		em.persist(saved);
	}
	
	public void editTransaction(String wording, Double transaction_value, Date date_operation, Date end_date_transaction,
			  int day_number, String description, TransactionType transactionType, TargetTransaction targetTransaction, Category category,
			  PeriodUnit periodUnit, int idTransaction){
		PeriodicTransaction edited = em.find(PeriodicTransaction.class, idTransaction);
		edited.setWording(wording);
		edited.setTransactionValue(transaction_value);
		edited.setDateOperation(date_operation);
		edited.setEndDateTransaction(end_date_transaction);
		edited.setDayNumber(day_number);
		edited.setDescription(description);
		edited.setTransactionType(transactionType);
		edited.setTargetTransaction(targetTransaction);
		edited.setCategory(category);
		//inutile : em.persist(edited);
	}
	
	public Category findCatById(int id){
		return em.find(Category.class, id);
	}
	public TargetTransaction findTarById(int id){
		return em.find(TargetTransaction.class, id);
	}
	public TransactionType findTypById(int id){
		return em.find(TransactionType.class, id);
	}
	
	public Double getAmount(String rdsign, String value) throws BadArgumentException {
		if (value.isEmpty()){
			throw new BadArgumentException ("Le montant de transaction doit être un nombre positif");
		}
		else if (rdsign.equals("moins")){
			return -Math.abs(Double.valueOf(value)); 		
		}
		else return Math.abs(Double.valueOf(value));
	}
	
	public String getSign(double amount){
		if (amount<0){
			return "moins";
		}
		return "plus";
	}
	
	public List<TransactionType> rdClickRestrainTransactionTypes(String rdsign){
		if (rdsign.equals("moins")){
			return findAllTypes();
			}
		else {
			List<TransactionType> rdplus=new ArrayList<TransactionType>();
			rdplus.add(findTypById(2));
			rdplus.add(findTypById(4));
			return rdplus;
		}
	}
	

	
}
