package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="targettransaction")
@NamedQueries({
	@NamedQuery(name="TargetTransaction.findAll", query="SELECT a FROM TargetTransaction a"),
	@NamedQuery(name="TargetTransaction.findAllName", query="SELECT a.targetName FROM TargetTransaction a")
})
public class TargetTransaction {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="target_name")
	private String target_name;
	
	@Column(name="IBAN")
	private String IBAN;
	

	public TargetTransaction(){
	}
	public TargetTransaction(String target_name,String IBAN) {
		
		if (target_name.isEmpty()){
			throw new IllegalArgumentException("wording cannot be empty");
		}
		if (IBAN.length() != 27){
			throw new IllegalArgumentException("IBAN is composed of 27 car in France");
		}
		if (!IBAN.toUpperCase().startsWith("FR")){
			throw new IllegalArgumentException("IBAN begins with 'FR' in France");
		}
		
		this.target_name=target_name;
		this.IBAN=IBAN;
	}
	
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer val){
		if(val <= 0){
			throw new IllegalArgumentException();
		}
		this.id = val;
	}
	
	public String getTargetName(){
		return this.target_name;
	}
	public void setTargetName(String name){
		if (name.isEmpty()){
			throw new IllegalArgumentException("wording cannot be empty");
		}
		this.target_name = name;
	}
	
	public String getIBAN(){
		return this.IBAN;
	}
	public void setIBAN(String iban){
		if (iban.length() != 27){
			throw new IllegalArgumentException("IBAN is composed of 27 car in France");
		}
		if (!iban.toUpperCase().startsWith("FR")){
			throw new IllegalArgumentException("IBAN begins with 'FR' in France");
		}
		this.IBAN = iban;
	}
	@Override
	/**
	 * Return true si tout les champs sont �gaux (mais pas les addresses m�moires)
	 */
	public boolean equals(Object obj){
		if(obj instanceof TargetTransaction){
			TargetTransaction tmp = (TargetTransaction)obj;
			
			if(tmp.getTargetName().equals(this.getTargetName()) && 
			   tmp.getIBAN().equals(this.getIBAN())){
				return true;
			}
			else return false;
		}
		else return false;
	}
	
	@Override
	/**
	 * Return le nom de la cible
	 */
	public String toString(){
		return this.target_name;
	}
}
