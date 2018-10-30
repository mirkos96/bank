package model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="periodictransaction")
@NamedQuery(name="PeriodicTransaction.findAll", query="SELECT t FROM PeriodicTransaction t")
public class PeriodicTransaction{

	/* VARIABLES */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="wording")
	private String wording;
	@Column(name="transaction_value")
	private Double transaction_value;
	@Column(name="date_operation")
	@Temporal(TemporalType.DATE)
	private Date date_operation;
	@Column(name="end_date_transaction")
	@Temporal(TemporalType.DATE)
	private Date end_date_transaction;
	@Column(name="day_number")
	private int day_number;
	@Column(name="description")
	private String description;
	@ManyToOne
	@JoinColumn(name="id_transactiontype")
	private TransactionType transactionType;
	@ManyToOne
	@JoinColumn(name="id_targettransaction")
	private TargetTransaction targetTransaction;
	@ManyToOne
	@JoinColumn(name="id_category")
	private Category category;
	@ManyToOne
	@JoinColumn(name="id_periodunit")
	private PeriodUnit periodUnit;
	//Utile uniquement pour faire le lien avec la bdd
	
	@ManyToOne
	@JoinColumn(name="id_account")
	private Account account;

	public PeriodicTransaction(){
	}
	
	/**
	 * 
	 * @param wording : libel�
	 * @param transaction_value : valeur
	 * @param date_operation : date active
	 * @param end_date_transaction : date de fin de cycle (optionnelle)
	 * @param day_number : jour actif du cycle
	 * @param description : optionnelle
	 * @param transactionType : type de transaction (virement, ch�que...)
	 * @param targetTransaction : compte cibl�
	 * @param category	: 	
	 * @param periodUnit : type de cycle (mensuel,hebdo...)
	 */
	public PeriodicTransaction(String wording, Double transaction_value, Date date_operation, Date end_date_transaction,
							  int day_number, String description, TransactionType transactionType, TargetTransaction targetTransaction, Category category,
							  PeriodUnit periodUnit){
		if(wording.isEmpty()){
			throw new IllegalArgumentException("Le libel� ne peut �tre vide");
		}
		if(transaction_value == null){
			throw new NullPointerException("La valeur de la ligne ne peut �tre vide ou null");
		}
		if(date_operation == null){
			throw new NullPointerException("La date de d�but ne peut �tre null");
		}
		if(transactionType == null){
			throw new NullPointerException("le type de transaction ne peut �tre null");
		}
		if(category == null){
			throw new NullPointerException("le type de transaction ne peut �tre null");
		}
		if((periodUnit == null)){
			if(day_number > 0)
			throw new NullPointerException("Une period ou un jour d'�ch�ance ne peuvent �tre d�clar� l'un sans l'autre");
		}
		else if(day_number <= 0){
			if(periodUnit!= null){
				throw new NullPointerException("Une period ou un jour d'�ch�ance ne peuvent �tre d�clar� l'un sans l'autre");
			}
		}
		if(targetTransaction == null){
			throw new NullPointerException("la cible ne peut �tre null");
		}
		if(description == null){
			this.description = "";
		}
		this.wording = wording;
		this.transaction_value = transaction_value;
		this.date_operation = date_operation;
		this.end_date_transaction = end_date_transaction;
		this.day_number = day_number;
		this.description = description;
		this.transactionType = transactionType;
		this.targetTransaction = targetTransaction;
		this.category = category;
		this.periodUnit = periodUnit;
	}
	
	/* GETTERS & SETTERS */
	
	
	public Integer getId(){
		return this.id;
	}
	public void setId(Integer val){
		if(val <= 0){
			throw new IllegalArgumentException();
		}
		this.id = val;
	}
	
	public String getWording(){
		return this.wording;
	}
	public void setWording(String wording){
		this.wording = wording;
	}
	
	public Double getTransactionValue(){
		
		return this.transaction_value;
	}
	public void setTransactionValue(Double val){
		if(val == null){
			throw new NullPointerException("La valeur de la ligne ne peut �tre vide ou null");
		}
		this.transaction_value = val;
	}
	
	public Date getDateOperation(){
		return this.date_operation;
	}
	public void setDateOperation(Date date){
		if(date == null){
			throw new NullPointerException("La date ne peut �tre null");
		}
		this.date_operation = date;
	}
	
	public Date getEndDateTransaction(){
		return this.end_date_transaction;
	}
	public void setEndDateTransaction(Date date){
		this.end_date_transaction = date;
	}
	
	public int getDayNumber(){
		return this.day_number;
	}
	public void setDayNumber(int day){
		/*if(day <= 0){
			throw new IllegalArgumentException("La cycle ne peut �tre n�gatif");
		}
		else if(this.getPeriodUnit() == null){
			throw new IllegalAccessError("L'unit� n'a pas �t� d�clar�e. Utilisez la m�thod setPeriodParam()");
		}
		if(day < 0){
			throw new IllegalArgumentException("La cycle ne peut �tre n�gatif");
		}
		this.day_number = day;
		*/
	}
	
	public String getDescription(){
		return this.description;
	}
	public void setDescription(String desc){
		this.description = desc;
	}
	
	public TransactionType getTransactionType(){
		return this.transactionType;
	}
	public void setTransactionType(TransactionType tt){
		if(tt == null){
			throw new NullPointerException("Le type ne peut �tre null");
		}
		this.transactionType = tt;
	}
	
	public TargetTransaction getTargetTransaction(){
		return this.targetTransaction;
	}
	public void setTargetTransaction(TargetTransaction target){
		if(target == null){
			throw new NullPointerException("La cible ne peut �tre null");
		}
		this.targetTransaction = target;
	}
	
	public Category getCategory(){
		return this.category;
	}
	public void setCategory(Category cat){
		if(cat == null){
			throw new NullPointerException("La cat�gorie ne peut �tre null");
		}
		this.category = cat;
	}
	public PeriodUnit getPeriodUnit(){
		return this.periodUnit;
	}
	public void setPeriodUnit(PeriodUnit pu){
		/*if(this.day_number == 0){
			throw new IllegalAccessError("Le num�ro de jour cyclique n'a pas �t� d�clar�. Utilisez la m�thod setPeriodParam()");
		}
		this.periodUnit = pu;
		*/
	}
	
	/**
	 * Set les deux param�tres �tant donn�s qu'ils sont li�s
	 * @param pu : type de p�riodicit� (annuelle, mensuelle...)
	 * @param daynumber : Jour actif(3e jour de chaque mois, 6e jour de la semaine...)
	 */
	public void setPeriodParam(PeriodUnit pu, int daynumber){
		if(pu != null && daynumber > 0){
			this.periodUnit = pu;
			this.day_number = daynumber;
		}
		else{
			throw new IllegalArgumentException("Le type de cycle et le jour actif doivent �tre tout les deux renseign�");
		}
	}
	public Account getAccount(){
		return this.account;
	}
	public void setAccount(Account acc){
		this.account = acc;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof PeriodicTransaction){
			PeriodicTransaction tmp = (PeriodicTransaction)obj;
			if(tmp.getDescription() == null){
				tmp.setDescription("");
			}
			
			if(tmp.getEndDateTransaction() == null &&  (tmp.getPeriodUnit() != null &&  tmp.getDayNumber() != 0)){
				if(tmp.getWording().equals(this.getWording()) &&
						   tmp.getDateOperation().equals(this.getDateOperation()) &&
						   tmp.getDayNumber() == this.getDayNumber() &&
						   tmp.getDescription().equals(this.getDescription()) &&
						   tmp.getPeriodUnit().equals(this.getPeriodUnit()) &&
						   tmp.getTargetTransaction().equals(getTargetTransaction()) &&
						   tmp.getTransactionValue() == this.getTransactionValue() &&
						   tmp.getTransactionType().equals(this.getTransactionType()) &&
						   tmp.getCategory().equals(this.getCategory())){
								return true;
				}
				else return false;
			}
			
			else if(tmp.getEndDateTransaction() == null &&  tmp.getPeriodUnit() == null &&  tmp.getDayNumber() == 0){
				if(tmp.getWording().equals(this.getWording()) &&
				   tmp.getDateOperation().equals(this.getDateOperation()) &&
				   tmp.getDescription().equals(this.getDescription()) &&
				   tmp.getTargetTransaction().equals(getTargetTransaction()) &&
			       tmp.getTransactionValue() == this.getTransactionValue() &&
				   tmp.getTransactionType().equals(this.getTransactionType()) &&
				   tmp.getCategory().equals(this.getCategory())){
							return true;
					}
					else return false;
			}
			else{
				if(tmp.getWording().equals(this.getWording()) &&
				   tmp.getDateOperation().equals(this.getDateOperation()) &&
				   tmp.getDayNumber() == this.getDayNumber() &&
				   tmp.getDescription().equals(this.getDescription()) &&
				   tmp.getEndDateTransaction().equals(this.getEndDateTransaction()) &&
				   tmp.getPeriodUnit().equals(this.getPeriodUnit()) &&
				   tmp.getTargetTransaction().equals(getTargetTransaction()) &&
				   tmp.getTransactionValue() == this.getTransactionValue() &&
				   tmp.getTransactionType().equals(this.getTransactionType()) &&
				   tmp.getCategory().equals(this.getCategory())){
						return true;
				}
				else return false;
			}
		}
		else return false;
	}

}